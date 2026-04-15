/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50518
Source Host           : localhost:3306
Source Database       : hotel

Target Server Type    : MYSQL
Target Server Version : 50518
File Encoding         : 65001

Date: 2019-01-11 08:12:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for actual_stay
-- ----------------------------
DROP TABLE IF EXISTS `actual_stay`;
CREATE TABLE `actual_stay` (
  `ID_Number` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `room_id` varchar(255) DEFAULT NULL,
  `stay_time` datetime DEFAULT NULL,
  `leave_time` datetime DEFAULT NULL,
  `total` float(30,2) DEFAULT NULL,
  KEY `ID_Number` (`ID_Number`),
  KEY `name` (`name`),
  KEY `room_id` (`room_id`),
  KEY `total` (`total`),
  CONSTRAINT `ID_Number` FOREIGN KEY (`ID_Number`) REFERENCES `customer` (`ID_Number`),
  CONSTRAINT `name` FOREIGN KEY (`name`) REFERENCES `customer` (`name`),
  CONSTRAINT `room_id` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `total` FOREIGN KEY (`total`) REFERENCES `income` (`total`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of actual_stay
-- ----------------------------
INSERT INTO `actual_stay` VALUES ('370687199709161234', '张三', '101', '2019-01-01 15:44:41', '2019-01-02 15:44:45', '300.20');
INSERT INTO `actual_stay` VALUES ('370687199945354', '王梅', '201', '2019-01-05 16:51:58', '2019-01-06 16:52:09', '790.00');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `ID_Number` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `birth` date DEFAULT NULL,
  `sex` varchar(40) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `emial` varchar(100) DEFAULT NULL,
  `state` tinyint(40) DEFAULT NULL,
  `room_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Number`,`name`),
  KEY `name` (`name`),
  KEY `ID_Number` (`ID_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('370656732423423', '小樱', '2018-09-12', '女', '17865133242', 'xiaoying@163.com', '0', null);
INSERT INTO `customer` VALUES ('3706871997', '李四', '2018-08-23', '男', '17865139288', 'qwe@qq.com', '0', '');
INSERT INTO `customer` VALUES ('370687199709161234', '张三', '2018-09-01', '男', '17865139882', '123123@qq.com', '1', '101');
INSERT INTO `customer` VALUES ('370687199909152981', '萨斯给', '2018-11-20', '男', '13053567890', 'zuozhu@qq.com', '0', null);
INSERT INTO `customer` VALUES ('370687199945354', '王梅', '2019-01-03', '女', '17861232132', 'df@163.com', '0', null);

-- ----------------------------
-- Table structure for income
-- ----------------------------
DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `income_date` datetime NOT NULL,
  `other_income` float(30,2) DEFAULT NULL,
  `room_income` float(30,2) DEFAULT NULL,
  `total` float(30,2) NOT NULL,
  PRIMARY KEY (`income_date`,`total`),
  KEY `total` (`total`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of income
-- ----------------------------
INSERT INTO `income` VALUES ('2018-12-15 14:52:17', '200.00', '260.00', '460.00');
INSERT INTO `income` VALUES ('2019-01-01 09:49:05', '100.00', '200.20', '300.20');
INSERT INTO `income` VALUES ('2019-01-02 09:56:56', '100.00', '198.00', '298.00');
INSERT INTO `income` VALUES ('2019-01-03 09:57:21', '200.00', '300.00', '500.00');
INSERT INTO `income` VALUES ('2019-01-04 09:57:57', '100.00', '690.00', '790.00');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `member_type` varchar(255) DEFAULT NULL,
  `discount` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('银卡', '8.8');

-- ----------------------------
-- Table structure for reserve
-- ----------------------------
DROP TABLE IF EXISTS `reserve`;
CREATE TABLE `reserve` (
  `reserve_begintime` datetime DEFAULT NULL,
  `reserve_endtime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reserve
-- ----------------------------
INSERT INTO `reserve` VALUES ('2018-10-03 10:07:13', '2018-10-04 10:07:20');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `floor_id` varchar(255) NOT NULL,
  `room_id` varchar(255) NOT NULL,
  `room_type` varchar(100) NOT NULL,
  `room_price` int(10) NOT NULL,
  `location` varchar(100) DEFAULT NULL,
  `state` varchar(100) NOT NULL,
  PRIMARY KEY (`room_id`),
  KEY `room_id` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('F1', '101', '大床房', '198', '东侧', '0');
INSERT INTO `room` VALUES ('F1', '102', '双人床', '200', '东部', '1');
INSERT INTO `room` VALUES ('F1', '103', '大床房', '198', '中部', '0');
INSERT INTO `room` VALUES ('F1', '104', '双人房', '200', '中部', '0');
INSERT INTO `room` VALUES ('F1', '105', '商务房', '298', '西部', '1');
INSERT INTO `room` VALUES ('F2', '201', '商务房', '298', '东部', '0');
INSERT INTO `room` VALUES ('F2', '202', '大床房', '209', '中部', '1');
INSERT INTO `room` VALUES ('F2', '203', '大床房', '209', '中部', '0');
INSERT INTO `room` VALUES ('F3', '301', '商务房', '298', '东部', '0');
INSERT INTO `room` VALUES ('F3', '302', '大床房', '198', '西部', '0');

-- ----------------------------
-- Table structure for ruzhulv
-- ----------------------------
DROP TABLE IF EXISTS `ruzhulv`;
CREATE TABLE `ruzhulv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `occupancy` varchar(255) DEFAULT NULL,
  `free_rate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ruzhulv
-- ----------------------------
INSERT INTO `ruzhulv` VALUES ('1', '0.3', '0.7');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userType` int(11) DEFAULT NULL,
  `user` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '前台操作员', '123qwe', '王五', '18', '女');
INSERT INTO `user` VALUES ('0', '系统管理员', '123', '张三', '21', '男');
-- ----------------------------
-- 统计报表、客户入住统计
-- ----------------------------
 select * from actual_stay where name = '张三';
 select * from actual_stay where stay_time>='2019-01-01' and stay_time<='2019-01-02';
-- ----------------------------
-- 存储过程
-- ----------------------------
 CREATE PROCEDURE incomeDay()
BEGIN
	select sum(other_income) from income where income_date>='2019-01-01' and income_date<='2019-01-02';
  select sum(room_income) from income where income_date>='2019-01-01' and income_date<='2019-01-02';
	select sum(total) from income where income_date>='2019-01-01' and income_date<='2019-01-02';
END;
CREATE PROCEDURE incomeMonth()
BEGIN
	select sum(other_income) from income where income_date>='2019-01-01' and income_date<='2019-01-31';
  select sum(room_income) from income where income_date>='2019-01-01' and income_date<='2019-01-31';
	select sum(total) from income where income_date>='2019-01-01' and income_date<='2019-01-31';
END;
CREATE PROCEDURE incomeDong()
BEGIN
	select sum(other_income) from income where income_date>='2018-12-01' and income_date<='2019-02-28';
  select sum(room_income) from income where income_date>='2018-12-01' and income_date<='2019-02-28';
	select sum(total) from income where income_date>='2018-12-01' and income_date<='2019-02-28';
END;
CREATE PROCEDURE incomeYear()
BEGIN
	select sum(other_income) from income where income_date>='2018-01-01' and income_date<='2019-12-31';
  select sum(room_income) from income where income_date>='2018-01-01' and income_date<='2019-12-31';
	select sum(total) from income where income_date>='2018-01-01' and income_date<='2019-12-31';
END;
-- ----------------------------
-- 客房统计
-- ----------------------------

 select * from room where room_type='大床房';	-- 房间全部信息
 SELECT SUM(state) from room ;								-- 入住房间数
 SELECT count(*) FROM room;										-- 总房间数

-- 入住率/空闲率
UPDATE ruzhulv set occupancy = (SELECT SUM(state) from room)/(SELECT count(*) FROM room) WHERE id=1;
UPDATE ruzhulv set free_rate= 1-(SELECT SUM(state) from room)/(SELECT count(*) FROM room) WHERE id=1;
SELECT occupancy,free_rate FROM ruzhulv where id=1;

