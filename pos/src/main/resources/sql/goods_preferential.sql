/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.240
Source Server Version : 50613
Source Host           : 192.168.1.240:3306
Source Database       : bst_assistant

Target Server Type    : MYSQL
Target Server Version : 50613
File Encoding         : 65001

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
