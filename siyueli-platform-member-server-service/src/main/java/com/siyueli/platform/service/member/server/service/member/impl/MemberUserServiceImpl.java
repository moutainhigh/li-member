package com.siyueli.platform.service.member.server.service.member.impl;

import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.grade.MemberGrade;
import com.siyueli.platform.member.pojo.member.MemberAccountHistory;
import com.siyueli.platform.member.pojo.member.MemberUser;
import com.siyueli.platform.member.pojo.member.MemberUserCompose;
import com.siyueli.platform.member.request.member.backendUserLogin.BackendUserLoginRequest;
import com.siyueli.platform.member.request.member.searchAllUser.SearchAllUserRequest;
import com.siyueli.platform.member.request.member.updateAmount.UpdateBalanceRequest;
import com.siyueli.platform.member.request.member.updateUser.UpdateMemberUserRequest;
import com.siyueli.platform.member.response.member.getuser.GetUserResponse;
import com.siyueli.platform.member.response.member.searchUser.MemberUserResponse;
import com.siyueli.platform.service.member.server.config.WeixinConfig;
import com.siyueli.platform.member.constants.MemberConstants;
import com.siyueli.platform.service.member.server.mapper.member.MemberUserMapper;
import com.siyueli.platform.service.member.server.service.member.MemberAccountHistoryServiceContract;
import com.siyueli.platform.service.member.server.service.member.MemberGradeServiceContract;
import com.siyueli.platform.service.member.server.service.member.MemberUserServiceContract;
import com.siyueli.platform.service.member.server.util.JsonTokenUtil;
import com.siyueli.platform.service.member.server.util.TokenProccessor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.siyue.platform.base.HttpResult;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.exceptions.exception.RequestException;
import cn.siyue.platform.request.HttpClientUtils;

/**
 * 会员表 服务实现类
 */
@Primary
@Service
public class MemberUserServiceImpl extends ServiceImpl<MemberUserMapper, MemberUser> implements MemberUserServiceContract {

  private JedisClusterServiceImpl jedisClusterService;

  private WeixinConfig weixinConfig;

  private MemberAccountHistoryServiceContract memberAccountHistoryService;

  private MemberGradeServiceContract memberGradeService;

  private JsonTokenUtil jsonTokenUtil;

  private DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Autowired
  public MemberUserServiceImpl(JedisClusterServiceImpl jedisClusterService,
                               WeixinConfig weixinConfig,
                               MemberAccountHistoryServiceContract memberAccountHistoryService,
                               MemberGradeServiceContract memberGradeService,
                               JsonTokenUtil jsonTokenUtil){
    this.jedisClusterService = jedisClusterService;
    this.weixinConfig = weixinConfig;
    this.memberAccountHistoryService = memberAccountHistoryService;
    this.memberGradeService = memberGradeService;
    this.jsonTokenUtil = jsonTokenUtil;
  }

