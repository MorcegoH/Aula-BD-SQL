/**
	Sistema para gestão de uma Assistência Técnica de computadores e periféricos
    @author Heder Santos
*/
create database dbinfoy;
use dbinfoy;

create table usuarios(
id int primary key auto_increment, 
usuario varchar(50) not null, 
login varchar(50) not null unique,
senha varchar(250) not null,
perfil varchar(50) not null
);

-- a linha abaixo insere uma senha com criptografia
-- md5() criptografa a senha em 128 bits
insert into usuarios(usuario,login,senha,perfil)
values('Heder Santos','admin',md5('123456'),'administrador'
);
insert into usuarios(usuario,login,senha,perfil) values
('Ailton Kratos','aerum',md5('123'),'operador');

-- selecionando o usuario e sua respectiva senha (tela de login)
select * from usuarios where login='admin' and senha=md5('123456');
select * from usuarios where id=1;
update usuarios set usuario='Ailton Kratos Aerum',
login='ailtongow',senha=md5('1234'), perfil='operador' where id=2;


delete from usuarios where id=3;

select * from usuarios;
-- char (tipo de dados que aceita uma String de caracteres não variáveis)
create table clientes(
idcli int primary key auto_increment,
nome varchar(50) not null,
cep char(9),
endereco varchar(50) not null,
numero varchar(12) not null,
complemento varchar(30),
bairro varchar(50) not null,
uf char(2) not null,
fone varchar(15) not null,
email varchar(100) 
);

alter table clientes modify email varchar(100) unique;
delete from clientes where idcli=14;
select * from clientes;

insert into clientes(nome,cep,endereco,numero,complemento,bairro,uf,fone,email,cidade) 
values ('Ana','08665480','Rua dos Bobos','0','casa 2','lapa','SP','11989885655','ana.tg@email.io','suzano');
insert into clientes(nome,cep,endereco,numero,complemento,bairro,uf,fone,email,cidade) 
values ('Amanda','08665475','Rua dos Campeoes','5','casa 1','tatuape','SP','11989887788','amanda.t1@email.io','mogi');
insert into clientes(nome,cep,endereco,numero,complemento,bairro,uf,fone,email,cidade) 
values ('Alexandre','08665596','Rua dos Oliofantes ','115','fundos','grajau','SP','11989889988','americo@email.io','ferraz');
insert into clientes(nome,cep,endereco,numero,complemento,bairro,uf,fone,email) 
values ('Soraia','08667789','Rua dos Ornintorrinco','79','Ap 7 Bl 5','Suzano','SP','11989998563','sora@email.io');
insert into clientes(nome,cep,endereco,numero,complemento,bairro,uf,fone,email) 
values ('Mathias','08666777','Rua das Terças','18','Serie 10','Casa do Palestra','SP','11989882555','socome@email.io');

select * from clientes;
 select idcli as ID, nome as Cliente, cep as Cep, endereco as Endereço, numero as Nº, complemento as Complemento,
 bairro as Bairro, uf as UF, fone as Fone, email as Email from clientes where nome like "a%";

-- TABELA OS
--  Foreign key (FK) cria um relacionamento de  para muitos (cliente - tbos)

create table tbos(
	os int primary key auto_increment,
    dataos timestamp default current_timestamp,
    tipo varchar(20) not null,
    statusos varchar(30) not null,
    equipamento varchar(200) not null,
    defeito varchar(200) not null,
    tecnico varchar(50),
    valor decimal(10,2),
	idcli int not null,
    foreign key(idcli) references clientes(idcli)
);

describe tbos;

insert into tbos(tipo,statusos,equipamento,defeito,idcli) values
('orçamento','bancada','Notebook Lenovo G90','Não Liga',1);
insert into tbos(tipo,statusos,equipamento,defeito,tecnico,valor,idcli) values
('orçamento','aguardando aprovação','Impressora HP2020','papel enroscando','Abael','60',1);
insert into tbos(tipo,statusos,equipamento,defeito,tecnico,valor,idcli) values
('serviço','retirado','Mac Air Pro','Lentidão do sistema','Monaco','15600',4);
insert into tbos(tipo,statusos,equipamento,defeito,tecnico,valor,idcli) values
('serviço','retirado','PC Rayzen 7','Problemas no SSD','Carluxo','1600',5);
insert into tbos(tipo,statusos,equipamento,defeito,tecnico,valor,idcli) values
('serviço','em concerto','Notebook Acer Aspire VX','Tela Preta','Morcego','4500',2);

select * from tbos;

-- (inner join) união de tabelas relacionadas para consultas e updates
-- Relatório 1

select * from tbos inner join clientes on tbos.idcli = clientes.idcli;

-- relatório II
select 
	tbos.equipamento,tbos.defeito,tbos.statusos,tbos.valor, clientes.nome,clientes.fone
    from tbos inner join clientes on tbos.idcli = clientes.idcli where statusos = 'aguardando aprovação';

-- relatório III (os, data formatada,(dia, mês e ano) equipamento
-- Defeito, valor, nome do cliente    

select
	tbos.os,date_format(tbos.dataos,'%d/%m/%y - %H:%i'),tbos.equipamento,tbos.defeito,tbos.valor,clientes.nome 
    from tbos inner join clientes on tbos.idcli = clientes.idcli where statusos = 'retirado';
    
    -- Relatorio IV
    
    select sum(valor) as faturamento from tbos where statusos = 'retirado';

