CREATE TABLE `account_checking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `type` int(4) NOT NULL COMMENT '类型',
  `money` decimal(12,2) NOT NULL COMMENT '金额',
  `remark` varchar(255) NULL COMMENT '备注',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  `update_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='对账表';