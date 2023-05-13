-- 创建数据库：
create database student_gui CHARACTER SET UTF8;
use student_gui;
-- 创建管理员表
CREATE TABLE `manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
-- 插入一个管理员用于登录
insert into manager(user_name,pwd) values('admin','123456');
-- 创建学生表

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `no` varchar(50) NOT NULL,
  `home_town` varchar(200) NOT NULL,
  `cn_score` decimal(5,2) NOT NULL DEFAULT '0.00',
  `en_score` decimal(5,2) NOT NULL DEFAULT '0.00',
  `math_score` decimal(5,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;