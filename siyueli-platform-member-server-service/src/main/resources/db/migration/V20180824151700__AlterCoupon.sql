ALTER TABLE `coupon`
  ADD COLUMN `card_coupon_id` bigint(20) NOT NULL COMMENT '微信卡券表id' AFTER `code`;

ALTER TABLE `coupon`
  ADD FOREIGN KEY (`card_coupon_id`) REFERENCES `weixin_card_coupon` (`id`);