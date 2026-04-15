-- 生成销售书单的存储过程
delimiter $$
create procedure 生成销售书单(IN a int,in b int)
BEGIN
if to_days(b) - to_days(a) < 30 THEN
insert into XX日销售报表(select 销售表.图书编号,销售表.图书名称,sum(销售金额),sum(销售数量),销售表.图书种类 from 销售表 WHERE 销售表.日期编号 >= a and 销售表.日期编号 <= b  GROUP BY 图书名称  );
end if;
if to_days(b) - to_days(a)>=30 and to_days(b) - to_days(a)<=90 then
insert into 月销售报表(select 销售表.图书编号,销售表.图书名称,sum(销售金额),sum(销售数量),销售表.图书种类 from 销售表 WHERE 销售表.日期编号 >= a and 销售表.日期编号 <= b  GROUP BY 图书名称 );
end if;
if to_days(b) - to_days(a) > 90 then
 insert into 季销售报表(select 销售表.图书编号,销售表.图书名称,sum(销售金额),sum(销售数量),销售表.图书种类 from 销售表 WHERE 销售表.日期编号 >= a and 销售表.日期编号 <= b GROUP BY 图书名称  );
end if;
end $$
delimiter ;

delimiter ;
--生成畅销书单的存储过程
delimiter $$
create procedure 生成销售书单1(IN a int,in b int)
BEGIN
if b-a < 30 THEN
create view 畅销书单 as select * from  XX日销售报表 order by 销售数量 desc limit 10;
end if;
if b-a>=30 and b-a<=90 then
create view 畅销书单 as select * from  月销售报表 order by 销售数量 desc limit 10;
end if;
if b-a > 90 then
create view 畅销书单 as select * from 季销售报表 order by 销售数量 desc limit 10;
end if;
end $$
delimiter ;



-- 会员信息的插入
insert into 会员信息(会员编号,会员姓名,会员性别,会员消费额度)values('103','李四','男','8000');


-- 根据会员消费的额度自动升级会员卡的级别的触发器
delimiter $$
CREATE TRIGGER upd_check BEFORE UPDATE ON 会员信息
FOR EACH ROW
BEGIN
IF NEW.会员消费额度 < 3000 THEN
SET NEW.会员等级 = "银卡";
SET NEW.会员折扣额度 = "3000";
SET NEW.折扣度 = "8.8折";
ELSEIF NEW.会员消费额度 >= 5000 THEN
SET NEW.会员等级 = "钻石卡";
SET NEW.会员折扣额度 = "10000";
SET NEW.折扣度 = "6.2折";
ELSE 
SET NEW.会员等级 = "金卡";
SET NEW.会员折扣额度 = "6000";
SET NEW.折扣度 = "5折";
END IF;
END$$
delimiter ;

-- 会员信息的删除
 delete
 from 会员信息
 where 会员编号='101';

--销售表信息插入
insert into 销售表(主编号,图书编号,图书名称,销售金额,销售数量,图书种类,销售日期,日期编号)values('20181209a100','a100','文学史','1000','50','文学类','2018-12-09','20181209');
insert into 销售表(主编号,图书编号,图书名称,销售金额,销售数量,图书种类,销售日期,日期编号)values('21181201b101','b101','荷马史诗','1000','10','历史类','2018-12-01','20181201');
insert into 销售表(主编号,图书编号,图书名称,销售金额,销售数量,图书种类,销售日期,日期编号)values('21181201c102','c102','高等数学','100','2','教学类','2018-12-01','20181201');
insert into 销售表(主编号,图书编号,图书名称,销售金额,销售数量,图书种类,销售日期,日期编号)values('21181209c103','c103','高等代数','200','4','教学类','2018-12-09','20181209');
insert into 销售表(主编号,图书编号,图书名称,销售金额,销售数量,图书种类,销售日期,日期编号)values('21181211c104','c104','高等几何','0','0','教学类','2018-12-11','20181211');
insert into 销售表(主编号,图书编号,图书名称,销售金额,销售数量,图书种类,销售日期,日期编号)values('21181221a105','a105','散文集','2000','100','文学类','2018-12-21','20181221');
insert into 销售表(主编号,图书编号,图书名称,销售金额,销售数量,图书种类,销售日期,日期编号)values('21181202d106','d106','大学英语','10','1','教学类','2018-12-02','20181202');
insert into 销售表(主编号,图书编号,图书名称,销售金额,销售数量,图书种类,销售日期,日期编号)values('21181101e107','e107','三体','15000','150','科幻类','2018-11-01','20181101');
insert into 销售表(主编号,图书编号,图书名称,销售金额,销售数量,图书种类,销售日期,日期编号)values('21181201f108','f108','盗墓笔记','20000','200','小说类','2018-12-01','20181201');
insert into 销售表(主编号,图书编号,图书名称,销售金额,销售数量,图书种类,销售日期,日期编号)values('21181201g109','g109','宇宙简史','2000','40','科学类','2018-12-01','20181201');
insert into 销售表(主编号,图书编号,图书名称,销售金额,销售数量,图书种类,销售日期,日期编号)values('21181201g110','g110','人类简史','2500','50','科学类','2018-12-01','20181201');

--修改会员信息
update 会员信息
set  会员性别='男'
where 会员编号='103'

--自动增加消费金额触发器
delimiter $$
CREATE TRIGGER u after UPDATE ON 顾客购买表
FOR EACH ROW
BEGIN
update 会员信息 set 会员消费额度=会员消费额度+new.销售额度 where 会员姓名=new.会员姓名;
END$$
delimiter ;

