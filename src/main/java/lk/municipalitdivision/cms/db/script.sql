CREATE DATABASE cms;
use cms;
create table user (
	emp_id varchar(255) primary key,
    emp_name varchar(255) not null,
    emp_gmail varchar(255) not null,
    emp_role varchar(255) not null,
    emp_password varchar(255) not null
);

INSERT INTO user (emp_id, emp_name, emp_gmail, emp_role, emp_password) VALUES
('E001', 'Alice Johnson', 'alice.johnson@gmail.com', 'Admin', 'pass123');

CREATE TABLE complaint (
    comp_id VARCHAR(255),
    emp_id VARCHAR(255),
    comp_date DATE,
    comp_time TIME,
    comp_title VARCHAR(255),
    comp_detail VARCHAR(255),
    comp_status VARCHAR(255),
    solution VARCHAR(255)
);