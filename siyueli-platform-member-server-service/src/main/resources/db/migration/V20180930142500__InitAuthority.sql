CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200)  NOT NULL COMMENT '角色名称',
  `code` varchar(50)  NOT NULL COMMENT '编号',
  `children_system`  tinyint(1) NOT NULL COMMENT '子系统',
  `status` tinyint(1) NOT NULL COMMENT '状态',
  `type` tinyint(1) NOT NULL COMMENT '类型',
  `scope` bigint(20) NULL COMMENT '可用范围',
  `creator` bigint(20) NULL COMMENT '创建人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   PRIMARY KEY (`id`)
) COMMENT='角色表';

CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_user_role` (`user_id`,`role_id`)
) COMMENT='用户角色表';

CREATE TABLE `permission_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '权限组名',
  `type` int(11) DEFAULT NULL COMMENT '组类型:前台(0)或者后台(1)',
  PRIMARY KEY (`id`)
) COMMENT='权限分组';

CREATE TABLE `permission_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `permission_group_id` bigint(20) NOT NULL COMMENT '权限组id',
  PRIMARY KEY (`id`)
) COMMENT='权限角色对应表';

CREATE TABLE `permission_action` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_url` varchar(128) NOT NULL DEFAULT '' COMMENT '权限url',
  `display_name` varchar(50) DEFAULT '' COMMENT '显示名',
  `group_id` bigint(20) NOT NULL COMMENT '权限分组',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='权限表';


