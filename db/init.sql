create table rules(
    id serial primary key,
    name varchar(255),
);

create table roles(
    id serial primary key,
    name varchar(255),
);

create table role_rule(
    id serial primary key,
    name varchar(255),
    role_id int references role(id),
    rule_id int references rule(id),
);

create table users(
    id serial primary key,
    name varchar(255),
    roles_id int references roles(id),
);

create table states(
    id serial primary key,
    name varchar(255),
);

create table categories(
    id serial primary key,
    name varchar(255),
);

create table items(
    id serial primary key,
    name varchar(255),
    user_id int references users(id),
    states_id int references states(id),
    categories_id int references categories(id),
);

create table comments(
    id serial primary key,
    name varchar(255),
    item_id int references items(id)
);

create table attachs(
    id serial primary key,
    name varchar(255),
    item_id int references items(id)
);

