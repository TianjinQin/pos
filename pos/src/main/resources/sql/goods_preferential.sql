/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50616
Source Host           : 127.0.0.1:3306
Source Database       : pos

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2016-03-03 00:00:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `goods_preferential`
-- ----------------------------
DROP TABLE IF EXISTS `goods_preferential`;
CREATE TABLE `goods_preferential` (
  `id` bigint(20) NOT NULL,
  `barcode` varchar(20) NOT NULL,
  `code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品优惠表';

-- ----------------------------
-- Records of goods_preferential
-- ----------------------------
INSERT INTO `goods_preferential` VALUES ('1', 'ITEM000005', 'alternative');
INSERT INTO `goods_preferential` VALUES ('2', 'ITEM000005', 'discount');
INSERT INTO `goods_preferential` VALUES ('3', 'ITEM000003', 'alternative');
INSERT INTO `goods_preferential` VALUES ('4', 'ITEM000001', 'discount');
