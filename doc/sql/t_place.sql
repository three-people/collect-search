/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : collectdb

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2016-11-17 00:13:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_place
-- ----------------------------
DROP TABLE IF EXISTS `t_place`;
CREATE TABLE `t_place` (
  `id` bigint(15) NOT NULL,
  `type` varchar(15) DEFAULT NULL,
  `name` varchar(63) DEFAULT NULL,
  `address` varchar(63) DEFAULT NULL,
  `area` varchar(15) DEFAULT NULL,
  `lessor` varchar(20) DEFAULT NULL,
  `lessorid` varchar(20) DEFAULT NULL,
  `lessorphone` varchar(20) DEFAULT NULL,
  `lessee` varchar(20) DEFAULT NULL,
  `lesseeid` varchar(20) DEFAULT NULL,
  `lesseephone` varchar(20) DEFAULT NULL,
  `extend` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
