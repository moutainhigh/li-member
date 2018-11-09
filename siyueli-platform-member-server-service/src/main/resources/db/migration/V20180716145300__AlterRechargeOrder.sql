ALTER TABLE `recharge_order`
  ADD COLUMN `remark` varchar(256) NULL COMMENT '备注' AFTER `status`;