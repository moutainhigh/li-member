package com.siyueli.platform.service.member.server.service.member;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.pojo.member.MemberUser;
import com.siyueli.platform.member.request.member.backendUserLogin.BackendUserLoginRequest;
import com.siyueli.platform.member.request.member.searchAllUser.SearchAllUserRequest;
import com.siyueli.platform.member.request.member.updateAmount.UpdateBalanceRequest;
import com.siyueli.platform.member.request.member.updateUser.UpdateMemberUserRequest;
import cn.siyue.platform.base.ResponseData;
import com.siyueli.platform.member.response.member.getuser.GetUserResponse;

/**
 * 会员表 服务类
 */
public interface MemberUserServiceContract extends IService<MemberUser> {

  /**
   * 前台会员登录
   */
  ResponseData userLogin(String jsCode);

  /**
   * 后台登录
   */
  ResponseData backendLogin(BackendUserLoginRequest backendUserLoginRequest);

  /**
   * 会员登出
   */
  ResponseData userLogout(String token);

  /**
   * 拉取用户信息接口
   */
  ResponseData getUserInfo();

  /**
   * 会员注册
   * @param cellPhone 手机号
   * @param openId 微信unionid
   * @param verificateCode 验证码
   */
  ResponseData registerUser(String cellPhone, String openId, String verificateCode);

  /**
   * 修改余额
   */
  ResponseData updateBalance(Long userId,UpdateBalanceRequest request);

  /**
   * 修改积分
   */
  ResponseData updatePoints(Long userId,UpdateBalanceRequest request);

  /**
   * 后台查询会员
   */
  ResponseData<Page> searchAllUser(int page, int size, SearchAllUserRequest request);

  /**
   * 修改会员资料
   */
  ResponseData updateUser(Long userId,UpdateMemberUserRequest request);

  ResponseData<GetUserResponse> getUserByOpenId(String openId);

}
