USE dss;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES  ON dss.* TO 'user'@'localhost';


INSERT INTO Configuração (NIF_Cliente, Pronta, Preco, Modelo) VALUES ("1111111111111111111111111", 0);

UPDATE obrigatório SET Stock = 1 WHERE ID = 1;

SELECT * FROM opcional;

SELECT preco FROM opcional ORDER BY preco ASC;
Select * FROM opcional;


SELECT * FROM opcional WHERE Pacote_ID  IS NULL;