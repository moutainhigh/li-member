ALTER TABLE activity_info
ADD COLUMN `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态，1-启用，0-禁用' AFTER `effect_time`;