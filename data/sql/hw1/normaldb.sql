create table gender
(
    id   serial primary key,
    name text
);

create table films
(
    id   serial primary key,
    name text
);

create table clients
(
    name      text,
    surname   text,
    address   text,
    primary key (name, surname, address),
    gender_id int references gender (id)
);

create table films_clients
(
    id       serial primary key,
    cl_name text,
    cl_surname text,
    cl_address text,
    films_id int references films (id),
    FOREIGN KEY (cl_name, cl_surname, cl_address)
        REFERENCES clients (name, surname, address)
        ON DELETE RESTRICT
        ON UPDATE CASCADE

);