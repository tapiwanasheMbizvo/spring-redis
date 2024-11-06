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

insert into _pets(name) values
('Bruno'),
('King Kong'),
('Rex'),
('max')
;