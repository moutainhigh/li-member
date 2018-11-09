CREATE TABLE `recharge_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(12,2)  NOT NULL DEFAULT '0.00'  COMMENT '金额',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `status` int(4) NOT NULL COMMENT '状态',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_recharge_order_user_id` (`user_id`),
  KEY `idx_recharge_order_status` (`status`),
  UNIQUE KEY `uidx_recharge_order_order_no` (`order_no`)
) COMMENT='充值订单表';