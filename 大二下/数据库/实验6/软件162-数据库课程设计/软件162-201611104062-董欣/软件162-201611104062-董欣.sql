/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50152
 Source Host           : localhost:3306
 Source Schema         : dbcar

 Target Server Type    : MySQL
 Target Server Version : 50152
 File Encoding         : 65001

 Date: 11/01/2019 11:36:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hcinfor
-- ----------------------------
DROP TABLE IF EXISTS `hcinfor`;
CREATE TABLE `hcinfor`  (
  `IDnumber` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Hctime` date NULL DEFAULT NULL,
  `Rent` int(10) NOT NULL,
  PRIMARY KEY (`IDnumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hcinfor
-- ----------------------------
INSERT INTO `hcinfor` VALUES ('20161110', '2019-01-10', 3000);
INSERT INTO `hcinfor` VALUES ('20161111', '2019-01-11', 3000);
INSERT INTO `hcinfor` VALUES ('20161112', '2019-01-12', 3000);
INSERT INTO `hcinfor` VALUES ('20161113', '2019-01-13', 4000);
INSERT INTO `hcinfor` VALUES ('20161114', '2019-01-14', 4000);
INSERT INTO `hcinfor` VALUES ('20161115', '2019-01-15', 5000);
INSERT INTO `hcinfor` VALUES ('20161116', '2019-01-16', 6000);

-- ----------------------------
-- Table structure for money
-- ----------------------------
DROP TABLE IF EXISTS `money`;
CREATE TABLE `money`  (
  `RentID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Tctime` datetime NOT NULL,
  `Hctime` datetime NOT NULL,
  `Cartype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CarID` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Rent` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`RentID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of money
-- ----------------------------
INSERT INTO `money` VALUES (30, '2019-01-01 14:14:58', '2019-01-10 00:00:00', '宝马', '鲁A1234', 3000);
INSERT INTO `money` VALUES (31, '2019-01-02 14:15:23', '2019-01-11 00:00:00', '捷达', '鲁A2345', 3000);
INSERT INTO `money` VALUES (32, '2019-01-03 14:15:55', '2019-01-12 00:00:00', '奔驰', '鲁A3456', 3000);
INSERT INTO `money` VALUES (33, '2019-01-05 14:16:21', '2019-01-13 00:00:00', '马自达', '鲁A4567', 4000);
INSERT INTO `money` VALUES (34, '2019-01-06 14:17:08', '2019-01-14 00:00:00', '大众', '鲁A5678', 4000);
INSERT INTO `money` VALUES (35, '2019-01-07 19:24:09', '2019-01-15 00:00:00', '奔驰', '鲁A6789', 5000);
INSERT INTO `money` VALUES (36, '2019-01-08 08:11:30', '2019-01-16 00:00:00', '宝马', '鲁A1123', 6000);

-- ----------------------------
-- Table structure for tcinfor
-- ----------------------------
DROP TABLE IF EXISTS `tcinfor`;
CREATE TABLE `tcinfor`  (
  `IDnumber` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `CarID` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Cartype` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Tctime` datetime NOT NULL,
  `Hctime` datetime NOT NULL,
  `SHctime` datetime NULL DEFAULT NULL,
  `Rent` int(15) NULL DEFAULT NULL,
  PRIMARY KEY (`IDnumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tcinfor
-- ----------------------------
INSERT INTO `tcinfor` VALUES ('20161110', '鲁A1234', '宝马', '2019-01-01 14:14:58', '2019-01-02 14:15:02', '2019-01-10 00:00:00', 3000);
INSERT INTO `tcinfor` VALUES ('20161111', '鲁A2345', '捷达', '2019-01-02 14:15:23', '2019-01-03 14:15:28', '2019-01-11 00:00:00', 3000);
INSERT INTO `tcinfor` VALUES ('20161112', '鲁A3456', '奔驰', '2019-01-03 14:15:55', '2019-01-04 14:15:59', '2019-01-12 00:00:00', 3000);
INSERT INTO `tcinfor` VALUES ('20161113', '鲁A4567', '马自达', '2019-01-05 14:16:21', '2019-01-06 14:16:26', '2019-01-13 00:00:00', 4000);
INSERT INTO `tcinfor` VALUES ('20161114', '鲁A5678', '大众', '2019-01-06 14:17:08', '2019-01-07 14:17:20', '2019-01-14 00:00:00', 4000);
INSERT INTO `tcinfor` VALUES ('20161115', '鲁A6789', '奔驰', '2019-01-07 19:24:09', '2019-01-08 19:24:15', '2019-01-15 00:00:00', 5000);
INSERT INTO `tcinfor` VALUES ('20161116', '鲁A1123', '宝马', '2019-01-08 08:11:30', '2019-01-09 08:11:55', '2019-01-16 00:00:00', 6000);

-- ----------------------------
-- Procedure structure for pc
-- ----------------------------
DROP PROCEDURE IF EXISTS `pc`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pc`(in p_pc1 varchar(100),in p_pc2 varchar(100))
begin
select Cartype 车辆类型,CarID 车牌号,Rent 租金,"" as 合计 from money where Tctime>=p_pc1 and Hctime<=p_pc2 union select "","","",sum(Rent) from money where Tctime>=p_pc1 and Hctime<=p_pc2;
End
;;
delimiter ;

-- ----------------------------
-- Procedure structure for pc1
-- ----------------------------
DROP PROCEDURE IF EXISTS `pc1`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pc1`(in p_pc1 varchar(100),in p_pc2 varchar(100),in p_pc3 int)
begin
insert into hcinfor (IDnumber,Hctime,Rent) values (p_pc1,p_pc2,p_pc3);
End
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table hcinfor
-- ----------------------------
DROP TRIGGER IF EXISTS `tg1`;
delimiter ;;
CREATE TRIGGER `tg1` AFTER INSERT ON `hcinfor` FOR EACH ROW update tcinfor set SHctime=new.Hctime,Rent=new.Rent where IDnumber=new.IDnumber
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table tcinfor
-- ----------------------------
DROP TRIGGER IF EXISTS `tg2`;
delimiter ;;
CREATE TRIGGER `tg2` AFTER UPDATE ON `tcinfor` FOR EACH ROW insert into money (Tctime,Hctime,Cartype,CarID,Rent) values (new.Tctime,new.SHctime,new.Cartype,new.CarID,new.Rent)
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
