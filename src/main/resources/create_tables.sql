drop table if exists person;

create table person(
id serial primary key,
first_name text,
last_name text,
age integer,
date_of_birth date
);

insert into person(first_name, last_name, age, date_of_birth) values
('Дима', 'Ловицкий', 12, '20-09-2009'),
('Максим', 'Кизилов', 28,'23-04-1994');