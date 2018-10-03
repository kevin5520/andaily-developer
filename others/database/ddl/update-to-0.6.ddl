
alter table backlog add `number_` int(11) unique;

alter table backlog add INDEX `number_index` (`number_`);

update backlog set number_ = id ;

alter table user_ add `description` text;



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



-- initial system default configuration
truncate system_configuration;
insert into system_configuration(id,guid,create_time,archived,per_page_size)
values
(50,'000eaee62f424060b358c5615313c236',now(),0,'TWENTY');

