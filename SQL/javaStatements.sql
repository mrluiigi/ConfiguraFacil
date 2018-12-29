USE dss;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES  ON dss.* TO 'user'@'localhost';


INSERT INTO Configuração (NIF_Cliente, Pronta, Preco, Modelo) VALUES ("1111111111111111111111111", 0)