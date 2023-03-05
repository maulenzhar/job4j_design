create table product(
                        id serial primary key,
                        name varchar(255),
                        expired_date date,
                        price float,
                        type_id int references type(id)
);

create table type(
                       id serial primary key,
                       name varchar(255)
);

insert into type(name)
values ('МОЛОЧНЫЕ ПРОДУКТЫ'),
       ('ЗЕРНОВЫЕ'),
       ('ФРУКТЫ И ОВОЩИ');

insert into product(name, expired_date, price, type_id)
values
       ('йогурт', '2023-01-31', 80, 1),
       ('кефир', '2023-01-30', 60, 1),
       ('ряженка', '2023-02-01', 75, 1),
       ('рис', '2023-12-01', 105, 2),
       ('горох', '2023-11-01', 95, 2),
       ('чечевица', '2023-10-01', 120, 2),
       ('яблоки', '2023-06-01', 60, 3),
       ('груши', '2023-07-01', 50, 3),
       ('хурма', '2023-02-01', 100, 3),
       ('морковь', '2023-02-10', 40, 3),
       ('капуста', '2023-02-20', 55, 3);





-- 3. Используя агрегатные функции вывести среднюю цену устройств.
select avg(d.price)
from devices d;

-- 4. Используя группировку вывести для каждого человека среднюю цену его устройств.
select p.name, avg(d.price)
from devices_people as dp
         join devices d on dp.device_id = d.id
         join people  p on dp.people_id = p.id
group by  p.name;

-- 5. Дополнить запрос п.4. условием, что средняя стоимость устройств должна быть больше 5000.
select p.name, avg(d.price)
from devices_people as dp
         join devices d on dp.device_id = d.id
         join people  p on dp.people_id = p.id
group by  p.name
having avg(d.price) > 5000;