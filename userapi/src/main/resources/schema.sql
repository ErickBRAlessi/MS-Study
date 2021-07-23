CREATE SCHEMA IF NOT EXISTS users;

drop table if exists users.user;

create table if not exists users.user(
      id bigserial primary key,
      nome varchar(100) not null,
      cpf  varchar(100) unique not null,
      endereco varchar(100) not null,
      email varchar(100) not null,
      telefone varchar(100) not null,
      data_cadastro varchar(100) not null
 );

