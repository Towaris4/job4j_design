create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

create table type(
	id serial primary key,
	name varchar(255)
);

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('ШОКОЛАД'), ('МАСЛО');
insert into product(name, type_id, expired_date, price) values ('Коровье молоко', 2, '01.02.2024', 80);
insert into product(name, type_id, expired_date, price) values ('Соевое молоко', 2, '01.02.2024', 60);
insert into product(name, type_id, expired_date, price) values ('Моцарелла', 1, '01.02.2025', 300);
insert into product(name, type_id, expired_date, price) values ('Гауда', 1, '01.02.2025', 400);
insert into product(name, type_id, expired_date, price) values ('Аленка', 3, '01.08.2024', 100);
insert into product(name, type_id, expired_date, price) values ('Тоблерон', 3, '01.08.2024', 100);

create view name_count as  select s.name, count(ss.name)
FROM product ss join type s on s.id = ss.type_id
GROUP BY s.name;

select * from name_count;


