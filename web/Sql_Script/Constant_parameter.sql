drop table if exists constant_parameter;

create table constant_parameter(
    id int primary key not null COMMENT '参数id',
    item_name varchar(16) not null COMMENT '参数名称',
    value double not null comment '参数值'
) comment ='常量参数表，包括五险比例、公积金比例';