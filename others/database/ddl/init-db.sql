--###############
--#   create database , if need create, cancel the comment
--###############
--#create database if not exists andaily_developer default character set utf8;
--#use andaily_developer set default character = utf8;

--###############
--#   grant privileges  to andaily/andaily
--###############
--#GRANT ALL PRIVILEGES ON andaily_developer.* TO andaily_developer@localhost IDENTIFIED BY "andaily_developer";

--###############
-- Domain:  User
--###############
Drop table  if exists user_;
CREATE TABLE `user_` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `last_login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(255) not null unique,
  `password` varchar(255) not null,
  `city` varchar(255),
  `nick_name` varchar(255),
  `cell_phone` varchar(255),
  `verify_code` varchar(255) unique,
  `type_` varchar(255) not null default 'User',
  `language_` varchar(255) not null default 'ENGLISH',
  `scrum_term` varchar(255),
  `team_id` int(11),
  `description` text,
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `team_id_index` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Domain:  Backlog
--
Drop table  if exists backlog;
CREATE TABLE `backlog` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `content` text,
  `priority` varchar(255) default 'DEFAULT',
  `type_` varchar(255) default 'NEEDS',
  `sprint_id` int(11),
  `creator_id` int(11),
  `join_sprint_user_id` int(11),
  `project_id` int(11),
  `estimate_time` int(11) default 0,
  `number_` int(11) unique,
  `join_sprint_time` datetime,
  PRIMARY KEY  (`id`),
  INDEX `sprint_id_index` (`sprint_id`),
  INDEX `join_sprint_user_id_index` (`join_sprint_user_id`),
  INDEX `guid_index` (`guid`),
  INDEX `project_id_index` (`project_id`),
  INDEX `number_index` (`number_`),
  INDEX `creator_id_index` (`creator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Domain:  Sprint
--
Drop table  if exists sprint;
CREATE TABLE `sprint` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `creator_id` int(11),
  `name_` varchar(255),
  `start_date` date,
  `deadline` date,
  `status` varchar(255) default 'CREATED',
  `pending_time` datetime,
  `finish_time` datetime,
  `archive_time` datetime,
  `archive_user_id` int(11),
  `project_id` int(11),
  `execute_team_id` int(11),
  `description` varchar(2000),
  `default_sprint` tinyint(1) default '0',
  PRIMARY KEY  (`id`),
  INDEX `creator_id_index` (`creator_id`),
  INDEX `archive_user_id_index` (`archive_user_id`),
  INDEX `project_id_index` (`project_id`),
  INDEX `execute_team_id_index` (`execute_team_id`),
  INDEX `guid_index` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Domain:  SprintTask
--
Drop table  if exists sprint_task;
CREATE TABLE `sprint_task` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `name_` varchar(255),
  `creator_id` int(11),
  `estimate_time` int(11) default 0,
  `actual_used_time` int(11) default 0,
  `sprint_id` int(11),
  `status` varchar(255) default 'CREATED',
  `pending_time` datetime,
  `executor_id` int(11),
  `number_` int(11),
  `finish_time` datetime,
  `cancel_time` datetime,
  `cancel_user_id` int(11),
  `priority` varchar(255) default 'DEFAULT',
  `urgent` tinyint(1) default '0',
  `larger_max_time` tinyint(1) default '0',
  `backlog_id` int(11),
  `description` varchar(2000),
  `finish_comment` varchar(255),
  PRIMARY KEY  (`id`),
  INDEX `creator_id_index` (`creator_id`),
  INDEX `sprint_id_index` (`sprint_id`),
  INDEX `executor_id_index` (`executor_id`),
  INDEX `cancel_user_id_index` (`cancel_user_id`),
  INDEX `backlog_id_index` (`backlog_id`),
  INDEX `guid_index` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Domain:  ScrumComment
