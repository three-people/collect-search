/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : collectdb

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2016-11-17 00:12:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_employer
-- ----------------------------
DROP TABLE IF EXISTS `t_employer`;
CREATE TABLE `t_employer` (
  `id` bigint(15) NOT NULL,
  `type` varchar(15) DEFAULT NULL,
  `name` varchar(63) DEFAULT NULL,
  `address` varchar(63) DEFAULT NULL,
  `chargeman` varchar(40) DEFAULT NULL,
  `chargemanid` varchar(20) DEFAULT NULL,
  `chargemanphone` varchar(20) DEFAULT NULL,
  `safeman` varchar(40) DEFAULT NULL,
  `safemanid` varchar(20) DEFAULT NULL,
  `safemanphone` varchar(20) DEFAULT NULL,
  `extend` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
