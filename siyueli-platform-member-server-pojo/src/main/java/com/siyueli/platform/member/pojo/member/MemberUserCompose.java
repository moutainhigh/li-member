package com.siyueli.platform.member.pojo.member;

import com.siyueli.platform.member.pojo.grade.MemberGrade;
import com.siyueli.platform.member.pojo.member.MemberUser;

import lombok.Data;

/**
 * <p>
 * 会员客户组装类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Data
public class MemberUserCompose  {

  private MemberUser memberUser;

  private MemberGrade memberGrade;

  private String token;

  private String openId;
}
