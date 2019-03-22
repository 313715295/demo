# Host: 127.0.0.1  (Version: 5.6.40-log)
# Date: 2018-06-21 23:21:33
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES gb2312 */;

#
# Structure for table "order_"
#

DROP TABLE IF EXISTS `order_`;
CREATE TABLE `order_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creatTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `code` varchar(50) DEFAULT NULL,
  `uid` smallint(5) unsigned DEFAULT NULL,
  `sum` mediumint(8) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order_"
#


#
# Structure for table "orderitem_"
#

DROP TABLE IF EXISTS `orderitem_`;
CREATE TABLE `orderitem_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) DEFAULT NULL,
  `count` smallint(5) unsigned DEFAULT NULL,
  `oid` mediumint(8) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_oid` (`oid`),
  KEY `idx_tid` (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

#
# Data for table "orderitem_"
#


#
# Structure for table "tea"
#

DROP TABLE IF EXISTS `tea`;
CREATE TABLE `tea` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `price` decimal(10,0) unsigned DEFAULT NULL,
  `stocks` smallint(6) unsigned DEFAULT NULL,
  `img` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

#
# Data for table "tea"
#

INSERT INTO `tea` VALUES (1,'°×²è',100,177,'°×²è1528257524815.jpg'),(2,'ºì²è',100,160,'ºì²è1528434004580.jpg'),(3,'Áú¾®',100,176,'Áú¾®.jpg'),(4,'»Æ½ðÑ¿',200,175,'»Æ½ðÑ¿.jpg'),(8,'²âÊÔ1',1,2,'²âÊÔ11527763121130.jpg'),(9,'²âÊÔ2',2,8,'²âÊÔ21529582498281.jpg'),(10,'test',1,13,'test1527828798638.jpg');

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  `autho` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`),
  KEY `idx_autho` (`autho`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'admin','admin',0);
