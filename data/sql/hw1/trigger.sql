-- 1.
create or replace function tax_product()
    returns trigger as
$$
BEGIN
update products
set price = price + price * 0.2
where id = (select id from inserted);
return new;
END;
$$
LANGUAGE 'plpgsql';


create trigger tax_trigger_product
    after insert
    on products
    referencing new table as inserted
    for each statement
execute procedure tax_product();

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 100);

-- 2.Триггер должен срабатывать до вставки данных и насчитывать налог на товар (нужно прибавить налог к цене товара). Здесь используем row уровень.
create or replace function tax_product_row()
    returns trigger as
$$
BEGIN
    new.price = new.price + new.price * 0.2;
return NEW;
END;
$$
LANGUAGE 'plpgsql';

create trigger tax_product_row_trigger_1
    before insert
    on products
    for each row
    execute procedure tax_product_row();

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 1, 100);

select *
from products;

-- 3.    Нужно написать триггер на row уровне, который при вставке продукта в таблицу products, будет заносить имя, цену и текущую дату в таблицу history_of_price.

create table history_of_price (
                                  id serial primary key,
                                  name varchar(50),
                                  price integer,
                                  date timestamp
);

create or replace function update_table()
    returns trigger as
$$
BEGIN
insert into history_of_price (name, price, date)
select name, price, now() from products order by id desc limit 1;
return NEW;
END;
$$
LANGUAGE 'plpgsql';

create trigger update_table_trigger
    after insert
    on products
    referencing new table as inserted
    for each statement
execute procedure update_table();

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 1, 100);

select *
from history_of_price;