
create schema tender;
use tender;
drop schema tender;

CREATE TABLE `tender_supplier_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(50) NOT NULL,
  `department_subscription` varchar(250) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tender_department_user` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `user_id` int(50) NOT NULL,
  `department_id` int(11) NOT NULL,
  `designation` varchar(100) NOT NULL,
  `district` varchar(200) NOT NULL,
  `approved` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


 drop table user;
CREATE TABLE `user` (
  `user_id`int(50) NOT NULL AUTO_INCREMENT,
  `user_full_name` varchar(250) NOT NULL,
  `user_login` varchar(100) NOT NULL,
  `user_password` varchar(200) NOT NULL,
  `user_password_salt` varchar(32) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `user_phone` varchar(20) DEFAULT NULL,
  `user_address` text,
  `user_role` varchar(100) NOT NULL DEFAULT 'normal',
  `user_created_date` int(10) unsigned NOT NULL,
  `user_last_logged` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE Key `email` (`email`)  
#  UNIQUE KEY `user_login` (`user_login`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

drop table role; 

CREATE TABLE `role`(

   `id`int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,

  PRIMARY KEY (`id`)


) ENGINE=InnoDB DEFAULT CHARSET=latin1;# AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

drop table user_role;

CREATE TABLE `tender_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `color` varchar(10) NOT NULL DEFAULT '333333',
  `parent` int(11) NOT NULL,
  `user_id` int(50) ,
  `created` int(11) unsigned NOT NULL,
  `updated` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `tender_department` WRITE;
/*!40000 ALTER TABLE `tender_department` DISABLE KEYS */;

drop table `tender_department` ;

INSERT INTO `tender_department` (`id`, `name`, `color`, `parent`, `user_id`, `created`, `updated`)
VALUES
	(1,'Public Works Department','f49338',0,1,1345196159,1345201278),
	(2,'Public Health Engineering','6bdb0f',0,1,1345196166,1345201291),
	(3,'Animal Husbandry & Veterinary','bf5f00',0,1,1345201115,1345201127),
	(4,'Power & Electricity','aa56ff',0,1,1345201170,1345201170),
	(5,'Crop Husbandry','1d66af',7,1,1345201336,1345204732),
	(6,'ZENICS','bf0000',0,1,1345201382,1345201382),
	(7,'ZIDCO','ff00ff',0,1,1345201398,1345201398),
	(12,'R&E','3f7f00',13,1,1348137363,1352884531),
	(13,'Agriculture','aaffaa',0,1,1348137406,1348137406),
	(14,'Crop Husbandry','00bf00',13,1,1348137453,1348138771),
	(15,'NLUP','3f7f00',0,1,1348138881,1348138881),
	(16,'Aizawl Municipal Council','bf0000',0,1,1348205411,1348205411),
	(17,'Administrative Training Institute','007f3f',0,1,1348205503,1348205503),
	(18,'Cooperation','7f3f00',0,1,1348205568,1348205568),
	(19,'Geology & Mineral Resources','007f00',0,1,1348205631,1348205631),
	(20,'Disaster Management & Rehabilitation','007f7f',0,1,1348205730,1348205730);

use app_db;

drop table user_role;

CREATE TABLE `user_role` (
  `user_email` varchar(50) NOT NULL,
 `role_id` int(40) NOT null ,
 
CONSTRAINT FK_USER_1
    FOREIGN KEY (`user_email`) 
  REFERENCES user (`email`) ,
  
  CONSTRAINT FK_ROLE_2
    FOREIGN KEY (`role_id`) 
  REFERENCES role (`id`) 
  
  )ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `District` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40),
 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `District` (`id`, `name`)
VALUES
	(1,'AIZAWL'),
    (2,'CHAMPHAI'),
    (3,'KOLASIB'),
    (4,'MAMIT'),
    (5,'LUNGLEI'),
    (6,'SIAHA');

