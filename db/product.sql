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

select s.name, s1.name
from product s join type s1 on s.type_id = s1.id
where s1.name LIKE 'СЫР';

select s.name, s1.name
from product s join type s1 on s.type_id = s1.id
where s.name LIKE '%мороженное%';

select s.name from product s where s.expired_date < current_date;

SELECT name, price FROM product WHERE price = (SELECT MAX(price) FROM product);

SELECT s.name, count(ss.name)
FROM product ss join type s on s.id = ss.type_id
GROUP BY s.name;

select * from product s join type ss on s.type_id = ss.id
where ss.name LIKE 'СЫР' or ss.name LIKE 'МОЛОКО';

select s.name from type s join product ss on ss.type_id = ss.id
group by s.name
having count(ss.type_id) < 10;

select * from product s join type ss on s.type_id = ss.id;


