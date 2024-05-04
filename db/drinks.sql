create table cup(
    id serial primary key,
    capacity int,
    drink_id int references drink(id),
);

create table drink(
    id serial primary key,
    name varchar(255)
)

insert into drink(name) values drink('tea');
insert into drink(name) values drink('coffie');
insert into drink(name) values drink('beer');

insert into cup(capacity, drink_id) values drink(300, 1);
insert into cup(capacity, drink_id) values drink(500, 2);
insert into cup(capacity, drink_id) values drink(1000, 3);

select p.name Имя, pp.capacity as 'Объем мл.'
from cup pp join drink p on pp.drink_id = p.id;