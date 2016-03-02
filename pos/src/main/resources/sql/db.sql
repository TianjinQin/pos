/*
Date: 2016-03-02 16:35:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `goods_preferential`
-- ----------------------------
DROP TABLE IF EXISTS `goods_preferential`;
CREATE TABLE `goods_preferential` (
  `id` bigint(20) NOT NULL,
  `barcode` varchar(20) NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品优惠表';

-- ----------------------------
-- Records of goods_preferential
-- ----------------------------

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '名称',
  `unit` varchar(5) NOT NULL COMMENT '单位',
  `barcode` varchar(20) NOT NULL COMMENT '条形码',
  `price` decimal(7,2) NOT NULL COMMENT '单价',
  `category` varchar(255) DEFAULT NULL COMMENT '类别',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_barcode` (`barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '可口可乐', '瓶', 'ITEM000005', '3.00', null);
INSERT INTO `goods` VALUES ('2', '苹果', '斤', 'ITEM000003', '5.50', null);
INSERT INTO `goods` VALUES ('3', '羽毛球', '个', 'ITEM000001', '1.00', null);

-- ----------------------------
-- Table structure for `preferential`
-- ----------------------------
DROP TABLE IF EXISTS `preferential`;
CREATE TABLE `preferential` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(5) NOT NULL COMMENT '优惠编码',
  `name` varchar(20) NOT NULL COMMENT '优惠名称',
  `priority` int(2) NOT NULL COMMENT '优先级',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='优惠表';

-- ----------------------------
-- Records of preferential
-- ----------------------------
INSERT INTO `preferential` VALUES ('1', 'a', '买二赠一', '10');
INSERT INTO `preferential` VALUES ('2', 'b', '95折', '9');

