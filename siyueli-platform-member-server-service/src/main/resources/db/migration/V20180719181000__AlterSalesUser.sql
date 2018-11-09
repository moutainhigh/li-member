ALTER TABLE `sales_user`
  ADD COLUMN `status` tinyint(1) NOT NULL COMMENT '状态' AFTER `type`;

ALTER TABLE `sales_user`
  ADD COLUMN `empno` varchar(64) NOT NULL COMMENT '员工工号' AFTER `code`;
