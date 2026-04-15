/*
 Navicat MySQL Data Transfer

 Source Server         : sh
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : sh1

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 11/01/2019 14:29:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate`  (
  `EvaluateID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `EvaluateStar` int(5) NULL DEFAULT NULL,
  `EvaluateWord` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `VIPID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `OrderID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `EvaluateState` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CheckState` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`EvaluateID`) USING BTREE,
  INDEX `VIP1`(`VIPID`) USING BTREE,
  INDEX `Order1`(`OrderID`) USING BTREE,
  CONSTRAINT `Order1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`orderid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `VIP1` FOREIGN KEY (`VIPID`) REFERENCES `vip` (`vipid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of evaluate
-- ----------------------------
INSERT INTO `evaluate` VALUES ('001', 5, '好', '001', '001', '不发布', '不通过');
INSERT INTO `evaluate` VALUES ('002', 4, '不错', '002', '002', '不发布', '不通过');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `GoodsID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `GoodsName` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `GoodsType` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Price` float(255, 2) NOT NULL,
  `Introduction` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `GoodsImage` date NULL DEFAULT NULL,
  `Number` int(255) NOT NULL,
  PRIMARY KEY (`GoodsID`) USING BTREE,
  INDEX `GoodsID`(`GoodsID`) USING BTREE,
  INDEX `GoodsID_2`(`GoodsID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('0001', '充电器', '家电', 10.00, NULL, NULL, 5);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `OrderID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `VIPID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SCID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `OrderDate` date NOT NULL,
  PRIMARY KEY (`OrderID`) USING BTREE,
  INDEX `OrderID`(`OrderID`) USING BTREE,
  INDEX `OrderID_2`(`OrderID`) USING BTREE,
  INDEX `OrderID_3`(`OrderID`) USING BTREE,
  INDEX `OrderID_4`(`OrderID`) USING BTREE,
  INDEX `OrderID_5`(`OrderID`) USING BTREE,
  INDEX `OrderID_6`(`OrderID`) USING BTREE,
  INDEX `OrderID_7`(`OrderID`) USING BTREE,
  INDEX `OrderID_8`(`OrderID`) USING BTREE,
  INDEX `OrderID_9`(`OrderID`) USING BTREE,
  INDEX `OrderID_10`(`OrderID`) USING BTREE,
  INDEX `OrderID_11`(`OrderID`) USING BTREE,
  INDEX `OrderID_12`(`OrderID`) USING BTREE,
  INDEX `OrderID_13`(`OrderID`) USING BTREE,
  INDEX `OrderID_14`(`OrderID`) USING BTREE,
  INDEX `OrderID_15`(`OrderID`) USING BTREE,
  INDEX `OrderID_16`(`OrderID`) USING BTREE,
  INDEX `OrderID_17`(`OrderID`) USING BTREE,
  INDEX `OrderID_18`(`OrderID`) USING BTREE,
  INDEX `OrderID_19`(`OrderID`) USING BTREE,
  INDEX `OrderID_20`(`OrderID`) USING BTREE,
  INDEX `OrderID_21`(`OrderID`) USING BTREE,
  INDEX `OrderID_22`(`OrderID`) USING BTREE,
  INDEX `OrderID_23`(`OrderID`) USING BTREE,
  INDEX `OrderID_24`(`OrderID`) USING BTREE,
  INDEX `OrderID_25`(`OrderID`) USING BTREE,
  INDEX `OrderID_26`(`OrderID`) USING BTREE,
  INDEX `OrderID_27`(`OrderID`) USING BTREE,
  INDEX `OrderID_28`(`OrderID`) USING BTREE,
  INDEX `OrderID_29`(`OrderID`) USING BTREE,
  INDEX `OrderID_30`(`OrderID`) USING BTREE,
  INDEX `OrderID_31`(`OrderID`) USING BTREE,
  INDEX `OrderID_32`(`OrderID`) USING BTREE,
  INDEX `OrderID_33`(`OrderID`) USING BTREE,
  INDEX `OrderID_34`(`OrderID`) USING BTREE,
  INDEX `OrderID_35`(`OrderID`) USING BTREE,
  INDEX `OrderID_36`(`OrderID`) USING BTREE,
  INDEX `OrderID_37`(`OrderID`) USING BTREE,
  INDEX `OrderID_38`(`OrderID`) USING BTREE,
  INDEX `OrderID_39`(`OrderID`) USING BTREE,
  INDEX `OrderID_40`(`OrderID`) USING BTREE,
  INDEX `OrderID_41`(`OrderID`) USING BTREE,
  INDEX `OrderID_42`(`OrderID`) USING BTREE,
  INDEX `OrderID_43`(`OrderID`) USING BTREE,
  INDEX `OrderID_44`(`OrderID`) USING BTREE,
  INDEX `OrderID_45`(`OrderID`) USING BTREE,
  INDEX `OrderID_46`(`OrderID`) USING BTREE,
  INDEX `OrderID_47`(`OrderID`) USING BTREE,
  INDEX `OrderID_48`(`OrderID`) USING BTREE,
  INDEX `OrderID_49`(`OrderID`) USING BTREE,
  INDEX `OrderID_50`(`OrderID`) USING BTREE,
  INDEX `OrderID_51`(`OrderID`) USING BTREE,
  INDEX `OrderID_52`(`OrderID`) USING BTREE,
  INDEX `OrderID_53`(`OrderID`) USING BTREE,
  INDEX `OrderID_54`(`OrderID`) USING BTREE,
  INDEX `OrderID_55`(`OrderID`) USING BTREE,
  INDEX `OrderID_56`(`OrderID`) USING BTREE,
  INDEX `VIP2`(`VIPID`) USING BTREE,
  INDEX `SC1`(`SCID`) USING BTREE,
  CONSTRAINT `SC1` FOREIGN KEY (`SCID`) REFERENCES `shoppingcart` (`scid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `VIP2` FOREIGN KEY (`VIPID`) REFERENCES `vip` (`vipid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('001', '001', '001', '2019-01-11');
INSERT INTO `orders` VALUES ('002', '002', '001', '2019-01-11');
INSERT INTO `orders` VALUES ('003', '003', '001', '2019-01-11');
INSERT INTO `orders` VALUES ('004', '004', '001', '2019-01-11');

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart`  (
  `SCID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `GoodsNumber` int(11) NOT NULL,
  `GoodsID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCID`) USING BTREE,
  INDEX `GoodsID`(`GoodsID`) USING BTREE,
  INDEX `SCID`(`SCID`) USING BTREE,
  CONSTRAINT `GoodsID` FOREIGN KEY (`GoodsID`) REFERENCES `goods` (`goodsid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES ('001', 5, '0001');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userPassword` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`userID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0001', 'zhangsan', '654321');
INSERT INTO `user` VALUES ('0002', 'lisi', '456789');

-- ----------------------------
-- Table structure for vip
-- ----------------------------
DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip`  (
  `VIPID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `VIPName` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `VIPLevel` int(255) NOT NULL,
  `VIPAddress` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `VIPPh` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`VIPID`) USING BTREE,
  INDEX `VIPID`(`VIPID`) USING BTREE,
  INDEX `VIPID_2`(`VIPID`) USING BTREE,
  INDEX `VIPID_3`(`VIPID`) USING BTREE,
  INDEX `VIPID_4`(`VIPID`) USING BTREE,
  INDEX `VIPID_5`(`VIPID`) USING BTREE,
  INDEX `VIPID_6`(`VIPID`) USING BTREE,
  INDEX `VIPID_7`(`VIPID`) USING BTREE,
  INDEX `VIPID_8`(`VIPID`) USING BTREE,
  INDEX `VIPID_9`(`VIPID`) USING BTREE,
  INDEX `VIPID_10`(`VIPID`) USING BTREE,
  INDEX `VIPID_11`(`VIPID`) USING BTREE,
  INDEX `VIPID_12`(`VIPID`) USING BTREE,
  INDEX `VIPID_13`(`VIPID`) USING BTREE,
  INDEX `VIPID_14`(`VIPID`) USING BTREE,
  INDEX `VIPID_15`(`VIPID`) USING BTREE,
  INDEX `VIPID_16`(`VIPID`) USING BTREE,
  INDEX `VIPID_17`(`VIPID`) USING BTREE,
  INDEX `VIPID_18`(`VIPID`) USING BTREE,
  INDEX `VIPID_19`(`VIPID`) USING BTREE,
  INDEX `VIPID_20`(`VIPID`) USING BTREE,
  INDEX `VIPID_21`(`VIPID`) USING BTREE,
  INDEX `VIPID_22`(`VIPID`) USING BTREE,
  INDEX `VIPID_23`(`VIPID`) USING BTREE,
  INDEX `VIPID_24`(`VIPID`) USING BTREE,
  INDEX `VIPID_25`(`VIPID`) USING BTREE,
  INDEX `VIPID_26`(`VIPID`) USING BTREE,
  INDEX `VIPID_27`(`VIPID`) USING BTREE,
  INDEX `VIPID_28`(`VIPID`) USING BTREE,
  INDEX `VIPID_29`(`VIPID`) USING BTREE,
  INDEX `VIPID_30`(`VIPID`) USING BTREE,
  INDEX `VIPID_31`(`VIPID`) USING BTREE,
  INDEX `VIPID_32`(`VIPID`) USING BTREE,
  INDEX `VIPID_33`(`VIPID`) USING BTREE,
  INDEX `VIPID_34`(`VIPID`) USING BTREE,
  INDEX `VIPID_35`(`VIPID`) USING BTREE,
  INDEX `VIPID_36`(`VIPID`) USING BTREE,
  INDEX `VIPID_37`(`VIPID`) USING BTREE,
  INDEX `VIPID_38`(`VIPID`) USING BTREE,
  INDEX `VIPID_39`(`VIPID`) USING BTREE,
  INDEX `VIPID_40`(`VIPID`) USING BTREE,
  INDEX `VIPID_41`(`VIPID`) USING BTREE,
  INDEX `VIPID_42`(`VIPID`) USING BTREE,
  INDEX `VIPID_43`(`VIPID`) USING BTREE,
  INDEX `VIPID_44`(`VIPID`) USING BTREE,
  INDEX `VIPID_45`(`VIPID`) USING BTREE,
  INDEX `VIPID_46`(`VIPID`) USING BTREE,
  INDEX `VIPID_47`(`VIPID`) USING BTREE,
  INDEX `VIPID_48`(`VIPID`) USING BTREE,
  INDEX `VIPID_49`(`VIPID`) USING BTREE,
  INDEX `VIPID_50`(`VIPID`) USING BTREE,
  INDEX `VIPID_51`(`VIPID`) USING BTREE,
  INDEX `VIPID_52`(`VIPID`) USING BTREE,
  INDEX `VIPID_53`(`VIPID`) USING BTREE,
  INDEX `VIPID_54`(`VIPID`) USING BTREE,
  INDEX `VIPID_55`(`VIPID`) USING BTREE,
  INDEX `VIPID_56`(`VIPID`) USING BTREE,
  INDEX `VIPID_57`(`VIPID`) USING BTREE,
  INDEX `VIPID_58`(`VIPID`) USING BTREE,
  INDEX `VIPID_59`(`VIPID`) USING BTREE,
  INDEX `VIPID_60`(`VIPID`) USING BTREE,
  INDEX `VIPID_61`(`VIPID`) USING BTREE,
  INDEX `VIPID_62`(`VIPID`) USING BTREE,
  INDEX `VIPID_63`(`VIPID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vip
-- ----------------------------
INSERT INTO `vip` VALUES ('001', '张三', 3, '天桥', NULL);
INSERT INTO `vip` VALUES ('002', '李四', 2, '海南', NULL);
INSERT INTO `vip` VALUES ('003', '王五', 4, '北京', NULL);
INSERT INTO `vip` VALUES ('004', '武大', 1, '山东', NULL);

-- ----------------------------
-- Procedure structure for CP
-- ----------------------------
DROP PROCEDURE IF EXISTS `CP`;
delimiter ;;
CREATE PROCEDURE `CP`(IN U_ID CHAR(20))
BEGIN
	UPDATE USER
	SET userPassword='123456'
	WHERE userID=U_ID;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table evaluate
-- ----------------------------
DROP TRIGGER IF EXISTS `CE`;
delimiter ;;
CREATE TRIGGER `CE` BEFORE UPDATE ON `evaluate` FOR EACH ROW BEGIN
	IF NEW.CheckState = '通过' THEN
	set NEW.EvaluateState = '发布';
	END IF;
	IF NEW.CheckState = '未通过' THEN
	set NEW.EvaluateState = '不发布';
	END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