--
Drop table  if exists scrum_comment;
CREATE TABLE `scrum_comment` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `developer_id` int(11),
  `comment_time` datetime,
  `content` text,
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `developer_id_index` (`developer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Domain:  SprintMeeting
--
Drop table  if exists sprint_meeting;
CREATE TABLE `sprint_meeting` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `meeting_date` date,
  `type_`  varchar(255),
  `content` text,
  `sprint_id` int(11),
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `sprint_id_index` (`sprint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Domain:  SprintMeetingDeveloper
--
Drop table  if exists sprint_meeting_developer;
CREATE TABLE `sprint_meeting_developer` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `meeting_id` int(11),
  `developer_id` int(11),
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `developer_id_index` (`developer_id`),
  INDEX `meeting_id_index` (`meeting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Domain:  SprintTaskMoveRecord
--
Drop table  if exists sprint_task_move_record;
CREATE TABLE `sprint_task_move_record` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `from_sprint_id` int(11),
  `to_sprint_id` int(11),
  `task_id` int(11),
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `from_sprint_id_index` (`from_sprint_id`),
  INDEX `task_id_index` (`task_id`),
  INDEX `to_sprint_id_index` (`to_sprint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Domain:  Project
--
Drop table  if exists project;
CREATE TABLE `project` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `name_`  varchar(255),
  `code`  varchar(255),
  `creator_id`  int(11),
  `description`  text,
  `finish_date`  date,
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `creator_id_index` (`creator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;


--
-- Domain:  Team
--
Drop table  if exists team;
CREATE TABLE `team` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `name_` varchar(255),
  `creator_id`  int(11),
  `description`  text,
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `creator_id_index` (`creator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Domain:  TeamProject
--
Drop table  if exists team_project;
CREATE TABLE `team_project` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `project_id`  int(11),
  `team_id`  int(11),
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `team_id_index` (`team_id`),
  INDEX `project_id_index` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Domain:  ProjectProductOwner
--
Drop table  if exists project_product_owner;
CREATE TABLE `project_product_owner` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `project_id`  int(11),
  `product_owner_id`  int(11),
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `product_owner_id_index` (`product_owner_id`),
  INDEX `project_id_index` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  GeckoFile
-- ###############
Drop table  if exists gecko_file;
CREATE TABLE `gecko_file` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `name_` varchar(255),
  `path_` varchar(255),
  `suffix` varchar(255),
   `temp_` tinyint(1) default '0',
   `size_` int(20) default 0,
   `type_` varchar(255) default 'GeckoFile',
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  Document
-- ###############
Drop table  if exists document;
CREATE TABLE `document` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `creator_id` int(11),
  `file_id` int(11),
  `backlog_id` int(11),
   `type_` varchar(255) default 'Document',
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `creator_id_index` (`creator_id`),
  INDEX `file_id_index` (`file_id`),
  INDEX `backlog_id_index` (`backlog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ###############
-- Domain:  SprintTaskComment
-- ###############
Drop table  if exists sprint_task_comment;
CREATE TABLE `sprint_task_comment` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `who_id` int(11),
  `task_id` int(11),
  `content` text,
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `who_id_index` (`who_id`),
  INDEX `task_id_index` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;


-- ###############
-- Domain:  Log
-- ###############
Drop table  if exists log;
CREATE TABLE `log` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `operator_id` int(11),
  `task_id` int(11),
  `sprint_id` int(11),
  `meeting_id` int(11),
  `type_` varchar(255) default 'Log',
  `content` text,
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`),
  INDEX `operator_id_index` (`operator_id`),
  INDEX `meeting_id_index` (`meeting_id`),
  INDEX `sprint_id_index` (`sprint_id`),
  INDEX `task_id_index` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;


-- ###############
-- Domain:  SystemConfiguration
-- ###############
Drop table  if exists system_configuration;
CREATE TABLE `system_configuration` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `per_page_size` varchar(255) default 'TWENTY',
  PRIMARY KEY  (`id`),
  INDEX `guid_index` (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;


