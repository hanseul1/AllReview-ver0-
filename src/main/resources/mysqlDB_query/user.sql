-- user table create문
create table user(
	id varchar(100) not null primary key,
    pw varchar(100) not null,
    phone varchar(20),
    name varchar(10)
);

-- user table admin계정 insert문
insert into user(id,pw,name) values("admin","admin","admin");

