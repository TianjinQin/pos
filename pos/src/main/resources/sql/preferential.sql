/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50616
Source Host           : 127.0.0.1:3306
Source Database       : pos

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2016-03-03 00:00:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `preferential`
-- ----------------------------
DROP TABLE IF EXISTS `preferential`;
CREATE TABLE `preferential` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL COMMENT '优惠编码',
  `name` varchar(20) NOT NULL COMMENT '优惠名称',
  `priority` int(2) NOT NULL COMMENT '优先级',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='优惠表';

-- ----------------------------
-- Records of preferential
-- ----------------------------
INSERT INTO `preferential` VALUES ('1', 'alternative', '买二赠一', '10');
INSERT INTO `preferential` VALUES ('2', 'discount', '95折', '9');
