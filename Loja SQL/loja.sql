/**
	Atividade E-commerce
    @author Aluno Heder Santos
*/

-- Decimal (tipo numérico não inteiro 10,2 -> 10 digitos com 2 casas de precisão)

create database loja;
use loja;

create table clientes(
	idcli int primary key auto_increment,
    nome varchar(50) not null,
    email varchar(50) unique not null,
    senha varchar(250) not null
    );
    
    alter table clientes drop column cidade;
    
    insert into clientes (nome,email,senha) values
    ('Mikhael Santos','mikah.sanchez@git.io',md5('123456'));
     insert into clientes (nome,email,senha) values
    ('Ana Rodrigues','ana.silva@git.io',md5('123456'));
     insert into clientes (nome,email,senha) values
    ('Ayrton Santos','senna.f1@git.io',md5('123456'));
     insert into clientes (nome,email,senha) values
    ('Asap Sic','asa.sz@git.io',md5('123456'));
     insert into clientes (nome,email,senha) values
    ('Apolo Socks','appl.sza@git.io',md5('123456'));
     insert into clientes (nome,email,senha) values
    ('Augustus Silenio','august.cesar@git.io',md5('123456'));
    
    
    -- Login
    -- and (o select só será executado se as duas condições existirem.
    select * from clientes where email='mikah.sanchez@git.io' and senha=md5('123456');
    -- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    
	describe clientes;
    select * from clientes;
    select * from clientes where nome like "a%";
    select idcli as ID, nome as cliente, email as Email, senha as Senha from clientes where nome like "a%";
    
    select senha from clientes where idcli=1;
   -- buscar biblioteca rs2xml.jar Angels and Demons
    
    create table produtos (
		codigo int primary key auto_increment,
        barcode varchar(50) unique,
        produto varchar(100) not null,
        fabricante varchar(100) not null,
        -- ### Comando abaixo refere-se a data e hora automaticos ###
        datacad timestamp default current_timestamp,
        -- ### date é tipo de dados relacionados a data no formato YYYYMMDD ###
        dataval date not null,
        estoque int not null,
        estoquemin int not null,
        medida varchar(50) not null,
        valor decimal(10,2),
        loc varchar(100)
);
	
	describe produtos;
    
insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc) 
values 
('caneta bic vermelho','BIC',20221005,100,10,'CX',28.75,'setor A p2');
insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc) 
values ('bota de couro','Dafit',20231005,150,5,'CX',192.70,'setor B  A5');
insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc) 
values ('chaveiro','suice',20251005,13,10,'CX',52.90,'setor C A2');
insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc)  
values ('carne','JBS',20191007,152,8,'PCT',179.90,'setor A p6');
insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc) 
values ('requeijão cheetos','Elma Chips',20201007,100,52,'PCT',9.90,'setor D p6');
insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc)  
values ('cerveja','skol',20201127,10,25,'LT',4.90,'setor c A6');
insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc) 
values ('vodka','russia',20211006,1,20,'GF',39.90,'setor A E9');

select * from produtos;

-- Inventario de Estoque (Total)
select sum(valor * estoque) as total from produtos;

-- Relatorio de reposição do estoque
select * from produtos where estoque < estoquemin;

-- Relatorio de reposição do estoque 2 
-- date_format() -> fomatar a exibição da data
-- %d = dia %m = mês %y = ano (2 digitos) %Y = ano (4 digitos)
select codigo as código, produto, 
date_format(dataval,'%d/%m/%y') as data_validade,
estoque, estoquemin as estoque_minimo from produtos where estoque < estoquemin;

-- Relatório de produtos vencidos 1
select codigo,produto,date_format(dataval,'%d/%m/%y') as data_validade from produtos;

-- Relatório de validaden dos produtos 2
-- curdate() = data atual
select codigo as codigo, produto,date_format(dataval,'%d/%m/%y') as data_validade, datediff(dataval,curdate()) as dias_restantes from produtos;

-- ATIVIDADE 3 INDIVIDUAL
-- 5 inserts ok || Selects personalizados ok || update simples | update completo | delete

update produtos set estoque=12 where codigo=1;
update produtos set nome='ailton',fabricante='chupa cabra', dataval=19910215,estoque=1,estoquemin=0,medida='GF',valor=1.00,loc='quinto' where codigo=3;
delete from produtos where codigo=4; 
    
create table pedidos (
	pedido int primary key auto_increment,
    dataped timestamp default current_timestamp,
    total decimal(10,2),
    idcli int not null,
    foreign key(idcli) references clientes(idcli)
    );
    
insert into pedidos(idcli) values(1);

-- Abertura do pedido
select pedidos.pedido, 
date_format(pedidos.dataped,'%d/%m/%Y - %H:%i') as data_pedido,
clientes.nome as cliente, clientes.email as email from pedidos
inner join clientes on pedidos.idcli = clientes.idcli;

create table carrinho (
	pedido int not null,
    codigo int not null,
    quantidade int not null,
	foreign key(pedido) references pedidos(pedido),
    foreign key(codigo) references produtos(codigo)
    );
