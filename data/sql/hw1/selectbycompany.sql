CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO public.company (id, name) VALUES (1, 'company1');
INSERT INTO public.company (id, name) VALUES (2, 'company2');
INSERT INTO public.company (id, name) VALUES (3, 'company3');
INSERT INTO public.company (id, name) VALUES (4, 'company4');
INSERT INTO public.company (id, name) VALUES (5, 'company5');

INSERT INTO public.person (id, name, company_id) VALUES (1, 'person1', 1);
INSERT INTO public.person (id, name, company_id) VALUES (2, 'person2', 2);
INSERT INTO public.person (id, name, company_id) VALUES (3, 'person3', 3);
INSERT INTO public.person (id, name, company_id) VALUES (4, 'person4', 4);
INSERT INTO public.person (id, name, company_id) VALUES (5, 'person5', 4);
INSERT INTO public.person (id, name, company_id) VALUES (6, 'person6', 3);
INSERT INTO public.person (id, name, company_id) VALUES (7, 'person7', 4);
INSERT INTO public.person (id, name, company_id) VALUES (8, 'person8', 3);
INSERT INTO public.person (id, name, company_id) VALUES (9, 'person9', 5);
INSERT INTO public.person (id, name, company_id) VALUES (10, 'person10', 5);


-- 1. В одном запросе получить
-- - имена всех person, которые не состоят в компании с id = 5;
-- - название компании для каждого человека.
select p.name, c.name
from person p
         join company c on p.company_id = c.id
where company_id <> 5;

-- 2. Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
-- Нужно учесть, что таких компаний может быть несколько.
select c.name as name, count(p.id) as count
from person p
    join company c on p.company_id = c.id
group by c.name
having count(p.id) >= (select count(p.id) as count
    from person p
    join company c on p.company_id = c.id
    group by c.name
    order by count desc
    limit 1);