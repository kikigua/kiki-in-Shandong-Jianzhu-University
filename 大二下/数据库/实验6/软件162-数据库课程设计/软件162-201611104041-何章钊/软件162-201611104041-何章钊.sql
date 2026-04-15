Create database coursedesign;

CREATE TABLE `student` (
  `Sno` int(11) NOT NULL,
  `Sname` varchar(100) NOT NULL,
  `Ssex` varchar(100) DEFAULT NULL,
  `Sid` int(11) DEFAULT NULL,
  `ClassId` varchar(100) DEFAULT NULL,
  `Spassword` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Sno`),
  KEY `fk_classid` (`ClassId`),
  CONSTRAINT `fk_classid` FOREIGN KEY (`ClassId`) REFERENCES `class` (`ClassId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Teacher` (
  `Tno` varchar(100) NOT NULL,
  `Tname` varchar(100) NOT NULL,
  `Tsex` varchar(100) DEFAULT NULL,
  `Tstate` varchar(100) DEFAULT NULL,
  `Tpassword` varchar(100)DEFAULT NULL,
   PRIMARY KEY (`Tno`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `Class` (
  `ClassId` varchar(100) NOT NULL,
  `Cname` varchar(100) NOT NULL,
  PRIMARY KEY (`ClassId`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `Admin` (
  `AId` varchar(100) NOT NULL,`Aname` varchar(100) NOT NULL,
  `Apassword` varchar(100)  DEFAULT NULL,PRIMARY KEY (`AId`)
) DEFAULT CHARSET=utf8;

Insert into Student(Sno,Sname,Ssex,Sid,ClassId,Spassword)values(201611104040,"付成杰","男",370181,"a1","123");
Insert into Student(Sno,Sname,Ssex,Sid,ClassId,Spassword)values(201611104041,"何章钊","男",370181,"a1","456");
Insert into Student(Sno,Sname,Ssex,Sid,ClassId,Spassword)values(201611104039,"高睿","女",370182,"a2","134");


Insert into Class（ClassId，Cname）values（“a1”，"天才班"）；
Insert into Class（ClassId，Cname）values（“a2”，"精英班"）；
Insert into Class（ClassId，Cname）values（“a3”，"普通班"）；

insert into admin(Aid,Aname,Apassword) values("001","小明","abc123");

delimiter $$
create procedure Snofind(in Sno varchar(100))
BEGIN
set Sno=CONCAT('%',Sno,'%');
select * from student where student.Sno like Sno ORDER BY student.Sno;
end $$
delimiter;

update student set Sid=370184 where Sno=201611104041;

delete from student where Sno=201611104040;

Insert into teacher(Tno,Tname,Tsex,Tstate,Tpassword)values("001","小娜","女","组长","abc");
Insert into teacher(Tno,Tname,Tsex,Tstate,Tpassword)values("002","小慧","女","科长","def");
Insert into teacher(Tno,Tname,Tsex,Tstate,Tpassword)values("003","小刚","男","成员","ade");

delimiter $$
create procedure Tnofind(in Tno varchar(100))
BEGIN
set Tno=CONCAT('%',Tno,'%');
select * from teacher where teacher.Tno like Tno ORDER BY teacher.Tno;
end $$
delimiter;

update teacher set Tstate="委员" where Tno="003";

delete from teacher where Tno="003"






