package com.siyueli.platform.member.pojo.grade;

import com.siyueli.platform.member.pojo.grade.MemberGrade;
import com.siyueli.platform.member.pojo.rights.MemberRights;

import java.util.List;

import lombok.Data;

/**
 * <p>
 * 会员等级组合类
 * </p>
 *
 * @author Sipin ERP Development Team
 */

@Data
public class MemberGradeCompose extends MemberGrade {




    private List<MemberRights> memberRightsList;


}
