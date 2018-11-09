CREATE TABLE `activity_window_plate` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`name` varchar(255) NOT NULL COMMENT '板块名',
	`sort` int(11) NULL COMMENT '排序',
	`plate_flag` varchar(190) NOT NULL COMMENT '板块标记',
	`status` tinyint(2) NOT NULL COMMENT '状态',
	`create_at` datetime NOT NULL COMMENT '创建时间',
	`update_at` datetime NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	UNIQUE KEY `uidx_activity_window_plate_plate_flag` (`plate_flag`)
) COMMENT='活动橱窗板块表';

CREATE TABLE `activity_window_group` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`name` varchar(255) NOT NULL COMMENT '分组名',
	`plate_id` bigint(20) NOT NULL COMMENT '所属板块',
	`sort` int(11) NULL COMMENT '排序',
	`group_flag` varchar(190) NOT NULL COMMENT '分组标记',
	`status` tinyint(2) NOT NULL COMMENT '状态',
	`create_at` datetime NOT NULL COMMENT '创建时间',
	`update_at` datetime NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	UNIQUE KEY `uidx_activity_window_group_group_flag` (`group_flag`)
) COMMENT='活动橱窗分组表';

CREATE TABLE `activity_window_content` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`name` varchar(255) NOT NULL COMMENT '内容名',
	`group_id` bigint(20) NOT NULL COMMENT '分组id',
	`sort` int(11) NULL COMMENT '排序',
	`pic_url` varchar(255) NOT NULL COMMENT '图片url',
	`content_url` varchar(255) NOT NULL COMMENT '内容url',
	`description` varchar(255) NULL COMMENT '描述',
	`status` tinyint(2) NOT NULL COMMENT '状态',
	`create_at` datetime NOT NULL COMMENT '创建时间',
	`update_at` datetime NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`)
)COMMENT='活动橱窗内容表';