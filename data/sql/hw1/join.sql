create table departments
(
    id   serial primary key,
    name varchar(255)
);

create table employees
(
    id             serial primary key,
    name           varchar(255),
    departments_id int references departments (id)
);

insert into departments(name)
values ('department 1');
insert into departments(name)
values ('department 2');
insert into departments(name)
values ('department 3');

insert into employees(name, departments_id)
values ('employees 1', 1);
insert into employees(name, departments_id)
values ('employees 2', 2);
insert into employees(name, departments_id)
values ('employees 3', 3);
insert into employees(name, departments_id)
values ('employees 4', null);
insert into employees(name, departments_id)
values ('employees 5', null);
insert into employees(name, departments_id)
values ('employees 6', 2);

-- 3. Используя left join найти департаменты, у которых нет работников
select *
from employees e
         left join departments d on d.id = e.departments_id
where e.departments_id is null;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат
-- (порядок вывода колонок в эти запросах также должен быть идентичный).
select *
from employees e
         left join departments d on d.id = e.departments_id;
select e.*, d.*
from departments d
         right join employees e on d.id = e.departments_id;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens
(
    id     serial primary key,
    name   varchar(255),
    gender varchar(255)
);
insert into teens(name, gender)
values
       ('John', 'man'),
       ('Olivia', 'woman'),
       ('Amelia', 'woman'),
       ('James', 'woman');
select * from teens t1 cross join teens t2;

