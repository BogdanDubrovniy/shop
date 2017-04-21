drop table if exists user_restrict;
drop table if exists users;
drop table if exists goods;
drop table if exists orders_list;
drop table if exists orders_entity;
drop table if exists orders;
drop table if exists user_orders;

create table user_restrict(
restict_id integer not null auto_increment primary key,
restrict_tries integer not null,
restrict_time long not null
);

insert into user_restrict values(default, 0, 1483036943039);
insert into user_restrict values(default, 0, 1483036943039);
insert into user_restrict values(default, 0, 1483036943039);

create table users(
user_id integer not null auto_increment primary key,
user_name varchar(20) not null,
user_surname varchar(20) not null,
user_login varchar(17) not null unique,
user_password varchar(17) not null,
user_email varchar(45) not null,
user_spam boolean not null,
id_user_restrict integer not null
);

INSERT INTO users VALUES(DEFAULT, 'Administrator', '', 'admin', 'adminPassword', 'admin@mail.com',
false, 1);
INSERT INTO users VALUES(DEFAULT, 'Peter', 'Peter', 'peter', 'peterPassword', 'peter@mail.com',
false, 2);
INSERT INTO users VALUES(DEFAULT, 'Grzegosz', 'Teodorczik', 'grzegosz', 'grzegoszPassword',
'teodorzik@mail.com',
true, 3);

create table goods(
good_id integer not null auto_increment primary key,
good_name varchar(15),
good_category varchar(15) not null,
good_firm varchar(15) not null,
good_price integer not null,
good_photo varchar(55)
);

INSERT INTO goods VALUES(DEFAULT, 'predator N345', 'boots', 'Nike', 350, 'deff.PNG');
INSERT INTO goods VALUES(DEFAULT, 'converse I', 'converse', 'Adidas', 330, '');
INSERT INTO goods VALUES(DEFAULT, 'Chuck Taylor', 'converse', 'Adidas', 530, 'ChuckTaylor.PNG');
INSERT INTO goods VALUES(DEFAULT, 'F50 2015', 'boots', 'Adidas', 430, 'F50.PNG');


create table orders(
orders_id integer not null auto_increment primary key
);

insert into orders values(default);
insert into orders values(default);
insert into orders values(default);

create table orders_list(
list_id integer not null references orders(orders_id),
id_o_goods integer not null references goods(good_id)
);

insert into orders_list values(1, 1);
insert into orders_list values(1, 2);
insert into orders_list values(1, 4);

 insert into orders_list values(2, 3);
 insert into orders_list values(2, 1);
-- insert into orders_list values(2, 4);
insert into orders_list values(3, 2);

create table orders_entity(
order_id integer not null,
order_entity_status varchar(15) not null,
order_entity_details varchar(45) not null,
order_entity_date varchar(45),
order_entity_user_info varchar(35) not null
);

insert into orders_entity values(1, 'prepared', 'not confirmed', '2016-12-20', 'user info');
insert into orders_entity values(2, 'ready', 'all good', '2016-12-21', 'user info');
insert into orders_entity values(3, 'not ready', 'all good', '2016-12-22', 'user info');


create table user_orders(
id_user integer not null references users(user_id),
id_orders integer not null unique references orders(orders_id)
);

insert into user_orders values(1, 1);
insert into user_orders values(1, 2);
insert into user_orders values(2, 3);

-- insert into user_orders values(2, 2);
-- insert into user_orders values(1, 3);