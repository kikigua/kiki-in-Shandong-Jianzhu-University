/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50152
Source Host           : localhost:3306
Source Database       : carrentaldb

Target Server Type    : MYSQL
Target Server Version : 50152
File Encoding         : 65001

Date: 2019-01-11 16:15:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `car`
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `Cartype` char(10) NOT NULL,
  `CarID` char(10) NOT NULL,
  `Buytime` date DEFAULT NULL,
  `Statete` char(5) NOT NULL,
  `Rent` int(10) DEFAULT NULL,
  `Deposit` int(5) DEFAULT NULL,
  PRIMARY KEY (`Cartype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES ('奇瑞', '鲁E12345', '2018-05-20', '空闲', '400', '10000');
INSERT INTO `car` VALUES ('宝马', '鲁D12345', '2018-05-20', '在租', '400', '10000');
INSERT INTO `car` VALUES ('宝马520', '鲁A12345', '2018-05-20', '在租', '400', '10000');

-- ----------------------------
-- Table structure for `tcinfor`
-- ----------------------------
DROP TABLE IF EXISTS `tcinfor`;
CREATE TABLE `tcinfor` (
  `CarID` char(10) NOT NULL,
  `IDnumber` int(20) NOT NULL,
  `Mode` char(10) NOT NULL,
  `Hctime` date DEFAULT NULL,
  `Tctime` date DEFAULT NULL,
  `Rent` int(10) DEFAULT NULL,
  `Deposit` int(5) DEFAULT NULL,
  PRIMARY KEY (`CarID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tcinfor
-- ----------------------------
INSERT INTO `tcinfor` VALUES ('鲁A12345', '46000', '日租', '2018-08-21', '2018-08-20', '400', '10000');
INSERT INTO `tcinfor` VALUES ('鲁B12345', '46001', '月租', '2018-09-21', '2018-08-21', '3500', '20000');
INSERT INTO `tcinfor` VALUES ('鲁C12345', '46002', '月租', '2018-09-22', '2018-08-22', '2000', '5000');
INSERT INTO `tcinfor` VALUES ('鲁D12345', '46000', '日租', '2018-08-21', '2018-08-20', '400', '10000');

-- ----------------------------
-- Table structure for `yyinfor`
-- ----------------------------
DROP TABLE IF EXISTS `yyinfor`;
CREATE TABLE `yyinfor` (
  `Cartype` char(10) NOT NULL,
  `IDnumber` int(20) NOT NULL,
  `Name` char(10) NOT NULL,
  `Yytime` date DEFAULT NULL,
  `Tctime` date DEFAULT NULL,
  `Hctime` date DEFAULT NULL,
  PRIMARY KEY (`Cartype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yyinfor
-- ----------------------------
INSERT INTO `yyinfor` VALUES ('奔驰', '46004', '凤', '2019-01-11', '2019-01-11', '2019-01-11');
INSERT INTO `yyinfor` VALUES ('奥迪A6', '46001', '宋幕', '2018-08-11', '2018-08-21', '2018-09-21');
INSERT INTO `yyinfor` VALUES ('宝马520', '46000', '黄冉', '2018-08-10', '2018-08-20', '2018-09-20');
INSERT INTO `yyinfor` VALUES ('捷达', '46002', '李牧', '2018-08-12', '2018-08-22', '2018-09-22');
INSERT INTO `yyinfor` VALUES ('马自达', '46003', '三', '2018-08-11', '2018-08-21', '2018-09-21');

-- ----------------------------
-- Procedure structure for `quickly`
-- ----------------------------
DROP PROCEDURE IF EXISTS `quickly`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `quickly`(
IN Cartype varchar(20),IN IDnumber varchar(20),IN Name varchar(20))
begin
insert into yyinfor value(Cartype,IDnumber,Name,now(),now(),now());
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `t2`;
DELIMITER ;;
CREATE TRIGGER `t2` AFTER INSERT ON `tcinfor` FOR EACH ROW UPDATE Car SET Statete='在租'
where Car.CarID=new.CarID
;;
DELIMITER ;
