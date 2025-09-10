select version();

show databases; -- 권한 

create user 'lion'@'localhost' identified by 'lion1234';
create user 'lion'@'%' identified by 'lion1234';
create database likedb;

use likedb;

show tables;
-- 권한 부여 
grant All privileges on likedb.* to 'lion'@'localhost';
grant All privileges on likedb.* to 'lion'@'%';
grant all privileges on likedb.* to 'carami'@'%';

grant create user on *.* to 'carami'@'%';

flush privileges; -- 권한 적용

use mysql;

select user,host from mysql.user where user='lion';

use liondb;

drop user 'lion'@'localhost';


select * from mysql.user;