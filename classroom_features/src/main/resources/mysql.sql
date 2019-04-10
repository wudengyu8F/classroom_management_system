CREATE TABLE user (
id int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
user_id int(11) unsigned NOT NULL DEFAULT '0' COMMENT '学号',
username varchar(20) DEFAULT '0' COMMENT '用户名',
password varchar(50) DEFAULT '0' COMMENT '密码',
sex tinyint(1) DEFAULT '0' COMMENT '性别(0:男;1:女)',
roles varchar(30) DEFAULT 'student' COMMENT '角色(student;admin)',
PRIMARY KEY (id),
UNIQUE KEY `uniq` (user_id),
KEY `search_key` (user_id,password)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

insert into user (user_id,username,password,sex) value (10000111,"zhangsan","E10ADC3949BA59ABBE56E057F20F883E",0),
(10000112,"lisi","E10ADC3949BA59ABBE56E057F20F883E",1);

create table classroom(
id int(11) unsigned not null auto_increment comment 'id',
teaching_building varchar(10) not null default '0' comment '教学楼',
room_number int(11) unsigned not null DEFAULT '0' comment '教室号',
room_local varchar(20) default '0' comment '教学楼位置',
seats_number int(11) unsigned default '0' comment '教室座位数量',
multimedia_equipment varchar(50) default '0' comment '教室器材信息',
primary key (id),
unique key `uniq` (teaching_building,room_number)
)ENGINE=InnoDB DEFAULT  charset=utf8 comment = '教室表';


insert into classroom (teaching_building, room_number, room_local, seats_number, multimedia_equipment) value ("教1",1,"东南角",120,"电脑，投影仪"),
("教1",2,"东南角",120,"电脑，投影仪"),("教1",3,"东南角",90,"电脑，投影仪，化学仪器"),("教2",1,"东北角",90,"电脑，投影仪，物理仪器"),("教2",2,"东北角",120,"电脑，投影仪");



create table reservation_info(
id int(11) unsigned not null auto_increment comment 'id',
room_id int(11) unsigned not null DEFAULT '0' comment '教室唯一id(教室表中的教室id)',
date int(8) unsigned NOT NULL DEFAULT '0' COMMENT '预约日期(20181205)',
time int(1) unsigned not null default '0' comment '预约时间(1:1.2节;2:3.4节;3:5.6节;4:7.8节;5:9.10节)',
user_id int(11) unsigned NOT NULL DEFAULT '0' COMMENT '预约用户学号',
username varchar(20) DEFAULT '0' COMMENT '预约用户姓名',
reservation_desc varchar(50) default '' comment '预约理由描述',
is_pass int(1) default '0' comment '是否通过(0:未通过;1:通过;2:取消申请;3:驳回)',
reject_desc varchar(50) default '不同意' comment '驳回理由',
primary key (id),
unique key `uniq` (room_id,date,user_id,time),
key `user_search` (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约信息表';


insert into reservation_info (room_id, date, time, user_id, username,reservation_desc,is_pass)  value
(1,20190306,4,10000111,"zhangsan","计算机协会活动",0),(1,20190306,4,10000112,"lisi","欢乐时光",0),
(2,20190306,4,10000111,"zhangsan","计算机协会活动",0),(2,20190306,4,10000112,"lisi","欢乐时光",1);

create table class_schedule(
id int(11) unsigned not null auto_increment comment 'id',
room_id int(11) unsigned not null DEFAULT '0' comment '教室唯一id(教室表中的教室id)',
subject_id int(11) unsigned not null DEFAULT '0' comment '课程唯一id',
date int(8) unsigned NOT NULL DEFAULT '0' COMMENT '日期(20181205)',
time int(1) unsigned not null default '0' comment '上课时间(1:1.2节;2:3.4节;3:5.6节;4:7.8节;5:9.10节)',
teacher_id int(11) unsigned not null default '0' comment '教师id',
primary key (id),
UNIQUE KEY `uniq` (room_id,date,time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程表';
