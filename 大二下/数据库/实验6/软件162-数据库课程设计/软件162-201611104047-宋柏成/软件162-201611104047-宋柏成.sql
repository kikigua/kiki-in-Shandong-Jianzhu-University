
create database bookstore;

CREATE TABLE `bookstore`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `passwd` VARCHAR(45) NOT NULL,
  `tel` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `level` INT NULL AFTER `address`,
`money` INT NULL AFTER `level`;
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `bookstore`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ad_name` VARCHAR(45) NOT NULL,
  `ad_passwd` VARCHAR(45) NULL,
  `worknum` INT NOT NULL,
  `ad_tel` VARCHAR(45) NOT NULL
  PRIMARY KEY (`id`),
  UNIQUE INDEX `worknum_UNIQUE` (`worknum` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `bookstore`.`sale_man` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sa_name` VARCHAR(45) NOT NULL,
  `sa_tel` VARCHAR(45) NOT NULL,
  `sa_num` INT NOT NULL,
  `sa_passwd` VARCHAR(45) NULL
  PRIMARY KEY (`id`),
  UNIQUE INDEX `sa_num_UNIQUE` (`sa_num` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
 
insert into `admin` (id,ad_name,ad_tel)
values
(1, "q", "10010");
insert into `sale_man` (id,sa_name,sa_tel)
values
(1, "42", "10086");

create trigger num_add
before insert
on `bookstore`.`admin`
for each row
set new.worknum = 
new.id+1000;

create trigger sa_num_add
before insert
on `sale_man`
for each row
set new.sa_num = new.id+1100;

delimiter $$
create procedure admin_procedure(in worknum int)
BEGIN
select id,ad_name,worknum,ad_tel
from (select * 
from `admin`) 
order by id DESC ;
end $$
delimiter ;
delimiter $$
create procedure sale_procedure(in sa_num int)
BEGIN
select id,sa_name,sa_num,sa_tel
from (select * 
from `sale_man`) 
order by id DESC ;
end $$
delimiter ;

delete from `user` where id=1;

update from `user` where id=1;

insert into `user` (id,name,tel)
values
(1, "42", "10086");

create trigger user_del
after delete
on `user`
for each row
delete from `user` where id=1;

create trigger user_up
before update
on `user`
for each row
update from `user` where id=1;

create trigger user_add
before insert
on `user`
for each row
INSERT INTO `user`(id,name,tel) VALUES(1, "42", "10086");

delimiter $$
create procedure user_procedure(in id int)
BEGIN
select id,u_name,tel,email,address
from (select * 
from `user`) 
order by id DESC ;
end $$
delimiter ;

CREATE DEFINER=`root`@`localhost` TRIGGER `reset_userpasswd` AFTER UPDATE ON `user` FOR EACH ROW BEGIN
update `user` SET NEW.passwd = "123456"
where new.passwd=null;
END
 
