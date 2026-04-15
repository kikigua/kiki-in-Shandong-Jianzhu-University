CREATE TABLE S
(sno CHAR(10)PRIMARY KEY,
sname CHAR(10)UNIQUE,
sdept CHAR(10),
tno CHAR(10),
tname CHAR(10),
jys CHAR(10),
ydm CHAR(10),
sms CHAR(10),
wx CHAR(10),
zt char(10),
);
 INSERT INTO S(sno,sname,sdept,tno,tname,jys,ydm,sms,wx,zt)VALUES('16111','张远','IS','6111','孙建','计算机','C/ydm','C/sms','C/wx','已提交');
 insert INTO S(sno,sname,sdept,tno,tname,jys,ydm,sms,wx,zt) VALUES('16202','马文','TM','6101','文军','土木','D/ydm','D/sms','D/wx','已提交');
 insert INTO S(sno,sname,sdept,tno,tname,jys,ydm,sms,wx,zt) VALUES('16303','马军','TM','6102','谭伟','土木','D/data/ydm','D/data/sms','D/data/wx','已提交');
 insert INTO S(sno,sname,sdept,tno,tname,jys,ydm,sms,wx,zt) VALUES('16101','谭三九','IS','6112','廖胜','计算机','C/data/ydm','C/data/sms','C/data/wx','已提交');
 insert INTO S(sno,sname,sdept,tno,tname,jys,ydm,sms,wx,zt) VALUES('16109','程思涵','IS','6118','郭玲','计算机','D/ydm','D/sms','D/wx','已提交');
 insert INTO S(sno,sname,sdept,tno,tname,jys,ydm,sms,wx,zt) VALUES('16501','孙梦涵','CH','6201','贾青','测绘','D/ydm','D/sms','D/wx','已提交');
 insert INTO S(sno,sname,sdept,tno,tname,jys,ydm,sms,wx,zt) VALUES('16509','徐军','CH','6205','陈冬青','测绘','D/ydm','D/sms','D/wx','已提交');
 go
 create view B_S
 as
 select tno,sno,sname,ydm,sms,wx
 from S
 with check option;
 go
 go
 create view A_S(sdept,sno,ydm,sms,wx)
 as
 select sdept,count(sno),count(ydm),count(sms),count(wx)
 from S
 group by sdept
 with check option;
 go
 go
 create view l_S(jys,tname,sno,ydm,sms,wx)
 as
 select jys,tname,count(sno),count(ydm),count(sms),count(wx)
 from S
 group by jys,tname
 with check option;
 go
 go
 create view C_S(sno,sname,zt)
 as
 select sno,sname,zt
 from S
 with check option;
 go
 select*
 from B_S;
 select*
 from A_S;
 select *
 from C_S;
 select *
 from l_S;