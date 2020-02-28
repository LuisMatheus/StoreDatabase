-- MySQL Script generated by MySQL Workbench
-- 11/26/15 15:47:31
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cliente` (
  `Cpf` INT(11) NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `dataDeNascimento` DATE NOT NULL,
  `Telefone` INT(11) NOT NULL,
  PRIMARY KEY (`Cpf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`lentes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`lentes` (
  `Codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `grauOlhoEsquerdo` DOUBLE NOT NULL,
  `grauOlhoDireito` DOUBLE NOT NULL,
  `Transitions` TINYINT(1) NULL DEFAULT NULL,
  `VideoFilter` TINYINT(1) NULL DEFAULT NULL,
  `Antirreflexo` TINYINT(1) NULL DEFAULT NULL,
  `Lentescol` TINYINT(1) NULL DEFAULT NULL,
  `Crizal` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`lentesdecontato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`lentesdecontato` (
  `Codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `grauOlhoDireito` DOUBLE NOT NULL,
  `grauOlhoEsquerdo` DOUBLE NOT NULL,
  `Cor` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`oculos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`oculos` (
  `Codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `Modelo` VARCHAR(45) NOT NULL,
  `Cor` VARCHAR(45) NOT NULL,
  `Tamanho` DOUBLE NOT NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`produto` (
  `Codigo` INT(11) NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `EmEstoque` TINYINT(1) NOT NULL,
  `Valor` DOUBLE NOT NULL,
  `Lentes_Codigo` INT(11) NULL DEFAULT NULL,
  `LentesDeContato_Codigo` INT(11) NULL DEFAULT NULL,
  `Oculos_Codigo` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  INDEX `fk_Produto_Lentes1_idx` (`Lentes_Codigo` ASC),
  INDEX `fk_Produto_LentesDeContato1_idx` (`LentesDeContato_Codigo` ASC),
  INDEX `fk_Produto_Oculos1_idx` (`Oculos_Codigo` ASC),
  CONSTRAINT `fk_Produto_Lentes1`
    FOREIGN KEY (`Lentes_Codigo`)
    REFERENCES `mydb`.`lentes` (`Codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Produto_LentesDeContato1`
    FOREIGN KEY (`LentesDeContato_Codigo`)
    REFERENCES `mydb`.`lentesdecontato` (`Codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Produto_Oculos1`
    FOREIGN KEY (`Oculos_Codigo`)
    REFERENCES `mydb`.`oculos` (`Codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`itemvenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`itemvenda` (
  `Venda_Codigo` INT(11) NOT NULL,
  `Produto` VARCHAR(45) NOT NULL,
  `Quantidade` INT(11) NOT NULL,
  `Produto_Codigo` INT(11) NOT NULL,
  PRIMARY KEY (`Venda_Codigo`),
  INDEX `fk_ItemVenda_Produto1_idx` (`Produto_Codigo` ASC),
  CONSTRAINT `fk_ItemVenda_Produto1`
    FOREIGN KEY (`Produto_Codigo`)
    REFERENCES `mydb`.`produto` (`Codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`venda` (
  `notaFiscal` INT(11) NOT NULL,
  `Data` DATE NOT NULL,
  `nomeDoCliente` VARCHAR(45) NOT NULL,
  `Cliente_Cpf` INT(11) NOT NULL,
  PRIMARY KEY (`notaFiscal`),
  INDEX `fk_Venda_Cliente1_idx` (`Cliente_Cpf` ASC),
  CONSTRAINT `fk_Venda_Cliente1`
    FOREIGN KEY (`Cliente_Cpf`)
    REFERENCES `mydb`.`cliente` (`Cpf`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`itemvenda_venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`itemvenda_venda` (
  `ItemVenda_Venda` INT(11) NOT NULL,
  `Venda_notaFiscal` INT(11) NOT NULL,
  PRIMARY KEY (`ItemVenda_Venda`, `Venda_notaFiscal`),
  INDEX `fk_ItemVenda_has_Venda_Venda1_idx` (`Venda_notaFiscal` ASC),
  INDEX `fk_ItemVenda_has_Venda_ItemVenda_idx` (`ItemVenda_Venda` ASC),
  CONSTRAINT `fk_ItemVenda_has_Venda_ItemVenda`
    FOREIGN KEY (`ItemVenda_Venda`)
    REFERENCES `mydb`.`itemvenda` (`Venda_Codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ItemVenda_has_Venda_Venda1`
    FOREIGN KEY (`Venda_notaFiscal`)
    REFERENCES `mydb`.`venda` (`notaFiscal`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;