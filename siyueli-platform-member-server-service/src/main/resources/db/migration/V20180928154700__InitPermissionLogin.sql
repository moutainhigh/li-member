CREATE TABLE `permission_login` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`uri` varchar(255) NOT NULL COMMENT '访问的uri',
	`need_login` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否需要登录：0-不需要，1-需要',
	`uri_name` varchar(255) NOT NULL COMMENT '接口名称',
	`uri_category` varchar(255) NOT NULL COMMENT '接口分类',
	`create_at` datetime NOT NULL COMMENT '创建时间',
	`update_at` datetime NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`)
) COMMENT='访问的uri是否需要登录';