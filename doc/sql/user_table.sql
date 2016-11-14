/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : collectdb

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2016-11-14 23:47:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `id` bigint(20) NOT NULL,
  `uname` varchar(50) NOT NULL,
  `pwd` varchar(40) NOT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `realName` varchar(20) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `age` int(5) DEFAULT NULL,
  `role` int(3) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `ufrom` int(2) DEFAULT NULL,
  `ip` varchar(30) DEFAULT NULL,
  `departmentName` varchar(40) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name_pwd` (`uname`,`pwd`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
