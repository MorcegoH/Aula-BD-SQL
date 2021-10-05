/**
	Agenda de Contatos
    @author Aluno Heder Santos
*/
-- Exibir banco de dados do servidor
show databases;
-- criar um novo banco de dados
create database dbagenda;
-- excluir banco de dados
drop database dbteste;
-- Selecionar o banco de dados
use dbagenda;

-- verificar tabelas existentes
show tables;

-- Criando uma tabela
-- Toda tabela precisa ter uma chave primária (PK)
-- int (tipos de dados -> números inteiros)
-- primary key  -> transforma esse campo em chave primaria
-- auto_increment -> numeração automativa
-- varchar (tipo de dados equivalente a String (50) número máximo de caracteres
-- not null -> preenchimento obrigatório
-- unique não permite valores duplicados na tabela

create table contatos(
	id int primary key auto_increment, 
    nome varchar(50) not null, 
    fone varchar(15) not null,
    email varchar(50) unique
);

show tables;

-- descrição da tabela
describe contatos;

-- alterar o nome de um campo na tabela
alter table contatos change nome contato varchar(50) not null;

-- Adicionando uma coluna
alter table contatos add column obs varchar(250);

-- Adicionando uma coluna em um local especifico da tabela
alter table contatos add column fone2 varchar(15) after fone;

-- Modificar o tipo de dados e/ou validação da coluna
alter table contatos modify column fone2 int;
alter table contatos modify column email varchar(100) not null;

-- Excluir uma coluna
alter table contatos drop column obs;

-- Excluir a tabela
drop table contatos;

-- CRUD (Create Read Update Delete)
-- operações básicas do banco de dados
-- CRUD (Criando - inserindo dados)
insert into contatos(nome,fone,email) values ('Heder Santos','11989884524','heder.lsantos@gmail.com');
insert into contatos(nome,fone,email) values ('Ailton Aerum','11989884455','ailtinho.js@git.io');
insert into contatos(nome,fone,email) values ('Isamel Cristão','11989883322','ismaelcrist.css@git.io');
insert into contatos(nome,fone,email) values ('Maria SerieC','11999884455','mariac.vaicair@git.io');
insert into contatos(nome,fone) values ('Daniel Fortnite','119988776655');
insert into contatos(nome,fone,email) values ('Cassio SP','119988776655','tricolorcassio.html@git.io');
insert into contatos(nome,fone,email) values ('Bruce Wayne','119988775566','batcave@git.io');
insert into contatos(nome,fone,email) values ('Ameba Unecelular','11989883322','tec.css@git.io');

-- CRUD (READ)
-- selecionar todos os registros (dados) da tabela
select * from contatos;

-- Selecionar colunas especificas da tabela
select nome, fone from contatos;

-- selecionar colunas em ordem crescente e decrescente
select * from contatos order by nome;

-- selecionar colunas em ordem crescente e decrescente (asc desc)
select id, nome from contatos order by id desc;

-- uso de filtros
select * from contatos where id= 1;
select * from contatos where nome = 'Ailton Aerum';
select * from contatos where nome like 'A%';

-- CRUD Update
-- ATENÇÃO!! Não esquece do comando WHERE. Usar sempre o ID para evitar problemas.
update contatos set email='leandro@email.com' where id=4;
update contatos set fone=1147428308 where id=2;


-- CRUD Delete
-- ATENÇÃO! Não esqueça do WHERE e usar sempre o ID para evitar problemas
delete from contatos where id=6;

