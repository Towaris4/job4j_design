create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

INSERT INTO car_bodies (name) VALUES ('Sedan'), ('SUV'), ('Hatchback'), ('Coupe'), ('Convertible'), ('Nomane');

INSERT INTO car_engines (name) VALUES ('4-cylinder'), ('V6'), ('V8'), ('6-cylinder'), ('V12');

INSERT INTO car_transmissions (name) VALUES ('Automatic'), ('Manual'), ('CVT');

INSERT INTO cars (name, body_id, engine_id, transmission_id)
VALUES ('Toyota Camry', 1, 1, 1),
       ('Ford Explorer', 2, 2, 2),
       ('Volkswagen Golf', 3, 1, 2),
       ('Chevrolet Corvette', 4, 2, 3),
       ('Honda Civic', 3, 1, NULL),
       ('Jeep Wrangler', 2, NULL, 2),
       ('BMW M3', 1, 3, 1),
       ('Mazda MX-5', 5, 2, 2),
       ('Mercedes-Benz S-Class', 4, 3, NULL);

select c.id, c.name, b.name, e.name, t.name from cars c
left join car_bodies b on c.body_id = b.id
left join car_engines e on c.engine_id = e.id
left join car_transmissions t on c.transmission_id = t.id;

select * from car_bodies b left join cars c on c.body_id = b.id where body_id is null;

select * from car_engines e left join cars c on c.engine_id = e.id where engine_id is null;

select * from car_transmissions b left join cars c on c.transmission_id = b.id where transmission_id is null;
