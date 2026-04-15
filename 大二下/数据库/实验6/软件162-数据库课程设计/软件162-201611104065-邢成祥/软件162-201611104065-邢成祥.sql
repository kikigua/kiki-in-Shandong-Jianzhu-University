CREATE TABLE Customer(
  ID_number varchar(40) PRIMARY KEY NOT NULL,
  Name varchar(40)UNIQUE NOT NULL,
  Sex varchar(40)NOT NULL,
  Birthday DATE NOT NULL,
  Telephone varchar(40) NOT NULL,
  Emial varchar(40) NOT NULL,
  Member varchar(40)DEFAULT NULL,
);
 
 INSERT INTO Customer(ID_number,Name,Sex,Birthday,Telephone,Emial,Member)
 values('2016111','李勇','男','1998-1-2','1123321','123@123','金');
 INSERT INTO Customer(ID_number,Name,Sex,Birthday,Telephone,Emial,Member)
 values('2016112','刘晨','男','1998-2-2','1124321','124@123','银');
 INSERT INTO Customer(ID_number,Name,Sex,Birthday,Telephone,Emial,Member)
 values('2016113','王敏','女','1998-1-3','1125321','125@123','银');
 INSERT INTO Customer(ID_number,Name,Sex,Birthday,Telephone,Emial,Member)
 values('2016114','张立','男','1998-1-4','1126321','126@123','');
 INSERT INTO Customer(ID_number,Name,Sex,Birthday,Telephone,Emial,Member)
 values('2016115','东华','男','1998-1-5','1127321','127@123','');
 INSERT INTO Customer(ID_number,Name,Sex,Birthday,Telephone,Emial,Member)
 values('2016116','任雪','女','1998-1-6','1128321','128@123','金');
 INSERT INTO Customer(ID_number,Name,Sex,Birthday,Telephone,Emial,Member)
 values('2016117','魏光','男','1998-1-7','1129321','129@123','');
 INSERT INTO Customer(ID_number,Name,Sex,Birthday,Telephone,Emial,Member)
 values('2016118','赵宇','男','1998-1-8','1129421','132@123','银');
 INSERT INTO Customer(ID_number,Name,Sex,Birthday,Telephone,Emial,Member)
 values('2016119','马丽','女','1998-1-9','1129521','133@123','');
 INSERT INTO Customer(ID_number,Name,Sex,Birthday,Telephone,Emial,Member)
 values('2016120','高鑫','男','1998-2-1','1129621','134@123','');

 

 CREATE TABLE  Room(
  Floor_number int NOT NULL ,
  Room_ID varchar(40)PRIMARY KEY NOT NULL,
  Price int NOT NULL,
  RoomType  varchar(40) NOT NULL,
  Room_State varchar(40) NOT NULL,
);

