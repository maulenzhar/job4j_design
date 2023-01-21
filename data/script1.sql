create table car(id serial primary key, name varchar(255), year int, isPetrol boolean);
insert into car(name, year, isPetrol) values('Toyota', '2003', true);
update car set name = 'BMW';
 select * from car;
 delete from car;
 select * from car;