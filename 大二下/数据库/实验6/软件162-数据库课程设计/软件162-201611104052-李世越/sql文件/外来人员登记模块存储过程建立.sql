CREATE PROCEDURE InGo(
dormid VARCHAR(10),studentid char(12),studentname VARCHAR(10),Date date,Goods 		VARCHAR(50),
Name VARCHAR(5),cardID CHAR(20),Reason VARCHAR(50),InTime 	datetime,OutTime datetime
)
BEGIN
INSERT INTO studentregister
VALUES(dormid,studentid,studentname,DATE,Goods);
INSERT INTO wailairegister
VALUES(name,cardID,Reason,InTime,OutTime);
END

