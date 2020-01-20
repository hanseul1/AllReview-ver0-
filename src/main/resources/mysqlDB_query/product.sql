-- category table create문
create table category(
no int primary key not null,
name varchar(50) not null
);

-- keyword table create문
create table keyword(
no int primary key not null auto_increment,
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

-- category 테이블 카테고리 insert문
insert into category(no,name) values(1,'digital');
insert into category(no,name) values(2,'fashion');
insert into category(no,name) values(3,'life');
insert into category(no,name) values(4,'interior');
insert into category(no,name) values(5,'sprots');
insert into category(no,name) values(6,'infant');
insert into category(no,name) values(7,'food');
insert into category(no,name) values(8,'beauty');
insert into category(no,name) values(9,'travel');