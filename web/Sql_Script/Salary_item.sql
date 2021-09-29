drop table if exists  Salary_item ;

create table salary_item(
    salary_id int primary key  COMMENT '工资项id',
    teacher_id int COMMENT '教师工号 外键 跟教师信息表关联',
    teacher_name varchar(128) COMMENT '教师名称',
    post_salary double check (post_salary>0) COMMENT '职务工资',
    title_salary double check (title_salary>0) COMMENT '职称工资',
    total_pay_amount double check (total_pay_amount>0) COMMENT '总共应付工资=职务工资+职称工资',
    insurance_id int COMMENT '五险常量id 外键 跟常量表关联',
    insurance double check (insurance>0) COMMENT '五险',
    public_reserve_funds_id int  COMMENT '住房公积金 外键 跟常量表关联',
    public_reserve_funds double check (public_reserve_funds>0) COMMENT '公积金',
    final_pay_amount double check (final_pay_amount>0 ) COMMENT '最终实发工资=应发工资-五险一金',
    key insurance_fk (insurance_id),
    constraint insruance_fk foreign key(insurance_id) references constant_parameter(id),
    key public_reserve_funds_fk (public_reserve_funds_id),
    constraint public_reserve_funds_fk foreign key (public_reserve_funds_id) references constant_parameter(id),
    key teacher_fk (teacher_id),
    constraint teacher_fk foreign key (teacher_id) references tea_info(id)
) comment ='工资项目表 列举工号 姓名 应发工资项 应发工资总额 五险一金 实发工资';
