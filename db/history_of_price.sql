create table products(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products(name, producer, count, price) values('Milk', 'Apple', 1, 100);

create trigger tax_trigger_statement
    after insert
    on products
    referencing new table as
    inserted
    for each statement
    execute procedure tax();

    create
	or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create
or replace function tax_trigger_row()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_row
    before insert
    on products
    for each row
    execute procedure tax_trigger_row();



insert into products(name, producer, count, price) values('Milk', 'Apple', 1, 100);

select * from products;

create table history_of_price(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);


create
or replace function add()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date) values(name, price, date);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_in_history
    after insert
    on products
    for each row
    execute procedure add();
