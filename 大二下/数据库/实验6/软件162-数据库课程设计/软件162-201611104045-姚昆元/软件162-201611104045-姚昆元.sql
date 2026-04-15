-- 创建新表：
CREATE TABLE bill (
id  char(10),
bid  char(10),
company  varchar(50),
time  datetime(6),
money int(10),
sum  int(10),
PRIMARY KEY (id),
FOREIGN key(bid) REFERENCES book(id)
);

create table book
(id char(10),
bname varchar(50),
author varchar(50),
price int(10),
class varchar(50),
company varchar(50),
time  datetime(6),
number int(10),
sid int(10),
primary key(id),

);

-- 存储过程，支持模糊查询：

-- 根据书名查询：
delimiter $$
create procedure namefind(in bname varchar(50))
BEGIN
set bname=CONCAT('%',bname,'%');
select * from book where book.bname like bname ORDER BY book.bname;
end $$
delimiter ;

-- 根据作者名查询：
delimiter $$
create procedure authorfind(in author varchar(50))
BEGIN
set author=CONCAT('%',author,'%');
select * from book where book.author like author ORDER BY book.author;
end $$
delimiter ;

-- 根据图书分类查询：
delimiter $$
create procedure classfind(in class varchar(50))
BEGIN
set class=CONCAT('%',class,'%');
select * from book where book.class like class ORDER BY book.class;
end $$
delimiter ;

-- 根据出版社查询：
delimiter $$
create procedure companyfind(in company varchar(50))
BEGIN
set company=CONCAT('%',company,'%');
select * from book where book.company like company ORDER BY book.company;
end $$
delimiter ;

-- 触发器：

-- 插入时自动计算库存
DELIMITER $
create trigger charu after insert
on bill for each row
begin
declare c char(10);
set c = (select bid from bill where id=new.id);
update book set number = number+new.number where id = c;
end$
DELIMITER ;

-- 插入数据：
insert into book(id,bname,author,price,class,company,time,number)values ('gd010',	'红楼梦',	'曹雪芹',	23,	'古典文学',	'人民文学出版社',	2012-12-12,	20);
insert into book(id,bname,author,price,class,company,time,number)values ('wm001',	'格列佛游记',	'斯威夫特',	18,	'外国名著',	'北方文学出版社',	2018-12-14,	20);
insert into book(id,bname,author,price,class,company,time,number)values ('sh004',	'丰乳肥臀',	'莫言',	27,	'生活休闲',	'作家出版社',	2012-11-01,	25);
insert into book(id,bname,author,price,class,company,time,number)values ('sh003',	'蛙',	'莫言',	18,	'生活休闲',	'作家出版社',	2012-10-01,	25);
insert into book(id,bname,author,price,class,company,time,number)values ('sh002',	'在细雨中呼喊',	'余华',	19,	'生活休闲',	'作家出版社',	2017-12-14,	20);
insert into book(id,bname,author,price,class,company,time,number)values ('sh001',	'活着',	'余华',	16,	'生活休闲',	'作家出版社',	2016-10-10,	10);
insert into book(id,bname,author,price,class,company,time,number)values ('kh001',	'三体',	'刘慈欣',	50,	'科幻小说',	'重庆出版社',	2017-03-01,	10);
insert into book(id,bname,author,price,class,company,time,number)values ('j004',	'新手学电脑',	'罗亮',	25,	'计算机',	'电子工业出版社',	2015-12-12,	5);
insert into book(id,bname,author,price,class,company,time,number)values ('j001',	'ps入门',	'张丹丹',	39,	'计算机',	'人民邮电出版社',	2010-12-14,	15);
insert into book(id,bname,author,price,class,company,time,number)values ('gd002',	'西游记',	'吴承恩',	56,	'古典文学',	'北方文学出版社',	2010-10-02,	10);

-- 执行存储过程：
set @bname='游';
call namefind(@bname);

set @author='承恩';
call authorfind(@author);

set @class='古典';
call classfind(@class);

set @company='文学';
call companyfind(@company);