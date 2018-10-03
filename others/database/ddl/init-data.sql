
-- initial user ,email: admin@andaily.com , password: admin
truncate user_;
insert into user_(id,guid,create_time,archived,email,password,nick_name,scrum_term,type_)
values
(50,'63a2974795ca4309925f209ab0af2688',now(),0,'admin@andaily.com',
'21232f297a57a5a743894a0e4a801fc3','admin','SUPER_MAN','Developer');

-- initial system default configuration
truncate system_configuration;
insert into system_configuration(id,guid,create_time,archived,per_page_size)
values
(50,'000eaee62f424060b358c5615313c236',now(),0,'TWENTY');


