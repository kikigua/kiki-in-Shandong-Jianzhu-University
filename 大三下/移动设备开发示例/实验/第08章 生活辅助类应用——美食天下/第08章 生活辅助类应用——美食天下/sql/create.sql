drop table if exists craft;					
create table craft(						/*创建制作工艺表*/
	craft varchar(20) primary key	
);

create table ctime(						/*创建时间表*/
	ctime varchar(20) primary key	
);

create table difficulty(					/*创建制作难度表*/
	difficulty varchar(20) primary key	
);

create table label(						/*创建标签表*/
	label varchar(20) primary key	
);

create table style(						/*创建菜系表*/
	style varchar(20) primary key	
);
	
create table tool(						/*创建厨具表*/
	tool varchar(20) primary key	
);

create table flavour(						/*创建口味表*/
	flavour varchar(20) primary key	
);

create table uid(					/*创建用户表*/
	uid varchar(10) primary key,			/*用户名*/	
	pwd varchar(10) ,				/*用户密码*/
	sculpture varchar(50) default 'initial.jpg',	/*用户头像*/
	sex char(8) DEFAULT 'bm',			/*用户性别*/
	entertime timestamp default current_timestamp,	/*注册时间*/
	fans int(5)  default 0,				/*粉丝数量*/
	random int(5)  default 0,			/*随拍数量*/
	menu int(5)  default 0,				/*菜品数量*/
	menuc int(5)  default 0,			/*收藏菜品数量*/
	randomc int(5)  default 0,			/*收藏随拍数量*/
	randomContent varchar(1000)  default '<#>',	/*用户收藏的随拍*/
	menuContent varchar(1000)  default '<#>',	/*用户收藏的菜品*/
	attention int(5)  default 0			/*用户关注度*/
);

create table attention(					/*创建关注表*/
	uid varchar(10) primary key,
	content varchar(1000) default '#',
	count int(5)  default 0,
	CONSTRAINT F_uid FOREIGN KEY (uid) REFERENCES uid(uid)
);

create table pro(					/*创建制作过程表*/
	id int(10) primary key auto_increment,
	step1 varchar(1000),
	step2 varchar(1000),
	step3 varchar(1000),
	step4 varchar(1000),
	step5 varchar(1000),
	step6 varchar(1000),
	step7 varchar(1000),
	step8 varchar(1000),
	step9 varchar(1000),
	step10 varchar(1000),
	step11 varchar(1000),
	step12 varchar(1000),
	step13 varchar(1000),
	step14 varchar(1000),
	step15 varchar(1000),
	step16 varchar(1000),
	step17 varchar(1000),
	step18 varchar(1000),
	step19 varchar(1000),
	step20 varchar(1000)
);

create table menu(					/*创建菜品表*/
	id int(10) primary key auto_increment,		/*菜品编号*/
	cname varchar(50),				/*菜品名称*/
	uid varchar(10),				/*菜品上传者*/
	primarypic varchar(50),				/*菜品主图*/
	style varchar(20),				/*菜系*/
	difficulty varchar(20),				/*困难度*/
	flavour varchar(20),				/*菜品口味*/
	ctime varchar(20),				/*菜品制作时间*/
	tools varchar(20),				/*厨具*/
	craft varchar(20),				/*制作工艺*/
	food varchar(1000),				/*主料*/
	codiments varchar(1000),			/*配料*/
	introduction varchar(1000),			/*菜品简介*/
	tips varchar(1000),				/*菜品提示*/
	slike int(5) default 0,				/*制作技巧*/
	collection int(5) default 0,			/*喜好菜品的人数*/
	pinglun int(5) default 0,			/*评论菜品的人数*/
	uploadtime timestamp default current_timestamp,	/*菜品上传时间*/
	likeuser varchar(1000) default '<#>',		/*喜好菜品的用户*/
	state tinyint(4) default 1,			/*菜品状态*/
	sculpture varchar(50) not null,			/*上传者头像*/
constraint menu_fk_uid foreign key (uid) references uid(uid),/*上传者外键*/
	constraint menu_fk_style foreign key(style) references style(style),						/*菜系外键*/
	constraint menu_fk_difficulty foreign key(difficulty) references difficulty(difficulty),					/*制作困难度外键*/
	constraint menu_fk_flavour foreign key(flavour) references flavour(flavour),									/*菜系口味外键*/	
	constraint menu_fk_ctime foreign key(ctime) references ctime(ctime),						/*制作时间外键*/
	constraint menu_fk_id foreign key(id) references pro(id),/*编号外键*/
	constraint menu_fk_craft foreign key(craft) references craft(craft)/*制作工艺外键*/
);

