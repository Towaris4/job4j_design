create table fauna(
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('bobr', 70, 01.01.1950);
insert into fauna(name, avg_age, discovery_date) values ('fish', 5, 01.01.0001);
insert into fauna(name, avg_age, discovery_date) values ('wolf', 7, 01.01.1111);

select * from fauna where name LIKE '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';