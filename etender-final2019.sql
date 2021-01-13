
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

drop table downloads;
CREATE TABLE `downloads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(250) NOT NULL,
  `seo_title` varchar(250) DEFAULT NULL,
  `description` text,
  `author` varchar(250) NOT NULL,
  `url` text NOT NULL,
  `download_count` int(10) NOT NULL DEFAULT '0',
  `created` int(11) DEFAULT NULL,
  `public` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `downloads` (`id`, `title`, `seo_title`, `description`, `author`, `url`, `download_count`, `created`, `public`)
VALUES
	(38,'Notification.pdf',NULL,NULL,'1','/uploads/tender/tender_46_5179026ea70ef.pdf',661,NULL,1),
	(39,'Notice Inviting Tender.pdf',NULL,NULL,'176','/uploads/tender/tender_47_518b8deb5879d.pdf',470,NULL,1);
    
    
    
    
    CREATE TABLE `tender_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tender_number` text NOT NULL,
  `issue_date` int(11) NOT NULL,
  `last_date_of_submission` int(11) NOT NULL,
  `opening_date` int(11) NOT NULL,
  `department` int(11) NOT NULL,
  `subject` text NOT NULL,
  `detail` longtext NOT NULL,
  `author` int(11) NOT NULL,
  `attachment` varchar(60) NOT NULL,
  `created` int(11) NOT NULL,
  `modified` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `tender_main` (`id`, `tender_number`, `issue_date`, `last_date_of_submission`, `opening_date`, `department`, `subject`, `detail`, `author`, `attachment`, `created`, `modified`)
VALUES
	(1,'No.D.24012/1/2012-DTE(AGR-INM)',1347388200,1348554600,1348558200,14,'Tender Notice for Supply of Slaked Lime','Sealed Tenders are invited from the Manufacturing Firms for supply of Slaked Lime under MMA 2012 as mentioned in the Attachment. Details and prescribed forms can be obtained by paying Rs. 100/- from INM Section, Directorate of Agriculture within office hours.',4,'1',1348138228,1348138228),
	(2,'No.D.11015/2/2009-NLUP',1346610600,1348824600,1348826400,15,'Tender Notice for lease of Cold Storages','Sealed Tenders are invited from the interested Firms for lease of Cold Storages at Champhai, Vairengte and Serchhip',5,'2,3',1348140432,1348739377),
	(3,'No.ASC/AGR/2012-12/01',1348425000,1349937000,1349940600,14,'Tender Notice for Supply of Laboratory Equipment, Chemicals & Glassware.','Short Sealed Tenders in prescribed form are invited from Manufacturing Company/Firm or Authorized Dealer for supply of Laboratory Equipment, Chemicals & Glassware for use in Soil Testing Laboratory & Bio-fertilizer Production Unit.',4,'4',1348734293,1348739886),
	(5,'No. T-11019/03/11-CE(SO)/P&E/48',1348511400,1351233000,1351240200,29,'Corrigendum','The Notice inviting tender No.T-11019/03/10-CE(SO)/P&E/37 of Dt. 13.8.2012 issued by Chief Engineer, System Operation, P&E Department, Mizoram is hereby extended upto 12:00 hrs on Dt. 26.10.2012 due to un-avoidable circumstances, and will be opened on the same day at 14:00 Hrs.\r\n\r\nAll other terms and conditions remain unchanged.',19,'',1348831400,1348831400),
	(6,'No.D.21013/2/2008-COMEX',1348425000,1349937000,1349940600,21,'Quotation invited for supply of machinery & equipment items','Sealed Quotations in plain paper are hereby invited from the local suppliers for supply of the machinery and equipment items (mentioned in attachment). Detail can be seen in the Office of Commissioner of Excise & Narcotics',21,'5',1349077693,1349077693),
	(7,'No.B.12022/13/2012-DTE (AGR-OP)',1349289000,1351668600,1351670400,14,'Tender Notice for supply of Oil Palm Harvesting Tools','Sealed Tender are invited from the Manufacturers/Authorized Dealers for supply of Oil Palm Harvesting Tools for Oil Palm Area Expansion under RKVY during 2012-2013 in Mizoram. Detail can be seen in Notice Board of Directorate of Agriculture (Crop Husbandry) during office hours.',4,'6',1349341071,1349341071);
	