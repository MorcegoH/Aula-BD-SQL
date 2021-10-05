/**
	Atividade Carrinho
    @author Aluno Heder Santos
*/

-- Decimal (tipo numérico não inteiro 10,2 -> 10 digitos com 2 casas de precisão)

create database loja;
use loja;
create table carrinho 
(
	sku int primary key auto_increment,
    produto varchar(150) not null,
    quantidade int not null,
    valor int not null
);

alter table carrinho change valor valor decimal(10,2) not null;

describe carrinho;

-- CRUD (Create)
insert into carrinho (produto,quantidade,valor) values ('caneta bic CX30',10,17.50);
insert into carrinho (produto,quantidade,valor) values ('Cheetos Requeijao',2,9.20);
insert into carrinho (produto,quantidade,valor) values ('Alexa',1,20.7);
insert into carrinho (produto,quantidade,valor) values ('Soju',5,35.9);
insert into carrinho (produto,quantidade,valor) values ('Yakissoba',6,45.9);
insert into carrinho (produto,quantidade,valor) values ('Celular',2,1599.0);
insert into carrinho (produto,quantidade,valor) values ('Alforge',1,350.9);

-- CRUD (Read)
select * from carrinho;

-- Operações Matematicas no banco de dados
select (valor * quantidade) as parcial from carrinho;
select sum(valor * quantidade) as total from carrinho;

-- Selects CRUD
select sku, produto from carrinho;
select quantidade, produto, valor from carrinho;
select sku, valor, produto from carrinho;

-- Select Colunas
select * from carrinho order by produto;
select * from carrinho order by quantidade;
select * from carrinho order by sku;

-- descrescente;
select sku, produto from carrinho order by sku desc;
select valor, sku from carrinho order by valor desc;
select sku, produto, valor from carrinho order by produto desc;

-- Filtros
select * from carrinho where sku= 1;
select * from carrinho where valor= 350.9;
select * from carrinho where produto='Cheetos Requeijao';

-- Update - ATENÇÃO!! Não esquece do comando WHERE. Usar sempre o ID para evitar problemas.
update carrinho set quantidade=7 where sku=3;
update carrinho set quantidade=63 where sku=6;
update carrinho set quantidade=10 where sku=4;
update carrinho set quantidade=2 where sku=6;

-- Delete - ATENÇÃO!! Não esquece do comando WHERE. Usar sempre o ID para evitar problemas.
delete from carrinho where sku=6;
delete from carrinho where sku=2;
