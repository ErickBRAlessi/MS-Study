create schema if not exists shopping;

drop table if exists shopping.item;
drop table if exists shopping.shop;

create table shopping.shop (
id bigserial primary key,
user_identifier varchar(100) not null,
date timestamp not null,
total float not null
);

create table shopping.item (
shop_id bigserial REFERENCES shopping.shop(id),
product_identifier varchar(100) not null,
price float not null
);