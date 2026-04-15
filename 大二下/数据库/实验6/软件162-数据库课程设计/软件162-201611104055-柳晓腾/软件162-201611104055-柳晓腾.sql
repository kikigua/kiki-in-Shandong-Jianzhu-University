/*
Navicat MySQL Data Transfer

Source Server         : qqq
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : kfglxt

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-01-11 10:45:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for adjust
-- ----------------------------
DROP TABLE IF EXISTS `adjust`;
CREATE TABLE `adjust` (
  `RoomId` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Originalcost` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Currentcost` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Adjustmenttime` datetime DEFAULT NULL,
  `Adjustname` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`RoomId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of adjust
-- ----------------------------
INSERT INTO `adjust` VALUES ('11', '1000', '200', '2019-01-11 10:08:54', '张三');

-- ----------------------------
-- Table structure for costhis
-- ----------------------------
DROP TABLE IF EXISTS `costhis`;
CREATE TABLE `costhis` (
  `CusIDcard` varchar(20) NOT NULL DEFAULT '',
  `RoomId` varchar(10) NOT NULL DEFAULT '',
  `Time` datetime NOT NULL,
  `CostType` varchar(10) NOT NULL,
  `AllTost` double NOT NULL,
  `Remarks` varchar(30) DEFAULT NULL,
  `Uid` varchar(15) NOT NULL,
  `DoTime` datetime NOT NULL,
  PRIMARY KEY (`CusIDcard`,`RoomId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of costhis
-- ----------------------------

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `CusIDcard` varchar(20) NOT NULL,
  `CusName` varchar(8) NOT NULL,
  `CusSex` varchar(2) NOT NULL,
  `CusAge` varchar(2) NOT NULL,
  `CusTel` varchar(20) NOT NULL,
  `CusHome` varchar(20) NOT NULL,
  `CusEml` varchar(20) NOT NULL,
  `CusType` varchar(20) NOT NULL,
  PRIMARY KEY (`CusIDcard`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `RoomId` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `FloorNumber` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `RoType` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `RoomCost` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `RoomState` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`RoomId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('11', '1', '1', '200', '1');

-- ----------------------------
-- Table structure for stayin
-- ----------------------------
DROP TABLE IF EXISTS `stayin`;
CREATE TABLE `stayin` (
  `CusIDcard` varchar(20) NOT NULL DEFAULT '',
  `RoomID` varchar(10) NOT NULL DEFAULT '',
  `Deposit` double DEFAULT NULL,
  `ScheduledStartTime` datetime DEFAULT NULL,
  `ScheduledEndTime` datetime DEFAULT NULL,
  `StayInTime` datetime DEFAULT NULL,
  `Uid` varchar(15) NOT NULL,
  `DoTime` datetime NOT NULL,
  PRIMARY KEY (`CusIDcard`,`RoomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stayin
-- ----------------------------

-- ----------------------------
-- Table structure for stayinhis
-- ----------------------------
DROP TABLE IF EXISTS `stayinhis`;
CREATE TABLE `stayinhis` (
  `CusIDcard` varchar(255) NOT NULL,
  `Roomid` varchar(255) NOT NULL,
  `StayInTime` datetime NOT NULL,
  `StayOutTime` datetime NOT NULL,
  `OtherRate` double DEFAULT NULL,
  `RoomRate` double NOT NULL,
  `Uid` varchar(255) NOT NULL,
  `DoTime` datetime NOT NULL,
  PRIMARY KEY (`CusIDcard`,`Roomid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stayinhis
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Uname` varchar(12) NOT NULL,
  `Uid` int(6) NOT NULL,
  `Utype` varchar(6) NOT NULL,
  `Upassword` varchar(12) NOT NULL,
  `TrName` varchar(8) NOT NULL,
  `Uage` int(3) NOT NULL,
  `Usex` varchar(2) NOT NULL,
  PRIMARY KEY (`Uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for vip
-- ----------------------------
DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip` (
  `CusType` varchar(10) NOT NULL,
  `DisCount` double NOT NULL,
  PRIMARY KEY (`CusType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vip
-- ----------------------------
DROP TRIGGER IF EXISTS `update_cost`;
DELIMITER ;;
CREATE TRIGGER `update_cost` AFTER UPDATE ON `room` FOR EACH ROW BEGIN
UPDATE adjust SET Originalcost=old.RoomCost where old.RoomId=adjust.RoomId;
UPDATE adjust SET Currentcost = new.RoomCost where old.RoomId=adjust.RoomId;
UPDATE adjust set Adjustmenttime = NOW();
end
;;
DELIMITER ;
