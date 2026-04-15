/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50152
Source Host           : localhost:3306
Source Database       : ckrk

Target Server Type    : MYSQL
Target Server Version : 50152
File Encoding         : 65001

Date: 2019-01-11 10:34:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cangku`
-- ----------------------------
DROP TABLE IF EXISTS `cangku`;
CREATE TABLE `cangku` (
  `CID` varchar(30) NOT NULL DEFAULT '',
  `GName` varchar(30) DEFAULT NULL,
  `GNum` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cangku
-- ----------------------------
INSERT INTO `cangku` VALUES ('c1', '货物A', '0');
INSERT INTO `cangku` VALUES ('c2', '货物B', '4');
INSERT INTO `cangku` VALUES ('c3', '货物C', '0');

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `DID` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `DName` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `DManager` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`DID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('d1', '部门A', '张三');
INSERT INTO `department` VALUES ('d2', '部门B', '小一');
INSERT INTO `department` VALUES ('d3', '部门C', '小二');

-- ----------------------------
-- Table structure for `people`
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `PID` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `PName` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `PTel` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `PSex` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `PDID` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES ('p1', '张三', '1345627111', '男', 'd1');
INSERT INTO `people` VALUES ('p2', '吴莉', '1345627222', '女', 'd2');
INSERT INTO `people` VALUES ('p3', '王五', '1345627333', '男', 'd2');

-- ----------------------------
-- Table structure for `rk`
-- ----------------------------
DROP TABLE IF EXISTS `rk`;
CREATE TABLE `rk` (
  `RID` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `RHID` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `RGID` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `RGName` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `RTime` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `RNum` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `RPrice` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `RMoney` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `RSID` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `RFID` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `RPID` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `Year` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `Jidu` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `Type` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`RID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of rk
-- ----------------------------
INSERT INTO `rk` VALUES ('r2', 'h2', 'g2', '货物B', '4月1日', '2', '15', '30', 's3', 'f3', 'p1', '2019', '2', '一类');
INSERT INTO `rk` VALUES ('r3', 'h3', 'g3', '货物C', '10月1日', '1', '30', '30', 's2', 'f2', 'p2', '2019', '4', '二类');
INSERT INTO `rk` VALUES ('r4', 'h4', 'g4', '货物C', '10月1日', '1', '30', '30', 's1', 'f4', 'p3', '2019', '4', '二类');
INSERT INTO `rk` VALUES ('r6', 'h6', 'g6', '货物B', '4月1日', '1', '15', '15', 's1', 'f6', 'p3', '2019', '2', '二类');

-- ----------------------------
-- Table structure for `supply`
-- ----------------------------
DROP TABLE IF EXISTS `supply`;
CREATE TABLE `supply` (
  `SID` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `SName` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `SAddress` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `STel` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`SID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of supply
-- ----------------------------
INSERT INTO `supply` VALUES ('s1', '供应商A', '山东建筑大学', '1345627890');
INSERT INTO `supply` VALUES ('s2', '供应商B', '山东大学', '1345627200');
INSERT INTO `supply` VALUES ('s3', '供应商C', '济南大学', '13456278344');
DROP TRIGGER IF EXISTS `入库`;
DELIMITER ;;
CREATE TRIGGER `入库` BEFORE INSERT ON `rk` FOR EACH ROW update cangku
set GNum=GNum+new.RNum where GName=new.RGName
;;
DELIMITER ;
