CREATE TABLE todo(
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	detail varchar(1000) NOT NULL,
	created_at datetime DEFAULT current_timestamp(),
	finished_at datetime DEFAULT NULL,
	state enum('未完成','进行中','已完成') DEFAULT '未完成',
	memo varchar(200) DEFAULT NULL,
	PRIMARY KEY (id)
)