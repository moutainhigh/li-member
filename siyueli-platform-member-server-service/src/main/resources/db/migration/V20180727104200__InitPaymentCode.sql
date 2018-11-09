CREATE TABLE payment_code (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_id` bigint(20) NOT NULL COMMENT '用户id',
	`payment_code` varchar(128) NOT NULL COMMENT '付款码',
	`order_no` varchar(64) NULL COMMENT '订单号',
	`amount` decimal(12,2)  NULL DEFAULT '0.00'  COMMENT '金额',
	`valid_time` datetime NOT NULL COMMENT '有效时间',
	`is_used` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否使用：0-没有使用，1-已经使用',
	`created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  	`updated_at` datetime NOT NULL COMMENT '更新时间',
  	PRIMARY KEY (`id`),
  	KEY `idx_payment_code_user_id` (`user_id`),
  	KEY `idx_payment_code_order_no` (`order_no`),
  	UNIQUE KEY `uidx_payment_code_payment_code` (`payment_code`)
) COMMENT='付款码表';