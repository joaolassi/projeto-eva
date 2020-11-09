CREATE TABLE IF NOT EXISTS arte (
  `idArte` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `data` DATE NOT NULL,
  `valor` DECIMAL(10,0) NOT NULL,
  `tipoArte` VARCHAR(45) NOT NULL,
   `imagem` VARCHAR(1000),
  PRIMARY KEY (`idArte`))
ENGINE = InnoDB;

INSERT INTO arte (`idArte`, `nome`, `descricao` ,`data`, `valor`, `tipoArte`) VALUES ('1', 'Medusa', 'Tinta óleo.' ,'20200119', '20000', 'Quadro');
INSERT INTO arte (`idArte`, `nome`, `descricao` ,`data`, `valor`, `tipoArte`) VALUES ('2', 'Clock', 'Tirada com uma Canon 7 EOS','20200319', '15000', 'Fotografia');
INSERT INTO arte (`idArte`, `nome`, `descricao` ,`data`, `valor`, `tipoArte`) VALUES ('3', 'Hero', 'Desenho digital' ,'20200512', '300', 'Desenho');
INSERT INTO arte (`idArte`, `nome`, `descricao` ,`data`, `valor`, `tipoArte`) VALUES ('4', 'Aracnideo', 'Aranha feita em mármore' ,'20200724', '5000', 'Escultura');

