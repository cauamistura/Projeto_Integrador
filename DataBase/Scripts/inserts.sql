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
(1, 'gaspar', 'linda', 1);

INSERT INTO `dbpi`.`tendereco`
(`BDCEP`,
`BDIDCIDADE`,
`BDBAIRRO`)
VALUES
(1, 1, 'Bela vista');

INSERT INTO `dbpi`.`tclinica`
(`BDIDCLINICA`,
`BDIDCEP`,
`BDCNPJ`,
`BDNOME`,
`BDNOMEFANTASIA`,
`BDSENHA`)
VALUES
(1, 1, 'cnpj', 'nome clinica', 'nome fantasia', '1');

INSERT INTO `dbpi`.`tpermicao`
(`BDIDPERMICAO`,
`BDPERMICAO`,
`BDDESCRISSAO`)
VALUES
(1, 'permicao', 'descricao');

INSERT INTO `dbpi`.`tuser`
(`BDIDUSER`,
`BDIDCLINICA`,
`BDCPF`,
`BDMAIL`,
`BDSENHA`,
`BDIDPERMICAO`)
VALUES
(1, 1, '1', 'user@gmail.com', '1', 1);

INSERT INTO `dbpi`.`tdadosuser`
(`BDCEP`,
`BDNOME`,
`BDGENERO`,
`BDTELEFONE`,
`BDDATANASCIMENTO`,
`BDIDUSER`,
`BDIDCLINICA`)
VALUES
(1, 'nome clinica', 'm', '3333-3333', '2004-05-21', 1, 1);

INSERT INTO `dbpi`.`tpets`
(`BDIDPET`,
`BDIDRACA`,
`BDNOMEPET`,
`BDAPELIDO`,
`BDDATANASCIMENTO`)
VALUES
(1, 1, 'dog', 'mal', '2018-05-03');

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
(1, 'dogmall', 1);

INSERT INTO `dbpi`.`tcomorbidade`
(`BDIDCOMORBIDADE`,
`BDNOMECOMORBIDADE`,
`BDDESCCOMORBIDADE`)
VALUES
(1, 'nomecomorbidade', 'desccomorbidade');


INSERT INTO `dbpi`.`tatendimento_entrada`
(`BDIDENTRADA`,
`BDIDPET`,
`BDIDCOMORBIDADE`,
`BDDATAENTRADA`,
`BDDESCRISSAO`)
VALUES
(1, 1, 1, '2023-02-28', 'descricao');

INSERT INTO `dbpi`.`tatendimento_saida`
(`BDIDSAIDA`,
`BDIDENTRADA`,
`BDIDPET`,
`BDIDCOMORBIDADE`,
`BDIDDATASAIDA`)
VALUES
(1, 1, 1, 1, '2023-03-01');

INSERT INTO `dbpi`.`tmedicacao`
(`BDIDMEDICACAO`,
`BDNOMEMEDICACAO`,
`BDDESCRICAO`)
VALUES
(1, 'paracetamol', 'dores');

INSERT INTO `dbpi`.`treceita`
(`BDIDPET`,
`BDIDMEDICACAO`,
`BDINICIORECEITA`, 
`BDFINALRECEITA`,
`BDDESCRICAO`)
VALUES
(1, 1, '2022-12-01', '2023-01-23', 'dores');

INSERT INTO `dbpi`.`tpets_tuser`
(`BDIDPET`,
`BDIDUSER`,
`BDIDCLINICA`)
VALUES
(1, 1, 1);



