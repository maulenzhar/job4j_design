
create table type(
                     id serial primary key,
                     name varchar(255)
);

create table product(
                        id serial primary key,
                        name varchar(255),
                        expired_date date,
                        price float,
                        type_id int references type(id)
);


insert into type(name)
values ('МОЛОЧНЫЕ ПРОДУКТЫ'),
       ('ЗЕРНОВЫЕ'),
       ('ЗЕРНОВЫЕ'),
       ('ФРУКТЫ И ОВОЩИ'),
       ('СЫР');

insert into product(name, expired_date, price, type_id)
values
('йогурт', '2023-01-31', 80, 1),
('мороженое', '2023-01-31', 45, 1),
('кефир', '2023-01-30', 60, 1),
('ряженка', '2023-02-01', 75, 1),
('рис', '2023-12-01', 105, 2),
('горох', '2023-11-01', 95, 2),
('чечевица', '2023-10-01', 120, 2),
('яблоки', '2023-06-01', 60, 3),
('груши', '2023-07-01', 50, 3),
('хурма', '2023-02-01', 100, 3),
('морковь', '2023-02-10', 40, 3),
('капуста', '2023-02-20', 55, 3),
('Моцарелла', '2023-01-31', 150, 4);

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
 select *
from product p
join type t on p.type_id = t.id
where t.name ilike '%сыр%';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select *
from product p
where p.name ilike '%мороженое%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select *
from product p
where p.expired_date < current_date;

-- 4. Написать запрос, который выводит самый дорогой продукт. Запрос должен быть универсальный и находить все
-- продукты с максимальной ценой.
select *
from product p
where price = (select max(p1.price) from product p1);

-- 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select t.name, count(t.id)
from product p
         join type t on p.type_id = t.id
group by t.id;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select *
from product p
         join type t on p.type_id = t.id
where t.name ilike '%МОЛОКО%' or t.name ilike '%СЫР%'

--  7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
--  Под количеством подразумевается количество продуктов определенного типа. Например,
--  если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла", которые ему принадлежат,
--  то количество продуктов типа "СЫР" будет 2.

select t.name, count(t.id)
from product p
         join type t on p.type_id = t.id
group by t.id
having count(t.id) < 10;

-- 8. Вывести все продукты и их тип.
select p.name, t.name
from product p
         join type t on p.type_id = t.id;