  @Override
  public ResponseData userLogin(String jsCode) {

    try {

      MemberUser memberUser;

      Map weixinMap = new HashMap();
      weixinMap.put("appid", weixinConfig.getAppid());
      weixinMap.put("secret", weixinConfig.getSecret());
      weixinMap.put("js_code", jsCode);
      HttpResult httpResult = HttpClientUtils.doGet(weixinConfig.getUrl(), weixinMap);

      JSONObject json = (JSONObject)(new JSONParser().parse(httpResult.getBody()));
      if(json.containsKey("errcode")){
        return ResponseData.build(
            ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
            ResponseBackCode.ERROR_PARAM_INVALID.getMessage());
      }
      String openid = (String)json.get("openid");

      //从数据库里查找是否是新用户或者是否已注册
      Map loginMap = new HashMap();
      loginMap.put("unnit_id",openid);
      List<MemberUser> memberUserList = selectByMap(loginMap);

      if(memberUserList != null && memberUserList.size() > 0){
        //老用户
        memberUser = memberUserList.get(0);
        String cellPhone = memberUser.getCellphone();
        if(cellPhone == null || "".equals(cellPhone)) {

          //未绑定手机号，提示要绑定手机号
          return ResponseData.build(ResponseBackCode.ERROR_NEW_OBJECT.getValue(), ResponseBackCode.ERROR_NEW_OBJECT.getMessage(), openid);
        }
      }else{
        //新用户，提示前端要注册

        //手动把unnion_id插入到表中
        memberUser = new MemberUser();
        memberUser.setOpenId(openid);
        memberUser.setCode(code());
        insert(memberUser);
        return ResponseData.build(ResponseBackCode.ERROR_NEW_OBJECT.getValue(),ResponseBackCode.ERROR_NEW_OBJECT.getMessage(),openid);
      }

      //生成token
      String token = TokenProccessor.getInstance().makeToken(jsCode);

      //保存之前对密码清空
      jedisClusterService.set(MemberConstants.REDIS_USER_SESSION_KEY_FRONT + ":" + token, memberUser.getId()+"");

      //设置session的过期时间
      jedisClusterService.expire(MemberConstants.REDIS_USER_SESSION_KEY_FRONT + ":" + token, MemberConstants.SSO_SESSION_EXPIRE);

      //返回token
      MemberUserCompose memberUserCompose = new MemberUserCompose();
      MemberGrade memberGrade = memberGradeService.selectById(memberUser.getGradeId());
      memberUserCompose.setMemberGrade(memberGrade);

      memberUser.setPassword(null);
      memberUserCompose.setMemberUser(memberUser);
      memberUserCompose.setToken(token);
      memberUserCompose.setOpenId(openid);
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage(),memberUserCompose);
    }catch (Exception e){
      throw new RequestException(ResponseBackCode.ERROR_FAIL.getValue(), e.getMessage());
    }
  }

  /**
   * 后台登录
   */
  @Override
  public ResponseData backendLogin(BackendUserLoginRequest backendUserLoginRequest) {
    try{


      Map<String,Object> map = new HashMap<>();
      map.put("code",backendUserLoginRequest.getCode());
      List<MemberUser> memberUserList =  selectByMap(map);
      MemberUser storeUser;
      if(memberUserList != null && memberUserList.size() > 0){
        storeUser = memberUserList.get(0);
        //判断密码是否正确
        boolean result = BCrypt.checkpw(backendUserLoginRequest.getPassword(), storeUser.getPassword());
        if(!result){
          return ResponseData.build(
              ResponseBackCode.ERROR_USER_NOT_EXIST.getValue(),
              ResponseBackCode.ERROR_USER_NOT_EXIST.getMessage()
          );
        }
        //生成token
        String token = TokenProccessor.getInstance().makeToken(backendUserLoginRequest.getCode());
        //保存之前对密码清空
        jedisClusterService.set(MemberConstants.REDIS_USER_SESSION_KEY_BACKEND + ":" + token,storeUser.getId()+"");

        //设置session的过期时间
        jedisClusterService.expire(MemberConstants.REDIS_USER_SESSION_KEY_BACKEND + ":" + token, MemberConstants.SSO_SESSION_EXPIRE);

        MemberUserCompose memberUserCompose = new MemberUserCompose();
        storeUser.setPassword(null);
        memberUserCompose.setMemberUser(storeUser);
        memberUserCompose.setToken(token);
        return ResponseData.build(
            ResponseBackCode.SUCCESS.getValue(),
            ResponseBackCode.SUCCESS.getMessage(),memberUserCompose);
      }
      return ResponseData.build(
          ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),
          ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage()
      );
    }catch (Exception e){
      throw new RequestException(ResponseBackCode.ERROR_FAIL.getValue(), e.getMessage());
    }
  }

  /**
   * 登出
   *
   * @param token 令牌
   */
  @Override
  public ResponseData userLogout(String token) {
    jedisClusterService.del(MemberConstants.REDIS_USER_SESSION_KEY_FRONT + ":" + token);
    //返回token
    return ResponseData.build(
        ResponseBackCode.SUCCESS.getValue(),
        ResponseBackCode.SUCCESS.getMessage()
    );
  }

  /**
   * 拉取用户信息接口
   */
  @Override
  public ResponseData getUserInfo() {
    MemberUserCompose memberUserCompose = new MemberUserCompose();
    Long userId = jsonTokenUtil.getCurrentUserId();
    MemberUser memberUser = selectById(userId);
    MemberGrade memberGrade = memberGradeService.selectById(memberUser.getGradeId());
    memberUserCompose.setMemberGrade(memberGrade);
    memberUser.setPassword(null);
    memberUserCompose.setMemberUser(memberUser);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage(),memberUserCompose);
  }



  /**
   * 注册
   * @param cellPhone 手机号
   * @param openId 微信openId
   * @param verificateCode 验证码
   */
  @Override
  public ResponseData registerUser(String cellPhone, String openId, String verificateCode) {
    try{


      //验证验证码是否有效且没有过期
      String results = jedisClusterService.get(cellPhone + ":" + verificateCode);
      if(results == null || "".equals(results)){
        return ResponseData.build(
            ResponseBackCode.ERROR_CAPTCHA_INVALID.getValue(),
            ResponseBackCode.ERROR_CAPTCHA_INVALID.getMessage()
        );
      }

      //验证unionid
      Map<String,Object> map = new HashMap<>();
      map.put("unnit_id",openId);
      List<MemberUser> memberUserList = selectByMap(map);
      if(memberUserList == null || memberUserList.size() == 0){
        return ResponseData.build(
            ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
            ResponseBackCode.ERROR_PARAM_INVALID.getMessage()
        );
      }
      MemberUser memberUser = memberUserList.get(0);

      //把手机号插入到用户表中
      memberUser.setCellphone(cellPhone);
      updateById(memberUser);

      //生成token
      String token = TokenProccessor.getInstance().makeToken(cellPhone);

      jedisClusterService.set(MemberConstants.REDIS_USER_SESSION_KEY_FRONT + ":" + token,memberUser.getId()+"");

      //设置session的过期时间
      jedisClusterService.expire(MemberConstants.REDIS_USER_SESSION_KEY_FRONT + ":" + token, MemberConstants.SSO_SESSION_EXPIRE);
      MemberUserCompose memberUserCompose = new MemberUserCompose();
      MemberGrade memberGrade = memberGradeService.selectById(memberUser.getGradeId());
      memberUserCompose.setMemberGrade(memberGrade);

      //清空密码
      memberUser.setPassword(null);
      memberUserCompose.setMemberUser(memberUser);
      memberUserCompose.setToken(token);
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),
                                ResponseBackCode.SUCCESS.getMessage(),memberUserCompose);
    }catch (Exception e){
      throw new RequestException(ResponseBackCode.ERROR_FAIL.getValue(), e.getMessage());
    }
  }

  /**
   * 修改余额
   */
  @Transactional(rollbackFor = Exception.class)
  @Override
  public ResponseData updateBalance(Long userId,UpdateBalanceRequest request) {
    MemberUser memberUser = selectById(userId);
    if(memberUser == null){
      return  ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage());
    }


    MemberAccountHistory memberAccountHistory = new MemberAccountHistory();
    memberAccountHistory.setUserId(userId);
    int histroyType = MemberConstants.SMALL_TYPE_PERSONAL_ADD;

    BigDecimal balance = request.getAmount();
    BigDecimal totalBalance = memberUser.getBalance();

    if(MemberConstants.BALANCE_REDUCE.equals(request.getOperateType())){

      //减少
      totalBalance = totalBalance.subtract(balance);
      histroyType = MemberConstants.SMALL_TYPE_PERSONAL_REDUCE; }
    else{
      //增加
      totalBalance = totalBalance.add(balance);

    }
    memberUser.setBalance(totalBalance);
    memberAccountHistory.setTerminalBalance(totalBalance);
    memberAccountHistory.setBalance(balance);
    memberAccountHistory.setType(MemberConstants.TYPE_BALANCE);
    memberAccountHistory.setSmallType(histroyType);

    //更新用户表里的字段
    updateById(memberUser);

    memberAccountHistoryService.insert(memberAccountHistory);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }


  /**
   * 修改积分
   */
  @Transactional(rollbackFor = Exception.class)
  @Override
  public ResponseData updatePoints(Long userId,UpdateBalanceRequest request) {
    MemberUser memberUser = selectById(userId);
    if(memberUser == null){
      return  ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(), ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage());
    }


    MemberAccountHistory memberAccountHistory = new MemberAccountHistory();
    memberAccountHistory.setUserId(userId);
    int histroyType = MemberConstants.SMALL_TYPE_PERSONAL_ADD;

    BigDecimal points = request.getAmount();
    BigDecimal totalPoints = memberUser.getPoints();

    if(MemberConstants.BALANCE_REDUCE.equals(request.getOperateType())){

      //减少
      totalPoints = totalPoints.subtract(points);
      histroyType = MemberConstants.SMALL_TYPE_PERSONAL_REDUCE;
    }else{
      //增加
      totalPoints = totalPoints.add(points);

    }
    memberUser.setPoints(totalPoints);
    memberAccountHistory.setTerminalPoints(totalPoints);
    memberAccountHistory.setPoints(points);
    memberAccountHistory.setType(MemberConstants.TYPE_POINTS);
    memberAccountHistory.setSmallType(histroyType);

    //更新用户表里的字段
    updateById(memberUser);

    memberAccountHistoryService.insert(memberAccountHistory);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  @Override
  public ResponseData<Page> searchAllUser(int page, int size, SearchAllUserRequest request) {
    Page userPage = new Page<MemberUserResponse>(page, size);
    userPage.setAsc(false);
    try{
      String registerTimeBefore = null;
      String registerTimeAfter = null;
      if(request !=null && request.getRegisterTime()!=null){
        boolean isRightDate = isRightDateStr(request.getRegisterTime(),"yyyy-MM-dd");
        if(!isRightDate){
          return new ResponseData<>(ResponseBackCode.ERROR_PARAM_INVALID.getValue(),"日期格式不正确，请按yyyy-MM-dd格式查询");
        }
        registerTimeBefore = request.getRegisterTime() + " 00:00:00";
        registerTimeAfter = request.getRegisterTime() + " 23:59:59";
      }

      List<MemberUser> memberUserList = baseMapper.searchAllUser(userPage.getLimit(), userPage.getOffset(),registerTimeBefore,registerTimeAfter,request);
      if(memberUserList != null && memberUserList.size() > 0){
        List<MemberUserResponse> responseList = new ArrayList<>();
        for (MemberUser memberUser:memberUserList){
          MemberUserResponse response = new MemberUserResponse();
          BeanUtils.copyProperties(memberUser,response);
          response.setPassword(null);
          responseList.add(response);
        }
        userPage.setRecords(responseList);
      }
    }catch (Exception e){
      throw new RequestException(ResponseBackCode.ERROR_PARAM_INVALID.getValue(),e.getMessage());
    }
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),
                              ResponseBackCode.SUCCESS.getMessage(),userPage);
  }

  @Override
  public ResponseData updateUser(Long userId, UpdateMemberUserRequest request) {
    //根据传入的id查找数据库中是否有该用户
    MemberUser user = selectById(userId);
    if (request.getCellphone() != null && !"".equals(request.getCellphone())){
      user.setCellphone(request.getCellphone());
    }
    if (request.getCarNum() != null && !"".equals(request.getCarNum())){
      user.setCarNum(request.getCarNum());
    }
    if (request.getBirthday() != null){
      boolean isRightDate = isRightDateStr(request.getBirthday(),"yyyy-MM-dd");
      if(!isRightDate){
        return  ResponseData.build(ResponseBackCode.ERROR_PARAM_INVALID.getValue(),ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),"日期格式不正确，请按yyyy-MM-dd格式查询");
      }
      LocalDateTime birthDay = LocalDate.parse(request.getBirthday(), dateformatter).atStartOfDay();
      user.setBirthday(birthDay);
    }
    updateById(user);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());

  }

  @Override
  public ResponseData<GetUserResponse> getUserByOpenId(String openId) {
    EntityWrapper<MemberUser> entityWrapper = new EntityWrapper<MemberUser>();
    entityWrapper.eq("unnit_id", openId);
    MemberUser memberUser = selectOne(entityWrapper);

    GetUserResponse userVo = null;
    if (memberUser != null) {
      userVo = new GetUserResponse();
      BeanUtils.copyProperties(memberUser, userVo);
    }
    return ResponseUtil.success(userVo);
  }

  /**
   * 生成6位数的帐号
   */
  private String code(){
    String code = "M000001";
    //取经销商最大的code并加1
    Page userPage = new Page<MemberUser>(1, 10);
    userPage.setAsc(false);
    List<MemberUser> memberUserList = selectPage(userPage, new EntityWrapper<MemberUser>().orderBy("id", false)).getRecords();

    if(memberUserList != null && memberUserList.size() > 0){
      long id = memberUserList.get(0).getId();
      if(id < 10){
        code = "M00000"+(id+1);
      }else if(id < 100){
        code = "M0000"+(id+1);
      }else if(id < 1000){
        code = "M000"+(id+1);
      }else if(id < 10000){
        code = "M00"+(id+1);
      }else if(id < 100000){
        code = "M0"+(id+1);
      }else{
        code = "M"+(id+1);
      }
    }
    return code;
  }

  /**
   * 判断日期格式是否正确
   */
  private  boolean isRightDateStr(String dateStr,String datePattern){
    DateFormat dateFormat  = new SimpleDateFormat(datePattern);
    try {
      //采用严格的解析方式，防止类似 “2017-05-35” 类型的字符串通过
      dateFormat.setLenient(false);
      dateFormat.parse(dateStr);
      Date date = dateFormat.parse(dateStr);
      //重复比对一下，防止类似 “2017-5-15” 类型的字符串通过
      String newDateStr = dateFormat.format(date);
      if(dateStr.equals(newDateStr)){
        return true;
      }else {
        return false;
      }
    } catch (ParseException e) {
      return false;
    }
  }
}
