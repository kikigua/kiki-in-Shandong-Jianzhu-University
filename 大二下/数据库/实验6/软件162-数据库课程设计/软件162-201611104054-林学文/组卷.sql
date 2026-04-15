CREATE TABLE Paper (
  Pid varchar(40)  not NULL,
  Ptype varchar(40) DEFAULT NULL,
	Pgapfilling varchar(40) DEFAULT NULL,
	Pmultiple varchar(40) DEFAULT NULL,
	Pjudgement varchar(40) DEFAULT NULL,
	Ppaperscore int(200) DEFAULT null,
  Pjointime datetime DEFAULT NULL,
  PRIMARY KEY (Pid)
) ;
  INSERT INTO Paper VALUES('null','A','25','25','50','100','2019.1.1');
 INSERT INTO Paper VALUES('数据库','A','20','30','50','100','2019.1.1');
