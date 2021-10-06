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

-- CRIAÇÃO TABELA ESTOQUE
create table estoque (
		codigo int primary key auto_increment,
        barcode varchar(50) unique,
        nome varchar(100) not null,
        fabricante varchar(100) not null,
        -- ### Comando abaixo refere-se a data e hora automaticos ###
        datacad timestamp default current_timestamp,
        -- ### date é tipo de dados relacionados a data no formato YYYYMMDD ###
        dataval date not null,
        quantidade int not null,
        estoquemin int not null,
        medida varchar(50) not null,
        valor decimal(10,2),
        loc varchar(100)
);
	
describe estoque;
    
insert into  estoque(nome,fabricante,dataval,quantidade,estoquemin,medida,valor,loc) values ('caneta bic vermelho','BIC',20221005,100,10,'CX',28.75,'setor A p2');
insert into estoque(nome,fabricante,dataval,quantidade,estoquemin,medida,valor,loc) values ('bota de couro','Dafit',20231005,150,5,'CX',192.70,'setor B  A5');
insert into estoque(nome,fabricante,dataval,quantidade,estoquemin,medida,valor,loc) values ('chaveiro','suice',20251005,13,10,'CX',52.90,'setor C A2');
insert into estoque(nome,fabricante,dataval,quantidade,estoquemin,medida,valor,loc) values ('carne','JBS',20191007,152,8,'PCT',179.90,'setor A p6');
insert into estoque(nome,fabricante,dataval,quantidade,estoquemin,medida,valor,loc) values ('requeijão cheetos','Elma Chips',20201007,100,52,'PCT',9.90,'setor D p6');
insert into estoque(nome,fabricante,dataval,quantidade,estoquemin,medida,valor,loc) values ('cerveja','skol',20201127,10,25,'LT',4.90,'setor c A6');
insert into estoque(nome,fabricante,dataval,quantidade,estoquemin,medida,valor,loc) values ('vodka','russia',20211006,1,20,'GF',39.90,'setor A E9');
select * from estoque;

-- Inventario de Estoque (Total)
select sum(valor * quantidade) as total from estoque;

-- Relatorio de reposição do estoque
select * from estoque where quantidade < estoquemin;

-- Relatorio de reposição do estoque 2 
-- date_format() -> fomatar a exibição da data
-- %d = dia %m = mês %y = ano (2 digitos) %Y = ano (4 digitos)
select codigo as código, nome, 
date_format(dataval,'%d/%m/%y') as data_validade,
quantidade, estoquemin as estoque_minimo from estoque where quantidade < estoquemin;

-- Relatório de produtos vencidos 1
select codigo,nome,date_format(dataval,'%d/%m/%y') as data_validade from estoque;

-- Relatório de validaden dos produtos 2
-- curdate() = data atual
select codigo as codigo, nome,date_format(dataval,'%d/%m/%y') as data_validade, datediff(dataval,curdate()) as dias_restantes from estoque;

-- ATIVIDADE 3 INDIVIDUAL


