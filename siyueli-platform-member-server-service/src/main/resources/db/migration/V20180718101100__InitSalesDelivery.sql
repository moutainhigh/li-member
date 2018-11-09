drop TABLE  if exists sales_agency_delivery;
CREATE TABLE `sales_agency_delivery` (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  shop_id bigint(20) NOT NULL COMMENT '门店id',
  receiver_name varchar(50) NOT NULL COMMENT '收货人',
  cellphone varchar(50) NOT NULL COMMENT '手机号码',
  address varchar(200) NOT NULL COMMENT '详细地址',
  full_address varchar(200) NOT NULL COMMENT '省市区+详细地址',
  province_code bigint(20)  COMMENT '省code',
  city_code bigint(20)  COMMENT '市code',
  district_code bigint(20)  COMMENT '区code',
  label varchar(50)  COMMENT '标签',
  default_address tinyint(1)  DEFAULT '0' COMMENT '默认地址:0代表不是默认地址，1代表默认地址',
  status tinyint(1)  NOT NULL DEFAULT '0' COMMENT '状态:0代表有效，1代表失效(删除)',
  create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
) COMMENT='门店配送信息表';
