ALTER TABLE `member_account_history`
  ADD COLUMN `terminal_balance` decimal(12,2) NULL COMMENT '期末余额' AFTER `balance`;
ALTER TABLE `member_account_history`
  ADD COLUMN `terminal_points` decimal(12,2) NULL COMMENT '期末积分' AFTER `points`;
