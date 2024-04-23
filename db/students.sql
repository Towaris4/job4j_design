create table students(
	id serial primary key,
	name varchar(255),
	scholarship boolean,
	age smallserial
);
insert into students(name, scholarship, age) values('Иван', true, 26);
update students set scholarship = false;
delete from students;
select * from students;