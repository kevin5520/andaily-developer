-- MySQL dump 10.13  Distrib 5.5.15, for Win32 (x86)
--
-- Host: localhost    Database: andaily_developer
-- ------------------------------------------------------
-- Server version	5.5.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `backlog`
--

DROP TABLE IF EXISTS `backlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `backlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `content` text,
  `priority` varchar(255) DEFAULT 'DEFAULT',
  `type_` varchar(255) DEFAULT 'NEEDS',
  `sprint_id` int(11) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `join_sprint_user_id` int(11) DEFAULT NULL,
  `join_sprint_time` datetime DEFAULT NULL,
  `estimate_time` int(11) DEFAULT '0',
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `sprint_id_index` (`sprint_id`),
  KEY `join_sprint_user_id_index` (`join_sprint_user_id`),
  KEY `guid_index` (`guid`),
  KEY `creator_id_index` (`creator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `backlog`
--

LOCK TABLES `backlog` WRITE;
/*!40000 ALTER TABLE `backlog` DISABLE KEYS */;
INSERT INTO `backlog` VALUES (1,'b6e6c2e021a04f9b9494f510ddcc406c','2013-09-08 11:35:33',0,'需要添加用户的引用到当前的操作, 当创建task, sprint, backlog等.','DEFAULT','NEEDS',7,NULL,NULL,NULL,7,5),(2,'5f8a47641a5444afbb7f08ad740929d3','2013-09-08 11:37:33',0,'asfdsffsdfewewsd','DEFAULT','NEEDS',7,NULL,NULL,NULL,3,5),(3,'748af5cde3ca485d80422a7886d6a858','2013-09-09 23:41:59',0,'当用户添加一个销售人员时,系统要检查销售人员的资格是否合适,是否俱有售卖资格,并需要上传个人下面的图片作为参考.等等.<br />\r\n需要要剪切功能哦.','DEFAULT','NEEDS',7,NULL,NULL,NULL,4,5),(4,'a3e4938a2e0449c98ddfbe2bbcb5ef47','2013-09-10 21:26:09',1,'asfwfeefsd','DEFAULT','NEEDS',NULL,NULL,NULL,NULL,0,5),(5,'154a3fb580964fc6b2e0916cb3cdaf8f','2013-09-10 22:40:26',0,'High ...... bugs','HIGH','BUGS',7,NULL,NULL,NULL,1,5),(6,'9eb32ed0e4b8454ba1ee69845ad1f5fd','2013-09-11 22:58:51',0,'asfdfsfasfaaewfwe','DEFAULT','NEEDS',7,NULL,NULL,NULL,3,5),(7,'0f95f4e04db441dc9346a6e48389cbd5','2013-11-06 22:50:20',0,'New backlog','DEFAULT','NEEDS',NULL,NULL,NULL,NULL,2,1),(8,'421acc88aea942e6b0063236ae7c2275','2013-11-16 11:50:36',0,'When super man(initial user,only one) login system, a developer managment menu will be show on the dashboard; another menus include system log; report<br />\r\n<br />\r\nSuper man manage project too(a menu call Project) <strong>???</strong><br />\r\nor allow Scrum master create project..<br />\r\n<br />\r\nHe can add/edit/archive/reset password of developer.&nbsp;<br />\r\n<br />\r\nDefine a domain call Team(super man manage it), a team include Product owner(one or more); Scrum master(one or more) ; Scrum memeber(one or more); team name<br />\r\n<br />\r\nA team have one or more projects.<br />\r\n<br />\r\n--------------------------------------------------------------------<br />\r\n<br />\r\nSuper man have 4 menus: Developer; Team; Monitor and Report<br />\r\n<br />\r\nOne team have one or more developer(include 3 roles developer: Scrum master, scrum member and product owner)<br />\r\n<br />\r\n&nbsp;','DEFAULT','NEEDS',NULL,NULL,NULL,NULL,10,5),(9,'f64fc79406f94b3eadca7f1b32326950','2013-11-16 12:10:00',1,'fasdfdssdfsd','DEFAULT','NEEDS',NULL,NULL,NULL,NULL,3,2),(10,'748740022b22473a81ea6517415ca6ac','2013-11-16 12:10:11',0,'asfsdfdsfsddfsfdsfdsfsdfdffd','DEFAULT','NEEDS',NULL,NULL,NULL,NULL,1,2),(11,'68a9babf5c7b4837a5f8c4ca80354507','2013-11-16 12:10:20',0,'sffsdssfd','DEFAULT','NEEDS',NULL,NULL,NULL,NULL,1,2);
/*!40000 ALTER TABLE `backlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `name_` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `description` text,
  `finish_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `creator_id_index` (`creator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'0e24f4ed47714b5b85f9cf44a8d35c71','2013-10-22 22:32:27',0,'Gecko','GK',NULL,'safsdfsf',NULL),(2,'71323ef8f6724a42b40347fa1e17c4d8','2013-10-22 22:40:34',0,'Rubbit','RB',NULL,'Andaily是一个项目<br />\r\n<br />\r\n<strong>要完成日常的管理.&nbsp;</strong><br />\r\n<br />\r\n&nbsp;','2013-12-31'),(3,'eee052533c3940369573656f53ca594d','2013-10-29 22:47:34',1,'asffsfdsdf','',NULL,'sfsfds','2013-10-30'),(4,'f5efa38e6a7844d49321dc40cb035967','2013-10-29 22:48:16',1,'ate','',NULL,'afdsdsf','2013-10-30'),(5,'b7da5d807c8c443fa73d19c43ccf6718','2013-10-29 23:08:00',0,'Andaily-developer','AD',NULL,'Andaily-developer , from Andaily .','2013-12-31');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_developer`
--

DROP TABLE IF EXISTS `project_developer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_developer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `project_id` int(11) DEFAULT NULL,
  `developer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `developer_id_index` (`developer_id`),
  KEY `project_id_index` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_developer`
--

