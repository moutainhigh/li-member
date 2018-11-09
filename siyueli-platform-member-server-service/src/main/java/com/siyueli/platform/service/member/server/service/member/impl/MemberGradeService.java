package com.siyueli.platform.service.member.server.service.member.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.grade.MemberGrade;
import com.siyueli.platform.member.pojo.grade.MemberGradeCompose;
import com.siyueli.platform.member.pojo.rights.MemberRights;
import com.siyueli.platform.member.pojo.rights.MemberRightsGrade;
import com.siyueli.platform.member.request.grade.addGrade.SaveMemberGradeRequest;
import com.siyueli.platform.member.request.grade.updateGrade.UpdateMemberGradeRequest;
import com.siyueli.platform.service.member.server.mapper.member.MemberGradeMapper;
import com.siyueli.platform.service.member.server.service.member.MemberGradeServiceContract;
import com.siyueli.platform.service.member.server.service.member.MemberRightsGradeServiceContract;
import com.siyueli.platform.service.member.server.service.member.MemberRightsServiceContract;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * <p>
 * 会员等级表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class MemberGradeService extends ServiceImpl<MemberGradeMapper, MemberGrade> implements MemberGradeServiceContract {

  private MemberRightsGradeServiceContract memberRightsGradeService;

  private MemberRightsServiceContract memberRightsService;

  @Autowired
  public MemberGradeService(MemberRightsGradeServiceContract memberRightsGradeService,
                            MemberRightsServiceContract memberRightsService){
    this.memberRightsGradeService = memberRightsGradeService;
    this.memberRightsService = memberRightsService;
  }

  /**
   * 新建等级实现类
   *
   */
  @Transactional(rollbackFor = Exception.class)
  @Override
  public ResponseData addGrade(SaveMemberGradeRequest request) {

    MemberGrade memberGrade = new MemberGrade();
    //保存地址
    BeanUtils.copyProperties(request, memberGrade);

    //插入到等级表
    insert(memberGrade);

    //插入到权益等级中间表
    List<Long> rights = request.getRightsId();
    List<MemberRightsGrade> memberRightsGradeList = new ArrayList<>();
    MemberRightsGrade memberRightsGrade;
    for (int i = 0, size = rights.size(); i < size; i++) {
      Long rightId = rights.get(i);
      memberRightsGrade = new MemberRightsGrade();
      memberRightsGrade.setGradeId(memberGrade.getId());
      memberRightsGrade.setRightsId(rightId);
      memberRightsGradeList.add(memberRightsGrade);
    }
    if (memberRightsGradeList.size() > 0) {
      memberRightsGradeService.insertBatch(memberRightsGradeList);
    }
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public ResponseData updateGrade(Long gradeId,UpdateMemberGradeRequest request) {

    MemberGrade memberGrade = new MemberGrade();
    //保存地址
    BeanUtils.copyProperties(request, memberGrade);
    memberGrade.setId(gradeId);
    //更新到等级表
    updateById(memberGrade);

    //先从相关等级权益中间表中删除数据
    Map map = new HashMap();
    map.put("grade_id", memberGrade.getId());
    memberRightsGradeService.deleteByMap(map);

    //插入到权益等级中间表
    List<Long> rights = request.getRightsId();
    List<MemberRightsGrade> memberRightsGradeList = new ArrayList<>();
    MemberRightsGrade memberRightsGrade;
    int size = rights.size();
    for (int i = 0; i < size; i++) {
      memberRightsGrade = new MemberRightsGrade();
      memberRightsGrade.setGradeId(memberGrade.getId());
      memberRightsGrade.setRightsId(rights.get(i));
      memberRightsGradeList.add(memberRightsGrade);
    }
    if (memberRightsGradeList.size() > 0) {
      memberRightsGradeService.insertBatch(memberRightsGradeList);
    }
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());
  }

  @Override public ResponseData<Page> searchAllGrade(int page, int size) {

    Page userPage = new Page<MemberGrade>(page, size);
    userPage.setAsc(false);
    userPage = selectPage(userPage,new EntityWrapper<MemberGrade>().orderBy("promote_condition"));
    List<MemberGrade> memberGradeList = userPage.getRecords();
    if(memberGradeList !=null){
      for (int i = 0,length = memberGradeList.size(); i < length; i++){
        MemberGrade memberGrade = memberGradeList.get(i);

        //组合等级和权益数据
        MemberGradeCompose memberGradeCompose = (MemberGradeCompose) memberGrade.clone();
        List<MemberRights> memberRightsList = memberRightsService.getRightsByGradeId(memberGrade.getId());
        memberGradeCompose.setMemberRightsList(memberRightsList);
        memberGradeList.set(i,memberGradeCompose);
      }
    }
    userPage.setRecords(memberGradeList);
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(),userPage);
  }

  /**
   * 删除等级
   */
  @Transactional(rollbackFor = Exception.class)
  @Override public ResponseData deleteGradeById(Long gradeId) {
    //删除等级表中gradeId
    deleteById(gradeId);

    //删除等级权益表
    Map map = new HashMap();
    map.put("grade_id",gradeId);
    memberRightsGradeService.deleteByMap(map);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

}
