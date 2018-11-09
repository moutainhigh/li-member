/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.service.grade;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.pojo.grade.MemberGrade;
import com.siyueli.platform.member.request.grade.addGrade.SaveMemberGradeRequest;
import com.siyueli.platform.member.request.grade.updateGrade.UpdateMemberGradeRequest;
import com.siyueli.platform.service.member.client.callback.grade.MemberGradeServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.siyue.platform.base.ResponseData;

/**
 * 调用等级服务生产者的接口
 */
@FeignClient(name = "siyueli-member-service", path = "/member/grade", fallback = MemberGradeServiceFallBack.class)
public interface MemberGradeService {

  @RequestMapping(value = "/addGrade", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData addGrade(SaveMemberGradeRequest request);

  @RequestMapping(value = "/updateGrade/{gradeId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateGrade(@PathVariable(value = "gradeId") Long gradeId, UpdateMemberGradeRequest request);


  @RequestMapping(value = "/deleteGrade/{gradeId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData deleteGrade(@PathVariable(value = "gradeId") Long gradeId);


  @RequestMapping(value = "/searchAllGrade", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page> searchAllGrade(@RequestParam("page") int page, @RequestParam("size") int size);

}
