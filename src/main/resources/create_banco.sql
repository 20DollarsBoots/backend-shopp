create table category (
id serial primary key,
name varchar(200) not null,
description varchar(300)
);

create table product (
id serial primary key,
name varchar(200) not null,
description varchar(300),
price double precision,
id_category bigint references  category
);

create table users (
id serial primary key,
name varchar(300) not null,
email varchar(200) not null,
cpf varchar(14) not null,
password varchar(200) not null,
token varchar(400),
zipCode varchar(8) not null,
state varchar(50) not null,
city varchar(50) not null,
place varchar(100) not null,
neighborhood varchar(100) not null,
complement varchar(100),
number integer not null
);

create table request (
id serial primary key,
id_user bigint references users,
requestedDate timestamp,
total double precision
);

create table orderedItem (
id serial primary key,
id_order bigint references request,
id_product bigint references product,
unitPrice double precision ,
amount double precision ,
totalPrice double precision
);
