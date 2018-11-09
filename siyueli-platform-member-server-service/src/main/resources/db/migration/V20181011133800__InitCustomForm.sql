CREATE TABLE `custom_form_field` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`name` varchar(255) NOT NULL COMMENT '名称',
	`field_name` varchar(255) NOT NULL COMMENT '字段名',
	`validate_regular` varchar(255) NULL COMMENT '校验规则',
	`update_at` datetime NOT NULL COMMENT '更新时间',
	`create_at` datetime NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) COMMENT='表单字段表';

CREATE TABLE `custom_field_option` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`field_id` bigint(20) NOT NULL COMMENT '字段id',
	`field_value` varchar(255) NOT NULL COMMENT '字段选项值',
	`show_type` smallint(2) NOT NULL COMMENT '展示类型',
	`update_at` datetime NOT NULL COMMENT '更新时间',
	`create_at` datetime NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) COMMENT='字段选项表';

CREATE TABLE `custom_form_category` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`name` varchar(255) NOT NULL COMMENT '名称',
	`parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父级id',
	`update_at` datetime NOT NULL COMMENT '更新时间',
	`create_at` datetime NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) COMMENT='表单分类表';

CREATE TABLE `custom_form` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`name` varchar(255) NOT NULL COMMENT '名称',
	`category_id` bigint(20) NOT NULL COMMENT '分类id',
	`update_at` datetime NOT NULL COMMENT '更新时间',
	`create_at` datetime NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) COMMENT='表单';

CREATE TABLE `custom_form_field_relationship` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`form_id` bigint(20) NOT NULL COMMENT '表单id',
	`field_id` bigint(20) NOT NULL COMMENT '字段id',
	`required` tinyint(1) NOT NULL COMMENT '是否必填',
	`sort` int(4) NOT NULL COMMENT '排序',
	`update_at` datetime NOT NULL COMMENT '更新时间',
	`create_at` datetime NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) COMMENT='表单与字段关系表';