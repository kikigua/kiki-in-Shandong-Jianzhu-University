SELECT *
FROM student
where StudentID IN(
SELECT StudentID
FROM studentregister
where Goods LIKE '%电脑%' );

