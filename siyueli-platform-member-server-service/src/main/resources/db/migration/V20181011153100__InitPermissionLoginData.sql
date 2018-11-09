INSERT INTO `permission_login` (`id`, `uri`, `need_login`, `uri_name`, `uri_category`, `create_at`, `update_at`)
VALUES
	(284,'/api/siyueli-member/member/region/index',1,'获取中国所有省市区信息列表接口','省市地区信息查询接口','2018-09-29 00:00:00','2018-09-29 17:31:00'),
	(290,'/api/siyueli-member/member/user/getUserInfo',1,'查找用户信息','斯越里_前台_会员管理接口','2018-09-29 00:00:00','2018-09-29 17:31:00'),
	(291,'/api/siyueli-member/member/user/registerUser',1,'注册会员接口','斯越里_前台_会员管理接口','2018-09-29 00:00:00','2018-09-29 17:31:00'),
	(292,'/api/siyueli-member/member/user/searchAllGrade',1,'查找所有等级','斯越里_前台_会员管理接口','2018-09-29 00:00:00','2018-09-29 17:31:00'),
	(293,'/api/siyueli-member/member/user/updateUser/{userId}',1,'修改个人资料接口','斯越里_前台_会员管理接口','2018-09-29 00:00:00','2018-09-29 17:31:00'),
	(294,'/api/siyueli-member/member/user/userLogin',1,'登录','斯越里_前台_会员管理接口','2018-09-29 00:00:00','2018-09-29 17:31:00'),
	(295,'/api/siyueli-member/member/user/userLogout',1,'登出','斯越里_前台_会员管理接口','2018-09-29 00:00:00','2018-09-29 17:31:00'),
	(296,'/api/siyueli-member/payment/getAccountLog',1,'得到帐户变更记录','斯越里_充值接口','2018-09-29 00:00:00','2018-09-29 17:31:00'),
	(297,'/api/siyueli-member/payment/getRechargeResult',1,'得到充值结果','斯越里_充值接口','2018-09-29 00:00:00','2018-09-29 17:31:00'),
	(298,'/api/siyueli-member/payment/recharge',1,'充值','斯越里_充值接口','2018-09-29 00:00:00','2018-09-29 17:31:00'),
	(299,'/api/siyueli-member/payment/rechargeNotify',1,'充值结果通知','斯越里_充值接口','2018-09-29 00:00:00','2018-09-29 17:31:00'),
	(300,'/api/siyueli-member/paymentCode/deductMoney',1,'扣款','斯越里_付款码接口','2018-09-29 00:00:00','2018-09-29 17:31:00'),
	(301,'/api/siyueli-member/paymentCode/getPaymentCode',1,'得到付款码','斯越里_付款码接口','2018-09-29 00:00:00','2018-09-29 17:31:00');
