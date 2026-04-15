创建user表，
CREATE TABLE `user` (
  `account` varchar(12) NOT NULL,
  `type` varchar(10) NOT NULL,
  `password` varchar(11) NOT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


创建teacher表，
CREATE TABLE `teacher` (
  `Tno` int(11) NOT NULL,
  `Tname` varchar(20) NOT NULL,
  `Tpassword` varchar(11) NOT NULL,
  `Tsex` varchar(20) NOT NULL,
  `Ttitle` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Tno`)
)ENGINE=InnoDBDEFAULTCHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

创建student表，
CREATE TABLE `student` (
  `Sno` varchar(12) NOT NULL,
  `Sname` varchar(20) NOT NULL,
  `Spassword` varchar(11) NOT NULL,
  `Ssex` varchar(11) NOT NULL,
  `IDcard` varchar(20) NOT NULL,
  `Cno` varchar(10) NOT NULL,
  PRIMARY KEY (`Sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

创建course表，
CREATE TABLE `course` (
`Kno`  int(11) NOT NULL ,
`Kname`  varchar(20) NOT NULL ,
`credit`  int(11) NOT NULL ,
`Kpre`  varchar(10) NOT NULL ,
`Ktime`  varchar(50) NULL ,
PRIMARY KEY (`Kno`)
)
;

创建attendance表，
CREATE TABLE `attendance` (
`Kno` int(11) NOT NULL,
`Cno` varchar(50) NOT NULL,
`Sno` varchar(20) NOT NULL,
`Sname` varchar(20) NOT NULL,
`Weekno` varchar(20) NOT NULL,
`Ktimes` varchar(10) DEFAULT NULL,
`QJtimes` varchar(50) DEFAULT NULL,
`QJreason` varchar(20) DEFAULT NULL,
 PRIMARY KEY (`Kno`,`Cno`,`Sno`,`Sname`,`Weekno`)
)ENGINE=InnoDBDEFAULTCHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

创建class表，
CREATE TABLE `class` (
  `Cno` varchar(12) NOT NULL,
  `Cname` varchar(20) NOT NULL,
  PRIMARY KEY (`Cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

录入考勤信息
insert into attendance values("1","001","201611104039","9","0","1","感冒");

修改考勤信息
update attendance set Ktimes="2" where Kno="1"and Cno="1"and Weekno="8"and Sname="高" and Sno="201611104039";

按周次查询考勤，
select * from attendance order by 	Weekno;

按课程编号查询考勤，
select * from attendance order by Kno;

创建存储过程，实现查询周次是某个特定周次的所有考勤信息
CREATE PROCEDURE `selectWeekno`(
IN `周次` VARCHAR(50)
)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
select Kno 课程编号,Cno 班级编号,Sno 学号,Weekno 周次,Ktimes 旷课次数,QJtimes 请假次数,QJreason 请假原因
from attendance
where Weekno=周次;
END

创建存储过程，实现查询课程编号是某个特定课程编号的所有考勤信息
CREATE PROCEDURE `selectkno`(
IN `课程编号` VARCHAR(50)
)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
select Kno 课程编号,Cno 班级编号,Sno 学号,Weekno 周次,Ktimes 旷课次数,QJtimes 请假次数,QJreason 请假原因
from attendance
where Kno=课程编号;
END





