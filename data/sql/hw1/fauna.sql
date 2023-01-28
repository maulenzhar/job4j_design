create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values
                                                        ('giant lizards',  10000,  '1950-09-01'),
                                                        ('crocodiles', 21000,  '1951-09-01'),
                                                        ('frilled lizard', 10001,  '1952-09-01'),
                                                        ('black snake', 100,  '1949-09-01'),
                                                        ('Australian bull shark', 10002, '1948-09-01'),
                                                        ('whales', 10,  '1947-09-01'),
                                                        ('Kerguelen fur seal', 11,   '1958-09-01'),
                                                        ('Sea leopard', 12,  null),
                                                        ('crabeater seal', 13, null),
                                                        ('Weddell seal', 14,  null),
                                                        ('southern elephant seal', 15, null),
                                                        ('drop fish', 16, null);

select *
from fauna
where name ilike '%fish%';

select *
from fauna
where avg_age >= 10000 and avg_age < 21000;

select *
from fauna
where discovery_date is null;

select *
from fauna
where DATE_PART('Year', discovery_date) < 1950;