create table wallets(
    id serial primary key,
    owner varchar(50),
    currency varchar(50),
    balance integer
);

insert into wallets(owner, currency, balance) values ('Ivan Tarasov', 'dollar', 5000);
insert into wallets(owner, currency, balance) values ('Igor Petrov', 'rubles', 500000);
insert into wallets(owner, currency, balance) values ('Misha Ivanov', 'euro', 7000);

begin transaction;
insert into wallets(owner, currency, balance) values ('Roman Abramovich', 'dollar', 2147483647);
savepoint first_savepoint;
select * from wallets;
drop table wallets;
savepoint second_savepoint;
select * from wallets;
rollback to first_savepoint;
select * from wallets;
commit transaction;