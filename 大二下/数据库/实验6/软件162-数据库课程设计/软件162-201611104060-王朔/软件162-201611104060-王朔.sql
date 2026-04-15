/*
Navicat MySQL Data Transfer

Source Server         : localhost:3306
Source Server Version : 50518
Source Host           : localhost:3306
Source Database       : 企业仓库

Target Server Type    : MYSQL
Target Server Version : 50518
File Encoding         : 65001

Date: 2019-01-11 11:32:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cangku`
-- ----------------------------
DROP TABLE IF EXISTS `cangku`;
CREATE TABLE `cangku` (
  `CID` varchar(30) NOT NULL DEFAULT '',
  `CName` varchar(30) NOT NULL,
  `CNumber` varchar(30) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cangku
-- ----------------------------
INSERT INTO `cangku` VALUES ('cangku001', '仓库A', '-1');
INSERT INTO `cangku` VALUES ('cangku002', '仓库B', '2');
INSERT INTO `cangku` VALUES ('cangku003', '仓库C', '1');

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `DID` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `DName` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `DManager` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`DID`),
  KEY `DManager` (`DManager`),
  CONSTRAINT `DManager` FOREIGN KEY (`DManager`) REFERENCES `user` (`UID`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('depart002', '部门B', 'yonghu002');
INSERT INTO `department` VALUES ('depart003', '部门C', 'yonghu003');
INSERT INTO `department` VALUES ('depart004', '部门D', 'yonghu003');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `GID` varchar(30) CHARACTER SET utf8 NOT NULL,
  `GName` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `GSize` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `GTime` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `GSupply` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`GID`),
  KEY `GSize` (`GSize`),
  KEY `GSupply` (`GSupply`),
  CONSTRAINT `GSupply` FOREIGN KEY (`GSupply`) REFERENCES `supply` (`SID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `GSize` FOREIGN KEY (`GSize`) REFERENCES `cangku` (`CID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('goods001', '货物A', 'cangku003', '1月8日', 'supply002');
INSERT INTO `goods` VALUES ('goods003', '货物A', 'cangku002', '1.4', 'supply001');
INSERT INTO `goods` VALUES ('goods006', '货物A', 'cangku003', '1.4', 'supply001');
INSERT INTO `goods` VALUES ('huowu006', '货物A', 'cangku002', '1.4', 'supply001');
INSERT INTO `goods` VALUES ('huowu009', '货物A', 'cangku002', '1.4', 'supply001');

-- ----------------------------
-- Table structure for `hetong`
-- ----------------------------
DROP TABLE IF EXISTS `hetong`;
CREATE TABLE `hetong` (
  `HID` varchar(30) NOT NULL DEFAULT '',
  `HName` varchar(30) DEFAULT NULL,
  `HMagager` varchar(30) DEFAULT NULL,
  `SID` varchar(30) DEFAULT NULL,
  `GID` varchar(30) DEFAULT NULL,
  `GName` varchar(30) DEFAULT NULL,
  `SManager` varchar(30) DEFAULT NULL,
  `HTime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`HID`),
  KEY `SID` (`SID`),
  KEY `GID` (`GID`),
  CONSTRAINT `SID` FOREIGN KEY (`SID`) REFERENCES `supply` (`SID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `GID` FOREIGN KEY (`GID`) REFERENCES `goods` (`GID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hetong
-- ----------------------------
INSERT INTO `hetong` VALUES ('hetong001', '合同A', '张三', 'supply001', 'goods001', '货物A', '李四', '1月6号');
INSERT INTO `hetong` VALUES ('hetong002', '合同B', '李四', 'supply004', 'goods003', '货物B', '李四', '1月6号');

-- ----------------------------
-- Table structure for `supply`
-- ----------------------------
DROP TABLE IF EXISTS `supply`;
CREATE TABLE `supply` (
  `SID` varchar(30) NOT NULL,
  `SName` varchar(30) DEFAULT NULL,
  `SAdd` varchar(30) DEFAULT NULL,
  `SManager` varchar(30) DEFAULT NULL,
  `STel` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`SID`),
  KEY `SManager` (`SManager`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supply
-- ----------------------------
INSERT INTO `supply` VALUES ('supply001', '供应商A', '泺源大街', '张三', '12312312312');
INSERT INTO `supply` VALUES ('supply002', '供应商B', '山东建筑大学', '张三2', '12312314');
INSERT INTO `supply` VALUES ('supply004', '供应商D', '山东建筑大学', '张三4', '213122231');
INSERT INTO `supply` VALUES ('supply005', '供应商E', '复旦大学', '张三5', '1312132');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UID` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `UPassword` varchar(30) DEFAULT NULL,
  `UName` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `UAge` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `USex` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `DID` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`UID`),
  KEY `DID` (`DID`),
  CONSTRAINT `DID` FOREIGN KEY (`DID`) REFERENCES `department` (`DID`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('yonghu002', '123', '李四', '40', '女', 'depart002');
INSERT INTO `user` VALUES ('yonghu003', '123', '王五', '30', '男', 'depart003');
INSERT INTO `user` VALUES ('yonghu004', '123', '张四', '90', '男', 'depart002');
INSERT INTO `user` VALUES ('yonghu008', '123', '于八', '80', '男', 'depart002');

-- ----------------------------
-- View structure for `shitu`
-- ----------------------------
DROP VIEW IF EXISTS `shitu`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `shitu` AS select `hetong`.`HName` AS `HName`,`goods`.`GID` AS `GID`,`goods`.`GSize` AS `GSize`,`goods`.`GSupply` AS `GSupply`,`hetong`.`HMagager` AS `HMagager` from (`hetong` join `goods` on((`goods`.`GID` = `hetong`.`GID`))) ;
DROP TRIGGER IF EXISTS `入库`;
DELIMITER ;;
CREATE TRIGGER `入库` BEFORE INSERT ON `goods` FOR EACH ROW update cangku
set CNumber=CNumber+1 where CID=new.GSize
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `出库`;
DELIMITER ;;
CREATE TRIGGER `出库` BEFORE DELETE ON `goods` FOR EACH ROW update cangku
set CNumber=CNumber-1 where CID=old.GSize
;;
DELIMITER ;
