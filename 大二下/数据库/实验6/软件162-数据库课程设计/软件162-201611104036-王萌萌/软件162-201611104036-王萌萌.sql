

Source Server         : qqq
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : bysjxt

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001


-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stuid` varchar(11) NOT NULL,
  `sname` varchar(20) NOT NULL,
  `sex` char(2) DEFAULT NULL,
  `age` char(10) DEFAULT NULL,
  `classname` varchar(20) DEFAULT NULL COMMENT '班级名',
  `phone` char(11) DEFAULT NULL,
  `specid` char(3) DEFAULT NULL,
  `tid` char(6) DEFAULT NULL,
  PRIMARY KEY (`stuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `tid` char(6) NOT NULL,
  `tname` varchar(20) NOT NULL,
  `sex` char(2) DEFAULT NULL,
  `tdept` char(3) DEFAULT NULL COMMENT '所属教研室',
  `tpost` char(3) DEFAULT NULL COMMENT '职称',
  `studydirect` varchar(40) DEFAULT NULL COMMENT '研究方向',
  `phone` char(11) DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Table structure for week
-- ----------------------------
DROP TABLE IF EXISTS `week`;
CREATE TABLE `week` (
  `stuid` varchar(11) NOT NULL COMMENT '学号',
  `weekid` int(6) NOT NULL COMMENT '周总结编号',
  `thisweek` varchar(500) DEFAULT NULL COMMENT '本周工作内容',
  `nextweek` varchar(500) DEFAULT NULL COMMENT '下周工作安排',
  `support` varchar(100) DEFAULT NULL COMMENT '需要的支持说明',
  `teacherview` varchar(200) DEFAULT NULL COMMENT '教师评价考核',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`weekid`,`stuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-功能描述和SQL语句
按照专业、教师查询所指导学生所填写的有效周总结数，显示：学号、姓名、有效周总结数
SELECT a.stuid,b.sname,COUNT(a.weekid) sumweek
from student b, week a
WHERE b.stuid = a.stuid and a.stuid='20161110401'

SELECT DISTINCT a.stuid,b.sname,weekid,thisweek,nextweek,
support,teacherview,remark
from student b, week a, class c,specialty d
WHERE b.stuid = a.stuid and a.stuid='20161110401'

SELECT DISTINCT a.stuid,b.sname,weekid,thisweek,nextweek,
support,teacherview,remark
from student b, week a
WHERE b.stuid = a.stuid and b.specid='001'

SELECT DISTINCT a.stuid,b.sname,weekid,thisweek,nextweek,
support,teacherview,remark
from student b, week a
WHERE b.stuid = a.stuid and b.tid='100201'
