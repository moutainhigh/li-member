drop TABLE  if exists sales_agency_account_history;
CREATE TABLE `sales_agency_account_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agency_id` bigint(20) NOT NULL COMMENT '经销商id',
  `order_no`  varchar(50)  NOT NULL COMMENT '单号',
  `serial_no`  varchar(50)  NULL COMMENT '交易流水号',
  `points` decimal(12,2) DEFAULT NULL COMMENT '积分',
  `terminal_points` decimal(12,2) DEFAULT NULL COMMENT '期末积分',
  `balance` decimal(12,2) DEFAULT NULL COMMENT '余额',
  `terminal_balance` decimal(12,2) DEFAULT NULL COMMENT '期末余额',
  `operator` varchar(50)  NOT NULL COMMENT '操作人',
  `type` tinyint(1) DEFAULT NULL COMMENT '类型',
  `small_type` tinyint(1) DEFAULT NULL COMMENT '每种类型中的小类型',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_agency_account_history_sales_agency` (`agency_id`),
  unique index inx_agency_account_history_sales_agency (order_no),
  CONSTRAINT `fk_agency_account_history_sales_agency` FOREIGN KEY (`agency_id`) REFERENCES `sales_agency` (`id`)
) COMMENT='经销商帐户历史表';
