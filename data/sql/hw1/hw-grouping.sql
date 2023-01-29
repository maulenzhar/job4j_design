create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name)
values ('Аня'),
       ('Ваня'),
       ('Боря');
insert into devices(name, price)
values ('Телевизор LG 32" 32LQ630B6LA', 27000),
       ('Телевизионная Приставка Яндекс', 5500),
       ('Телевизор TCL 50', 25000),
       ('Настенный кронштейн', 4500),
       ('Музыкальный центр LG', 5900);
insert into devices_people(device_id, people_id)
values (1, 1),(2, 1),(3, 1), (4, 1),(5, 1),
       (3, 2),(4, 2),(4, 2),(5, 2),
       (2, 3),(3, 3),(4, 3);


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