CREATE TABLE `sport_event_category` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`name` varchar(255) NOT NULL COMMENT '名称',
	`parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父级id',
	`update_at` datetime NOT NULL COMMENT '更新时间',
	`create_at` datetime NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) COMMENT='体育赛事分类表';

CREATE TABLE `sport_event_form` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`name` varchar(255) NOT NULL COMMENT '名称',
	`form_id` bigint(20) NOT NULL COMMENT '表单id',
	`category_id` bigint(20) NOT NULL COMMENT '分类id',
	`update_at` datetime NOT NULL COMMENT '更新时间',
	`create_at` datetime NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) COMMENT='赛事表单表';

CREATE TABLE `sport_event_sign_up` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`event_form_id` bigint(20) NOT NULL COMMENT '赛事表单id',
	`sport_name` varchar(255) NOT NULL COMMENT '赛事名称',
	`begin_time` datetime NOT NULL COMMENT '报名开始时间',
	`end_time` datetime NOT NULL COMMENT '报名结束时间',
	`rule_desc` text NULL COMMENT '规则说明',
	`fee` decimal(12,2) NOT NULL COMMENT '报名费用',
	`need_audit` tinyint(1) NOT NULL COMMENT '需要审核',
	`need_verify` tinyint(1) NOT NULL COMMENT '需要运动员认证',
	`only_adult_athlete` tinyint(1) NOT NULL COMMENT '仅限成人运动员',
	`member_id` bigint(20) NULL COMMENT '会员id',
	`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '已删除',
	`status` tinyint(2) NOT NULL COMMENT '状态',
	`update_at` datetime NOT NULL COMMENT '更新时间',
	`create_at` datetime NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) COMMENT='赛事报名表';

CREATE TABLE `sport_event_sign_up_field` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`sign_up_id` bigint(20) NOT NULL COMMENT '赛事报名id',
	`field_id` bigint(20) NOT NULL COMMENT '字段id',
	`field_value` varchar(255) NULL COMMENT '字段值',
	`update_at` datetime NOT NULL COMMENT '更新时间',
	`create_at` datetime NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) COMMENT='赛事报名字段值表';