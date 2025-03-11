create table wallets(
    id serial primary key,
    owner varchar(50),
    currency varchar(50),
    balance integer
);

insert into wallets(owner, currency, balance) values ('Ivan Tarasov', 'dollar', 5000);
insert into wallets(owner, currency, balance) values ('Igor Petrov', 'rubles', 500000);
insert into wallets(owner, currency, balance) values ('Misha Ivanov', 'euro', 7000);

/*win1* uncommitted*/
/*win1*/
SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
BEGIN;
/*win2*/
SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
BEGIN;
/*win1*/
SELECT * FROM wallets WHERE owner = 'Ivan Tarasov';
/*win2*/
UPDATE wallets SET balance = 6000 WHERE owner = 'Ivan Tarasov';
/*win1*/
SELECT * FROM wallets WHERE owner = 'Ivan Tarasov';
/*win2*/
ROLLBACK;
/*win1*/
SELECT * FROM wallets WHERE owner = 'Ivan Tarasov';
/*win1*/
COMMIT;
/*win2*/
COMMIT;

/*COMMITTED*/
/*win1*/
BEGIN;
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SELECT * FROM wallets WHERE balance > 0;
/*win2*/
BEGIN;
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
INSERT INTO wallets (owner, currency, balance) VALUES ('New User', 'euro', 1000);
COMMIT;
/*win1*/
SELECT * FROM wallets WHERE balance > 0;
COMMIT;

/*REPEATABLE*/
/*win1*/
BEGIN;
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
SELECT * FROM wallets WHERE balance > 0;
/*win2*/
BEGIN;
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
INSERT INTO wallets (owner, currency, balance) VALUES ('New User', 'euro', 1000);
COMMIT;
/*win1*/
SELECT * FROM wallets WHERE balance > 0;
COMMIT;

/*win1* SERIALIZABLE*/
/*win1*/
BEGIN;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SELECT * FROM wallets WHERE balance > 0;
/*win2*/
BEGIN;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
INSERT INTO wallets (owner, currency, balance) VALUES ('New User', 'euro', 1000);
COMMIT;
/*win1*/
SELECT * FROM wallets WHERE balance > 0;
COMMIT;