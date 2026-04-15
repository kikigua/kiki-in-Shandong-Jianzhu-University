/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50152
Source Host           : localhost:3306
Source Database       : kefangguanli1

Target Server Type    : MYSQL
Target Server Version : 50152
File Encoding         : 65001

Date: 2019-01-11 08:14:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `costhis`
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
INSERT INTO `costhis` VALUES ('123', '001', '2007-06-07 00:00:00', '早餐，午餐', '200', 'mmp', '002', '2007-07-07 00:00:00');

-- ----------------------------
-- Table structure for `customer`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('20171717', '的话', '男', '18', '121434', '山东', '123@qq.com', '会员');

-- ----------------------------
-- Table structure for `room`
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `RoomId` varchar(20) NOT NULL,
  `FloorNumber` varchar(20) NOT NULL,
  `RoomState` varchar(20) NOT NULL,
  `RoomType` varchar(20) NOT NULL,
  `RoomCost` double NOT NULL,
  PRIMARY KEY (`RoomId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('101', '1', '空闲', '标间', '200');
INSERT INTO `room` VALUES ('102', '1', '入住', '标间', '200');

-- ----------------------------
-- Table structure for `stayin`
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
INSERT INTO `stayin` VALUES ('123', '001', '200', '2007-06-07 00:00:00', '2007-07-07 00:00:00', '2007-07-08 00:00:00', '002', '2007-07-07 00:00:00');

-- ----------------------------
-- Table structure for `stayinhis`
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
INSERT INTO `stayinhis` VALUES ('123', '001', '2007-06-07 00:00:00', '2007-07-07 00:00:00', '200', '100', '002', '2007-07-07 00:00:00');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Uid` varchar(15) NOT NULL,
  `Uname` varchar(15) NOT NULL,
  `Utype` varchar(15) NOT NULL,
  `Upassword` varchar(15) NOT NULL,
  `TrName` varchar(15) NOT NULL,
  `Uage` int(11) NOT NULL,
  `Usex` char(1) NOT NULL,
  PRIMARY KEY (`Uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '小王', '非会员', '234', '王旺', '23', '男');
INSERT INTO `user` VALUES ('20', '小明', '会员', '123', '王明', '18', '男');

-- ----------------------------
-- View structure for `vw3`
-- ----------------------------
DROP VIEW IF EXISTS `vw3`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw3` AS select `stayin`.`RoomID` AS `RoomID`,`stayinhis`.`OtherRate` AS `OtherRate`,`stayinhis`.`RoomRate` AS `RoomRate`,`customer`.`CusName` AS `CusName`,`stayin`.`Deposit` AS `Deposit`,(`stayinhis`.`OtherRate` + `stayinhis`.`RoomRate`) AS `zongfeiyong` from ((`stayin` join `stayinhis`) join `customer`) where ((`stayin`.`CusIDcard` = `stayinhis`.`CusIDcard`) and (`stayinhis`.`CusIDcard` = `customer`.`CusIDcard`)) group by `stayin`.`CusIDcard` ;

-- ----------------------------
-- View structure for `vw4`
-- ----------------------------
DROP VIEW IF EXISTS `vw4`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw4` AS select `stayin`.`RoomID` AS `RoomID`,`stayinhis`.`OtherRate` AS `OtherRate`,`stayinhis`.`RoomRate` AS `RoomRate`,`customer`.`CusName` AS `CusName`,`stayin`.`Deposit` AS `Deposit`,(`stayinhis`.`OtherRate` + `stayinhis`.`RoomRate`) AS `zongfeiyong` from ((`stayin` join `stayinhis`) join `customer`) where ((`stayin`.`CusIDcard` = `stayinhis`.`CusIDcard`) and (`stayinhis`.`CusIDcard` = `customer`.`CusIDcard`) and (`stayinhis`.`Roomid` = `stayin`.`RoomID`) and (`stayinhis`.`Roomid` = '001')) group by `stayin`.`CusIDcard` ;
INSERT INTO stayin (
	stayInId,
  CusIDcard,
	RoomId,
	Uid,
	Deposit,
	ScheduledStartTime,
	ScheduledEndTime,
	DoTime
)
VALUES
	(2,
		'370126199509163413',
		'002',
		'123',
		'100',
		'2018-1-11',
		'2018-1-15',
		'2018-1-10'
	)
	SELECT
	RoomId
FROM
	room
WHERE
	RoomState = '空闲'
AND RoomType = '标间'
AND RoomId NOT IN (
	SELECT
		RoomId
	FROM
		stayin
	WHERE
		(
			ScheduledStartTime <= '2018-1-13'
			AND '2018-1-13' <= ScheduledEndTime
		)
	OR (
		ScheduledStartTime <= '2018-1-17'
		AND '2018-1-17' <= ScheduledEndTime
	)
	OR (
		ScheduledStartTime >= '2018-1-13'
		AND '2018-1-17' >= ScheduledEndTime
	)
)