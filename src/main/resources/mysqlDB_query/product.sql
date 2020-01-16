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

-- category 테이블 카테고리 insert문
insert into category(no,name) values(1,'food');
insert into category(no,name) values(2,'beauty');
insert into category(no,name) values(3,'electronic');

-- keyword 테이블 sample 키워드 insert문
insert into keyword(no,word,category) values(1,'맛',1);
insert into keyword(no,word,category) values(2,'플레이팅',1);
insert into keyword(no,word,category) values(3,'색감',2);
insert into keyword(no,word,category) values(4,'가격',2);
insert into keyword(no,word,category) values(5,'화면',3);
insert into keyword(no,word,category) values(6,'크기',3);

