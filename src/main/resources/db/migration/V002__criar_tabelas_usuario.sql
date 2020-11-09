
CREATE TABLE permissao (
  `id` BIGINT NOT NULL,
  `descricao` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE usuario (
  `id` BIGINT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `senha` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));

CREATE TABLE IF NOT EXISTS usuario_permissao (
  `usuario_id` BIGINT NOT NULL,
  `permissao_id` BIGINT NOT NULL,
  PRIMARY KEY (`usuario_id`, `permissao_id`),
  INDEX `fk_usuario_has_permissao_permissao1_idx` (`permissao_id` ASC) ,
  INDEX `fk_usuario_has_permissao_usuario_idx` (`usuario_id` ASC) ,
  CONSTRAINT `fk_usuario_has_permissao_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES usuario (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_permissao_permissao1`
    FOREIGN KEY (`permissao_id`)
    REFERENCES permissao (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO usuario (id, nome, email, senha) values (1, 'Master', 'master@eva.com', '$2a$10$6bF4hQ.UkMEdjRdKnsHMxepdqKaPTSqC3/nFevM.RmKmVPQDVraFK');
INSERT INTO usuario (id, nome, email, senha) values (2, 'Servant', 'servant@eva.com', '$2a$10$2ZYGLcE/G5Lds9TVPQoB9Owx/uVc.dBpUGyI40yEHwYOWM1tM.T4O');
INSERT INTO usuario (id, nome, email, senha) values (3, 'Jonas', 'jonas@eva.com', '$2a$10$AmOd02rSY/3be.KiDVJu5uXjxnLRjfeL/5pgFWH0dIPn6DVqvUDwq');

INSERT INTO permissao (id, descricao) values (1, 'ROLE_CADASTRAR_ARTE');
INSERT INTO permissao (id, descricao) values (2, 'ROLE_PESQUISAR_ARTE');
INSERT INTO permissao (id, descricao) values (3, 'ROLE_EXCLUIR_ARTE');


-- master
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 1);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 2);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 3);
-- servant
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (2, 1);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (2, 2);
-- jonas
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3,2);
