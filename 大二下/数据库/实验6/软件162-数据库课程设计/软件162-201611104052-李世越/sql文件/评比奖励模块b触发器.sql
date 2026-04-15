create TRIGGER tg1
AFTER INSERT on dormitorycheck
for each ROW
BEGIN  
update reward SET avergeGrade=(SELECT AVG(Grade) FROM dormitorycheck WHERE 	dormitorycheck.dormId=new.dormId) 
WHERE reward.dormId=new.dormId;
END






