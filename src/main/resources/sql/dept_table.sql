CREATE TABLE sys.dept_table (
	dept_num int NOT NULL,
	dept_name varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (dept_num),
	CONSTRAINT deptName UNIQUE KEY (dept_name)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='';
