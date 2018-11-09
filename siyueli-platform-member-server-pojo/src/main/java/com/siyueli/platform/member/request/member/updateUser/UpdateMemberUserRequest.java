/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.request.member.updateUser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 前台修改个人资料请求对象
 */
@Data
@ApiModel(value = "UpdateMemberUserRequest")
public class UpdateMemberUserRequest{

  /**
   * 手机号码
   */
  @ApiModelProperty(value = "手机号码")
  private String cellphone;

  /**
   * 车牌号
   */
  @ApiModelProperty(value = "车牌号")
  private String carNum;
  /**
   * 生日
   */
  @ApiModelProperty(value = "生日")
  private String birthday;

}
