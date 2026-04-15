UPDATE reward 
SET rewardLevel='一等奖'
WHERE AvergeGrade>=90;

UPDATE reward 
SET rewardLevel='二等奖'
WHERE AvergeGrade>=80 AND AvergeGrade<90;

UPDATE reward 
SET rewardLevel='三等奖'
WHERE AvergeGrade>=70 AND AvergeGrade<80;  




