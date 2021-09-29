drop table if exists Admin_info;

create table Admin_info(
    id int(10) primary key auto_increment,
    username varchar(32),
    password varchar(32)
);

insert into Admin_info(id,username,password) values(1,'xiejinlong','123');
insert into Admin_info(id,username,password) values(2,'tanzhengtao','123');
insert into Admin_info(id,username,password) values(3,'wangqiyue','123');

commit;