LOCK TABLES `project_developer` WRITE;
/*!40000 ALTER TABLE `project_developer` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_developer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scrum_comment`
--

DROP TABLE IF EXISTS `scrum_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scrum_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `developer_id` int(11) DEFAULT NULL,
  `comment_time` datetime DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `developer_id_index` (`developer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scrum_comment`
--

LOCK TABLES `scrum_comment` WRITE;
/*!40000 ALTER TABLE `scrum_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `scrum_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sprint`
--

DROP TABLE IF EXISTS `sprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sprint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `creator_id` int(11) DEFAULT NULL,
  `name_` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `status` varchar(255) DEFAULT 'CREATED',
  `pending_time` datetime DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `archive_time` datetime DEFAULT NULL,
  `archive_user_id` int(11) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `default_sprint` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `creator_id_index` (`creator_id`),
  KEY `archive_user_id_index` (`archive_user_id`),
  KEY `guid_index` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sprint`
--

LOCK TABLES `sprint` WRITE;
/*!40000 ALTER TABLE `sprint` DISABLE KEYS */;
INSERT INTO `sprint` VALUES (1,'64b946dee6a14782996e931bc5abf4e8','2013-08-17 09:31:38',0,NULL,'Andaily 0.1','2013-08-29','2013-08-30','FINISHED','2013-08-29 00:16:00','2013-08-29 00:30:41',NULL,NULL,'OK. this is the first bll.a<br />\r\n&nbsp;<br />\r\nscrum.sad<br />\r\nsfwe<br />\r\nwefwefsf111111<a href=\"http://asfdds.com\">111111111</a>',5,0),(2,'56b95a8b9a4a49ab9c98cbf4086bf952','2013-08-17 09:32:37',0,NULL,'Andaily 0.2','2013-08-22','2013-08-31','PENDING','2013-08-22 00:01:44',NULL,NULL,NULL,'<p>safsaf<strong>asfsf</strong></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<ol>\r\n	<li><strong>safwasf</strong></li>\r\n</ol>\r\n\r\n<p><strong>​sfwfewfesdf</strong></p>\r\n\r\n<p>&nbsp;</p>\r\n',5,0),(3,'a6110f3206c544f89366a96fd8c7f584','2013-08-23 00:54:55',1,NULL,'afsdfwefsdf','2013-08-23','2013-08-24','CREATED','2013-08-23 00:55:10',NULL,NULL,NULL,'test',5,0),(4,'937ca6dc485c41f6a66c605cf340dc95','2013-08-27 23:33:56',0,NULL,'Andaily 0.2.1','2013-08-28','2013-09-05','PENDING','2013-09-03 23:06:05',NULL,NULL,NULL,'<strong>Think module codes</strong>',5,0),(5,'8d04a0ab4aa14b23814df398a2dcf74b','2013-08-29 01:16:33',0,NULL,'Andaily Developer 0.1','2013-08-29','2013-09-15','FINISHED','2013-08-29 01:16:51','2013-10-02 15:51:28',NULL,NULL,'<em>The developer moduel first scrum.</em><br />\r\n<br />\r\nSupport baisc function for monitor the development tasks.',5,0),(6,'0c4fb3f274a940e6b82e82b0e10b687d','2013-09-02 01:01:42',0,NULL,'test sprint1122','2013-09-04','2013-09-05','FINISHED','2013-09-04 00:12:50','2013-09-04 00:13:30',NULL,NULL,'tesste',5,0),(7,'15279b3104884dcaacd84218d28708fb','2013-09-02 01:02:03',0,NULL,'test sprint9999','2013-09-02','2013-09-13','CREATED',NULL,NULL,NULL,NULL,'testedfsdf',5,0),(8,'5dae5f29fdf34526a88b727bd0ce9673','2013-09-04 00:19:50',1,NULL,'testaaaa','2013-09-04','2013-09-05','CREATED',NULL,NULL,'2013-09-04 00:20:22',NULL,'testsss',5,0),(9,'10236f7c90864547acfd5fc0d7200578','2013-09-04 00:20:40',0,NULL,'testing ....','2013-09-04','2013-09-13','FINISHED','2013-09-04 00:20:52','2013-09-04 00:21:54',NULL,NULL,'test',5,0),(10,'13ba304bffe84017b11bf4636b08c0bb','2013-09-04 00:22:56',0,NULL,'testafasweewwe','2013-09-04','2013-09-07','FINISHED','2013-09-04 00:23:16','2013-09-04 00:23:27',NULL,NULL,'afssdfdsf',5,0),(11,'c224060ab8a34a6db0a85d7713acd09e','2013-09-21 22:55:28',0,NULL,'test deadline sprint','2013-09-21','2013-09-22','FINISHED','2013-09-21 22:56:44','2013-09-21 23:07:55',NULL,NULL,'test sprint for deadline',5,0),(12,'099b7147ef0a45fbbfd77cde635e72a2','2013-09-29 21:00:50',0,NULL,'Andaily Developer 0.2','2013-10-09','2013-10-23','FINISHED','2013-10-09 15:07:57','2013-11-13 22:27:14',NULL,NULL,'Go on developing Andaily Developer open soure project, in the sprint, we will finish the tasks as follow:\r\n<ol>\r\n	<li>Move the Andaily Developer codes from Andaily project, it will be a independent project. at the same time, keep the moved codes in Andaily project(do not use remove it).</li>\r\n	<li>Implement Project module actions. for example: Project management, Refer project with backlog,sprint, project memeber managment...</li>\r\n	<li>Do the remain tasks from &lt;Andaily Developer 0.1&gt; sprints</li>\r\n	<li>Implement documents action. Allow use upload and download backlog document and so on;</li>\r\n	<li>Check, test and upgrade the two sprints finished&nbsp;actions.</li>\r\n	<li>Allow use move created task to another sprint</li>\r\n</ol>\r\n',5,0),(13,'0fb95a12e90940d3a77aa00d0b704997','2013-09-29 21:09:24',0,NULL,'Andaily Developer 0.3(Security)','2013-11-16','2013-12-16','PENDING','2013-11-16 11:06:27',NULL,NULL,NULL,'In the sprint, we will add security for the project, includ tasks: login/logout; define roles(Product owner,Scrum master,Team member); different role show reference menu and actions; ......',5,1),(14,'d16cfedc0a2546e8a9c318ceeff6f993','2013-11-06 23:15:06',0,NULL,'gecko-0.1','2013-11-22','2013-11-30','CREATED',NULL,NULL,NULL,NULL,'teaf',1,0),(15,'8d59b466dfa947ecb108a7ead2e3213a','2013-11-16 12:19:56',1,NULL,'test3333','2013-11-16','2013-11-22','CREATED',NULL,NULL,'2013-11-16 12:23:51',NULL,'test',2,0),(16,'0108df906ac440a68970e400eb73d9e8','2013-11-16 12:20:19',0,NULL,'Test3232','2013-11-16','2013-11-23','CREATED',NULL,NULL,NULL,NULL,'asfsadff',2,0),(17,'bc36a164c92a481c8c7bdde5db94d44b','2013-11-20 22:03:16',0,NULL,'Andaily Developer 0.4','2014-01-01','2014-01-15','CREATED',NULL,NULL,NULL,NULL,'A planing sprint in the future.',5,0);
/*!40000 ALTER TABLE `sprint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sprint_meeting`
--

DROP TABLE IF EXISTS `sprint_meeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sprint_meeting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `meeting_date` date DEFAULT NULL,
  `type_` varchar(255) DEFAULT NULL,
  `content` text,
  `sprint_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `sprint_id_index` (`sprint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sprint_meeting`
--

LOCK TABLES `sprint_meeting` WRITE;
/*!40000 ALTER TABLE `sprint_meeting` DISABLE KEYS */;
INSERT INTO `sprint_meeting` VALUES (20,'0e587c9a7fa742bda15e4dbc9a8a3b3c','2013-10-01 17:27:00',0,'2013-10-01','DAILY_STANDING','okokok',5),(21,'a9ae0015a3f342259a21d46ee9a243c0','2013-10-01 17:45:55',0,'2013-10-01','DAILY_STANDING','editor.getData()asfasf',5),(22,'4f5811ed01434f89be82185d22d06ae1','2013-10-01 17:52:04',0,'2013-09-30','DAILY_STANDING','Status:&nbsp;<br />\r\n<br />\r\n<br />\r\nChallenge:<br />\r\n<br />\r\n<br />\r\nImport updates:<br />\r\n&nbsp;',5),(23,'ad82835fd54e4e898adf8de347f0243a','2013-10-01 21:38:19',0,'2013-09-29','DAILY_STANDING','Status: #23 finished<br />\r\n<br />\r\nChallenge: no',5),(24,'61cdfc743c18411899574c2112326cae','2013-10-01 21:54:06',0,'2013-09-28','SPRINT_PLANNING','Start to do the sprint',5),(25,'fd40368b7dac4fd582191b71f077e36b','2013-10-02 14:29:21',0,'2013-10-02','SPRINT_REVIEW','review',5),(26,'063524a098c74179abce855b79c8014d','2013-10-02 14:29:37',0,'2013-10-02','RETROSPECTIVE','retrospective meeting<br />\r\n<br />\r\nmeetingSprint',5),(27,'a2781b99664e46c48db86a8c81306b3c','2013-10-02 14:38:09',0,'2013-10-02','DAILY_STANDING','asfdsdfsd',5),(28,'f21d5f867515480b82bc9fef72e92652','2013-10-02 14:43:19',0,'2013-10-02','DAILY_STANDING','asfdddddddddddddddddddddddddddddddd',5),(29,'32a18e4227604cf884285b27540eb7a2','2013-10-02 14:45:18',0,'2013-09-30','DAILY_STANDING','sffffffffffffffffffff',5),(30,'272b50d29f9a40d3a770dd32fafb127f','2013-10-02 14:46:48',0,'2013-09-28','RETROSPECTIVE','sdddddddddddddddddd<strong>OKOKOK</strong>',5),(31,'d8c5078d52c242fca5c4142f7353ed98','2013-10-02 14:53:55',0,'2013-10-01','SPRINT_PLANNING','sfdfafsfsf',5),(32,'c175460b15e5446eb841e8b500b231d5','2013-10-02 14:54:30',0,'2013-10-01','RETROSPECTIVE','ssssssssssssssssssssssssssss',5),(33,'489f83b7a72e4210ac8d807458fc3e34','2013-11-11 23:08:41',0,'2013-11-11','DAILY_STANDING','sadffsddf',14),(34,'d58a4141684e4cd3a042ef8706f51846','2013-11-16 20:33:41',0,'2013-11-16','RETROSPECTIVE','Finish the sprint',12);
/*!40000 ALTER TABLE `sprint_meeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sprint_meeting_developer`
--

DROP TABLE IF EXISTS `sprint_meeting_developer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sprint_meeting_developer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `meeting_id` int(11) DEFAULT NULL,
  `developer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `developer_id_index` (`developer_id`),
  KEY `meeting_id_index` (`meeting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sprint_meeting_developer`
--

LOCK TABLES `sprint_meeting_developer` WRITE;
/*!40000 ALTER TABLE `sprint_meeting_developer` DISABLE KEYS */;
/*!40000 ALTER TABLE `sprint_meeting_developer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sprint_task`
--

DROP TABLE IF EXISTS `sprint_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sprint_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `name_` varchar(255) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `estimate_time` int(11) DEFAULT '0',
  `actual_used_time` int(11) DEFAULT '0',
  `sprint_id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT 'CREATED',
  `pending_time` datetime DEFAULT NULL,
  `executor_id` int(11) DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `cancel_time` datetime DEFAULT NULL,
  `cancel_user_id` int(11) DEFAULT NULL,
  `priority` varchar(255) DEFAULT 'DEFAULT',
  `urgent` tinyint(1) DEFAULT '0',
  `backlog_id` int(11) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `finish_comment` varchar(255) DEFAULT NULL,
  `larger_max_time` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `creator_id_index` (`creator_id`),
  KEY `sprint_id_index` (`sprint_id`),
  KEY `executor_id_index` (`executor_id`),
  KEY `cancel_user_id_index` (`cancel_user_id`),
  KEY `backlog_id_index` (`backlog_id`),
  KEY `guid_index` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sprint_task`
--

LOCK TABLES `sprint_task` WRITE;
/*!40000 ALTER TABLE `sprint_task` DISABLE KEYS */;
INSERT INTO `sprint_task` VALUES (1,'26a6830ffc0741a4b86ec6ed7e6e5e8c','2013-08-18 14:42:29',0,'Sprint task form',NULL,180,180,2,'FINISHED','2013-09-29 22:44:10',NULL,'2013-09-29 22:44:20',NULL,NULL,'DEFAULT',0,NULL,'<p>asfsf</p>\r\n',NULL,0),(2,'1fae9ec7f7bd4aa0927d9ea8aca99fab','2013-08-18 14:49:59',0,'190. Integration test auto start thing scheduler, it\'s not work  ',NULL,240,240,2,'FINISHED','2013-09-29 22:43:40',NULL,'2013-09-29 22:43:48',NULL,NULL,'DEFAULT',0,NULL,'OK.&nbsp;<br />\r\nNo, test it .<br />\r\n<a href=\"http://www.andaily.com\" target=\"_blank\">http://www.andaily.com</a>',NULL,0),(3,'c5b3980a5b91490ba7f5591110aba620','2013-08-18 14:51:56',1,'Sprint form validator ',NULL,240,0,2,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',1,NULL,'sfdasf<br />\r\nsfewf<br />\r\nsf<br />\r\nwefsdfds<br />\r\n<strong>sfwefsf<br />\r\n<br />\r\nd</strong><br />\r\n&nbsp;',NULL,0),(4,'bd94b83de0c44ca0b4054bcfaa91709b','2013-08-18 15:01:20',1,'Sprint task form',NULL,180,0,2,'CANCELED','2013-08-19 22:43:54',NULL,NULL,'2013-08-20 22:34:30',NULL,'DEFAULT',1,NULL,'<ul>\r\n	<li>asfsf</li>\r\n</ul>\r\n',NULL,0),(5,'33fc3178c6b5428b889d00bd7348dfbf','2013-08-18 15:01:41',0,'monkeyk1987@gmail.com',NULL,60,90,1,'FINISHED','2013-08-29 00:16:05',NULL,'2013-08-29 00:16:18',NULL,NULL,'DEFAULT',0,NULL,'<p>saDDSA</p>\r\n',NULL,0),(6,'001e505f92ce467a92fc7396093a8862','2013-08-18 15:02:14',1,'Sprint form validator ',NULL,240,0,2,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',1,NULL,'',NULL,0),(7,'7072175119cf49a78ec0a4c8624f23e9','2013-08-18 15:05:21',0,'Sprint form validator ',NULL,240,240,2,'FINISHED','2013-08-18 19:18:45',NULL,'2013-08-20 21:51:49',NULL,NULL,'DEFAULT',1,NULL,'',NULL,0),(8,'a5bde356fe4240718af792e116acc15b','2013-08-18 17:10:17',0,'169.Count of every status task on tab pages   ',NULL,60,0,2,'FINISHED',NULL,NULL,'2013-08-20 21:46:54',NULL,NULL,'LOW',0,NULL,'dddddd',NULL,0),(9,'c850ffc30f1c4bff9b2487e42fecd540','2013-08-19 22:28:53',1,'157. Add developer-servlet.xml and try to test it  ',NULL,60,0,2,'CANCELED','2013-08-20 22:33:32',NULL,NULL,'2013-08-20 22:33:40',NULL,'DEFAULT',0,NULL,'<strong>sadsds</strong>',NULL,0),(10,'074decc924ff487da6ce696be879bc45','2013-08-19 22:31:20',1,'asdfwfeefds',NULL,30,0,2,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',0,NULL,'<ol>\r\n	<li>safddssfd</li>\r\n	<li>wf</li>\r\n	<li>ds</li>\r\n	<li>wefsd</li>\r\n</ol>\r\n',NULL,0),(11,'4256c3dc79da49edb59e6bfe5d674b6f','2013-08-19 23:27:41',0,'Fix relation path issue',NULL,30,30,2,'FINISHED','2013-08-28 00:17:39',NULL,'2013-09-29 22:44:15',NULL,NULL,'DEFAULT',1,NULL,'fix is as soon as possible',NULL,0),(12,'d4de9ea8fb13481fb9a70da798e2d91f','2013-08-20 21:51:35',0,'Refer the current user for Sprint,SprintTask',NULL,60,60,2,'FINISHED','2013-08-20 22:34:14',NULL,'2013-09-29 14:41:47',NULL,NULL,'DEFAULT',0,NULL,'Give me the create user, cancel user and so on..',NULL,0),(13,'8d6431255e154e1c9d0be3079a97b638','2013-08-20 22:17:05',1,'asfwfewfewef',NULL,30,0,2,'CANCELED','2013-08-20 22:17:13',NULL,NULL,'2013-08-20 22:17:19',NULL,'DEFAULT',0,NULL,'sf',NULL,0),(14,'1be6f35869ff468c80d20778c2117b18','2013-08-20 22:36:20',1,'monkeyking1987@126.com',NULL,30,0,2,'CREATED',NULL,NULL,NULL,NULL,NULL,'HIGH',0,NULL,'',NULL,0),(15,'f17f664c6f45426bb1dc0047e28830da','2013-08-20 22:58:34',0,'Add developer-servlet.xml and try to test it  ',NULL,30,30,2,'FINISHED','2013-08-20 23:01:23',NULL,'2013-09-29 22:43:19',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(16,'8aa885b1036d4e9880d61f4e1e764e80','2013-08-20 22:58:52',0,'Add developer decorator pages  ',NULL,60,0,2,'CANCELED','2013-08-23 01:08:03',NULL,NULL,'2013-08-27 23:31:33',NULL,'DEFAULT',0,NULL,'asfdfsdasd<br />\r\n<strong>fweqweffwsdafsdsfd</strong>',NULL,0),(17,'c152764b2f714e379eeead50681eb53b','2013-08-23 01:06:03',1,'6.Add forgot password action     ',NULL,60,0,2,'CANCELED','2013-08-23 01:06:16',NULL,NULL,'2013-08-23 01:06:31',NULL,'DEFAULT',0,NULL,'6.Add forgot password action &nbsp; &nbsp;&nbsp;',NULL,0),(18,'9f5259ef361b487db1366d11c83c5738','2013-08-27 23:35:18',0,'Design Think domain and refer domains',NULL,120,0,4,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',0,NULL,'Domain list:\r\n<ol>\r\n	<li>​Think</li>\r\n	<li>ThinkDetails</li>\r\n	<li>ThinkVote</li>\r\n</ol>\r\n',NULL,0),(19,'015276d6a5734f3ea0648e5a959d9202','2013-08-27 23:58:54',0,'ScrumSecurityChecker',NULL,30,0,4,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',0,NULL,'asfsf',NULL,0),(20,'a7af1f946ea8480e95fb94d5807581a3','2013-08-29 01:18:24',0,'160. Backlog overview page action ',NULL,60,150,5,'FINISHED','2013-09-02 01:20:35',NULL,'2013-09-10 21:29:30',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(21,'75c99c8f4c4d4d03a168cf6f53d8b1ca','2013-08-29 01:18:44',0,'164. Backlog form',NULL,120,120,5,'FINISHED','2013-09-02 01:20:26',NULL,'2013-09-08 11:46:53',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(22,'42ef41ac237d45eba0dbe2d41f220885','2013-08-29 01:19:02',0,'165. Sprint overview  ',NULL,120,120,5,'FINISHED','2013-08-29 01:33:00',NULL,'2013-09-02 00:47:39',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(23,'16487df734864e0ba33e9f2f5c022648','2013-08-29 01:19:18',0,'167. Add tip after save action on page',NULL,60,30,5,'FINISHED','2013-08-30 22:38:18',NULL,'2013-09-02 01:07:15',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(24,'80911da1c79c4e58bb93f423c07cce34','2013-08-29 01:19:35',0,'171. Refer the action user to Sprint,SprintTask and Backlog',NULL,60,0,13,'CREATED','2013-10-01 22:07:17',NULL,NULL,NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(25,'db5be6fe8a064fb18a7a5ba6c15743f4','2013-08-29 01:19:51',0,'173.Group show Sprint on task overview select element',NULL,30,30,5,'FINISHED','2013-08-29 01:29:05',NULL,'2013-08-29 01:32:18',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(26,'c6a856c519db4bd6a562b74d78c384a9','2013-08-29 01:22:17',0,'157. Add developer-servlet.xml and try to test it',NULL,30,30,5,'FINISHED','2013-08-29 01:22:51',NULL,'2013-08-29 01:23:20',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(27,'1f54d56833b441b5bafc4da5d5ff6004','2013-08-29 01:22:31',0,'158. Add developer decorator pages',NULL,60,30,5,'FINISHED','2013-08-29 01:22:42',NULL,'2013-08-29 01:23:10',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(28,'152db99db2184cfab9f5a0e1867bff3f','2013-08-29 01:24:03',0,'159. Develop dashboard action(load data,  search by page ...)',NULL,120,240,5,'FINISHED','2013-08-29 01:28:59',NULL,'2013-08-29 01:32:08',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(29,'3bd4629553954de4a11bcf3c0c1b493e','2013-08-29 01:24:15',0,'161. Sprint form ',NULL,180,180,5,'FINISHED','2013-08-29 01:28:55',NULL,'2013-08-29 01:31:05',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(30,'32f7c027b6ff45e98caa4f9ec6a7fecf','2013-08-29 01:24:27',0,'162. Sprint task form ',NULL,180,180,5,'FINISHED','2013-08-29 01:28:40',NULL,'2013-08-29 01:30:55',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(31,'c90f556b54094570a910c8a145289cd8','2013-08-29 01:24:38',0,'163. Developer page link actions',NULL,180,180,5,'FINISHED','2013-08-29 01:28:34',NULL,'2013-08-29 01:30:44',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(32,'59db15b1d15b4741a98189638e6714ca','2013-08-29 01:24:54',0,'166. Sprint form validator ',NULL,60,60,5,'FINISHED','2013-08-29 01:28:28',NULL,'2013-08-29 01:30:32',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(33,'169e7cc53f7f4cfca8145e4dc05d3996','2013-08-29 01:25:08',0,'168. Toggle menu active when click different urls ',NULL,30,30,5,'FINISHED','2013-08-29 01:28:25',NULL,'2013-08-29 01:30:18',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(34,'f16772ed703a45f4b5e398d648f1d36c','2013-08-29 01:25:19',0,'169. Count of every status task on tab pages ',NULL,60,60,5,'FINISHED','2013-08-29 01:28:22',NULL,'2013-08-29 01:30:06',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(35,'4173ff5f8133470caa5d1b7f5896dbd8','2013-08-29 01:25:33',0,'170. Add tooltip action',NULL,30,30,5,'FINISHED','2013-08-29 01:26:32',NULL,'2013-08-29 01:29:50',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(36,'29c67853885c4fa6b71c298fe13b9772','2013-08-29 01:25:48',0,'172.Sprint develop home page actions  ',NULL,120,120,5,'FINISHED','2013-08-29 01:26:26',NULL,'2013-08-29 01:29:23',NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(37,'e82428e2c1c14650977cc900aab5aad8','2013-08-29 01:42:04',0,'174.Show the finished task, the finished sprint time difference ',NULL,60,60,5,'FINISHED','2013-08-29 01:42:23',NULL,'2013-08-30 22:31:58',NULL,NULL,'DEFAULT',0,NULL,'Busincess requirement:\r\n<ol>\r\n	<li>If the finished task used time &gt; or &lt; estimate time , show the time difference.e.g. &nbsp;estimate time is 1 hour, used time is 1.5 hour, then&nbsp;<strong>Actual Used&nbsp;</strong>column show: <strong><em>1.5(+0.5)</em></strong>;if the used time is 0.5 hour, then&nbsp;<strong>Actual Used&nbsp;</strong>column show <strong><em>0.5(-0.5)</em></strong></li>\r\n	<li>Finished Sprint tooltip show the time difference as the task rule.</li>\r\n</ol>\r\n',NULL,0),(38,'9c9dc30811de45ac9709496ded178ef4','2013-08-30 22:37:36',0,'Show the Sprint activities',NULL,180,0,13,'CREATED','2013-11-06 23:01:06',NULL,NULL,NULL,NULL,'LOW',0,NULL,'<ol>\r\n	<li>On the &#39;Task&#39; page add &quot;activities&#39; submenu to &#39;Action&quot; menu, always show the submenu no matter what status of the Sprint</li>\r\n	<li>When click the submenu , go to the Sprint activities page. One activites is look like &quot;Mkk already finish the task &lt;task name&gt; on 2013-09-03 23:34&quot;.</li>\r\n</ol>\r\n',NULL,0),(39,'3c1be01d4c1b4db7b840ab44a810d3cf','2013-09-02 00:47:23',0,'176.Sprint overview page actions(add task menu)  ',NULL,60,60,5,'FINISHED','2013-09-02 00:47:33',NULL,'2013-09-04 00:28:33',NULL,NULL,'DEFAULT',0,NULL,'Check all the sprint action on the overview page, include add &#39;task&#39; menu&nbsp;',NULL,0),(40,'e1f37d331cdc43698b9d8f389a8c5fde','2013-09-02 00:58:31',0,'ttttttttttttttttttttttttttttttttttt',NULL,30,0,4,'PENDING','2013-11-13 22:14:54',NULL,NULL,NULL,NULL,'DEFAULT',0,NULL,'',NULL,0),(41,'02a09d6bb7a04bcfbfd7d27e75a6d0fa','2013-09-02 01:01:03',0,'ssssssssssssssssssssssssssss',NULL,30,90,4,'FINISHED','2013-11-13 21:56:48',NULL,'2013-11-13 22:13:00',NULL,NULL,'DEFAULT',0,NULL,'sfsfsf','',0),(42,'f6d809180cc9492187369815bd3b7f62','2013-09-02 01:09:10',0,'Design project domain',NULL,90,90,12,'FINISHED','2013-10-10 23:22:13',NULL,'2013-10-16 23:23:48',NULL,NULL,'DEFAULT',0,NULL,'Design Project domain , include refer to Sprint and so on',NULL,0),(43,'9fcb624763794dfcbbf021301f106e95','2013-09-02 01:09:47',0,'sfdafsf',NULL,240,240,4,'FINISHED','2013-11-13 21:58:38',NULL,'2013-11-13 21:58:48',NULL,NULL,'DEFAULT',0,NULL,'','4',1),(44,'8f7122a6489243b592ff9fcf71453adc','2013-09-02 01:13:45',0,'safwfwef',NULL,30,240,4,'FINISHED','2013-11-12 22:56:56',NULL,'2013-11-13 21:57:01',NULL,NULL,'DEFAULT',0,NULL,'sfsf','4',1),(45,'0717ef3e36cd4ba5aded2c7b8c205038','2013-09-03 23:00:59',0,'Use STRender replace Sprint details,Task details text',NULL,60,90,12,'FINISHED','2013-11-11 22:27:12',NULL,'2013-11-12 22:05:08',NULL,NULL,'DEFAULT',0,NULL,'Do not use append string in <strong>SprintDto </strong>and <strong>SprintTaskDto</strong>, use <strong>STRender </strong>render the text from template files&nbsp;<br />\r\n<em>Maybe use dialog replace popuar in future, i think it is much better</em>.',NULL,0),(46,'311a64dbf778401797f750dfb185c6a0','2013-09-05 00:34:16',0,'Test Sprint,SprintTask backlog is work or not',NULL,60,60,5,'FINISHED','2013-09-09 23:55:54',NULL,'2013-09-10 22:47:26',NULL,NULL,'DEFAULT',0,NULL,'After finish Backlog form,overview tasks, we need to test the backlog refer to the sprint,task is work normally.',NULL,0),(47,'7a19b91ae3194fcdb2ccdabcd0f15c4c','2013-09-09 23:55:01',1,'asfdsffsdfewewsd',NULL,30,0,7,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',0,2,'asfdsffsdfewewsdasfdsffsdfewewsdasfdsf fsdfewewsd&nbsp;style=&quot;vertical-align: middle;&quot;&nbsp;style=&quot;vertical-align: middle;&quot;',NULL,0),(48,'9478b06f7d1642a29ba8647fa64b8351','2013-09-10 00:26:55',0,'Backlog add estimateTime field',NULL,60,60,5,'FINISHED','2013-09-10 21:29:55',NULL,'2013-09-11 23:51:54',NULL,NULL,'DEFAULT',0,NULL,'Backlog domain add <em>estimateTime&nbsp;</em>field, the same as Sprint estimateTime field; the field max hours is <strong>16 </strong>, add refer to show on sprint-form and &nbsp;task-form pages',NULL,0),(49,'0fef07f0d4df45e784c30515a386a8fc','2013-09-10 22:31:18',0,'需要添加用户的引用到当前的操作, 当创建task, sprint, backlog等.',NULL,90,0,7,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',0,1,'需要添加用户的引用到当前的操作, 当创建task, sprint, backlog等.',NULL,0),(50,'2bebe69f9d1843adaad6395d64bb5e45','2013-09-11 23:53:23',0,'Sprint form dynamic show total backlog estimate times',NULL,30,60,5,'FINISHED','2013-09-11 23:53:28',NULL,'2013-09-12 22:40:51',NULL,NULL,'DEFAULT',0,NULL,'Sprint form dynamic show total backlog estimate times, <strong>Total estimate(hour): 4</strong>',NULL,0),(51,'0608d126297543079d4f0a0da5b85f05','2013-09-12 22:40:35',0,'Sprint overview show Backlog informations',NULL,90,90,5,'FINISHED','2013-09-12 22:40:43',NULL,'2013-09-12 23:26:57',NULL,NULL,'DEFAULT',0,NULL,'If the sprint have one or more backlog references, add a icon(<em>icon-tags</em>) on the row last,<br />\r\nwhen click the icon, show a dialog, the dialog content is the backlogs, show on a table.',NULL,0),(52,'a0ce295628a84bfe890c6271d6d3a3e6','2013-09-12 22:43:14',0,'Task overview show backlog content',NULL,60,60,5,'FINISHED','2013-09-12 22:43:18',NULL,'2013-09-16 00:33:53',NULL,NULL,'DEFAULT',0,NULL,'If the task refer to a backlog, show a icon(<em>icon-tag</em>) on the row last position, click the icon open popuar show the backlog content',NULL,0),(53,'2543106940064e768ce456678d85cba0','2013-09-12 23:33:51',0,'Per overview footer show total times on the task overview',NULL,60,60,5,'FINISHED','2013-09-12 23:34:17',NULL,'2013-09-17 00:52:57',NULL,NULL,'DEFAULT',0,NULL,'Include tabs: pending, created,finsihed,canceled, all tasks &nbsp;5 overviews',NULL,0),(54,'2b1e1bc7442a44ad8f251afd87ebd54b','2013-09-16 00:31:50',0,'Test backlog',NULL,60,0,7,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',0,5,'High ...... bugs (<strong>1</strong>)',NULL,0),(55,'4be8535a52e34ff8a7aaffa71c825f9d','2013-09-16 01:00:49',0,'zcsddsds',NULL,30,0,7,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',0,NULL,'afsddfsfd',NULL,0),(56,'5edb72de86e544ec8d7b369992edc010','2013-09-17 00:49:08',0,'Sprint add real time time report',NULL,90,0,13,'CREATED','2013-11-11 22:26:26',NULL,NULL,NULL,NULL,'DEFAULT',0,NULL,'At a sub-menu to the action menu &quot;<strong>Time report</strong>&quot;, &nbsp;it will be dynamic show the sprint time status: total estimate time, used time, pending time, created time and so on.<br />\r\n<br />\r\nI will do it after project refer to Developer.',NULL,0),(57,'8e6f3649a131432faebb0078fed51750','2013-09-17 00:51:13',0,'Design SprintMeet domain',NULL,60,60,5,'FINISHED','2013-09-17 00:51:18',NULL,'2013-09-24 00:01:48',NULL,NULL,'DEFAULT',0,NULL,'Sprint meet, &nbsp;include meet type, join users, content, date fields...',NULL,0),(58,'652094de05034e3a9e6994811bab50b2','2013-09-17 00:52:32',0,'Change all sprint end date to deadline field',NULL,30,30,5,'FINISHED','2013-09-17 00:52:37',NULL,'2013-09-21 23:08:24',NULL,NULL,'DEFAULT',0,NULL,'Include sql column name',NULL,0),(59,'80eeb9a164884ddd98adb74f393e0bfd','2013-09-21 22:58:03',0,'tadsdfs',NULL,120,120,11,'FINISHED','2013-09-21 22:59:41',NULL,'2013-09-21 23:00:04',NULL,NULL,'DEFAULT',1,NULL,'fasfds',NULL,0),(60,'a0982994604e4bb493a4acf3d1812d74','2013-09-22 22:55:02',0,'Finish task time maybe larger than 4 hours',NULL,60,90,12,'FINISHED','2013-11-11 22:28:53',NULL,'2013-11-13 22:14:21',NULL,NULL,'LOW',0,NULL,'Find how to fix the design issue and resolve it.','',0),(61,'3296bf8444f64fa0918ccadd4f6ffae0','2013-09-22 23:56:43',0,'Add developer domain',NULL,60,60,5,'FINISHED','2013-09-22 23:57:21',NULL,'2013-09-23 00:40:20',NULL,NULL,'DEFAULT',0,NULL,'It is a subclass of User, which have a field call&nbsp;<strong>ScrumTerm </strong>.&nbsp;',NULL,0),(62,'bcf61e08627943be8ac46de478b3db63','2013-09-22 23:57:11',0,'Add ScrumComment domain',NULL,30,30,5,'FINISHED','2013-09-22 23:57:16',NULL,'2013-09-23 22:48:48',NULL,NULL,'DEFAULT',0,NULL,'who on what time , do what.',NULL,0),(63,'7685b10d5cd54c5bb1cbc1d03c108ca2','2013-09-24 00:01:37',0,'Create Burn down chart resolver ',NULL,180,180,5,'FINISHED','2013-09-24 00:01:43',NULL,'2013-09-29 14:46:51',NULL,NULL,'DEFAULT',0,NULL,'A resolver for burn down chart , return all reference chart data&nbsp;',NULL,0),(64,'c127492cc4514c918a6ae47e69b9dbe3','2013-09-24 22:06:32',0,'Sprint meeting form action',NULL,180,180,5,'FINISHED','2013-09-24 22:06:36',NULL,'2013-10-01 17:46:50',NULL,NULL,'DEFAULT',0,NULL,'Sprint meeting form action: &nbsp;add/edit/validation<br />\r\n<br />\r\n&nbsp;',NULL,0),(65,'3c81564e1ddb430fbf41e264163c5cf9','2013-09-28 00:55:47',0,'Show burn down chart details',NULL,120,120,5,'FINISHED','2013-09-29 15:11:51',NULL,'2013-09-29 22:52:46',NULL,NULL,'DEFAULT',0,NULL,'Show the burn down chart details when click details icon on the developer page, the details includes full chart and time report.',NULL,0),(66,'4cb7785ff3e74900a36dbe2b19e7d1a9','2013-09-29 20:32:39',0,'Show the latest 5 meetings on developer home page',NULL,60,60,5,'FINISHED','2013-09-29 22:53:43',NULL,'2013-10-01 21:55:28',NULL,NULL,'DEFAULT',0,NULL,'FYI, &nbsp;<br />\r\nOn the developer home page, show the latest 5 meetings as list, and add a more link at the bottom, when click the more link, go to the sprint meeting overview.&nbsp;<br />\r\n<br />\r\nthe content look like as below:\r\n<ul>\r\n	<li>2013-09-12 &nbsp;(Daily standing)</li>\r\n	<li>2013-09-11 &nbsp;(Daily standing)</li>\r\n	<li>2013-09-11 &nbsp;(Sprint planning)</li>\r\n	<li>...</li>\r\n</ul>\r\n',NULL,0),(67,'ef3e8b1905c2421e9265a89f3e5ace99','2013-09-29 20:39:49',0,'Sprint meeting overview',NULL,120,120,5,'FINISHED','2013-10-01 17:47:06',NULL,'2013-10-02 14:55:26',NULL,NULL,'DEFAULT',0,NULL,'Show all the &nbsp;sprint meeting on a page(include pagination), the meeting order by meetin date desc.<br />\r\nthe row meeting content include: &nbsp;meeting date;meeting type, and part of the content.<br />\r\nwhen click the meeting date, show two menus: edit and details.',NULL,0),(68,'bae75eb427cd4c13881cfb45589b77a9','2013-09-29 20:44:52',0,'Edit sprint meeting action',NULL,30,30,5,'FINISHED','2013-10-01 17:49:00',NULL,'2013-10-02 15:50:50',NULL,NULL,'DEFAULT',0,NULL,'Click the edit meun on the meeting overview page, open a sprint meeting form popup, the popup is same as the add meeting form, then edit it &nbsp;and save it.',NULL,0),(69,'173c2150bd474c2bb79b1243188968bb','2013-09-29 21:03:17',0,'Show sprint meeting details action',NULL,30,30,5,'FINISHED','2013-10-01 17:49:08',NULL,'2013-10-02 15:50:56',NULL,NULL,'DEFAULT',0,NULL,'When click the &#39;details&#39; menu on the meeting overview page, show a popup, the content is the meeting date,content,type and so on.',NULL,0),(70,'e4e56303b06a44eabd03d1563db0f6ac','2013-10-01 22:17:04',0,'Show legend when transfer to All task tab on developer page',NULL,30,30,12,'FINISHED','2013-10-09 15:09:58',NULL,'2013-10-09 15:19:34',NULL,NULL,'DEFAULT',0,NULL,'Show different color for diffent status task legend on the right area when show &#39;All task&#39; tab.',NULL,0),(71,'554fd18d15db4e489d88f2f231766db7','2013-10-09 15:16:32',0,'Project form action',NULL,120,120,12,'FINISHED','2013-10-16 23:23:40',NULL,'2013-10-29 22:06:57',NULL,NULL,'DEFAULT',0,NULL,'Add/edit project form.',NULL,0),(72,'8c1ddc65dc7946cd842536b9fe6f98df','2013-10-09 15:18:57',0,'Allow use move created task to another sprint',NULL,120,120,12,'FINISHED','2013-10-09 15:19:02',NULL,'2013-10-09 22:59:36',NULL,NULL,'DEFAULT',0,NULL,'Allow use move created task to another sprint;<br />\r\n<br />\r\nOnly <strong>canceled </strong>task allow to move. the target sprint status is <strong>Created </strong>or <strong>Pending</strong>',NULL,0),(73,'48abc5769638402fa34a89a2a555f233','2013-10-09 23:01:20',0,'Move the Andaily Developer codes from Andaily project',NULL,180,180,12,'FINISHED','2013-10-09 23:01:45',NULL,'2013-10-10 23:15:12',NULL,NULL,'HIGH',0,NULL,'Move it and test it , also keep old codes in Andaily project;<br />\r\n<br />\r\nCheck in the new codes to Git OSC and so on.',NULL,0),(74,'d4bd294cd9074705a90872f9470c4c64','2013-10-14 22:28:42',0,'Config andaily-developer jenkins in CloudBees',NULL,60,60,12,'FINISHED','2013-10-14 22:28:48',NULL,'2013-10-14 23:22:21',NULL,NULL,'DEFAULT',0,NULL,'Config andaily-developer jenkins in CloudBees',NULL,0),(75,'27099c38205649eda1dabe1a0b89b3fe','2013-10-16 23:25:06',0,'Project overview',NULL,90,90,12,'FINISHED','2013-10-16 23:25:11',NULL,'2013-10-29 22:43:19',NULL,NULL,'DEFAULT',0,NULL,'Project overview, include paginated; search filter...',NULL,0),(76,'354e93fc96bd47b29e2a45752a2c9a20','2013-10-29 22:32:54',0,'Project overview, backlog overview add projectGuid condition when query',NULL,90,90,12,'FINISHED','2013-10-29 22:33:00',NULL,'2013-10-31 22:58:02',NULL,NULL,'DEFAULT',0,NULL,'When query on sprint overview or backlog overview, we should be check project, and add the condition',NULL,0),(77,'2f33406e7ac14c838d54496ac6c39b4b','2013-10-31 22:41:02',0,'Sprint form refer to project',NULL,60,60,12,'FINISHED','2013-10-31 22:42:22',NULL,'2013-11-06 23:15:49',NULL,NULL,'DEFAULT',0,NULL,'If when create sprint refer a projectGuid, do not change as before, otherwise, choose a project for the new sprint',NULL,0),(78,'ae5bb5b3254b438199701acd69698f49','2013-10-31 22:42:07',0,'Backlog form refer to project',NULL,60,60,12,'FINISHED','2013-10-31 22:58:36',NULL,'2013-11-06 22:53:07',NULL,NULL,'DEFAULT',0,NULL,'When create bakclog if have <em>projectGuid </em>parameter, do not change as before.otherwise, choose a project is necessary',NULL,0),(79,'d98513089209498399767eba85596f0e','2013-11-11 22:11:51',0,'Task overview sprint select show project name',NULL,30,30,12,'FINISHED','2013-11-11 22:11:57',NULL,'2013-11-11 22:20:41',NULL,NULL,'DEFAULT',0,NULL,'Add project neme after sprint name on the task overview sprint select element',NULL,0),(80,'3c4172d0c187409798f56bff4648d4c2','2013-11-11 23:13:40',0,'Set a default sprint on task overview page',NULL,30,60,13,'FINISHED','2013-11-16 11:37:54',NULL,'2013-11-20 22:00:38',NULL,NULL,'DEFAULT',0,NULL,'If set a default sprint on task overview page, then if click &quot;Task&quot; menu directly, will set current sprint is default sprint; &nbsp;<strong>only one default sprint</strong>.','',0),(81,'3117616b21b743e896740ca84a19b646','2013-11-12 22:10:17',0,'afsdfewfewds',NULL,240,241,2,'FINISHED','2013-11-12 22:10:21',NULL,'2013-11-12 22:52:43',NULL,NULL,'DEFAULT',0,NULL,'sfdsdffds','okok',0),(82,'1ecba3fff48241478667bb963e316396','2013-11-12 22:12:45',0,'Finish task modal add comment field',NULL,60,60,12,'FINISHED','2013-11-12 22:12:49',NULL,'2013-11-13 22:14:14',NULL,NULL,'DEFAULT',0,NULL,'Finish task modal add comment input element, allow user input comment when finishing.','',0),(83,'c7ccabdcc8fd44108982c345ba857ab0','2013-11-13 22:25:00',0,'asfdsdf',NULL,30,240,2,'FINISHED','2013-11-13 22:25:04',NULL,'2013-11-13 22:25:26',NULL,NULL,'DEFAULT',0,NULL,'sfdsfd','more reason',1),(84,'3ba78d301edb40a892f17d8db266e8ee','2013-11-13 22:38:12',0,'Add count of meetings on sprint overview menu item',NULL,30,30,13,'FINISHED','2013-11-16 11:06:39',NULL,'2013-11-16 11:20:57',NULL,NULL,'DEFAULT',0,NULL,'Add count of meetings on sprint overview menu item','',0),(85,'2690eebfe50b463aa832ea51a3abeb7e','2013-11-13 22:39:15',0,'Add count of sprints,count of backlogs on project overview menu',NULL,30,30,13,'FINISHED','2013-11-16 11:20:47',NULL,'2013-11-16 11:34:49',NULL,NULL,'DEFAULT',0,NULL,'Add count of sprints,count of backlogs on project overview menu','',0),(86,'22ff5516adb64cae9de6696b84476816','2013-11-16 11:25:47',0,'Design Developer domain',NULL,90,90,13,'FINISHED','2013-11-20 22:00:45',NULL,'2013-11-21 22:16:29',NULL,NULL,'DEFAULT',0,NULL,'Design Developer domain, include reference domains: roles..','',0),(87,'693e9b6d2c4e40208bd7aa38e04a7990','2013-11-20 22:06:56',0,'Backlog overview add sprintGuid condition',NULL,60,0,17,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',0,NULL,'Add a backlog menu item on the sprint overview menu;&nbsp;<br />\r\nIf click the menu, go to backlog overview , only show the sprint backlogs,\r\n<div><br />\r\nIt is allow the sprint backlogs add exist new backlog(it&#39;s mean the backlog not bind any sprint yet)</div>\r\n,allow to add backlog to a pending sprint.',NULL,0),(88,'e0ac7b8526754e709c0ccf9bb4f28cee','2013-11-20 22:34:18',0,'Design Team domain',NULL,60,60,13,'FINISHED','2013-11-20 22:35:25',NULL,'2013-11-21 22:16:24',NULL,NULL,'DEFAULT',0,NULL,'Define Team domain, a team includes: name, creator, projects and memebers.<br />\r\nThe members includes: Scrum master, scrum member and product owner roles','',0),(89,'469b7c9dddd44ab8870e97afd902e455','2013-11-20 22:35:20',0,'Security configuration',NULL,120,150,13,'FINISHED','2013-11-21 22:16:44',NULL,'2013-11-24 11:14:44',NULL,NULL,'DEFAULT',0,NULL,'Add spring security configuration<br />\r\ninclude menus on main.jsp ...','',0),(90,'4c9981a8bac8425aad714f11ea70dd1c','2013-11-20 22:37:07',0,'Assign task to a developer',NULL,60,0,13,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',0,NULL,'The created task add a menu call: Assign..., if click the menu, will popup a modal , choose a developer do the task.',NULL,0),(91,'7985a0121bb842e2a4ed0c8455e72015','2013-11-20 22:39:46',0,'\"Do it\" menu add a confirm modal ',NULL,60,0,13,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',0,NULL,'When click &quot;Do it&quot; menu , a confirm modal will be open, the task executor will be show. Maybe the content look like &quot;Are your sure do the task now, {developer name}?&quot; . &nbsp;',NULL,0),(92,'55a5f72109a2474f86b4d86d2ae09782','2013-11-23 13:23:31',0,'Super man redirect to user overview after sign in successful',NULL,30,0,13,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',0,NULL,'Super man redirect to user overview after sign in successful',NULL,0),(93,'69969f2f38bc4024bd12bb06ac8737a3','2013-11-23 13:24:11',0,'User overview',NULL,60,60,13,'FINISHED','2013-11-24 11:14:26',NULL,'2013-11-25 22:53:44',NULL,NULL,'DEFAULT',0,NULL,'','',0),(94,'782a172aafcb492390e58696b19749d4','2013-11-23 13:24:21',0,'User form',NULL,90,150,13,'FINISHED','2013-11-25 23:29:19',NULL,'2013-12-03 23:51:45',NULL,NULL,'DEFAULT',0,NULL,'','Too long',0),(95,'7023d8a252cd4251901978305f855e5e','2013-11-24 10:51:59',0,'Remember me action',NULL,60,60,13,'FINISHED','2013-11-25 22:53:57',NULL,'2013-11-25 23:29:02',NULL,NULL,'DEFAULT',0,NULL,'It is same as andaily remember me','',0),(96,'99e73d224018460a8b3ff122439630b4','2013-11-24 10:53:50',0,'Profile action',NULL,120,0,13,'CREATED',NULL,NULL,NULL,NULL,NULL,'DEFAULT',0,NULL,'User profile, include update user information, change password , clean saved username/password and so on',NULL,0),(97,'341255da1b7547449cdbb6924875da40','2013-11-24 11:14:17',0,'Security url pattern setting',NULL,90,0,13,'CREATED',NULL,NULL,NULL,NULL,NULL,'LOW',0,NULL,'Setting every url pattern of security.xml<br />\r\n<br />\r\nand test it',NULL,0),(98,'6cca0b87f4ad44ce9c28d68c2cb5dd73','2013-11-25 22:55:23',0,'User overview menu action',NULL,60,120,13,'FINISHED','2013-12-03 23:55:55',NULL,'2013-12-05 22:56:05',NULL,NULL,'DEFAULT',0,NULL,'User overview add menu(for email filed), includes: &nbsp;Edit,enable/disable, archive','',0),(99,'85752288288a4ccd9503823a874df637','2013-12-03 23:57:00',0,'Team overview',NULL,90,90,13,'FINISHED','2013-12-05 22:56:58',NULL,'2013-12-17 22:56:36',NULL,NULL,'DEFAULT',0,NULL,'','',0),(100,'959d447d1f61419b89c44f4afd6f2968','2013-12-03 23:57:08',0,'Team form',NULL,90,0,13,'PENDING','2013-12-05 22:57:03',NULL,NULL,NULL,NULL,'DEFAULT',0,NULL,'',NULL,0);
/*!40000 ALTER TABLE `sprint_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sprint_task_move_record`
--

DROP TABLE IF EXISTS `sprint_task_move_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sprint_task_move_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `from_sprint_id` int(11) DEFAULT NULL,
  `to_sprint_id` int(11) DEFAULT NULL,
  `task_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `from_sprint_id_index` (`from_sprint_id`),
  KEY `task_id_index` (`task_id`),
  KEY `to_sprint_id_index` (`to_sprint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sprint_task_move_record`
--

LOCK TABLES `sprint_task_move_record` WRITE;
/*!40000 ALTER TABLE `sprint_task_move_record` DISABLE KEYS */;
INSERT INTO `sprint_task_move_record` VALUES (20,'c6a9b7311a5b4ddeab6bd6550f79992c','2013-10-09 22:30:24',0,5,12,45),(21,'b813901fda3d4f49be3f9a6e3b632e56','2013-10-09 22:57:59',0,5,12,24),(22,'c89c0657d1904bdb95e269fa3ee22956','2013-10-09 22:58:08',0,5,12,56),(23,'74dc9524d8b94f0e8991f09a0e3c265d','2013-11-13 22:33:41',0,12,13,56),(24,'3dc5792f3a2449ef93730268db93ec83','2013-11-13 22:33:49',0,12,13,38),(25,'0b8c46df0ef9482085debac1ab6c9cff','2013-11-13 22:33:56',0,12,13,24);
/*!40000 ALTER TABLE `sprint_task_move_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `name_` varchar(255) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `creator_id_index` (`creator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_project`
--

DROP TABLE IF EXISTS `team_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `project_id` int(11) DEFAULT NULL,
  `team_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  KEY `guid_index` (`guid`),
  KEY `team_id_index` (`team_id`),
  KEY `project_id_index` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_project`
--

LOCK TABLES `team_project` WRITE;
/*!40000 ALTER TABLE `team_project` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_`
--

DROP TABLE IF EXISTS `user_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `last_login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `cell_phone` varchar(255) DEFAULT NULL,
  `verify_code` varchar(255) DEFAULT NULL,
  `scrum_term` varchar(255) DEFAULT NULL,
  `type_` varchar(255) NOT NULL DEFAULT 'User',
  `team_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `cell_phone` (`cell_phone`),
  UNIQUE KEY `verify_code` (`verify_code`),
  KEY `user_guid_index` (`guid`),
  KEY `user_email_index` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_`
--

LOCK TABLES `user_` WRITE;
/*!40000 ALTER TABLE `user_` DISABLE KEYS */;
INSERT INTO `user_` VALUES (1,'322os-9kkwew2-locde1-',NULL,0,'2013-04-13 05:33:38','monkeyking1987@126.com','7eedb15d71f49962208f4bd9200b7c12','Chengdu(成都)','mkk','13308231107',NULL,NULL,'User',NULL),(2,'user-uuid-2',NULL,1,'2012-04-27 09:29:10','monkeyk1987@gmail.com','05a671c66aefea124cc08b76ea6d30bb','Chengdu',NULL,'13344441107',NULL,NULL,'User',NULL),(3,'ce9362c4-2bfc-4154-83a8-68651669b167','2012-02-09 23:03:56',1,'2012-02-11 09:06:50','343344981@qq.com','edab525a39fa49a8638b7aad02200012','成都',NULL,'34444444444444',NULL,NULL,'User',NULL),(4,'627bbfc1-c606-4a34-8166-cd1fbe41e3de','2012-05-13 15:43:51',0,'2013-03-30 08:13:53','1589155482@qq.com','ce1403031ae5a0344a9835590ad5faf1','','pagelover','',NULL,NULL,'User',NULL),(6,'9fdf6cd70b1b40f9ad15ac02a57db90d','2013-03-12 23:43:48',0,'2013-03-30 07:37:43','249960083@qq.com','25d55ad283aa400af464c76d713c07ad','','',NULL,NULL,NULL,'User',NULL),(8,'452fd38f5f294755b26f1f7f632aa452','2013-03-26 17:05:04',0,'2013-03-30 07:37:43','softwindking@163.com','f71480207a9667a32f03415955906e48','','',NULL,NULL,NULL,'User',NULL),(9,'62494203a49e4d1ead9d1969d49032b4','2013-03-26 18:42:35',0,'2013-03-30 07:37:43','greenwishing@msn.cn','25d55ad283aa400af464c76d713c07ad','cd','wishing',NULL,NULL,NULL,'User',NULL),(10,'ccc554094c0c44c288e295e5031d3f86','2013-03-26 21:12:28',0,'2013-03-30 07:37:43','pagelover@qq.com','ce1403031ae5a0344a9835590ad5faf1','成都','pagelover','15196631289',NULL,NULL,'User',NULL),(29,'6d734f1bd85a41f98aa3ddbff6cae741','2013-04-13 05:48:34',0,'2013-04-13 05:52:30','574768274@qq.com','7f544c0832c57851d8621622cdc4daaf','','other me','123456',NULL,NULL,'User',NULL),(32,'e5f45d92937342febf91e4460ec0c214',NULL,0,'2013-04-22 08:58:09','13880306477@andaily.com','4428c6c474502e61151877825bb41961','成都','王文磊','13880306477',NULL,NULL,'User',NULL),(33,'990f291658fd49e1b09eaf9ff48e2dcb',NULL,0,'2013-04-22 08:58:10','mkk@andaily.com','4428c6c474502e61151877825bb41961','成都','李胜钊','13474007014',NULL,NULL,'User',NULL),(34,'d7f175c45c944ac3aeec6b47a18e00d2','2013-07-17 00:23:50',0,'2013-07-16 16:25:03','111@11.com','1bbd886460827015e5d605ed44252251','','111',NULL,NULL,NULL,'User',NULL),(35,'22c81ad7ae2d47f08066a101b310f8d3','2013-07-17 20:40:53',0,'2013-07-17 12:46:32','411277703@qq.com','1bbd886460827015e5d605ed44252251','xa','411277703','411277703',NULL,NULL,'User',NULL),(36,'c69950a821104b308ddd0bffaf9681be','2013-07-17 20:42:58',0,'2013-07-17 12:47:09','abc@126.com','1bbd886460827015e5d605ed44252251','','acb',NULL,NULL,NULL,'User',NULL),(37,'733f512b0ea54d7d9eb4d53d45ff2d31','2013-07-31 23:35:33',1,'2013-07-31 15:35:33','aswwdd@ad.com','1bbd886460827015e5d605ed44252251','','',NULL,'77fc39602ad041d59c9a828970f770d6',NULL,'User',NULL),(38,'d0ac35773f334c22b50747e6b476ed9d','2013-07-31 23:43:29',1,'2013-07-31 15:43:29','0987654@123.com','1bbd886460827015e5d605ed44252251','','',NULL,'323df93bd4894a818953f5083544e09f',NULL,'User',NULL),(39,'32d9f9cf0ca2498ea365b8a108d9cd95','2013-07-31 23:55:50',1,'2013-07-31 15:55:50','rrrrrrr@rr.com','1bbd886460827015e5d605ed44252251','','rRrRRR',NULL,'ca859c0a30d04f4a9fdd2df62a1f1645',NULL,'User',NULL),(40,'afea5451b7374c4895ae7d9635658f1b','2013-08-01 00:07:00',1,'2013-07-31 16:07:00','ewsadds@fdad.com','1bbd886460827015e5d605ed44252251','','',NULL,'bb8ec78bc7bf4c71a8d2f517a26544de',NULL,'User',NULL),(41,'1d75dd07fc244a2d971fed3df7402394','2013-08-01 23:24:08',0,'2013-08-01 15:26:29','9494@94.com','d234d5ac2a00b814828bb2ac3deba4bb','','9494',NULL,NULL,NULL,'User',NULL),(42,'edb824baf5574c85941a2af2a9b77dcc','2013-08-01 23:28:49',1,'2013-08-01 15:28:49','9999@99.com','1bbd886460827015e5d605ed44252251','','',NULL,'547445aaf8dd4130b0bb83fd1e2c5620',NULL,'User',NULL),(43,'228397d163e741e793754c438278db6e','2013-08-01 23:32:24',0,'2013-08-01 15:34:00','dddd@dd.com','c957fcad002e0517f5b5f8c0936feca4','','',NULL,NULL,NULL,'User',NULL),(50,'63a2974795ca4309925f209ab0af2688','2013-11-23 13:00:13',0,'2013-11-23 05:28:24','admin@andaily.com','21232f297a57a5a743894a0e4a801fc3',NULL,'admin',NULL,NULL,'SUPER_MAN','Developer',NULL),(52,'86d340150c75487b9166e3c3afc07112','2013-12-03 23:48:34',0,'2013-12-03 15:48:34','lukai@honyee.cc','fcea920f7412b5da7be0cf42b8c93759',NULL,'lukai',NULL,NULL,'MEMBER','Developer',NULL),(53,'c6b43ff84c24494ba1011c6ffc1e57e0','2013-12-03 23:49:06',0,'2013-12-05 14:46:59','chengtao@honyee.cc','3827c4c7c3b830b9c81bd0057d6969de',NULL,'ct','134556676776',NULL,'MEMBER','Developer',NULL),(54,'5d475ffb838c4477920a3d7f8350398a','2013-12-03 23:50:59',0,'2013-12-03 15:50:59','chengtao1@honyee.cc','0c01eedfb21b8c5fd6cde6a6f728cac2',NULL,'chengtao','1566777777',NULL,'PRODUCT_OWNER','Developer',NULL),(55,'08100f2b9ee545739254ee42475c0af8','2013-12-03 23:51:18',0,'2013-12-06 15:42:41','kuangyawen@honyee.cc','7bc6bfb6789186843ecbfc70047c308d',NULL,'kuangyawen1111','2313244322',NULL,'MEMBER','Developer',NULL),(58,'79c3727cdddc406aa9d9ef6162a30e4e','2013-12-17 23:04:36',0,'2013-12-17 15:04:36','shengzhao@andaily.com','dbd4adce1a9568c4b1413644f7363b66',NULL,'lsz',NULL,NULL,'MEMBER','Developer',NULL),(59,'7f55cbfec79f40d4a09254f2f643912c','2013-12-17 23:07:19',0,'2013-12-17 15:08:33','shengzhao@honyee.cc','f78e95a7c3176f2b40916fc683cd2f19',NULL,'sdf','1345566767763',NULL,'MASTER','Developer',NULL),(60,'dc4a8e16cd9440dba6bfbc808f1ddb8e','2013-12-17 23:08:02',0,'2013-12-17 15:08:02','sheng3zhao@wdcy.cc','f78e95a7c3176f2b40916fc683cd2f19',NULL,'asfsafd','15667777772',NULL,'MEMBER','Developer',NULL);
/*!40000 ALTER TABLE `user_` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-12 21:18:29
