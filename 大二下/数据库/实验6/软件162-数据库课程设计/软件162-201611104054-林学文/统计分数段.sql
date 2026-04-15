create table Countscore(`分数段` char (10), number SMALLINT);
CREATE DEFINER=`root`@`localhost` PROCEDURE `countSoreP`()
BEGIN
    declare less60 smallint default 0;
    declare b60a70 smallint default 0;
    declare b70a80 smallint default 0;
    declare b80a90 smallint default 0;
    declare more90 smallint default 0; 
    declare countcno char(4) default '****';



    SELECT COUNT(*)
    INTO less60 FROM Cscore
    WHERE cno = '8' AND grade < 60;
    SELECT COUNT(*)
    INTO b60a70 FROM Cscore
    WHERE cno = '8' AND grade >= 60 AND grade < 70;

    SELECT COUNT(*)
    INTO b70a80 FROM Cscore
    WHERE cno = '8' AND grade >= 70 AND grade < 80;

    SELECT COUNT(*)
    INTO b80a90 FROM Cscore
    WHERE cno = '8' AND grade >= 80 AND grade < 90;

    SELECT COUNT(*)INTO more90 FROM Cscore
    WHERE cno = '8' AND grade >= 90;
    create table countScore( scorestage char(10), number smallint);
    insert into countscore values('x<60', less60);
    insert into countscore values('60<=x<70', b60a70);
    insert into countscore values('70<=x<80', b70a80);
    insert into countscore values('80<=x<90', b80a90);
    insert into countscore values('x>=90', more90);
END
