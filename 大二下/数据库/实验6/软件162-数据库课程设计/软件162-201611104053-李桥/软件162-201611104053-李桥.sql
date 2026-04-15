create VIEW room (Dno,Rno,count)
as select DISTINCT Dno,Rno,count(Sno)
from student
GROUP BY Dno,Rno;
create PROCEDURE chaxun(in cRno int,in cDno int)
BEGIN
if cRno=null or cDno=null then
select * from room where Rno=cRno or Dno=cDno;
else 
select * from room where Rno=cRno and Dno=cDno;
end if;
end;

create  PROCEDURE FENPEI( in startroom int,in startdepart int)
BEGIN
 declare StudentId int default 2016001;
 declare have int DEFAULT 0;
 declare stucount int DEFAULT 0;
 declare ssex VARCHAR(10);
 while startroom<119 do
 if startdepart=1 then
	set ssex='M';
 else 
	set ssex='W';
	end if;
 select count into have from room where Rno=startroom and Dno=startdepart;
 SELECT have;
 select MIN(Sno) into StudentId from student where Rno is null and Sex=ssex;
 SELECT StudentId;
IF have=0 then
	select count(*)into stucount from student where Rno is null and Sex=ssex;
	select stucount;
	if stucount/4>=1 then
	UPDATE student set Rno=startroom,Dno=startdepart where Sno=StudentId;
	UPDATE student set Rno=startroom,Dno=startdepart where Sno=StudentId+1;
	UPDATE student set Rno=startroom,Dno=startdepart where Sno=StudentId+2;
	UPDATE student set Rno=startroom,Dno=startdepart where Sno=StudentId+3;
end if;

elseif have=1 then
	select count(*)into stucount from student where Rno is null and Sex=ssex;
	select stucount;
	if stucount%4=3 then				
	 UPDATE student set Rno=startroom,Dno=startdepart where Sno=StudentId;
	 UPDATE student set Rno=startroom,Dno=startdepart where Sno=StudentId+1;
	 UPDATE student set Rno=startroom,Dno=startdepart where Sno=StudentId+2;
  end if;
elseif have=2 then
	select count(*)into stucount from student where Rno is null and Sex=ssex;
	select stucount;
 if stucount%4=2 then
 UPDATE student set Rno=startroom,Dno=startdepart where Sno=StudentId;
 UPDATE sstudent set Rno=startroom,Dno=startdepart where Sno=StudentId+1;
 end if;
elseif have=3 then
	select count(*)into stucount from student where Rno is null and Sex=ssex;
	select stucount;
  if stucount%4=1 then
   UPDATE student set Rno=startroom,Dno=startdepart where Sno=StudentId;
  end if ;
elseif have=4 then
  set startroom=startroom+1;
end if;
end while;
end;

update student
set Dno is null,Rno is null
where Rno = 101 and Sex='M'
