ALTER TABLE `member_account_history`
  ADD COLUMN `small_type` tinyint(1) NULL COMMENT '每种类型中的小类型' AFTER `type`;
