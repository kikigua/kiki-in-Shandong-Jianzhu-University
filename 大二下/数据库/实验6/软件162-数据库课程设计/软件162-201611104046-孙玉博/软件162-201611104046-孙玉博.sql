create table ApartmentManager(
AMno char(20) primary key,
AMname char(20),
PWord char(20)
)


create table Apartment(
Ano char(20) primary key,
AMno char(20),
foreign key (AMno) references ApartmentManager(AMno)
on delete cascade
on update cascade
);


create table Room(
Rno char(20) primary key,
Ano char(20) ,  
Floor int,
DSno char(20) ,
foreign key (Ano) references Apartment(Ano)
on delete cascade
on update cascade
);


create table Student(
Sno char(20) primary key,
Sname char(20),
Department char(40),
Grade char(20),
Class char(20),
Ano char(20),
foreign key(Ano) references Apartment(Ano),
Rno char(20),
foreign key(Rno) references Room(Rno)
on delete cascade
on update cascade
);

insert into ApartmentManager(AMno,AMname,PWord)values('001','公寓管理员1','123456');
insert into ApartmentManager(AMno,AMname,PWord)values('002','公寓管理员2','123456');
insert into ApartmentManager(AMno,AMname,PWord)values('003','公寓管理员3','123456');
insert into ApartmentManager(AMno,AMname,PWord)values('004','公寓管理员4','123456');
insert into ApartmentManager(AMno,AMname,PWord)values('005','公寓管理员5','123456');


insert into Apartment(Ano,AMno)values('001','001');
insert into Apartment(Ano,AMno)values('002','002');
insert into Apartment(Ano,AMno)values('003','003');
insert into Apartment(Ano,AMno)values('004','004');
insert into Apartment(Ano,AMno)values('005','005');


insert into Room(Rno,Ano,Floor,DSno)values('001101','001',1,'舍长1');
insert into Room(Rno,Ano,Floor,DSno)values('001102','001',1,'舍长2');
insert into Room(Rno,Ano,Floor,DSno)values('001103','001',1,'舍长3');
insert into Room(Rno,Ano,Floor,DSno)values('001104','001',1,'舍长4');
insert into Room(Rno,Ano,Floor,DSno)values('001105','001',1,'舍长5');


insert into Student(Sno,Sname,Department,Grade,Class,Ano,Rno)values('201600000001','学生1','计算机科学与技术学院','2016','软件162','001','001101');
insert into Student(Sno,Sname,Department,Grade,Class,Ano,Rno)values('201600000002','学生2','计算机科学与技术学院','2016','软件162','001','001101');
insert into Student(Sno,Sname,Department,Grade,Class,Ano,Rno)values('201600000003','学生3','计算机科学与技术学院','2016','软件162','001','001101');
insert into Student(Sno,Sname,Department,Grade,Class,Ano,Rno)values('201600000004','学生4','计算机科学与技术学院','2016','软件162','001','001101');
insert into Student(Sno,Sname,Department,Grade,Class,Ano,Rno)values('201600000005','学生5','计算机科学与技术学院','2016','软件162','001','001101');



create procedure AMcreate(AMno char(20),AMname char(20),PWord char(20))
begin
insert into ApartmentManager values(AMno,AMname,PWord);
end



create procedure AMdelete(AMno char(20))
begin
delete from ApartmentManager where ApartmentManager.AMno=AMno;
end



create procedure AMupdate(AMno char(20),AMname char(20),PWord char(20))
begin
update ApartmentManager set ApartmentManager.AMname=AMname  where ApartmentManager.AMno=AMno;
update ApartmentManager set ApartmentManager.PWord=PWord where ApartmentManager.AMno=AMno;
end


create procedure ResetPW(AMno char(20))
begin
update ApartmentManager set ApartmentManager.PWord='123456' where ApartmentManager.AMno=AMno;
end

call AMcreate('006','公寓管理员6','666666');
call AMdelete('001');
call AMupdate('002','张三','111111');
call ResetPW('006');





