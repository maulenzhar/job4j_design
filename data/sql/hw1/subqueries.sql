CREATE TABLE customers(
                          id serial primary key,
                          first_name text,
                          last_name text,
                          age int,
                          country text
);

insert into customers(id, first_name, last_name, age, country)
values
(1, 'Natalie', 'Higgins', 23, 'A'),
(2, 'Esther', 'Hill', 24, 'B'),
(3, 'Rick', 'Powell', 25, 'C'),
(4, 'Mary', 'Blake', 26, 'D');

SELECT * FROM customers where age = (select min(age) from customers);

CREATE TABLE orders(
                       id serial primary key,
                       amount int,
                       customer_id int references customers(id)
);

insert into orders(id, amount, customer_id)
values
(1, 122, 1),
(2, 123, 2),
(3, 124, 3);

select *
from customers c where c.id not in (select o.customer_id from orders o) ;