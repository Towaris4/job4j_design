create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('iphone', 8000), ('macbook', 25000), ('PS5', 20000);
insert into people(name) values ('Ivan Tarasov'), ('Petr Arsentev');
insert into devices_people(people_id, device_id) values (2,1), (2,2), (2,3), (1,1), (1,2);

select avg(price) from devices;

select s1.name, avg(s2.price)
from devices_people ss
join people s1 on ss.people_id = s1.id
join devices s2 on ss.device_id = s2.id
group by s1.name;

select s1.name, avg(s2.price)
from devices_people ss
join people s1 on ss.people_id = s1.id
join devices s2 on ss.device_id = s2.id
group by s1.name
having avg(s2.price) > 5000;