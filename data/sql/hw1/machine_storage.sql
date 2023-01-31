create table car_bodies
(
    id   serial primary key,
    name varchar(255)
);

create table car_engines
(
    id   serial primary key,
    name varchar(255)
);

create table car_transmissions
(
    id   serial primary key,
    name varchar(255)
);

create table cars
(
    id              serial primary key,
    name            varchar(255),
    body_id         int references car_bodies (id),
    engine_id       int references car_engines (id),
    transmission_id int references car_transmissions (id)
);

insert into car_bodies(name)
values ('body 1');
insert into car_bodies(name)
values ('body 2');

insert into car_engines(name)
values ('engine 1');
insert into car_engines(name)
values ('engine 2');
insert into car_engines(name)
values ('engine 3');

insert into car_transmissions(name)
values ('transmission 1');
insert into car_transmissions(name)
values ('transmission 2');

insert into cars(name, body_id, engine_id, transmission_id)
values ('car 1', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id)
values ('car 2', 1, 1, null);
insert into cars(name, body_id, engine_id, transmission_id)
values ('car 3', null, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id)
values ('car 4', 1, null, 1);
insert into cars(name, body_id, engine_id, transmission_id)
values ('car 5', 1, 1, null);

-- Вывести список всех машин и все привязанные к ним детали. Нужно учесть, что каких-то деталей машина может и не содержать. В таком случае значение может быть null при выводе (например, название двигателя null);
select c.id, c.name car_name, cb.name body_name, ce.name engine_name, ct.name transmission_name
from cars c
         left join car_bodies cb on cb.id = c.body_id
         left join car_engines ce on ce.id = c.engine_id
         left join car_transmissions ct on c.transmission_id = ct.id;

-- Вывести кузовы, которые не используются НИ в одной машине.
select cb.name
from car_bodies cb
         left join cars c on cb.id = c.body_id
where c.id is null;

-- Вывести двигатели, которые не используются НИ в одной машине, аналогично п.2;
select ce.name
from car_engines ce
         left join cars c on ce.id = c.engine_id
where c.id is null;

-- Вывести коробки передач, которые не используются НИ в одной машине, аналогично п.2.
select ct.name
from car_transmissions ct
         left join cars c on ct.id = c.transmission_id
where c.id is null;