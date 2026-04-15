/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50523
Source Host           : localhost:3306
Source Database       : bysj

Target Server Type    : MYSQL
Target Server Version : 50523
File Encoding         : 65001

Date: 2019-01-11 11:10:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student_select
-- ----------------------------
DROP TABLE IF EXISTS `student_select`;
CREATE TABLE `student_select` (
  `sno` varchar(11) NOT NULL DEFAULT '' COMMENT '学号',
  `sname` varchar(20) NOT NULL COMMENT '姓名',
  `classroom` varchar(20) NOT NULL COMMENT '班级',
  `specialty` varchar(20) NOT NULL COMMENT '专业',
  `subid` bigint(20) DEFAULT NULL COMMENT '课题编号',
  `subname` varchar(20) DEFAULT NULL COMMENT '课题名',
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_select
-- ----------------------------

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `subid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课题编号',
  `subname` varchar(20) NOT NULL COMMENT '课题名',
  `isoutschool` varchar(5) DEFAULT NULL COMMENT '是否校外',
  `classroom` varchar(20) NOT NULL COMMENT '教研室',
  `tid` varchar(20) NOT NULL COMMENT '教师编号',
  `tname` varchar(20) NOT NULL COMMENT '教师姓名',
  PRIMARY KEY (`subid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------

-- ----------------------------
-- View structure for select_a
-- ----------------------------
DROP VIEW IF EXISTS `select_a`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `select_a` AS SELECT
`subject`.classroom,tid,tname,`subject`.subname,sno,sname
FROM `subject`,student_select ;

-- ----------------------------
-- Procedure structure for swop
-- ----------------------------
DROP PROCEDURE IF EXISTS `swop`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `swop`(in sno_A VARCHAR(11) , in sno_B VARCHAR(11))
BEGIN
DECLARE sno_C varchar(10);
set sno_C = @sno_A;
set sno_A = @sno_B ;
set sno_B = @sno_C;
End
;;
DELIMITER ;