create table random(					/*创建随拍表*/
	id int(10) primary key auto_increment,		/*随拍编号*/
	uid varchar(10),				/*随拍上传者*/
	city varchar(10),				/*随拍城市*/
	label varchar(20),				/*随拍标签*/
	introduce varchar(1000),			/*随拍简介*/	
	picPath varchar(1000),				/*随拍图片*/
	uploadTime timestamp default current_timestamp,	/*上传时间*/
	collection int(5) default 0,			/*收藏人数*/
	pinglun int(5) default 0,			/*评论人数*/
	slike int(4) default 0,				/*喜好人数*/
	likeUser varchar(1000) default '<#>',		/*喜好的人*/
	state tinyint(4) default 1,			/*随拍状态*/
	sculpture varchar(50),				/*上传者头像*/
	CONSTRAINT random_fk_uid FOREIGN KEY(uid) REFERENCES uid(uid)/*表的外键*/		
);

create table commentrandom(				/*随拍评论表*/
	id int(10) primary key auto_increment,
	random_id int(10),
	uid varchar(10),
	picPath varchar(50),
	word varchar(1000),
	times timestamp default current_timestamp,
	state tinyint(4) default 1,
	sculpture varchar(50),
	CONSTRAINT commentrandom_fk_random_id FOREIGN KEY(random_id) REFERENCES random(id),
	CONSTRAINT commentrandom_fk_uid FOREIGN KEY(uid) REFERENCES uid(uid)
);

create table manager(						/*创建管理员表*/
		cname varchar(30) primary key,
		pwd varchar(30)
);
		
create table menu_recommend(					/*创建精品菜品表*/
	menu_id int(10) primary key,
	r_time timestamp default current_timestamp,
	CONSTRAINT fk_id FOREIGN KEY (menu_id) REFERENCES menu(id)
);

drop table if exists random_recommend;				
create table random_recommend(					/*创建精品随拍表*/
	random_id int(10) primary key,
	r_time timestamp default current_timestamp,
	FOREIGN KEY (`random_id`) REFERENCES `random` (`id`)
);

create table recommend(					/*创建推荐表*/
	id int(5) primary key auto_increment,		/*推荐编号*/
	picPath varchar(50) not null,			/*推荐图片*/
	upTime timestamp default current_timestamp,	/*推荐时间*/
	tips varchar(7) not null			/*推荐语*/
);

create table commentmenu(				/*创建菜品评论表*/
	id int(5) primary key auto_increment,		/*评论编号*/
	menu_id int(10),				/*菜品编号*/
	uid varchar(10),				/*评论者*/
	picPath varchar(50),				/*评论时上传的图片*/
	word varchar(500),				/*评论语*/
	times timestamp default current_timestamp,	/*评论时间*/
	state tinyint(4) default 1,			/*评论状态*/
	sculpture varchar(50) not null,			/*评论者头像*/
	CONSTRAINT commentmenu_fk_uid FOREIGN KEY(uid) REFERENCES uid(uid),/*表的外键*/
	CONSTRAINT commentmenu_fk_menu FOREIGN KEY(menu_id) REFERENCES menu(id)
/*表的外键*/	
);
	