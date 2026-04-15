/*
Navicat MySQL Data Transfer

Source Server         : buy
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : buy

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-01-11 13:52:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminId` char(20) NOT NULL,
  `adminName` char(20) NOT NULL,
  `adminPassword` char(20) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '马冬梅', 'mdm666');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodsId` char(20) NOT NULL,
  `goodsName` char(20) NOT NULL,
  `typeId` char(20) DEFAULT NULL,
  `price` float(11,2) DEFAULT NULL,
  `nums` int(11) DEFAULT NULL,
  `imageName` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` char(100) DEFAULT NULL,
  `saleNums` int(11) DEFAULT NULL,
  PRIMARY KEY (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '大蛋糕', '1', '70.00', '10', '蛋糕.png', '甜品', '3');
INSERT INTO `goods` VALUES ('2', '中蛋糕', '1', '50.00', '15', '蛋糕.png', '甜品', '5');
INSERT INTO `goods` VALUES ('3', '小蛋糕', '1', '30.00', '30', '蛋糕.png', '甜品', '15');
INSERT INTO `goods` VALUES ('4', '小蛋糕', '1', '30.00', '5', '蛋糕.png', '甜品', '15');
INSERT INTO `goods` VALUES ('5', '小蛋糕', '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for kc
-- ----------------------------
DROP TABLE IF EXISTS `kc`;
CREATE TABLE `kc` (
  `id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goodsName` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `nums` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kc
-- ----------------------------
INSERT INTO `kc` VALUES ('1', '大蛋糕', '15');
INSERT INTO `kc` VALUES ('2', '中蛋糕', '20');
INSERT INTO `kc` VALUES ('3', '小蛋糕', '36');

-- ----------------------------
-- Table structure for vip
-- ----------------------------
DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip` (
  `vipId` char(20) NOT NULL,
  `vipName` char(20) NOT NULL,
  `vipAddress` varchar(60) DEFAULT NULL,
  `vipPhone` char(20) DEFAULT NULL,
  `vipLevel` char(5) DEFAULT NULL,
  `consumption` float(11,0) DEFAULT NULL,
  PRIMARY KEY (`vipId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vip
-- ----------------------------
INSERT INTO `vip` VALUES ('1', '小明', '济南市', '11223345', '二级', '3000');
INSERT INTO `vip` VALUES ('2', '张三', '济南市', '12345', '一级', '1000');
DROP TRIGGER IF EXISTS `cr`;
DELIMITER ;;
CREATE TRIGGER `cr` AFTER INSERT ON `goods` FOR EACH ROW begin
update KC
set kc.nums=nums+1 WHERE kc.goodsName=new.goodsName;
End
;;
DELIMITER ;
