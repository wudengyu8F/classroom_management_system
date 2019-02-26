CREATE TABLE user (
id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
user_id int(11) unsigned NOT NULL DEFAULT '0' COMMENT '学号',
username varchar(20) DEFAULT '0' COMMENT '用户名',
password varchar(50) DEFAULT '0' COMMENT '密码',
sex tinyint(1) DEFAULT '0' COMMENT '性别 0：男，1：女',
salt varchar(50) DEFAULT '0' COMMENT '加盐',
role varchar(30) DEFAULT 'student' COMMENT '角色',
perms varchar(30) DEFAULT 'user:search' COMMENT '权限,相互的权限以逗号分隔 user:search,user:update',
PRIMARY KEY (id),
UNIQUE KEY `uniq` (user_id),
UNIQUE KEY `search_key` (user_id,password)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

insert into user (user_id,username,password,sex,salt) values (10000111,"zhangsan","E10ADC3949BA59ABBE56E057F20F883E",0,"akdsfywiaxjcasdfa"),
(10000112,"lisi","E10ADC3949BA59ABBE56E057F20F883E",1,"cvuqnadsfuwjaisudn");

