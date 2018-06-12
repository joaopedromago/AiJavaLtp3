BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `cliente` (
	`id`	INTEGER UNSIGNED AUTO-INCREMENT NOT NULL,
	`dataCriacao`	TEXT NULL,
	`dataExclusao`	TEXT NULL,
	`nome`	TEXT NOT NULL,
	`cpf`	TEXT NOT NULL,
	`rg`	TEXT NOT NULL,
	`endereco`	TEXT NOT NULL,
	`telefone`	TEXT NOT NULL,
	`email`	TEXT NOT NULL,
	`numeroCompras`	INTEGER NOT NULL,
	PRIMARY KEY(`id`)
);

CREATE TABLE IF NOT EXISTS `vendedor` (
	`id`	INTEGER UNSIGNED AUTO-INCREMENT NOT NULL,
	`dataCriacao`	TEXT NULL,
	`dataExclusao`	TEXT NULL,
	`nome`	TEXT NOT NULL,
	`cpf`	TEXT NOT NULL,
	`rg`	TEXT NOT NULL,
	`endereco`	TEXT NOT NULL,
	`telefone`	TEXT NOT NULL,
	`email`	TEXT NOT NULL,
	PRIMARY KEY(`id`)
);

CREATE TABLE IF NOT EXISTS `servico` (
	`id`	INTEGER UNSIGNED AUTO-INCREMENT NOT NULL,
	`dataCriacao`	TEXT NULL,
	`dataExclusao`	TEXT NULL,
	`nome`	TEXT NOT NULL,
	`tipoServico`	INTEGER NOT NULL,
	`preco`	DOUBLE(9,2) NOT NULL,
	PRIMARY KEY(`id`)
);

CREATE TABLE IF NOT EXISTS `servicoVendedor` (
	`id`	INTEGER UNSIGNED AUTO-INCREMENT NOT NULL,
	`dataCriacao`	TEXT NULL,
	`dataExclusao`	TEXT NULL,
	`servicoId`	INTEGER NOT NULL,
	`vendedorId`	INTEGER NOT NULL,
	`quantidadeServicos`	INTEGER NOT NULL,
	`preco`	DOUBLE(9,2) NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`vendedorId`) REFERENCES(`vendedor`),
	FOREIGN KEY(`servicoId`) REFERENCES(`servico`)
);

CREATE TABLE IF NOT EXISTS `servicoVendedor` (
	`id`	INTEGER UNSIGNED AUTO-INCREMENT NOT NULL,
	`dataCriacao`	TEXT NULL,
	`dataExclusao`	TEXT NULL,
	`clienteId`	INTEGER NOT NULL,
	`vendedorId`	INTEGER NOT NULL,
	`servicoId`	INTEGER NOT NULL,
	`dataRealizacao`	DATE NOT NULL,
	PRIMARY KEY(`id`),
	FOREIGN KEY(`clienteId`) REFERENCES(`cliente`),
	FOREIGN KEY(`vendedorId`) REFERENCES(`vendedor`),
	FOREIGN KEY(`servicoId`) REFERENCES(`servico`)
);

COMMIT TRANSACTION;