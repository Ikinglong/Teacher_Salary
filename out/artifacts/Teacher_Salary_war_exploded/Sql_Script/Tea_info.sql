drop table if exists Tea_info;

create table Tea_info(
                         id int(10) primary key auto_increment,
                         realname varchar(32) not null,
                         sex char(1),
                         post varchar(32)

);