INSERT INTO Room(Floor_number,Room_ID,Price,Room_State,RoomType)
VALUES('1','101','80','单人间','空闲');
INSERT INTO Room(Floor_number,Room_ID,Price,Room_State,RoomType)
VALUES('1','102','80','单人间','预定');
INSERT INTO Room(Floor_number,Room_ID,Price,Room_State,RoomType)
VALUES('1','103','80','单人间','空闲');
INSERT INTO Room(Floor_number,Room_ID,Price,Room_State,RoomType)
VALUES('2','201','150','标准间','入住');
INSERT INTO Room(Floor_number,Room_ID,Price,Room_State,RoomType)
VALUES('2','202','150','标准间','预定');
INSERT INTO Room(Floor_number,Room_ID,Price,Room_State,RoomType)
VALUES('2','203','150','标准间','空闲');
INSERT INTO Room(Floor_number,Room_ID,Price,Room_State,RoomType)
VALUES('3','301','300','套房','入住');
INSERT INTO Room(Floor_number,Room_ID,Price,Room_State,RoomType)
VALUES('3','302','300','套房','入住 ');
INSERT INTO Room(Floor_number,Room_ID,Price,Room_State,RoomType)
VALUES('3','303','300','套房','空闲');
INSERT INTO Room(Floor_number,Room_ID,Price,Room_State,RoomType)
VALUES('4','401','1000','总统套房','预定 ');
INSERT INTO Room(Floor_number,Room_ID,Price,Room_State,RoomType)
VALUES('4','402','1000','总统套房','空闲');






 CREATE TABLE Reservation(
  Reservation_ID int PRIMARY KEY NOT NULL,
  Floor_number varchar(40)  NOT NULL,
  Room_ID varchar(40)UNIQUE NOT NULL,
  Telephone varchar(40) NOT NULL,
  Name varchar(40) UNIQUE NOT NULL,
  StartTime DATE NOT NULL,
  Endtime DATE NOT NULL,  
  FOREIGN KEY(Room_ID)REFERENCES Room(Room_ID)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
 );
 INSERT INTO Reservation(Reservation_ID,Floor_number,Room_ID,Telephone,Name,StartTime,Endtime)
 VALUES('1111','2','201','1129621','高鑫','2018-1-2','2018-1-5');
 INSERT INTO Reservation(Reservation_ID,Floor_number,Room_ID,Telephone,Name,StartTime,Endtime)
 VALUES('1112','3','301','1129521','马丽','2018-1-2','2018-1-5');
 INSERT INTO Reservation(Reservation_ID,Floor_number,Room_ID,Telephone,Name,StartTime,Endtime)
 VALUES('1113','3','302','1129421','赵宇','2018-1-2','2018-1-5');
 INSERT INTO Reservation(Reservation_ID,Floor_number,Room_ID,Telephone,Name,StartTime,Endtime)
 VALUES('1114','4','401','1129321','魏光','2018-1-2','2018-1-5');
 INSERT INTO Reservation(Reservation_ID,Floor_number,Room_ID,Telephone,Name,StartTime,Endtime)
 VALUES('1115','2','202','1128321','任雪','2018-1-2','2018-1-5');
 INSERT INTO Reservation(Reservation_ID,Floor_number,Room_ID,Telephone,Name,StartTime,Endtime)
 VALUES('1116','1','102','1127321','东华','2018-1-2','2018-1-5');



 CREATE TABLE Live(
   ID_number varchar(40) NOT NULL, 
   Name varchar(40) NOT NULL,
   Room_ID varchar(40) NOT NULL,
   StartLive date NOT NULL,
   EndLive date  NULL,
   Expense int NOT NULL,
   Operator varchar(40) NOT NULL,
   CustomerState varchar(40) NOT NULL,
   FOREIGN KEY(ID_Number)REFERENCES Customer(ID_Number)
   ON DELETE CASCADE
  ON UPDATE CASCADE,
   FOREIGN KEY(Room_ID)REFERENCES Room(Room_ID)
   ON DELETE CASCADE
  ON UPDATE CASCADE,
 );
 INSERT INTO Live(ID_number,Name,Room_ID,StartLive,EndLive,Expense,Operator,CustomerState)
 VALUES('2016120','高鑫','201','2018-1-2','','100','夏洛','入住');
 INSERT INTO Live(ID_number,Name,Room_ID,StartLive,EndLive,Expense,Operator,CustomerState)
 VALUES('2016119','马丽','301','2018-1-2','','100','夏洛','入住');
 INSERT INTO Live(ID_number,Name,Room_ID,StartLive,EndLive,Expense,Operator,CustomerState)
 VALUES('2016118','赵宇','302','2018-1-2','','100','夏洛','入住');

 /*客户管理：*/
 /*插入一个新的客户信息*/
 INSERT INTO Customer(ID_number,Name,Sex,Birthday,Telephone,Emial,Member)
 values('?','?','?','?','?','?','?');
 /*修改客户信息*/
 UPDATE Customer
 SET ?='?'
 WHERE ?='?';
 /*删除客户信息*/
 DELETE ?
 FROM Customer
 WHERE ?='?';
 /*根据姓名查询客户基本信息*/
 SELECT *
 FROM Customer
 where  Name='?';

 /*根据电话号查询客户基本信息*/
 SELECT *
 FROM Customer
 where Telephone='?';

 /*入住管理：*/
 /*根据客房标准查询状态为‘空闲’的客房信息*/
 SELECT Floor_number,Room_ID,Price
 FROM Room
 where RoomType='空闲' and Room_State='?';
 /*根据客户名和联系方式查询房间预订信息*/
 SELECT Floor_number,Room_ID,StartTime,Endtime
 FROM Reservation
 where Name='马丽'and Telephone='1129521';
 /*为指定的客户办理指定客房的入住手续*/
 INSERT INTO Live(ID_number,Name,Room_ID,StartLive,EndLive,Expense,Operator,CustomerState)
 VALUES('?','?','?','?','','?','?','?');

 /*退房管理*/
 go
create proc tuifang
   @iD_number varchar(40), 
   @name varchar(40),
   @room_ID varchar(40),
   @startLive date,
   @endLive date,
   @expense int,
   @operator varchar(40)
as 
begin
  INSERT INTO Live(ID_number,Name,Room_ID,StartLive,CustomerState,Expense,Operator)
  VALUES('@iD_number','@name','@room_ID','@startLive','@endLive','100','@operator');
 UPDATE Live
 SET Endlive=@endLive,CustomerState='退房',Expense=convert(int,(select datediff(day,StartLive,EndLive)))*convert(int,(select Price from Room where Room_ID=@room_ID ))+100
 WHERE Room_ID=@room_ID;
 UPDATE Room
 SET RoomType='空闲'
 WHERE Room_ID=@room_ID;
end;
go
 select datediff(day,StartLive,EndLive) from Live;
 exec tuifang'2016120','高鑫','201','2018-01-01','2018-01-05','100','夏洛'
 go
 SELECT * FROM Room where Room_ID='';


 select * from Customer;
 SELECT * FROM Room;
 select * from Reservation;
 select * from Live;
 select Member,COUNT(Member)
 FROM Customer
 Group by Member;
 go




 /*会员查询*/
 create proc stu4
 @member varchar(40)
as 
begin
 select * from Customer WHERE Member=@member;
 select Member,COUNT(Member)
 FROM Customer
 Group by Member;
end;
go
 
 exec stu4 '金'
 go
 

 
 
 

  
