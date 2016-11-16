/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : collectdb

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2016-11-17 00:13:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_people
-- ----------------------------
DROP TABLE IF EXISTS `t_people`;
CREATE TABLE `t_people` (
  `id` bigint(15) NOT NULL,
  `type` varchar(15) DEFAULT NULL,
  `subtype` varchar(15) DEFAULT NULL,
  `hostname` varchar(40) DEFAULT NULL COMMENT '户主',
  `hostid` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `hostphone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `number` int(3) DEFAULT NULL,
  `lessor` varchar(40) DEFAULT NULL COMMENT '出租人',
  `lessee` varchar(40) DEFAULT NULL COMMENT '租住人',
  `stay` varchar(40) DEFAULT NULL COMMENT '寄住人',
  `employee` varchar(255) DEFAULT '' COMMENT '从业人员',
  `expend` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
