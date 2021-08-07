create database JDBC;

use jdbc;
create table Product (
id int primary key auto_increment,
title nvarchar(50),
costProduct double,
brand_id int,
foreign key (brand_id) references brand(id)
);

create table Brand (
id int primary key auto_increment,
brandName nvarchar(50)
);