create table father(
    id serial primary key,
    name varchar(255),
	surname varchar(255)
);

create table childrens(
    id serial primary key,
    name varchar(255),
	surname varchar(255),
    father_id int references father(id)
);

***********

create table doctors(
     id serial primary key,
     name varchar(255)
 );
 
 create table patients(
     id serial primary key,
     name varchar(255)
 );
 
 create table doctors_patients(
     id serial primary key,
     doctor_id int references doctors(id),
     patient_id int references patients(id)
 );
 
 *******
 
 create table computer(
    id serial primary key,
    name varchar(255),
	number int
);
 
 create table mac_address(
    id serial primary key,
    number varchar(255,
    computer_id int references computer(id) unique
);

