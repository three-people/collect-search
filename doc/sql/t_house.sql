/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : collectdb

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2016-11-17 00:12:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_house
-- ----------------------------
DROP TABLE IF EXISTS `t_house`;
CREATE TABLE `t_house` (
  `id` bigint(15) NOT NULL,
  `type` varchar(15) NOT NULL COMMENT '平房，2楼房，写字楼',
  `subtype` varchar(15) DEFAULT NULL,
  `number` int(6) DEFAULT NULL COMMENT '门牌号',
  `host` varchar(40) DEFAULT '' COMMENT '户主',
  `owner` varchar(40) DEFAULT NULL COMMENT '产权人',
  `ownerid` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `ownerphone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `subnumber` varchar(15) DEFAULT NULL COMMENT '楼号',
  `unit` varchar(8) DEFAULT NULL COMMENT '单元',
  `floor` int(3) DEFAULT NULL COMMENT '楼层',
  `doornumber` varchar(8) DEFAULT NULL COMMENT '门牌号（楼房）',
  `employee` varchar(255) DEFAULT NULL COMMENT '从业人员',
  `expend` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
