create table if not exists menu(			/*菜品表的创建*/
	id int(10) primary key, 			/*菜品编号*/
	cname varchar(30), 				/*菜品名称*/
	uid varchar(10), 				/*上传者*/
	primaryPic varchar(30),				/*菜品主图*/
	difficulty varchar(10),				/*制作难度*/
	flavour varchar(10), 				/*菜品口味*/
	ctime varchar(10), 				/*制作时间*/
	tools varchar(10),				/*厨具*/
	craft varchar(10),				/*制作工艺*/
	food varchar(500),				/*菜品主料*/
	codiments varchar(500)，				/*菜品辅料*/
	introduction varchar(500),			/*菜品简介*/
	tips varchar(50),				/*小技巧*/
	slike int(5),					/*收藏人数*/
	collection int(5),				/*评论人数*/
	pinglun int(5),					/*上传时间*/
	uploadTime datetime,				/*喜好人数*/
	sculture varchar(20)				/*上传者头像*/
);
	
create table random(					/*创建随拍表*/			
	id int(10) primary key,				/*随拍编号*/
	uid varchar(10),				/*上传者*/
	sculture varchar(20),				/*上传者头像*/
	uploadTime datetime,				/*上传时间*/
	picPath varchar(500),				/*图片名称*/
	introduce varchar(500),				/*随拍简介*/
	label varchar(20),				/*标签*/
	city varchar(20),				/*所在城市*/
	slike int(5),					/*喜好的人数*/
	collection int(5),				/*收藏的人数*/
	pinglun int(5)					/*评论人数*/
);

create table pro(					/*创建制作过程表*/
	id int(10) primary key,
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

create table labels(						/*创建标签表*/
	label varchar(20) primary key	
);
