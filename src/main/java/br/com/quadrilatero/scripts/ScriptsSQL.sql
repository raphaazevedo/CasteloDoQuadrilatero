--criar a tabela de categoria
create table categoria(
	id			uuid		primary key,
	tipo		varchar(50)	not null unique
);
--criar a tabela de pessoa
create table pessoa(
	id			uuid			primary key,
	nome		varchar(150)	not null,
	apelido		varchar(30)		not null,
	email		varchar(50)		not null,
	nascimento  DATE 					,
	categoria_id	uuid		not null,
	foreign key(categoria_id)
		references categoria(id)
);
--criar a tabela de tipos de evento
create table tipoEvento(
	id			uuid			primary key,
	tipo		varchar(50)		not null unique
);
--criar a tabela de eventos
create table eventos(
	id			uuid			primary key,
	nome		varchar(150)	not null,
	dataEvento  DATE ,
	tipoEvento_id	uuid			not null,
	foreign key(tipoEvento_id)
		references tipoEvento(id)
);
-- alterando tabela de eventos
alter table eventos add column descricao text not null;
-- criando tabela função
create table funcao(
	id					uuid			primary key,
	tipoDeFuncao		varchar(200)	not null unique
);
create table depoimento_festa(
	id					uuid			primary key,
	escritor			varchar(100)	not null,
	depoimento			text  			not null,
	festa_id			uuid			not null,
	foreign key(festa_id)
		references eventos(id)
);
-- povoando a tabela categoria
insert into categoria(id, tipo) 
values(gen_random_uuid(), 'MORADOR');
insert into categoria(id, tipo) 
values(gen_random_uuid(), 'FUNCIONARIO');



