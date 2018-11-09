
CREATE TABLE member_operator (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  code varchar(50) NOT NULL COMMENT '运营人员code',
  name varchar(50) NOT NULL COMMENT '运营人员名称',
  grade_id varchar(50) NOT NULL DEFAULT '0' COMMENT '运营人员等级',
  cellphone varchar(50)  COMMENT '手机号码',
  mobile varchar(50) COMMENT '座机号码',
  identity_id varchar(50)  COMMENT '身份证号码',
  email varchar(50)  COMMENT '邮箱',
  password varchar(200) NOT NULL COMMENT '密码',
  address varchar(200)  COMMENT '详细地址',
  status tinyint(1)  NOT NULL DEFAULT '0' COMMENT '状态',
  type varchar(20) NULL COMMENT '类型',
  create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_at datetime COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY member_operator_unique_code (code)
) COMMENT='运营人员表';


CREATE TABLE member_business (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  code varchar(50) NOT NULL COMMENT '员工code',
  name varchar(50) NOT NULL COMMENT '员工名称',
  grade_id varchar(50) NOT NULL DEFAULT '0'  COMMENT '员工等级',
  cellphone varchar(50)  COMMENT '手机号码',
  mobile varchar(50) COMMENT '座机号码',
  identity_id varchar(50)  COMMENT '身份证号码',
  email varchar(50)  COMMENT '邮箱',
  password varchar(200) NOT NULL  COMMENT '密码',
  address varchar(200)  COMMENT '详细地址',
  status tinyint(1)  NOT NULL DEFAULT '0' COMMENT '状态',
  type varchar(20) NULL COMMENT '类型',
  create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_at datetime COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY member_business_unique_code (code)
) COMMENT='商家员工表';



CREATE TABLE `member_address` (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) NOT NULL COMMENT '用户id',
  receiver_name varchar(50) NOT NULL COMMENT '收货人',
  cellphone varchar(50) NOT NULL COMMENT '手机号码',
  address varchar(200) NOT NULL COMMENT '详细地址',
  full_address varchar(200) NOT NULL COMMENT '省市区+详细地址',
  province_code bigint(20)  COMMENT '省code',
  city_code bigint(20)  COMMENT '市code',
  district_code bigint(20)  COMMENT '区code',
  label varchar(50)  COMMENT '标签',
  default_address tinyint(1)  DEFAULT '0' COMMENT '默认地址:0代表不是默认地址，1代表默认地址',
  status tinyint(1)  NOT NULL DEFAULT '0' COMMENT '状态:0代表有效，1代表失效(删除)',
  create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
)COMMENT='会员地址表';


CREATE TABLE member_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  code varchar(50)  COMMENT '会员号',
  name varchar(50)  COMMENT '会员名称',
  cellphone varchar(50)  COMMENT '手机号码',
  grade_id bigint(20)  COMMENT '会员等级',
  create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  register_channel varchar(50) COMMENT '注册渠道',
  unnit_id varchar(50)  COMMENT '微信unnitId',
  car_num varchar(20) COMMENT '车牌号',
  birthday datetime COMMENT '生日',
  password varchar(200)   COMMENT '密码',
  status tinyint(1)  NOT NULL DEFAULT '0' COMMENT '状态',
  identity_id varchar(50)  COMMENT '身份证号码',
  email varchar(50)  COMMENT '邮箱',
  balance decimal(12,2)   COMMENT '余额',
  donate_balance decimal(12,2)   COMMENT '赠送余额',
  deposite decimal(12,2)   COMMENT '押金',
  points decimal(12,2)  COMMENT '积分',
  update_at datetime COMMENT '更新时间',
  PRIMARY KEY (id)
) COMMENT='会员表';

CREATE TABLE `member_account_history` (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) NOT NULL COMMENT '用户id',
  points decimal(12,2)  COMMENT '积分',
  balance decimal(12,2)   COMMENT '余额',
  deposite decimal(12,2)   COMMENT '押金',
  type tinyint(1)    COMMENT '类型',
  create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id),
  CONSTRAINT `fk_member_account_history_member_user` FOREIGN KEY (`user_id`) REFERENCES `member_user` (`id`)
)COMMENT='帐户历史表';




CREATE TABLE `member_grade` (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(50)  NOT NULL COMMENT '等级名称',
  img_url varchar(255)   COMMENT '背影图地址',
  promote_condition decimal(12,2)   COMMENT '晋升门槛',
  create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
)COMMENT='会员等级表';

CREATE TABLE `member_rights` (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(50)  NOT NULL COMMENT '权益名称',
  type tinyint(1) NOT NULL  COMMENT '权益类型',
  status tinyint(1) NOT NULL  COMMENT '权益状态',
  remarks text  COMMENT '权益说明',
  trigger_condition decimal(12,2)   COMMENT '触发条件',
  rights_content decimal(12,2)   COMMENT '权益内容',
  create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
)COMMENT='权益表';

CREATE TABLE `member_rights_grade` (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  rights_id bigint(20)  NOT NULL COMMENT '权益id',
  grade_id bigint(20) NOT NULL  COMMENT '等级id',
  create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
)COMMENT='权益等级表';


CREATE TABLE `region` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(50) COLLATE utf8mb4_unicode_520_ci NOT NULL DEFAULT '' COMMENT '地区名',
  `parent_id` bigint(20) NOT NULL COMMENT '父ID',
  `zip` varchar(10) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT '邮编',
  `sort` tinyint(3) unsigned DEFAULT '50' COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `region_index_parent_id` (`parent_id`) USING BTREE
) COMMENT='行政区';



