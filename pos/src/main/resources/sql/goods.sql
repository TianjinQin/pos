/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50616
Source Host           : 127.0.0.1:3306
Source Database       : pos

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2016-03-03 00:00:20
*/

SET FOREIGN_KEY_CHECKS=0;

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
