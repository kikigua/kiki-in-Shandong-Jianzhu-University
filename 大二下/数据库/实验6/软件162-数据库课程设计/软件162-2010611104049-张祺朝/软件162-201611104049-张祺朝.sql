/*
Navicat MySQL Data Transfer

Source Server         : 企业仓库
Source Server Version : 50518
Source Host           : localhost:3306
Source Database       : 企业仓库

Target Server Type    : MYSQL
Target Server Version : 50518
File Encoding         : 65001

Date: 2019-01-11 11:33:14
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
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `GID` varchar(30) CHARACTER SET utf8 NOT NULL,
  `GName` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `GSize` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `GTime` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `GSupply` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `GZhuangt` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `RID` varchar(30) DEFAULT NULL,
  `HID` varchar(30) DEFAULT NULL,
  `GNumber` varchar(30) DEFAULT NULL,
  `Price` varchar(30) DEFAULT NULL,
  `FNumber` varchar(30) DEFAULT NULL,
  `UID` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`GID`),
  KEY `GSize` (`GSize`),
  KEY `GSupply` (`GSupply`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`GSupply`) REFERENCES `supply` (`SID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `goods_ibfk_2` FOREIGN KEY (`GSize`) REFERENCES `cangku` (`CID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('goods003', '货物A', 'cangku002', '1月4日', 'supply001', '出库', '0000002', '002', '20', '20', '002', 'yonghu001');
INSERT INTO `goods` VALUES ('goods005', '货物D', 'cangku002', '1月3日', 'supply002', '入库', '0000001', '001', '1', '12', '001', 'yonghu002');
INSERT INTO `goods` VALUES ('goods006', '货物A', 'cangku003', '1月4日', 'supply001', '入库', '0000003', '003', '30', '100', '003', 'yonghu003');
INSERT INTO `goods` VALUES ('goods008', '货物R', 'cangku001', '1月8日', 'supply004', '出库', '0000008', '004', '50', '100', '004', 'yonghu003');

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
  CONSTRAINT `hetong_ibfk_1` FOREIGN KEY (`SID`) REFERENCES `supply` (`SID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `hetong_ibfk_2` FOREIGN KEY (`GID`) REFERENCES `goods` (`GID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hetong
-- ----------------------------
INSERT INTO `hetong` VALUES ('', null, null, null, null, null, null, null);

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
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`DID`) REFERENCES `department` (`DID`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('yonghu002', '123', '李四', '40', '女', 'depart002');
INSERT INTO `user` VALUES ('yonghu003', '123', '王五', '30', '男', 'depart003');
INSERT INTO `user` VALUES ('yonghu004', '123', '张四', '90', '男', 'depart002');
INSERT INTO `user` VALUES ('yonghu008', '123', '于八', '80', '男', 'depart002');

-- ----------------------------
-- View structure for `供应商视图`
-- ----------------------------
DROP VIEW IF EXISTS `供应商视图`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `供应商视图` AS select `supply`.`SID` AS `SID`,`supply`.`SName` AS `SName`,`supply`.`SAdd` AS `SAdd`,`goods`.`GID` AS `GID`,`goods`.`GName` AS `GName`,`goods`.`GTime` AS `GTime`,`goods`.`GNumber` AS `GNumber`,`goods`.`Price` AS `Price` from (`supply` join `goods` on((`supply`.`SID` = `goods`.`GSupply`))) ;

-- ----------------------------
-- View structure for `视图查询`
-- ----------------------------
DROP VIEW IF EXISTS `视图查询`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `视图查询` AS select `goods`.`GZhuangt` AS `GZhuangt`,`goods`.`GTime` AS `GTime`,`goods`.`GNumber` AS `GNumber`,`user`.`UName` AS `UName` from (`goods` join `user` on((`user`.`UID` = convert(`goods`.`UID` using utf8)))) ;
DROP TRIGGER IF EXISTS `入库`;
DELIMITER ;;
CREATE TRIGGER `入库` AFTER INSERT ON `goods` FOR EACH ROW update cangku
set CNumber=CNumber+1 where CID=new.GSize and new.GZhuangt='入库'
;;
DELIMITER ;
