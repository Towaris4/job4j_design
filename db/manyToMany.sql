create table lesson(
    id serial primary key,
    name varchar(255)
);
create table pupils(
    id serial primary key,
    name varchar(255),
);

create table pupils_lessons(
    id serial primary key,
    pupil_id int references pupils(id),
    lesson_id int references lesson(id),
);

insert into lesson(name) values ('math');
insert into lesson(name) values ('history');
insert into lesson(name) values ('informatics');

insert into pupils(name) values ('Dominik De Koko');
insert into pupils(name) values ('Ivan Tarasov');

insert into pupils_lessons(pupil_id, lesson_id) values (1, 1);
insert into pupils_lessons(pupil_id, lesson_id) values (1, 2);
insert into pupils_lessons(pupil_id, lesson_id) values (1, 3);
insert into pupils_lessons(pupil_id, lesson_id) values (2, 3);

select * from pupils_lessons;


