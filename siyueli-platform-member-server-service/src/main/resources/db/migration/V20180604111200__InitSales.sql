CREATE TABLE `sales_agency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20)  NOT NULL COMMENT '经销商帐号',
  `name` varchar(64) NOT NULL COMMENT '经销商名称',
  `grade` varchar(64) NOT NULL COMMENT '经销商等级',
  `address` text COMMENT '通讯地址',
  `phone` varchar(20) COMMENT '联系电话',
  `contacts` varchar(20) COMMENT '联系人',
  `balance` decimal(10,2) DEFAULT '0.00'  COMMENT '帐户余额',
  `discount` decimal(12,2) NOT NULL  COMMENT '结算折扣',
  `remark` text COMMENT '备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `sales_agency_index_code` (`code`)
) COMMENT='经销商表';

CREATE TABLE `sales_agency_delivery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contacts` varchar(20) NOT NULL  COMMENT '联系人',
  `phone` varchar(20)  NOT NULL COMMENT '电话',
  `address` text NOT NULL  COMMENT '地址',
  `code` varchar(20) NOT NULL   COMMENT '经销商帐号',
  `is_default` tinyint(1) DEFAULT '1' COMMENT '是否默认',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='经销商配送信息表';

CREATE TABLE `sales_agency_balance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` decimal(12,2)  NOT NULL DEFAULT '0.00'  COMMENT '金额',
  `trade_platform` varchar(64)  COMMENT '交易平台',
  `serial_num` varchar(64)  COMMENT '流水号',
  `business_type` varchar(64) NOT NULL COMMENT '业务类型',
  `order_num` varchar(64)  COMMENT '客户订单号',
  `code` varchar(20) NOT NULL   COMMENT '经销商帐号',
  `operator` varchar(64)  COMMENT '操作人',
  `total_balance` decimal(12,2)  NOT NULL DEFAULT '0.00'  COMMENT '期末余额',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='经销商余额表';


CREATE TABLE `sales_shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20)  NOT NULL COMMENT '门店code',
  `name` varchar(64) NOT NULL COMMENT '门店名称',
  `address` text NOT NULL COMMENT '门店地址',
  `agency_code` varchar(64) NOT NULL COMMENT '经销商帐号',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `sales_shop_index_code` (`code`)
) COMMENT='门店表';

CREATE TABLE `sales_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(64)  NOT NULL COMMENT '员工帐号',
  `name` varchar(64)  NOT NULL COMMENT '姓名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `shop_code` varchar(20) COMMENT '门店code',
  `type` tinyint(1) NOT NULL COMMENT '类型',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sales_user_unique_code` (`code`,`shop_code`)
) COMMENT='门店员工表';




