create table customer
(
    id   serial primary key,
    name varchar(255)
);

create table orders
(
    id          serial primary key,
    customer_id int references customer (id)
);

insert into customer(name)
values ('Alfred'),
       ('Antonio'),
       ('John'),
       ('Ana ')
;

insert into orders(customer_id)
values (3),
       (4);

select *
from orders o
         join customer c on o.customer_id = c.id;

select c.name, o.id
from orders o
         join customer c on o.customer_id = c.id;

select c.name as Заказчик, o.id as Заказ
from orders o
         join customer c on o.customer_id = c.id;

select c.name as Заказчик, o.id Заказ
from orders o
         join customer c on o.customer_id = c.id;