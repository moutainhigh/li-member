
ALTER TABLE `member_grade`
  ADD COLUMN img_secret varchar(50) COMMENT '图片密钥' AFTER `img_url`;
