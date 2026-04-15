/* 创建数据库*/
Create database book default charset utf8;

/*顾客：*/
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `Name` char(20) NOT NULL,
  `Expenditure` int(11) NOT NULL,
  `class` char(20) NOT NULL,
  `phone` char(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*书：*/
CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `author` char(20) NOT NULL,
  `inprice` int(11) NOT NULL,
  `outprice` int(11) NOT NULL,
  `type` char(20) NOT NULL,
  `company` char(20) NOT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*当前销售列表：*/
CREATE TABLE `nowsell` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `time` varchar(50) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*销售列表：*/
CREATE TABLE `selllist` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `author` varchar(20) NOT NULL,
  `bid` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `class` varchar(50) DEFAULT NULL,
  `discount` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `sumprice` int(11) NOT NULL,
  `time` varchar(50) NOT NULL,
  `profit` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*添加各种图书信息
将各种图书的信息添加进图书信息表*/
insert into book(id,name,author,inprice,outprice,type,company,stock)values(1, '我的奋斗', '希特勒',10,30, '社科', '人民教育出版社',30)
/*添加顾客信息*/
insert into customer(id,Name,Expenditure,class,phone)values(1,'张三',5000,'钻石','123456789');
insert into customer(id,Name,Expenditure,class,phone)values(2,'李四',4000,'金卡','123456788');
insert into customer(id,Name,Expenditure,class,phone)values(3,'王五',3000,'银卡','123456787');
/*触发器*/
DELIMITER $
create trigger updatebook after insert
on nowsell for each row
begin
declare c int;
declare d int;
set  c = (select id from nowsell where id=new.id);
set d=(select number from nowsell where id=new.id);
update book set stock=stock-d where id=c;
end$
DELIMITER ;

/*更新当前销售清单 
能够记录当前正在售书的书号、顾客编号、售出时间、售出数量*/
insert into nowsell(id,customer_id,time,number)values(1,1,2008,5)
/*存储过程*/
Delimiter//
Create procedure proc(in cname char(20)) 
BEGIN
Declare dengji char(20); 
declare discountprice int;
declare custom_id int;
declare book_id int;
declare book_price int;
declare sell_number int;
declare sell_time char(50);
declare book_name char(20);
declare book_author char(20);
declare book_inprice int;
declare book_type char(20);
declare book_company char(50);
declare yingli int;
declare zongjia int;
set dengji=(select class from customer where Name=cname);
set custom_id=(select id from customer where Name=cname);
set book_id=(select id from nowsell where customer_id=custom_id);
set sell_number=(select number from nowsell where customer_id=custom_id);
set sell_time=(select time from nowsell where customer_id=custom_id);
set book_price=(select outprice from book where id=book_id);
set book_name=(select name from book where id=book_id);
set book_author=(select author from book where id=book_id);
set book_inprice=(select inprice from book where id=book_id);
set book_type=(select type from book where id=book_id);
set book_company=(select company from book where id=book_id);
if dengji='钻石' then
set discountprice=0.9*book_price;
end if;
if dengji='金卡' then
set discountprice=0.8*book_price;
end if;
if dengji='银卡' then
set discountprice=0.7*book_price;
end if;
if dengji='普通顾客' then
set discountprice=book_price;
end if;
set zongjia=discountprice*sell_number;
set yingli=zongjia-sell_number*book_inprice;
insert into selllist(id,name,author,bid,price,class,discount,number,sumprice,time,profit)
values(1,book_name,book_author,book_inprice,book_price,book_type,discountprice,sell_number,zongjia,sell_time,yingli);
END
//
/*运行存储过程*/
set @cname='张三';
call proc(@cname);

