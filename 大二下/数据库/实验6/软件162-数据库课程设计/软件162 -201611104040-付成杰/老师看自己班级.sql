 select Cno,Classname,Sno ,Sname
 from kq
 WHERE Classname='一班'
 order by Sno;

  select Cno,Classname,Sno ,Sname
 from kq
 WHERE Classname='一班'
 order by Sno;

  select Cno,Classname,Sno ,Sname
 from kq
WHERE Cno=1
order by Sno

 select Cno,Classname,Sno ,Sname
 from kq
WHERE Cno=1
order by Sno

select Sno,Sname,sum(AbsentNum),sum(LeaveNum)
from kq
group by Sname
order by sum(AbsentNum) desc
