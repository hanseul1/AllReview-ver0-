-- category table create문
create table category(
no int primary key not null,
name varchar(50) not null
);

-- keyword table create문
create table keyword(
no int primary key not null,
word varchar(30) not null,
category int references category(no)
);

-- product table create문
create table product(
no int primary key not null,
name varchar(50) not null,
category int references category(no),
com int references company(no)
);


