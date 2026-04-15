/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : myku

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-01-11 11:04:00
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `pwtable`
-- ----------------------------
DROP TABLE IF EXISTS `pwtable`;
CREATE TABLE `pwtable` (
  `UserName` char(8) NOT NULL,
  `PassWord` char(6) NOT NULL,
  PRIMARY KEY (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pwtable
-- ----------------------------
INSERT INTO `pwtable` VALUES ('12345676', '124578');
INSERT INTO `pwtable` VALUES ('12345677', '123123');
INSERT INTO `pwtable` VALUES ('12345678', '123456');
INSERT INTO `pwtable` VALUES ('12345679', '111111');
INSERT INTO `pwtable` VALUES ('12345688', '154685');
INSERT INTO `pwtable` VALUES ('12345789', '124596');
INSERT INTO `pwtable` VALUES ('12345896', '222222');
INSERT INTO `pwtable` VALUES ('12368957', '254896');
INSERT INTO `pwtable` VALUES ('15987456', '123698');

-- ----------------------------
-- Table structure for `usertable`
-- ----------------------------
DROP TABLE IF EXISTS `usertable`;
CREATE TABLE `usertable` (
  `UserName` char(8) NOT NULL,
  `RealName` varchar(4) NOT NULL,
  `Sex` char(1) NOT NULL,
  `IdCard` char(18) NOT NULL,
  `UserRole` varchar(5) NOT NULL,
  `PassWord` char(6) NOT NULL,
  PRIMARY KEY (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usertable
-- ----------------------------
INSERT INTO `usertable` VALUES ('12345676', '刘六', '女', '456789456123565478', '经理', '123456');
INSERT INTO `usertable` VALUES ('12345677', '王五', '男', '123456789456123654', '业务管理员', '123123');
INSERT INTO `usertable` VALUES ('12345678', '张三', '男', '236236456145678945', '经理', '123456');
INSERT INTO `usertable` VALUES ('12345688', '侯九', '女', '145698748965124895', '普通业务员', '154685');
INSERT INTO `usertable` VALUES ('12345789', '马七', '男', '123457894612453689', '普通业务员', '124596');
INSERT INTO `usertable` VALUES ('12345896', '李一', '男', '145696325489658789', '普通业务员', '222222');
INSERT INTO `usertable` VALUES ('12368957', '马大姐', '女', '125987895648957896', '普通业务员', '254896');
INSERT INTO `usertable` VALUES ('12369789', '柳八', '男', '124578895632489678', '普通业务员', '456789');
INSERT INTO `usertable` VALUES ('15987456', '万户', '男', '154987894562315894', '普通业务员', '123698');

-- ----------------------------
-- View structure for `y_h`
-- ----------------------------
DROP VIEW IF EXISTS `y_h`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `y_h` AS select `usertable`.`UserName` AS `UserName`,`usertable`.`RealName` AS `RealName`,`usertable`.`Sex` AS `Sex`,`usertable`.`IdCard` AS `IdCard`,`usertable`.`UserRole` AS `UserRole`,`usertable`.`PassWord` AS `PassWord` from `usertable`;

-- ----------------------------
-- Procedure structure for `Delete_User`
-- ----------------------------
DROP PROCEDURE IF EXISTS `Delete_User`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Delete_User`(in du1 varchar(100))
BEGIN
delete from UserTable where UserName=du1;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `Insert_User`
-- ----------------------------
DROP PROCEDURE IF EXISTS `Insert_User`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Insert_User`(in qw VARCHAR(100),in ad VARCHAR(100),in zx VARCHAR(100),in ef VARCHAR(100),in cv VARCHAR(100),in fb VARCHAR(100))
BEGIN
insert 
 into UserTable(UserName,RealName,Sex,IdCard,UserRole,PassWord)
values (qw,ad,zx,ef,cv,fb);
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `Update_PassWord`
-- ----------------------------
DROP PROCEDURE IF EXISTS `Update_PassWord`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Update_PassWord`(in we VARCHAR(100),in ed VARCHAR(100))
BEGIN
update PwTable
set PassWord=ed
where UserName=we;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `Update_Role`
-- ----------------------------
DROP PROCEDURE IF EXISTS `Update_Role`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Update_Role`(in er VARCHAR(100),in ol VARCHAR(100))
BEGIN
update UserTable
set UserRole=ol
where UserName=er;
END
;;
DELIMITER ;
DELIMITER ;;
CREATE TRIGGER `t1` AFTER UPDATE ON `pwtable` FOR EACH ROW begin 
     update UserTable
     Set PassWord=new.PassWord
     Where UserName=new.UserName;
End
;;
DELIMITER ;
