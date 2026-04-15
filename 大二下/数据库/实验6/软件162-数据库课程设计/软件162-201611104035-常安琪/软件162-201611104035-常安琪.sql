/*管理员*/
CREATE TABLE admin (
Adid VARCHAR(12) NOT NULL,
Adname VARCHAR(20) NOT NULL,
password VARCHAR(11) NOT NULL,
PRIMARY KEY (Adid)
)
/*老师*/
CREATE TABLE teacher (
Tno VARCHAR(11) NOT NULL,
Tname VARCHAR(20) NOT NULL,
Tpassword VARCHAR(11) NOT NULL,
Tsex VARCHAR(20) NOT NULL,
Ttitle VARCHAR(20) NULL DEFAULT NULL,
PRIMARY KEY (Tno)
)
/*学生*/
CREATE TABLE student (
Sno VARCHAR(12) NOT NULL,
Sname VARCHAR(20) NOT NULL,
Spassword VARCHAR(11) NOT NULL,
Ssex VARCHAR(11) NOT NULL,
IDcard VARCHAR(20) NOT NULL,
Cno VARCHAR(10) NOT NULL,
PRIMARY KEY (Sno)
)
/*课程*/
CREATE TABLE course (
Kno VARCHAR(11) NOT NULL,
Kname VARCHAR(20) NOT NULL,
credit VARCHAR(11) NOT NULL,
Kpre VARCHAR(10) NOT NULL,
PRIMARY KEY (Kno)
)


SELECT *
FROM teacher;

SELECT *
FROM student;

SELECT *
FROM course;

DROP TABLE  course ;

INSERT 
INTO teacher (Tno,Tname,Tpassword,Tsex,Ttitle)
VALUES('12233','王老师','123456','男','教师');

INSERT 
INTO student (Sno,Sname,Spassword,Ssex,IDcard,Cno)
VALUES('2016111040xx','小明','111111','男','1111111111','5');

/*修改密码*/
update teacher set Tpassword='1234567' where Tno='12233';
update student set Spassword='112221255' where Sno='2016111040xx';


INSERT 
INTO course(Kno,Kname,credit,Kpre)
VALUES('1','软件设计','5','必修');

update course set Kname='软件开发' where Kno='1';
