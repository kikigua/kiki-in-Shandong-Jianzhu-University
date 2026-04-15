INSERT INTO reward(DormID,AvergeGrade)
select DormID,AVG(Grade)
FROM dormitorycheck
GROUP BY DormID;      

Select * from DormitoryCheck;




