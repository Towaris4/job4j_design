create table lesson(
    id serial primary key,
    name varchar(255)
);
create table pupils(
    id serial primary key,
    name varchar(255),
    lesson_id int references lesson(id)
);
insert into lesson(name) values ('math');
insert into pupils(name, int) values ('Vasyan', 1);

select * from pupils;
select * from lesson where id in (select lesson_id from employees);