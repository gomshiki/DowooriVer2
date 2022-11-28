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

CREATE TABLE Dowoori_ver2.dayoff_table (
	totalDayoff DATETIME NOT NULL,
	usedDayoff DATETIME NOT NULL,
	remainDayoff DATETIME NOT NULL,
	empNum BIGINT NULL,
	deptNum TINYINT NULL,
	CONSTRAINT dayoff_table_FK FOREIGN KEY (deptNum) REFERENCES Dowoori_ver2.dept_table(deptNum)
)