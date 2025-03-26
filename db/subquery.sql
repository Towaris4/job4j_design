CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers(first_name,last_name, age, country) values ('Ivan','Tarasov',27,'Russian');
insert into customers(first_name,last_name, age, country) values ('Fedya','Petrov',30,'Russian');
insert into customers(first_name,last_name, age, country) values ('Ararat','Oganisyan',25,'Armenia');

select * from customers where age = (select min(age) from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders(amount,customers_id) values ('500', 1);
insert into orders(amount,customers_id) values ('1000', 1);
insert into orders(amount,customers_id) values ('1000', 2);

SELECT * FROM customers
WHERE id NOT IN (SELECT customer_id FROM orders);
