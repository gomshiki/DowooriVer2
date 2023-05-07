CREATE TABLE sys.Board_table (
	Board_id bigint auto_increment NOT NULL,
	title varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
	emp_num bigint NOT NULL,
	dept_num int NULL,
	reason varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	start_date date NOT NULL,
	end_date date NULL,
	ampm varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	write_date timestamp DEFAULT CURRENT_TIMESTAMP  NULL,
	status varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
	approve_level varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
	CONSTRAINT Board_table_UN UNIQUE KEY (Board_id),
	CONSTRAINT `PRIMARY` PRIMARY KEY (Board_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='';
CREATE INDEX Board_table_FK USING BTREE ON sys.Board_table (emp_num);
CREATE INDEX Board_table_FK_1 USING BTREE ON sys.Board_table (dept_num);
