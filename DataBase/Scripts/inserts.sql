use dbpi;

INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (1,'AC', 'Acre');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (2,'AL', 'Alagoas');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (3,'AP', 'Amapá');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (4,'AM', 'Amazonas');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (5,'BA', 'Bahia');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (6,'CE', 'Ceará');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (7,'DF', 'Distrito Federal');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (8,'ES', 'Espírito Santo');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (9,'GO', 'Goiás');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (10,'MA', 'Maranhão');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (11,'MT', 'Mato Grosso');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (12,'MS', 'Mato Grosso do Sul');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (13,'MG', 'Minas Gerais');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (14,'PA', 'Pará');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (15,'PB', 'Paraíba');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (16,'PR', 'Paraná');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (17,'PE', 'Pernambuco');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (18,'PI', 'Piauí');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (19,'RJ', 'Rio de Janeiro');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (20,'RN', 'Rio Grande do Norte');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (21,'RS', 'Rio Grande do Sul');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (22,'RO', 'Rondônia');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (23,'RR', 'Roraima');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (24,'SC', 'Santa Catarina');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (25,'SP', 'São Paulo');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (26,'SE', 'Sergipe');
INSERT INTO TEstados (`BDIDUF`, `BDSIGLAUF`, `BDNOMEUF`) VALUES (27,'TO', 'Tocantins');

INSERT INTO `dbpi`.`tcidades`
(`BDIDCIDADE`,
`BDNOMECID`,
`BDDESCCID`,
`BDIDUF`)
VALUES
(1, 'Gaspar', 'linda', 1);

INSERT INTO `dbpi`.`tendereco`
(`BDCEP`,
`BDIDCIDADE`,
`BDBAIRRO`)
VALUES
(11111111, 1, 'Bela vista');


INSERT INTO `dbpi`.`tclinica`
(`BDIDCLINICA`,
`BDIDCEP`,
`BDCNPJ`,
`BDNOME`,
`BDNOMEFANTASIA`,
`BDSENHA`)
VALUES
(1, 11111111, '11.111.111/1111-11', 'Clínica Demonstração', 'Nome Fantasia', '1');


INSERT INTO `dbpi`.`tpermicao`
(`BDIDPERMICAO`,
`BDPERMICAO`,
`BDDESCRISSAO`)
VALUES
(1, 'Admin', 'descricao'),
(2, 'Funcionario', 'descricao'),
(3, 'Cliente', 'descricao');


INSERT INTO `dbpi`.`tuser`
(`BDIDUSER`,
`BDIDCLINICA`,
`BDCPF`,
`BDMAIL`,
`BDSENHA`,
`BDIDPERMICAO`)
VALUES
(1, 1, '111.111.111-11', 'admin@gmail.com', 'admin', 1),
(2, 1, '987.654.321-00', 'enzo@gmail.com', 'senha456', 2),
(3, 1, '111.222.333-44', 'vini@gmail.com', 'senha789', 3),
(4, 1, '555.444.333-22', 'ana@gmail.com', 'senhaABC', 1);

INSERT INTO `dbpi`.`tdadosuser`
(`BDCEP`,
`BDNOME`,
`BDGENERO`,
`BDTELEFONE`,
`BDDATANASCIMENTO`,
`BDIDUSER`,
`BDIDCLINICA`)
VALUES
('11111111', 'Admin', 'Masculino', '(11) 98765-4321', '1995-08-15', 1, 1),
('11111111', 'Enzo', 'Feminino', '(21) 99999-8888', '1980-03-10', 2, 1),
('11111111', 'Vini', 'Feminino', '(31) 55555-4444', '1999-12-01', 3, 1),
('11111111', 'Ana Flavia', 'Feminino', '(48) 77777-6666', '2004-11-19', 4, 1);

-- Tabela tespecie
INSERT INTO `dbpi`.`tespecie`
(`BDIDESPECIE`,
`BDNOMEESPECIE`)
VALUES
(1, 'Gato'),
(2, 'Cachorro'),
(3, 'Coelho'),
(4, 'Hamster');

-- Tabela traca
INSERT INTO `dbpi`.`traca`
(`BDIDRACA`,
`BDNOMERACA`,
`BDIDESPECIE`)
VALUES
(1, 'Siamês', 1),
(2, 'Labrador', 2),
(3, 'Angorá', 1),
(4, 'Poodle', 2),
(5, 'Holandês', 3),
(6, 'Sírio', 4);

INSERT INTO `dbpi`.`tpets`
(`BDIDPET`,
`BDIDRACA`,
`BDNOMEPET`,
`BDAPELIDO`,
`BDDATANASCIMENTO`,
`BDIDUSER`)
VALUES
(1, 1, 'Bolinha', 'Bolinha', '2018-01-01',1),
(2, 2, 'Rex', 'Rex', '2016-05-20',1),
(3, 3, 'Mel', 'Melzinha', '2020-03-10',1),
(4, 4, 'Mia', 'Mia', '2019-11-12',1);


INSERT INTO `dbpi`.`tcomorbidade`
(`BDIDCOMORBIDADE`,
`BDNOMECOMORBIDADE`,
`BDDESCCOMORBIDADE`)
VALUES
(1, 'Febre', 'Muita dor na cabeça');

INSERT INTO `dbpi`.`tmedicacao`
(`BDIDMEDICACAO`,
`BDNOMEMEDICACAO`,
`BDDESCRICAO`)
VALUES
(1, 'Paracetamol', 'dores'),
(2, 'Ibuprofeno', 'inflamações'),
(3, 'Amoxicilina', 'antibiótico'),
(4, 'Omeprazol', 'problemas no estômago');

INSERT INTO `dbpi`.`treceita`
(`BDIDRECEITA`,
`BDIDMEDICACAO`,
`BDINICIORECEITA`,
`BDFINALRECEITA`,
`BDDESCRICAO`)
VALUES
(1, 1, '2022-01-01', '2022-01-07', 'Tomar paracetamol de 8 em 8 horas para aliviar a dor de cabeça.');

INSERT INTO `dbpi`.`tatendimento_entrada`
(`BDIDENTRADA`,
`BDIDPET`,
`BDCOMORBIDADE`,
`BDDATAENT`,
`BDDESC`)
VALUES
(1, 1, 1, '2022-03-28', 'Pet apresentando sintomas de falta de ar.');

INSERT INTO `dbpi`.`tatendimento_saida`
(`BDIDSAIDA`,
`BDIDENTRADA`,
`BDIDPET`,
`BDIDRECEITA`,
`BDIDCOMORBIDADE`,
`BDDATASAIDA`,
`BDDESC`)
VALUES
(1, 1, 1, 1, 1, '2023-03-28', 'Alta após tratamento de infecção');

INSERT INTO `dbpi`.`tagendamento`
(`bdidagendamento`,
`bdidpet`,
`bddataagen`,
`bdhora`)
VALUES
(1, 1, '2023-05-23', '08:30');