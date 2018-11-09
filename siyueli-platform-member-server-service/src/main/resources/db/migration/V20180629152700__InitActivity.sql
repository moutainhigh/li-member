
CREATE TABLE activity_info (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL COMMENT '活动名称',
  type tinyint(1) NOT NULL COMMENT '活动类型',
  effect_type tinyint(1) NOT NULL COMMENT '生效类型',
  effect_time datetime  COMMENT '生效时间',
  create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
) COMMENT='活动基础信息表';

CREATE TABLE activity_role_info (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  activity_id bigint(20) NOT NULL COMMENT '活动id',
  coupon_id bigint(20)  COMMENT '卡券id',
  distribute_user_type tinyint(1) COMMENT '派发用户类型',
  points decimal(12,2)  COMMENT '赠送积分',
  recharge_amout decimal(12,2)   COMMENT '充值金额',
  donate_balance decimal(12,2)   COMMENT '赠送余额',
  promote_type tinyint(1) COMMENT '促销类型',
  create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
) COMMENT='规则信息表';

CREATE TABLE activity_user_coupon (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  role_id bigint(20) NOT NULL COMMENT '规则信息表id',
  coupon_id bigint(20)  COMMENT '卡券id',
  user_id bigint(20)  COMMENT '会员id',
  create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
) COMMENT='规则卡券用户表';
