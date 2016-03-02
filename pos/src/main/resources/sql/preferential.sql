/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.240
Source Server Version : 50613
Source Host           : 192.168.1.240:3306
Source Database       : bst_assistant

Target Server Type    : MYSQL
Target Server Version : 50613
File Encoding         : 65001

Date: 2016-03-02 16:35:36
*/

SET FOREIGN_KEY_CHECKS=0;

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
