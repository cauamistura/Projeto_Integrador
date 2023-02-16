
INSERT INTO `dbpi`.`tuser`
(`BDCPF`,
`BDMAIL`,
`BDSENHA`,
`BDCLINICA`,
`BDIDPERMISSAO`)
VALUES
(1, 'pedro@gmail.com', 123, false, null);

INSERT INTO `dbpi`.`testados`
(`BDIDUF`,
`BDNOMEUF`,
`BDSIGLAUF`)
VALUES
(1, 'Santa Catarina', 'SC');

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
`BDNUMERO`,
`BDBAIRRO`)
VALUES
(1, 1, 1, 'Bela vista');

INSERT INTO `dbpi`.`tdadosuser`
(`BDCPF`,
`BDGENERO`,
`BDTELEFONE`,
`BDCEP`)
VALUES
(1, 'M', '3333-3333', 1);

INSERT INTO `dbpi`.`tpermicao`
(`BDIDPERMISSAO`,
`BDPERMICAO`,
`BDDESCRISSAO`)
VALUES
(1, 'permitido', 'permitido');


INSERT INTO `dbpi`.`tpets`
(`BDIDPET`,
`BDIDRACA`,
`BDNOMEPET`,
`BDAPELIDO`,
`BDDATANASCIMENTO`)
VALUES
(1, 1, 'dog', 'mal', '2004-05-21');


INSERT INTO `dbpi`.`tuser_tpets`
(`BDCPF`,
`BDIDPET`)
VALUES
(1, 1);

INSERT INTO `dbpi`.`tespecie`
(`BDIDESPECIE`,
`BDNOMEESPECIE`)
VALUES
(1, 'gato');

INSERT INTO `dbpi`.`traca`
(`BDIDRACA`,
`BDNOMERACA`,
`BDIDESPECIE`)
VALUES
(1, 'boa', 1);

INSERT INTO `dbpi`.`tcomorbidade`
(`BDIDCOMORBIDADE`,
`BDNOMECOMORBIDADE`,
`BDDESCCOMORBIDADE`)
VALUES
(1, 'gripe', 'gripe');

INSERT INTO `dbpi`.`tatendimento_entrada`
(`BDIDENTRADA`,
`BDIDPET`,
`BDIDCOMORBIDADE`,
`BDDATAENTRADA`,
`BDDESCRISSAO`)
VALUES
(1, 1, 1, '2023-01-20', 'ferido');

INSERT INTO `dbpi`.`tatendimento_saida`
(`BDIDSAIDA`,
`BDIDENTRADA`,
`BDIDPET`,
`BDIDCOMORBIDADE`,
`BDIDDATASAIDA`)
VALUES
(1, 1, 1, 1, '2023-02-25');

INSERT INTO `dbpi`.`tmedicacao`
(`BDIDMEDICACAO`,
`BDNOMEMEDICACAO`,
`BDDESCRICAO`)
VALUES
(1, 'dipirona', 'bom');

INSERT INTO `dbpi`.`treceita`
(`BDIDPET`,
`BDIDMEDICACAO`,
`BDINICIORECEITA`,
`BDFINALRECEITA`,
`BDDESCRICAO`)
VALUES
(1, 1,'2023-01-25', '2023-03-15','ferido');





































