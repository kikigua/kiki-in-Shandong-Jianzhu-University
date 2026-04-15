/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : shiyan

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-01-11 12:44:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `baoxiu`
-- ----------------------------
DROP TABLE IF EXISTS `baoxiu`;
CREATE TABLE `baoxiu` (
  `bxno` char(5) NOT NULL,
  `Deno` char(2) DEFAULT NULL,
  `Rono` char(3) DEFAULT NULL,
  `szname` char(5) DEFAULT NULL,
  `wpname` char(5) DEFAULT NULL,
  `bxdate` char(15) DEFAULT NULL,
  `maname` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`bxno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of baoxiu
-- ----------------------------
INSERT INTO `baoxiu` VALUES ('10', '12', '663', '王六', '桌椅', '10.16', '王大爷');
INSERT INTO `baoxiu` VALUES ('11', '22', '110', '张三', '电风扇', '1.8', '李大叔');
INSERT INTO `baoxiu` VALUES ('13', '31', '512', '李四', '桌椅', '2.6', '孙大爷');

-- ----------------------------
-- Table structure for `ccxx`
-- ----------------------------
DROP TABLE IF EXISTS `ccxx`;
CREATE TABLE `ccxx` (
  `Deno` char(2) DEFAULT NULL,
  `Rono` char(3) DEFAULT NULL,
  `wpno` char(8) NOT NULL,
  `wpname` char(5) DEFAULT NULL,
  PRIMARY KEY (`wpno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of ccxx
-- ----------------------------
INSERT INTO `ccxx` VALUES ('11', '166', '11151105', '床');
INSERT INTO `ccxx` VALUES ('21', '122', '12112205', '床');
INSERT INTO `ccxx` VALUES ('22', '410', '22241001', '门');
INSERT INTO `ccxx` VALUES ('33', '308', '23330802', '门');

-- ----------------------------
-- Table structure for `kucun`
-- ----------------------------
DROP TABLE IF EXISTS `kucun`;
CREATE TABLE `kucun` (
  `wpname` char(5) NOT NULL,
  `wpsum` int(11) DEFAULT NULL,
  PRIMARY KEY (`wpname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of kucun
-- ----------------------------
INSERT INTO `kucun` VALUES ('卫生', '1100');
INSERT INTO `kucun` VALUES ('桌椅', '5000');
INSERT INTO `kucun` VALUES ('电视', '900');
INSERT INTO `kucun` VALUES ('门', '1000');
INSERT INTO `kucun` VALUES ('风扇', '6000');

-- ----------------------------
-- Table structure for `ruzhu`
-- ----------------------------
DROP TABLE IF EXISTS `ruzhu`;
CREATE TABLE `ruzhu` (
  `Deno` char(2) DEFAULT NULL,
  `Rono` char(3) DEFAULT NULL,
  `Sdept` char(5) DEFAULT NULL,
  `Sclass` char(10) DEFAULT NULL,
  `Sname` char(5) DEFAULT NULL,
  `rztime` char(10) DEFAULT NULL,
  `ydno` char(5) NOT NULL,
  PRIMARY KEY (`ydno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of ruzhu
-- ----------------------------
INSERT INTO `ruzhu` VALUES ('11', '544', '软件', '161', '大乔', '1.9', '1');
INSERT INTO `ruzhu` VALUES ('12', '155', '软测', '164', '张三', '1.10', '2');
INSERT INTO `ruzhu` VALUES ('24', '236', '网络', '163', '李四', '1.9', '3');
INSERT INTO `ruzhu` VALUES ('31', '113', '计科', '163', '王六', '1.11', '4');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Sno` char(10) NOT NULL,
  `Sname` char(5) DEFAULT NULL,
  `Sdept` char(5) DEFAULT NULL,
  `Ssex` char(5) DEFAULT NULL,
  `Sage` char(5) DEFAULT NULL,
  `Sclass` char(10) DEFAULT NULL,
  PRIMARY KEY (`Sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('20161101', '张三', '计科', '男', '20', '164');
INSERT INTO `student` VALUES ('201611018', '大乔', '软测', '女', '20', '164');
INSERT INTO `student` VALUES ('20161104', '小乔', '软件', '女', '19', '163');

-- ----------------------------
-- Table structure for `weixiu`
-- ----------------------------
DROP TABLE IF EXISTS `weixiu`;
CREATE TABLE `weixiu` (
  `wxno` char(5) NOT NULL,
  `wxmo` int(11) DEFAULT NULL,
  `bfmo` int(11) DEFAULT NULL,
  `maname` char(5) DEFAULT NULL,
  PRIMARY KEY (`wxno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of weixiu
-- ----------------------------
INSERT INTO `weixiu` VALUES ('1', '100', '10', '李四');
INSERT INTO `weixiu` VALUES ('2', '50', '30', '王五');
INSERT INTO `weixiu` VALUES ('3', '0', '100', '黑六');

-- ----------------------------
-- Procedure structure for `bxsearch1`
-- ----------------------------
DROP PROCEDURE IF EXISTS `bxsearch1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `bxsearch1`(bxdate char(10))
begin
select * from biaoxiu where bxdate=bxdate;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `delete1`
-- ----------------------------
DROP PROCEDURE IF EXISTS `delete1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete1`(sSNO int(10))
begin
delete from student where Sno=sSno;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `delete2`
-- ----------------------------
DROP PROCEDURE IF EXISTS `delete2`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete2`(bxno char(10))
begin
delete from baoxiu where bxno=bxno;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `delete3`
-- ----------------------------
DROP PROCEDURE IF EXISTS `delete3`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete3`(Rono char(10))
begin
delete from ccxx where Rono=Rono;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `insert1`
-- ----------------------------
DROP PROCEDURE IF EXISTS `insert1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert1`(Sno char(10),Sname char(10),Sdept char(10),Ssex char(10),Sage char(10),Sclass char(10))
BEGIN
insert into sdudent value(Sno ,Sname ,Sdept ,Ssex ,Sage ,Sclass );
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `insert2`
-- ----------------------------
DROP PROCEDURE IF EXISTS `insert2`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert2`(bxno char(10),Deno char(10),Rono char(10),szname char(10),wpname char(10),bxdate char(10),maname char(10))
begin
insert into baoxiu VALUES(bxno,Deno,Rono,szname,wpname,bxdate,maname);
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `insert3`
-- ----------------------------
DROP PROCEDURE IF EXISTS `insert3`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert3`(Deno char(10),Rono char(10),wpno char(10),wpname char(10))
begin
insert into ccxx VALUES(Deno,Rono,wpno,wpname);
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `insert4`
-- ----------------------------
DROP PROCEDURE IF EXISTS `insert4`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert4`(wxno char(10),wxmo char(10),bfmo char(10),maname char(10))
begin
insert into weixiu VALUES(wxno,wxmo,bfmo,maname);
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `search`
-- ----------------------------
DROP PROCEDURE IF EXISTS `search`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `search`(Sanme char(10))
begin
select * from student where Sname like sSname;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `search1`
-- ----------------------------
DROP PROCEDURE IF EXISTS `search1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `search1`(Sdept char(10),Sname char(10))
begin
select * from student where Sdept=Sdept and Sname like Sname;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `update1`
-- ----------------------------
DROP PROCEDURE IF EXISTS `update1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update1`(Sno char(10),Sname char(10),Sdept char(10),Ssex char(10),Sage char(10),Sclass char(10))
begin
update student set Sno=Sno,Sname=Sname,Cno=Cno,Mno=Mno,Rno=Rno,Dno=Dno,Sex=Sex where Sno=Sno;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `update2`
-- ----------------------------
DROP PROCEDURE IF EXISTS `update2`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update2`(Rono char(10),wpno char(10))
begin
update ccxx set Rono=Rono where wpno=wpno;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tg3`;
DELIMITER ;;
CREATE TRIGGER `tg3` AFTER UPDATE ON `ccxx` FOR EACH ROW begin
update kucun set wpsum=wpsum-1 where wpname=ccxx.wpname;
end
;;
DELIMITER ;

\*按报修时间查询   *\
select * 
from baoxiu
where bxdate='1.8';

\*按宿舍号查询   *\
select * 
from baoxiu
where Rono='110';

\*删除报修信息   *\
delete from baoxiu
where bxno='14';

\*财产查询   *\
select wpsum
from kucun
where wpname='门'；

\* 财产信息的删除*\
delete from ccxx
where Rono='154';

\* 财产信息的修改*\
update ccxx
set Rono='166'
where wpno='11151105';

\* 删除学生信息*\
delete from Student
where Sno='20161106';

\*学生表的查询*\
select * from student
where Sdept='软测';
select * from student
where Sclass = '164';

select * from student
where Sname like '小_';

\*住宿查询模糊查询*\
select *
from ruzhu 
where Sname like '%六'
