-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- ����: localhost
-- ��������: 2015 �� 03 �� 28 �� 14:57
-- �������汾: 5.0.51
-- PHP �汾: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- ���ݿ�: `b_150203_tushu`
-- 
CREATE DATABASE `b_150203_tushu` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `b_150203_tushu`;

-- --------------------------------------------------------

-- 
-- ��Ľṹ `admin`
-- 

CREATE TABLE `admin` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `screen_name` varchar(20) NOT NULL,
  `type` varchar(1) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

-- 
-- �������е����� `admin`
-- 

INSERT INTO `admin` VALUES (1, 'admin', 'admin', '����Ա', '0');
INSERT INTO `admin` VALUES (2, 'a', '1 ', 'aaaa ', '1');
INSERT INTO `admin` VALUES (3, 'b', '1 ', '123aaa ', '0');

-- --------------------------------------------------------

-- 
-- ��Ľṹ `info_info`
-- 

CREATE TABLE `info_info` (
  `info_id` int(11) NOT NULL auto_increment,
  `info_name` varchar(20) NOT NULL,
  `info_pic` varchar(200) NOT NULL,
  `info_description` varchar(5000) NOT NULL,
  `info_people` varchar(200) NOT NULL,
  `info_pphone` varchar(10) NOT NULL,
  `info_flag` int(11) NOT NULL,
  `info_count` varchar(10) NOT NULL default '0',
  PRIMARY KEY  (`info_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=44 ;

-- 
-- �������е����� `info_info`
-- 

INSERT INTO `info_info` VALUES (41, 'ϲ��', '111.jpg', '��ϲС�����ҵ�����', 'ë��С����', '002', 2, '0');
INSERT INTO `info_info` VALUES (42, 'Ѱ��', '112.jpg', '���ϣ��к������ڿ��۾�����ϵ�绰��12345678901', 'ë��С����', '001', 2, '0');
INSERT INTO `info_info` VALUES (43, 'activity', '003.png', 'ǰ30����ע�˻���û����ɻ����ѵĵ�ӰƱŶ��', 'ѾѾ', '003', 2, '0');

-- --------------------------------------------------------

-- 
-- ��Ľṹ `orders`
-- 

CREATE TABLE `orders` (
  `id` int(11) NOT NULL auto_increment,
  `info_name` varchar(200) NOT NULL,
  `user_name` varchar(200) NOT NULL,
  `addtime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `flag` varchar(2) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

-- 
-- �������е����� `orders`
-- 

INSERT INTO `orders` VALUES (12, 'activity', 'b', '2015-03-28 15:55:15', '-1');

-- --------------------------------------------------------

-- 
-- ��Ľṹ `type`
-- 

CREATE TABLE `type` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `xuehao` varchar(100) NOT NULL,
  `jiaoshi` varchar(100) NOT NULL,
  `addtime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `flag` varchar(10) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

-- 
-- �������е����� `type`
-- 

INSERT INTO `type` VALUES (1, '123', '20095555', '111111', '0000-00-00 00:00:00', '1');
INSERT INTO `type` VALUES (4, 'yaya', '12345678901', 'lili', '2015-03-28 15:56:44', '1');

-- --------------------------------------------------------

-- 
-- ��Ľṹ `user_tb`
-- 

CREATE TABLE `user_tb` (
  `uid` int(11) NOT NULL auto_increment,
  `user_userid` varchar(32) default NULL,
  `user_username` varchar(16) default NULL,
  `user_password` varchar(32) default NULL,
  `user_phone` varchar(32) default NULL,
  `user_address` varchar(255) default NULL,
  `user_grade` int(11) default NULL,
  PRIMARY KEY  (`uid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

-- 
-- �������е����� `user_tb`
-- 

INSERT INTO `user_tb` VALUES (1, '1', '1', '1', '1', '1', 0);
INSERT INTO `user_tb` VALUES (2, 'b', 'b', 'b', 'b', 'b', 0);
INSERT INTO `user_tb` VALUES (3, 'a', 'abc', 'b', 'a', 'a', 0);
INSERT INTO `user_tb` VALUES (4, 'aaa', 'aaabbb', 'a', '1399991000', 'zhongguo', 0);
INSERT INTO `user_tb` VALUES (5, 'ad', '123', '1', '258', '1234', 0);
INSERT INTO `user_tb` VALUES (7, '2', 'jtwmjp', '1', '15987453608', 'adjgm', 0);
INSERT INTO `user_tb` VALUES (8, '3', 'jamd', '123', '13856982258', 'chd', 0);



REVOKE ALL PRIVILEGES ON * . * FROM 'root'@'localhost';

GRANT ALL PRIVILEGES ON * . * TO 'root'@'localhost' WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;


