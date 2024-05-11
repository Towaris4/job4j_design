create table departments(
    id serial primary key,
    name varchar(255),
)

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int reference department(id),
);

insert into departments(name) values ('Marketing'), ('Finance'), ('Human Resources'), ('Software Development');

insert into employees(name, department_id) values ('Ivan Tarasov', 3), ('Petya Rostov', 3), ('Josiv Stalin', 2), ('Petr Arsentev', 3);

select * from departments s left join employees ss on s.department_id = ss.id;

select * from departments s right join employees ss on s.department_id = ss.id;

select * from departments s full join employees ss on s.department_id = ss.id;

select * from departments s cross join employees ss on s.department_id = ss.id;

select * from departments s left join employees ss on s.department_id = ss.id
where ss.id is null;

select s.name, ss.name from employees s right join departments ss on s.department_id = ss.id;
select s.name, ss.name from departments s left join employees ss on s.department_id = ss.id;

create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teen(name, gender) values ('Mishka', 'male'), ('Nastya', 'female'), ('Darya', 'female');

select n1.name, '-', n2.name from teens n1 cross join teens n2 where n1.gender > n2.gender;
