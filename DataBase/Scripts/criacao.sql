
-- -----------------------------------------------------
-- Schema DBPI
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DBPI` DEFAULT CHARACTER SET utf8 ;
USE `DBPI` ;

-- -----------------------------------------------------
-- Table `DBPI`.`TEstados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TEstados` (
  `BDIDUF` INT NOT NULL,
  `BDNOMEUF` VARCHAR(45) NOT NULL,
  `BDSIGLAUF` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`BDIDUF`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TCidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TCidades` (
  `BDIDCIDADE` INT NOT NULL,
  `BDNOMECID` VARCHAR(50) NOT NULL,
  `BDDESCCID` VARCHAR(150) NULL,
  `BDIDUF` INT NOT NULL,
  PRIMARY KEY (`BDIDCIDADE`),
  INDEX `fk_TCidades_TEstados1_idx` (`BDIDUF` ASC),
  CONSTRAINT `fk_TCidades_TEstados1`
    FOREIGN KEY (`BDIDUF`)
    REFERENCES `DBPI`.`TEstados` (`BDIDUF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TEndereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TEndereco` (
  `BDCEP` INT NOT NULL,
  `BDIDCIDADE` INT NOT NULL,
  `BDBAIRRO` VARCHAR(45) NULL,
  PRIMARY KEY (`BDCEP`),
  INDEX `fk_TEndereco_TCidades1_idx` (`BDIDCIDADE` ASC),
  CONSTRAINT `fk_TEndereco_TCidades1`
    FOREIGN KEY (`BDIDCIDADE`)
    REFERENCES `DBPI`.`TCidades` (`BDIDCIDADE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TClinica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TClinica` (
  `BDIDCLINICA` INT NOT NULL,
  `BDIDCEP` INT NOT NULL,
  `BDCNPJ` VARCHAR(18) NULL,
  `BDNOME` VARCHAR(45) NULL,
  `BDNOMEFANTASIA` VARCHAR(150) NULL,
  `BDSENHA` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`BDIDCLINICA`),
  INDEX `fk_TClinica_TEndereco1_idx` (`BDIDCEP` ASC),
  CONSTRAINT `fk_TClinica_TEndereco1`
    FOREIGN KEY (`BDIDCEP`)
    REFERENCES `DBPI`.`TEndereco` (`BDCEP`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TPermicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TPermicao` (
  `BDIDPERMICAO` INT NOT NULL,
  `BDPERMICAO` VARCHAR(40) NULL,
  `BDDESCRISSAO` VARCHAR(100) NULL,
  PRIMARY KEY (`BDIDPERMICAO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TUser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TUser` (
  `BDIDUSER` INT NOT NULL,
  `BDIDCLINICA` INT NOT NULL,
  `BDCPF` VARCHAR(14) NOT NULL,
  `BDMAIL` VARCHAR(100) NOT NULL,
  `BDSENHA` VARCHAR(8) NOT NULL,
  `BDIDPERMICAO` INT NOT NULL,
  PRIMARY KEY (`BDIDUSER`, `BDIDCLINICA`),
  INDEX `fk_TUser_TPermicao1_idx` (`BDIDPERMICAO` ASC),
  CONSTRAINT `fk_TUser_TClinica1`
    FOREIGN KEY (`BDIDCLINICA`)
    REFERENCES `DBPI`.`TClinica` (`BDIDCLINICA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TUser_TPermicao1`
    FOREIGN KEY (`BDIDPERMICAO`)
    REFERENCES `DBPI`.`TPermicao` (`BDIDPERMICAO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TDadosUser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TDadosUser` (
  `BDCEP` INT NOT NULL,
  `BDNOME` VARCHAR(45) NOT NULL,
  `BDGENERO` VARCHAR(18) NULL,
  `BDTELEFONE` VARCHAR(13) NULL,
  `BDDATANASCIMENTO` DATE NULL,
  `BDIDUSER` INT NOT NULL,
  `BDIDCLINICA` INT NOT NULL,
  PRIMARY KEY (`BDCEP`, `BDIDUSER`, `BDIDCLINICA`),
  INDEX `fk_TDadosUser_TEndereco1_idx` (`BDCEP` ASC),
  INDEX `fk_TDadosUser_TUser1_idx` (`BDIDUSER` ASC, `BDIDCLINICA` ASC),
  CONSTRAINT `fk_TDadosUser_TEndereco1`
    FOREIGN KEY (`BDCEP`)
    REFERENCES `DBPI`.`TEndereco` (`BDCEP`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TDadosUser_TUser1`
    FOREIGN KEY (`BDIDUSER` , `BDIDCLINICA`)
    REFERENCES `DBPI`.`TUser` (`BDIDUSER` , `BDIDCLINICA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TPets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TPets` (
  `BDIDPET` INT NOT NULL,
  `BDIDRACA` INT NOT NULL,
  `BDNOMEPET` VARCHAR(45) NOT NULL,
  `BDAPELIDO` VARCHAR(20) NULL,
  `BDDATANASCIMENTO` DATE NOT NULL,
  PRIMARY KEY (`BDIDPET`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TEspecie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TEspecie` (
  `BDIDESPECIE` INT NOT NULL,
  `BDNOMEESPECIE` VARCHAR(45) NULL,
  PRIMARY KEY (`BDIDESPECIE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TRaca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TRaca` (
  `BDIDRACA` INT NOT NULL,
  `BDNOMERACA` VARCHAR(45) NOT NULL,
  `BDIDESPECIE` INT NOT NULL,
  PRIMARY KEY (`BDIDRACA`, `BDIDESPECIE`),
  INDEX `fk_TRacas_TPets1_idx` (`BDIDRACA` ASC),
  INDEX `fk_TRaca_TEspecie1_idx` (`BDIDESPECIE` ASC),
  CONSTRAINT `fk_TRacas_TPets1`
    FOREIGN KEY (`BDIDRACA`)
    REFERENCES `DBPI`.`TPets` (`BDIDPET`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TRaca_TEspecie1`
    FOREIGN KEY (`BDIDESPECIE`)
    REFERENCES `DBPI`.`TEspecie` (`BDIDESPECIE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TComorbidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TComorbidade` (
  `BDIDCOMORBIDADE` INT NOT NULL,
  `BDNOMECOMORBIDADE` VARCHAR(45) NOT NULL,
  `BDDESCCOMORBIDADE` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`BDIDCOMORBIDADE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TAtendimento_Entrada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TAtendimento_Entrada` (
  `BDIDENTRADA` INT NOT NULL,
  `BDIDPET` INT NOT NULL,
  `BDIDCOMORBIDADE` INT NULL,
  `BDDATAENTRADA` DATE NULL,
  `BDDESCRISSAO` VARCHAR(300) NULL,
  PRIMARY KEY (`BDIDENTRADA`, `BDIDPET`),
  INDEX `fk_TAtendimento_Entrada_TPets1_idx` (`BDIDPET` ASC),
  INDEX `fk_TAtendimento_Entrada_TComorbidade1_idx` (`BDIDCOMORBIDADE` ASC),
  CONSTRAINT `fk_TAtendimento_Entrada_TPets1`
    FOREIGN KEY (`BDIDPET`)
    REFERENCES `DBPI`.`TPets` (`BDIDPET`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TAtendimento_Entrada_TComorbidade1`
    FOREIGN KEY (`BDIDCOMORBIDADE`)
    REFERENCES `DBPI`.`TComorbidade` (`BDIDCOMORBIDADE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TAtendimento_Saida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TAtendimento_Saida` (
  `BDIDSAIDA` INT NOT NULL,
  `BDIDENTRADA` INT NOT NULL,
  `BDIDPET` INT NOT NULL,
  `BDIDCOMORBIDADE` INT NOT NULL,
  `BDIDDATASAIDA` DATE NULL,
  PRIMARY KEY (`BDIDSAIDA`, `BDIDENTRADA`, `BDIDPET`, `BDIDCOMORBIDADE`),
  INDEX `fk_TAtendimento_Saida_TAtendimento_Entrada1_idx` (`BDIDENTRADA` ASC, `BDIDPET` ASC),
  INDEX `fk_TAtendimento_Saida_TComorbidade1_idx` (`BDIDCOMORBIDADE` ASC),
  CONSTRAINT `fk_TAtendimento_Saida_TAtendimento_Entrada1`
    FOREIGN KEY (`BDIDENTRADA` , `BDIDPET`)
    REFERENCES `DBPI`.`TAtendimento_Entrada` (`BDIDENTRADA` , `BDIDPET`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TAtendimento_Saida_TComorbidade1`
    FOREIGN KEY (`BDIDCOMORBIDADE`)
    REFERENCES `DBPI`.`TComorbidade` (`BDIDCOMORBIDADE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TMedicacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TMedicacao` (
  `BDIDMEDICACAO` INT NOT NULL,
  `BDNOMEMEDICACAO` VARCHAR(45) NULL,
  `BDDESCRICAO` VARCHAR(150) NULL,
  PRIMARY KEY (`BDIDMEDICACAO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TReceita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TReceita` (
  `BDIDPET` INT NOT NULL,
  `BDIDMEDICACAO` INT NOT NULL,
  `BDINICIORECEITA` DATE NULL,
  `BDFINALRECEITA` DATE NULL,
  `BDDESCRICAO` VARCHAR(300) NULL,
  PRIMARY KEY (`BDIDPET`, `BDIDMEDICACAO`),
  INDEX `fk_TReceita_TMedicacao1_idx` (`BDIDMEDICACAO` ASC),
  CONSTRAINT `fk_TReceita_TPets1`
    FOREIGN KEY (`BDIDPET`)
    REFERENCES `DBPI`.`TPets` (`BDIDPET`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TReceita_TMedicacao1`
    FOREIGN KEY (`BDIDMEDICACAO`)
    REFERENCES `DBPI`.`TMedicacao` (`BDIDMEDICACAO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBPI`.`TPETS_TUSER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBPI`.`TPETS_TUSER` (
  `BDIDPET` INT NOT NULL,
  `BDIDUSER` INT NOT NULL,
  `BDIDCLINICA` INT NOT NULL,
  PRIMARY KEY (`BDIDPET`, `BDIDUSER`, `BDIDCLINICA`),
  INDEX `fk_TPets_has_TUser_TUser1_idx` (`BDIDUSER` ASC, `BDIDCLINICA` ASC),
  INDEX `fk_TPets_has_TUser_TPets1_idx` (`BDIDPET` ASC),
  CONSTRAINT `fk_TPets_has_TUser_TPets1`
    FOREIGN KEY (`BDIDPET`)
    REFERENCES `DBPI`.`TPets` (`BDIDPET`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TPets_has_TUser_TUser1`
    FOREIGN KEY (`BDIDUSER` , `BDIDCLINICA`)
    REFERENCES `DBPI`.`TUser` (`BDIDUSER` , `BDIDCLINICA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
