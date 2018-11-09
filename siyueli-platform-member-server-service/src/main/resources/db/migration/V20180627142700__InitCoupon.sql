CREATE TABLE `coupon_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) NOT NULL COMMENT '卡券名称',
  `type_id` tinyint(4) NOT NULL DEFAULT '0' COMMENT '卡券类型',
  `status_id` tinyint(4) NOT NULL DEFAULT '0' COMMENT '卡券状态',
  `start_at` datetime NOT NULL COMMENT '领取开始时间',
  `end_at` datetime NOT NULL COMMENT '领取结束时间',
  `valid_time_start_at` datetime NULL COMMENT '有效开始时间',
  `valid_time_end_at` datetime NULL COMMENT '有效结束时间',
  `receipt_start_day` tinyint(2) NULL COMMENT '从第几天起有效，0表示当天有效',
  `receipt_end_day` tinyint(2) NULL COMMENT '有效后结束天数',
  `receipt_limit` tinyint(4) NOT NULL DEFAULT '1' COMMENT '限领张数',
  `stock_qty` int(11) NOT NULL COMMENT '库存数',
  `share_activity` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否与其他优惠活动共享',
  `share_club_card` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否与会员卡优惠共享',
  `scope` tinyint(4) NOT NULL DEFAULT '0' COMMENT '适用范围,0表示全场适用，1为可用商品，2为不可用商品',
  `threshold` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '使用门槛，消费满xx元',
  `discount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '抵扣金额或折扣率',
  `creator_id` bigint(20) NOT NULL COMMENT '创建者',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  PRIMARY KEY (`id`),
  KEY `coupon_task_index_name` (`name`)
) COMMENT='卡券任务';

CREATE TABLE `coupon_task_sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `task_id` bigint(20) NOT NULL COMMENT '卡券任务ID',
  `sku_no` varchar(50) NOT NULL  COMMENT 'SKU-NO编码',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `coupon_task_sku_index_task_id` (`task_id`)
) COMMENT='卡券任务适用商品/卡券任务不适用商品';

CREATE TABLE `coupon_task_gift` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `task_id` bigint(20) NOT NULL COMMENT '卡券任务ID',
  `sku_no` varchar(50) NOT NULL  COMMENT '赠品SKU-NO编码',
  `qty` int(11) NOT NULL COMMENT '赠品数量',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `coupon_task_gift_index_task_id` (`task_id`)
) COMMENT='卡券任务赠品';

CREATE TABLE `coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `task_id` bigint(20) NOT NULL COMMENT '卡券任务ID',
  `task_type_id` tinyint(4) NOT NULL DEFAULT '0' COMMENT '卡券类型',
  `code` varchar(64) NOT NULL COMMENT '优惠券',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `order_no` varchar(64) NULL COMMENT '优惠券作用的订单号',
  `source` tinyint(4) NOT NULL DEFAULT '0' COMMENT '领取的来源平台',
  `status_id` tinyint(4) NOT NULL DEFAULT '0' COMMENT '优惠券状态',
  `receipt_at` datetime NULL COMMENT '领取时间',
  `use_at`  datetime NULL COMMENT '使用时间',
  `valid_time_start_at` datetime DEFAULT NULL COMMENT '有效开始时间',
  `valid_time_end_at` datetime DEFAULT NULL COMMENT '有效结束时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `coupon_index_task_id_and_type` (`task_id`,`task_type_id`),
  UNIQUE KEY `coupon_unique_code` (`code`)
) COMMENT='优惠券';

CREATE TABLE `coupon_task_channel` (
  `coupon_task_id` bigint(20) NOT NULL COMMENT '卡券ID',
  `channel_id` tinyint(4) NOT NULL COMMENT '渠道ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  UNIQUE KEY `coupon_task_channel_unique_coupon_task_and_channel` (`coupon_task_id`, `channel_id`)
) COMMENT='卡券渠道关联表';
