/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50152
Source Host           : localhost:3306
Source Database       : testdb

Target Server Type    : MYSQL
Target Server Version : 50152
File Encoding         : 65001

Date: 2019-12-11 18:35:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classinfo
-- ----------------------------
DROP TABLE IF EXISTS `classinfo`;
CREATE TABLE `classinfo` (
  `className` varchar(20) NOT NULL,
  `specid` varchar(20) DEFAULT NULL,
  `tutor` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`className`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classinfo
-- ----------------------------
INSERT INTO `classinfo` VALUES ('网络171', '002', null);
INSERT INTO `classinfo` VALUES ('网络172', '002', null);
INSERT INTO `classinfo` VALUES ('计科171', '001', null);
INSERT INTO `classinfo` VALUES ('计科172', '001', null);
INSERT INTO `classinfo` VALUES ('软测171', '003', null);
INSERT INTO `classinfo` VALUES ('软测172', '003', null);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cno` int(11) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,
  `credit` int(11) DEFAULT NULL,
  PRIMARY KEY (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '数据库原理及应用', '4');
INSERT INTO `course` VALUES ('2', 'Java Web应用开发', '3');
INSERT INTO `course` VALUES ('3', '数据结构', '4');
INSERT INTO `course` VALUES ('4', '软件工程', '3');
INSERT INTO `course` VALUES ('5', '人工智能', '2');
INSERT INTO `course` VALUES ('6', '大数据', '2');
INSERT INTO `course` VALUES ('7', '软件项目管理', '2');
INSERT INTO `course` VALUES ('8', '软件过程管理', '3');

-- ----------------------------
-- Table structure for idcard
-- ----------------------------
DROP TABLE IF EXISTS `idcard`;
CREATE TABLE `idcard` (
  `IDCard_id` int(11) NOT NULL,
  `cardNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IDCard_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of idcard
-- ----------------------------

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `personId` int(11) NOT NULL,
  `personName` varchar(20) DEFAULT NULL,
  `IDCard_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `stuid` varchar(20) NOT NULL,
  `cno` int(11) NOT NULL,
  `grade` float DEFAULT NULL,
  PRIMARY KEY (`stuid`,`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('4', '1', '0');
INSERT INTO `sc` VALUES ('4', '3', '0');
INSERT INTO `sc` VALUES ('4', '4', '0');
INSERT INTO `sc` VALUES ('4', '5', '0');
INSERT INTO `sc` VALUES ('4', '6', '0');
INSERT INTO `sc` VALUES ('4', '7', '0');
INSERT INTO `sc` VALUES ('4', '8', '0');
INSERT INTO `sc` VALUES ('5', '1', '0');
INSERT INTO `sc` VALUES ('5', '2', '0');

-- ----------------------------
-- Table structure for spec
-- ----------------------------
DROP TABLE IF EXISTS `spec`;
CREATE TABLE `spec` (
  `specid` char(3) NOT NULL,
  `specname` varchar(20) DEFAULT NULL,
  `specmag` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`specid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spec
-- ----------------------------
INSERT INTO `spec` VALUES ('001', '计科', '张三22');
INSERT INTO `spec` VALUES ('002', '网络', '李四');
INSERT INTO `spec` VALUES ('003', '软件', '王五');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stuid` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `className` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`stuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('201707101192', '孙振国', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101193', '修旭蕾', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101194', '郝若晨', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101195', '李晓雯', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101196', '孙一月', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101197', '谭秋金', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101198', '段鲁青', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101199', '刘雪婷', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101200', '董静静', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101201', '徐伟', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101202', '李天翔', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101203', '田海强', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101204', '丰硕', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101205', '刘新天', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101206', '黄楠', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101207', '谢乃金', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101208', '张珊', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101209', '孙旭', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101210', '王书文', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101211', '赵其浩', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101212', '李庄鑫', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101213', '贺天航', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101214', '张善钧', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101215', '杨永义', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101216', '李硕', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101217', '高文浩', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101218', '周俊涛', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101219', '冯明凯', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101220', '周金浩', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101221', '樊迎宾', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101222', '史善力', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101223', '赵粤生', null, '0', null, '软测171');
INSERT INTO `student` VALUES ('201707101224', '肖桦', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101225', '赵美玲', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101226', '巫晓慧', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101227', '杨敏', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101228', '周文', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101229', '张亚男', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101230', '杜培霜', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101231', '王亚萍', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101232', '柴天钰', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101233', '格建华', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101234', '刘伟', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101235', '李智朋', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101236', '王佳木', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101237', '孙海庆', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101238', '薛景山', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101239', '闫金平', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101240', '席皓宇', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101241', '莫天乐', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101242', '杨涵清', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101243', '朱迪', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101244', '唐子铭', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101245', '张志成', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101246', '张宗恒', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101247', '王鑫敬', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101248', '任帅帅', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101249', '侯海旺', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101250', '黄建成', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101251', '吴成东', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101252', '李伟民', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101253', '韩明涛', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101254', '谢俊', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('201707101255', '翟润楠', null, '0', null, '软测172');
INSERT INTO `student` VALUES ('4', '李晨', '男', '21', '1997-12-31', '软测171');
INSERT INTO `student` VALUES ('5', '李娜', '女', '20', null, '软测171');
INSERT INTO `student` VALUES ('6', '张三', '男', '20', null, '软测172');
INSERT INTO `student` VALUES ('7', '刘晨', '男', '19', null, '软测172');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0', '刘晨', '123', '男', '0', null, null);
INSERT INTO `user` VALUES ('1', 'admin', '1', '女', '19', '2019-01-02', '管理员');
INSERT INTO `user` VALUES ('4', '李四', '1', '男', '19', '2019-01-02', '管理员');
INSERT INTO `user` VALUES ('7', '张三', '1', '男', '20', null, '管理员');
INSERT INTO `user` VALUES ('8', '张三三', '1', '男', '20', '2019-01-02', '管理员');
INSERT INTO `user` VALUES ('10', '李晨', '1', '男', '20', '2019-01-02', '管理员');
INSERT INTO `user` VALUES ('13', '李四', '1', '男', '0', '2019-01-02', '管理员');
INSERT INTO `user` VALUES ('23', '2', '1', '男', '20', '2019-01-02', '管理员');
INSERT INTO `user` VALUES ('33', '4', '1', null, null, null, '学生');
INSERT INTO `user` VALUES ('34', '5', '1', null, null, null, '学生');
INSERT INTO `user` VALUES ('35', '6', '1', null, null, null, '学生');
INSERT INTO `user` VALUES ('36', '7', '1', null, null, null, '学生');
