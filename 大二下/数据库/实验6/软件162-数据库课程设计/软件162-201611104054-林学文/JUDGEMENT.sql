CREATE TABLE Judgement (
  Jid int(11) NOT NULL,
  Jtitle text NOT NULL ,
	Janswer smallint(1) NOT NULL  ,
	Jjointime datetime NOT NULL,
	Jsubject VARCHAR(11) NOT NULL,
	Jpapeid varchar(11) NOT NULL ,
  PRIMARY KEY (Jid)

) ;
INSERT
 INTO Judgement
 VALUES('1','判断题','1','2019.1.1','数据库','A');
 INSERT
 INTO Judgement
 VALUES('2','判断题','2','2019.1.1','数据库','B');
 INSERT
 INTO Judgement
 VALUES('3','判断题','1','2019.1.1','数据库','A');
 INSERT
 INTO Judgement
 VALUES('4','判断题','1','2019.1.1','数据库','B');
 INSERT
 INTO Judgement
 VALUES('5','判断题','1','2019.1.1','数据库','A');