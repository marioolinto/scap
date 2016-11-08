CREATE DATABASE db_scap;

USE db_scap;

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `rg` varchar(9) DEFAULT NULL,
  `cpf` varchar(14) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `endereco` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=446 DEFAULT CHARSET=utf8;

CREATE TABLE `telefone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cliente_telefone` (`cliente_id`),
  CONSTRAINT `cliente_telefone` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=518 DEFAULT CHARSET=utf8;

CREATE TABLE `produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `descricao` tinytext NOT NULL,
  `valor` float NOT NULL,
  `imagem` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` int(11) NOT NULL,
  `numero` varchar(5) NOT NULL,
  `datapedido` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dataentrega` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `endereco` text,
  `observacao` text,
  `desconto` float NOT NULL,
  `valor` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pedido_cliente` (`cliente_id`),
  CONSTRAINT `pedido_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=586 DEFAULT CHARSET=utf8;


CREATE TABLE `pedidoitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pedido_id` int(11) NOT NULL,
  `produto_id` int(11) NOT NULL,
  `quantidade` int(3) NOT NULL,
  `valor` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pedido_item` (`pedido_id`),
  KEY `produto_itempedido` (`produto_id`),
  CONSTRAINT `pedido_item` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `produto_itempedido` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1065 DEFAULT CHARSET=utf8;

CREATE USER scap_root@localhost IDENTIFIED BY 'starfox@123';

GRANT ALL privileges ON db_scap.* to scap_root@localhost; 


