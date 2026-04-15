CREATE TABLE Gapfilling (
  Gid int(11) NOT NULL,
  Gtitle varchar(7) NOT NULL,
	Ganswer varchar(7) NOT NULL  ,
	Gjointime datetime NOT NULL,
	Gsubject VARCHAR(11) NOT NULL,
	GpaperId varchar(11) NOT NULL ,
  PRIMARY KEY (Gid)

);
INSERT
 INTO Gapfilling
 VALUES('1','填空题','1','2019.1.1','数据库','A');
 INSERT
 INTO Gapfilling
 VALUES('2','填空题','2','2019.1.1','数据库','B');