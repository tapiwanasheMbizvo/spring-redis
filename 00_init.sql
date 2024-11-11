create table _pets
(
    id serial primary key,
    name varchar(50)
);

create table _employees
(
    id serial primary key,
    name varchar(50),
    age int,
    salary numeric(10, 2)
);

create table _mq_messages
(
    id varchar(255) primary key,
    content text
);
insert into _pets(name) values
('Bruno'),
('King Kong'),
('Rex'),
('max')
;