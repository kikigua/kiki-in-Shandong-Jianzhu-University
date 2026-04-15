
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sno` varchar(11) NOT NULL,
  `sname` varchar(20) NOT NULL,
  `sex` char(2) DEFAULT NULL,
  `age` char(10) DEFAULT NULL,
  `classname` varchar(20) DEFAULT NULL COMMENT '班级名',
  `phone` char(11) DEFAULT NULL,
  `endyear` char(4) DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `tno` char(6) NOT NULL,
  `tname` varchar(20) NOT NULL,
  `sex` char(2) DEFAULT NULL,
  `tdept` char(3) DEFAULT NULL COMMENT '所属教研室',
  `tpost` char(3) DEFAULT NULL COMMENT '职称',
  `studydirect` varchar(40) DEFAULT NULL COMMENT '研究方向',
  `phone` char(11) DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `classid` bigint(20) NOT NULL AUTO_INCREMENT,
  `classname` varchar(20) NOT NULL,
  `specid` char(3) DEFAULT NULL COMMENT '专业号',
  PRIMARY KEY (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `specialty`;
CREATE TABLE `specialty` (
  `specid` char(3) NOT NULL DEFAULT '' COMMENT '专业号',
  `specname` varchar(20) NOT NULL COMMENT '专业名',
  `managerid` char(6) DEFAULT NULL COMMENT '负责人编号',
  PRIMARY KEY (`specid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `subid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课题编号',
  `subname` varchar(20) NOT NULL COMMENT '课题名',
  `content` text COMMENT '课题内容',
  `require` text COMMENT '课题要求',
  `isoutschool` smallint(5) DEFAULT NULL COMMENT '是否校外',
  `reference` text COMMENT '课题参考文献',
  `tno` char(6) DEFAULT NULL COMMENT '教师编号',
  `status` char(1) DEFAULT NULL COMMENT '课题状态',
  PRIMARY KEY (`subid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `subspec`;
CREATE TABLE `subspec` (
  `subspecid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课题专业号',
  `subid` bigint(20) NOT NULL COMMENT '课题号',
  `specid` char(6) NOT NULL COMMENT '专业号',
  `review` char(1) DEFAULT NULL COMMENT '审核标志',
  PRIMARY KEY (`subspecid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create Trigger ins_user AFTER INSERT
 on student                       
 for EACH ROW                                   
 begin 
   INSERT INTO user(usertype,userid,username,password) 
   VALUES('学生',new.sno,new.sname,'123456');   
 end;


create Trigger del_stu AFTER DELETE
on student                       
for EACH ROW                                   
begin 
 DELETE FROM  substu WHERE substu.stuid=old.stuid;
 DELETE FROM  week WHERE week.stuid=old.stuid;
 DELETE FROM  document WHERE document.stuid=old.stuid;
end; 


create Trigger ins_user_teacher AFTER INSERT
 on teacher                       
 for EACH ROW                                   
 begin 
   INSERT INTO user(usertype,userid,username,password) 
   VALUES('教师',new.tno,new.tname,'123456');   
 end;

 
CREATE PROCEDURE del_teacher (IN deltid char)
BEGIN
delete form teacher where tid=deltid;
delete form tb_subject where tid=deltid;
END;