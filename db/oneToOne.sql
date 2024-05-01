create table email(
    id serial primary key,
    name varchar(255)
);

create table account(
    id serial primary key,
    data varchar(255),
    email_id int references email(id) unique
);
