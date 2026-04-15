/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50152
Source Host           : localhost:3306
Source Database       : dbcar

Target Server Type    : MYSQL
Target Server Version : 50152
File Encoding         : 65001

Date: 2019-01-11 16:28:25
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `carinfo`
-- ----------------------------
DROP TABLE IF EXISTS `carinfo`;
CREATE TABLE `carinfo` (
  `Car_Id` char(10) NOT NULL DEFAULT '',
  `Car_Type` char(10) NOT NULL,
  `Car_State` char(5) NOT NULL,
  `Buytime` date NOT NULL,
  PRIMARY KEY (`Car_Id`),
  KEY `Car_Type` (`Car_Type`),
  CONSTRAINT `carinfo_ibfk_1` FOREIGN KEY (`Car_Type`) REFERENCES `cartype` (`Car_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carinfo
-- ----------------------------
INSERT INTO `carinfo` VALUES ('鲁A000', '五菱宏光', '空闲', '2018-08-08');
INSERT INTO `carinfo` VALUES ('鲁A111', '别克凯越', '在租', '2018-08-08');
INSERT INTO `carinfo` VALUES ('鲁A123', '丰田威驰', '空闲', '2018-08-08');
INSERT INTO `carinfo` VALUES ('鲁A1234', '宝马250', '在租', '2018-08-08');
INSERT INTO `carinfo` VALUES ('鲁A2222', '吉利帝豪', '在租', '2018-08-08');
INSERT INTO `carinfo` VALUES ('鲁A2311', '奇瑞风云', '空闲', '2019-01-11');
INSERT INTO `carinfo` VALUES ('鲁A2334', '宝马250', '空闲', '2018-08-08');
INSERT INTO `carinfo` VALUES ('鲁A3453', '比亚迪F3', '空闲', '2018-08-08');
INSERT INTO `carinfo` VALUES ('鲁A4221', '宝骏310', '空闲', '2018-08-08');
INSERT INTO `carinfo` VALUES ('鲁A4233', '宝马250', '空闲', '2019-01-11');
INSERT INTO `carinfo` VALUES ('鲁A4444', '大众POLO', '空闲', '2018-08-08');
INSERT INTO `carinfo` VALUES ('鲁A55111', '奇瑞风云', '空闲', '2019-01-11');
INSERT INTO `carinfo` VALUES ('鲁A6345', '长安悦翔', '空闲', '2018-08-08');
INSERT INTO `carinfo` VALUES ('鲁A6666', '大众捷达', '空闲', '2018-08-08');
INSERT INTO `carinfo` VALUES ('鲁A88811', '奇瑞风云', '空闲', '2019-01-11');
INSERT INTO `carinfo` VALUES ('鲁A8888', '奔腾B30', '空闲', '2018-08-08');
INSERT INTO `carinfo` VALUES ('鲁A9865', '长安悦翔', '空闲', '2018-08-08');

-- ----------------------------
-- Table structure for `cartype`
-- ----------------------------
DROP TABLE IF EXISTS `cartype`;
CREATE TABLE `cartype` (
  `Car_type` char(10) NOT NULL,
  `D_rent` int(5) NOT NULL,
  `M_rent` int(10) NOT NULL,
  `car_count` int(2) DEFAULT NULL,
  PRIMARY KEY (`Car_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cartype
-- ----------------------------
INSERT INTO `cartype` VALUES ('丰田威驰', '280', '5000', '1');
INSERT INTO `cartype` VALUES ('五菱宏光', '200', '5000', '1');
INSERT INTO `cartype` VALUES ('别克凯越', '280', '5000', '1');
INSERT INTO `cartype` VALUES ('吉利帝豪', '280', '5000', '1');
INSERT INTO `cartype` VALUES ('大众POLO', '280', '5000', '1');
INSERT INTO `cartype` VALUES ('大众捷达', '280', '5000', '1');
INSERT INTO `cartype` VALUES ('奇瑞风云', '300', '5500', '3');
INSERT INTO `cartype` VALUES ('奔腾B30', '280', '5000', '1');
INSERT INTO `cartype` VALUES ('宝马250', '300', '4000', '3');
INSERT INTO `cartype` VALUES ('宝骏310', '280', '5000', '1');
INSERT INTO `cartype` VALUES ('比亚迪', '200', '5899', '1');
INSERT INTO `cartype` VALUES ('比亚迪F3', '250', '6000', '1');
INSERT INTO `cartype` VALUES ('长安悦翔', '280', '5000', '2');

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `C_id` char(20) NOT NULL,
  `C_name` char(8) NOT NULL,
  `C_dept` char(20) DEFAULT NULL,
  `C_phone` char(11) NOT NULL,
  `C_firstTime` date NOT NULL,
  PRIMARY KEY (`C_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('222222', '王往往', '杀手锏', '1231', '2019-01-09');
INSERT INTO `customer` VALUES ('323332188601032131', '小字', '山东体育学院', '17865139999', '2019-01-10');
INSERT INTO `customer` VALUES ('370522', '小刚', '山东', '17827', '2019-01-10');
INSERT INTO `customer` VALUES ('37052218860101233', '小刚', '济南', '17865139999', '2019-01-10');
INSERT INTO `customer` VALUES ('37052218860103131', '小红', '淄博', '17865139999', '2019-01-10');
INSERT INTO `customer` VALUES ('370522188601032131', '小率', '菏泽', '17865139999', '2019-01-10');
INSERT INTO `customer` VALUES ('37052218860103231', '小兰', '东南', '17865139999', '2019-01-10');
INSERT INTO `customer` VALUES ('413332188601032131', '小请', '西北', '17865139999', '2019-01-10');
INSERT INTO `customer` VALUES ('42321188601032131', '小金', '山东建筑大学', '17865139999', '2019-01-10');
INSERT INTO `customer` VALUES ('43222188601032131', '小黄', '文员', '17865139999', '2019-01-10');

-- ----------------------------
-- Table structure for `huanche`
-- ----------------------------
DROP TABLE IF EXISTS `huanche`;
CREATE TABLE `huanche` (
  `Car_id` char(10) NOT NULL,
  `Cus_id` char(20) NOT NULL,
  `huanchetime` date NOT NULL,
  PRIMARY KEY (`Car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of huanche
-- ----------------------------

-- ----------------------------
-- Table structure for `orderinfo`
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `C_id` char(20) NOT NULL,
  `Car_Type` char(10) NOT NULL,
  `Order_time` date NOT NULL,
  `Tiche_time` date NOT NULL,
  `Huanche_time` date NOT NULL,
  PRIMARY KEY (`C_id`,`Car_Type`),
  KEY `Car_Type` (`Car_Type`),
  CONSTRAINT `orderinfo_ibfk_1` FOREIGN KEY (`C_id`) REFERENCES `customer` (`C_id`),
  CONSTRAINT `orderinfo_ibfk_2` FOREIGN KEY (`Car_Type`) REFERENCES `cartype` (`Car_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `tiche`
-- ----------------------------
DROP TABLE IF EXISTS `tiche`;
CREATE TABLE `tiche` (
  `Car_Id` char(10) NOT NULL,
  `C_id` char(20) NOT NULL,
  `tichetime` date NOT NULL,
  `huanchetime` date NOT NULL,
  PRIMARY KEY (`Car_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiche
-- ----------------------------

-- ----------------------------
-- View structure for `car_view`
-- ----------------------------
DROP VIEW IF EXISTS `car_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `car_view` AS select `carinfo`.`Car_Id` AS `Car_Id`,`carinfo`.`Car_Type` AS `Car_Type`,`carinfo`.`Car_State` AS `Car_State`,`cartype`.`M_rent` AS `M_rent`,`cartype`.`D_rent` AS `D_rent` from (`carinfo` join `cartype`) where (`carinfo`.`Car_Type` = `cartype`.`Car_type`);

-- ----------------------------
-- Procedure structure for `selectKongxiancar`
-- ----------------------------
DROP PROCEDURE IF EXISTS `selectKongxiancar`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectKongxiancar`(IN car_ttype CHAR(10))
BEGIN
SELECT carinfo.Car_Id,carinfo.Car_Type,carinfo.Car_State,
carinfo.Buytime,cartype.M_rent,cartype.D_rent 
FROM carinfo ,cartype
WHERE carinfo.Car_Type=cartype.Car_type 
and carinfo.Car_State='空闲' AND carinfo.Car_Type=car_ttype
; 
END
;;
DELIMITER ;
DELIMITER ;;
CREATE TRIGGER `jscount` AFTER INSERT ON `carinfo` FOR EACH ROW UPDATE cartype SET car_count=car_count+1
where cartype.Car_type=new.Car_type
;;
DELIMITER ;
DELIMITER ;;
CREATE TRIGGER `jscount2` AFTER DELETE ON `carinfo` FOR EACH ROW UPDATE cartype SET car_count=car_count-1
where cartype.Car_type=old.Car_type
;;
DELIMITER ;
DELIMITER ;;
CREATE TRIGGER `t2` AFTER INSERT ON `huanche` FOR EACH ROW UPDATE carinfo SET car_state='空闲'
where carinfo.Car_Id=new.Car_Id
;;
DELIMITER ;
DELIMITER ;;
CREATE TRIGGER `t1` AFTER INSERT ON `tiche` FOR EACH ROW UPDATE carinfo SET car_state='在租'
where carinfo.Car_Id=new.Car_Id
;;
DELIMITER ;
