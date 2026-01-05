create TABLE companies
(
    id INTEGER NOT NULL,
    name CHARACTER VARYING,
    CONSTRAINT companies_pkey PRIMARY KEY (id)
);

create TABLE people
(
    id INTEGER NOT NULL,
    name CHARACTER VARYING,
    company_id INTEGER REFERENCES companies(id),
    CONSTRAINT people_pkey PRIMARY KEY (id)
);

insert into companies (id, name) values
(1, 'Газпром'),
(2, 'Сбербанк'),
(3, 'Яндекс'),
(4, 'Лукойл'),
(5, 'Роснефть'),
(6, 'Тинькофф Банк'),
(7, 'ВКонтакте'),
(8, 'Альфа-Банк'),
(9, 'РЖД'),
(10, 'МТС');

insert into people (id, name, company_id) values
(1,  'Александр Иванов',        1),  -- Газпром
(2,  'Мария Петрова',           2),  -- Сбербанк
(3,  'Дмитрий Сидоров',         3),  -- Яндекс
(4,  'Елена Кузнецова',         4),  -- Лукойл
(5,  'Андрей Смирнов',          5),  -- Роснефть
(6,  'Ольга Васильева',         6),  -- Тинькофф Банк
(7,  'Игорь Морозов',           7),  -- ВКонтакте
(8,  'Наталья Новикова',        8),  -- Альфа-Банк
(9,  'Сергей Фёдоров',          9),  -- РЖД
(10, 'Татьяна Лебедева',        10), -- МТС
(11, 'Павел Громов',            3),  -- Яндекс (ещё один сотрудник)
(12, 'Анна Зайцева',            2),  -- Сбербанк
(13, 'Владимир Орлов',          NULL), -- без компании (фрилансер, в поиске и т.п.)
(14, 'Екатерина Соколова',      6),  -- Тинькофф Банк
(15, 'Роман Волков',            1);  -- Газпром

select
    p.name as person_name,
    c.name as company_name
from
    people p
left join
    companies c on p.company_id = c.id
where
    p.company_id is DISTINCT FROM 5;

select
    c.name as company_name,
    count(p.id) as person_count
from
    companies c
left join
    people p on p.company_id = c.id
group by
    c.id, c.name
having
    count(p.id) = (
        select max(cnt)
        from (
            select count(p2.id) as cnt
            from companies c2
            left join people p2 on p2.company_id = c2.id
            group by c2.id
        ) sub
    );