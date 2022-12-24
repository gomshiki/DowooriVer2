create table member_table(
    empNum  bigint not null auto_increment,
    name    varchar(15) not null,
    pw      varchar(20) not null,
    position varchar()
);

create table dept_table(
    deptNum tinyint not null,
    deptName,
    UNIQUE KEY uk_deptName (deptName),
    primary key(deptNum)
);

select * from dept_table;

select * from board_table;

select * from `Dowoori_ver2`.`Board_table` where emp_num=1;