USE dss;

INSERT INTO opcional
		(ID, preco, Designacao, Stock, Categoria, Pacote_ID)
        VALUES
        (1, 750, "Piano preto lacado", 50, "Acabamentos interiores", NULL),        
        (2, 3650, "AMG carbono mate", 13, "Acabamentos interiores", NULL),
        (3, 3650, "AMG fibra de carbono", 17, "Acabamentos interiores", NULL),
        (4, 500, "Madeira de freixo", 25, "Acabamentos interiores", NULL),
        (5, 10, "Madeira de freixo cinzento", 20, "Acabamentos interiores", NULL),
        
        (6, 500, "Vidros laterais escurecidos", 48, "Acabamentos exteriores", NULL),
        (7, 450, "Teto escurecido", 30, "Acabamentos exteriores", NULL),
        (8, 1200, "Cobertura do motor em carbono", 20, "Acabamentos exteriores", NULL),
        (9, 750, "Teto panorâmico", 13, "Acabamentos exteriores", NULL),
        (10, 500, "Pinça de travões colorida", 89, "Acabamentos exteriores", NULL),
        (11, 3000, "Spoiler traseiro", 7, "Acabamentos exteriores", NULL),
        (12, 100, "Gancho para reboque", 50, "Acabamentos exteriores", NULL),
        
        (13, 500, "Câmara de marcha atrás", 20, "Segurança", NULL),
        (14, 500, "Assistente de ângulo morto", 17, "Segurança", NULL),
        (15, 750, "Prevenção de colisão", 12, "Segurança", NULL),
        (16, 1000, "Assistente de direção", 8, "Segurança", NULL),
        (17, 500, "Sidebags traseiros", 40, "Segurança", NULL),
        (18, 150, "Extintor", 13, "Segurança", NULL),
        (19, 1000, "Câmara 360º", 15, "Segurança", NULL),
        (20, 350, "Assistente de faixa de rodagem", 23, "Segurança", NULL),
        (21, 250, "Assistente automático do controlo de velocidade", 30, "Segurança", NULL),
        
        (22, 1250, "Sintonizador de TV", 3, "Telemática", NULL),
        (23, 150, "Chave digital no smartphone", 30, "Telemática", NULL),
        (24, 350, "Carregamento wireless para smartphone", 13, "Telemática", NULL),
        (25, 350, "Carregamento wireless para smartphone (zona traseira)", 25, "Telemática", NULL),
        (26, 450, "Sirius Satellite Radio", 60, "Telemática", NULL),
        (27, 1500, "Sistema de Som Burmester", 30, "Telemática", NULL),
        (28, 5000, "Sistema de Som Burmester 3D High End", 10, "Telemática", NULL);

SELECT * FROM opcional;

INSERT INTO obrigatório
		(ID, preco, Designacao, Stock, Categoria)
        VALUES
        (1, 20000, "Motor 270kW", 23, "Motor"),
        (2, 22500, "Motor 300kW", 17, "Motor"),
        (3, 27000, "Motor 320kW", 12, "Motor"),
        (4, 35000, "Motor 430kW", 5, "Motor"),        

		(5, 5000, "Branco Polar", 100, "Pintura"),
        (6, 5000, "Azul Brilliant", 73, "Pintura"),
        (7, 5000, "Cinzento Graphite", 50, "Pintura"),
        (8, 5000, "Cinzento Selenite", 83, "Pintura"),
        (9, 5000, "Vermelho Jupiter", 55, "Pintura"),
        (10, 5000, "Preto Obsidian", 68, "Pintura"),
        (11, 7500, "Prata Eridium", 31, "Pintura"),
        
        (12, 1500, "Pele Preto", 120, "Estofos"),
        (13, 1500, "Pele cinzento e preto", 103, "Estofos"),
        (14, 2000, "Pele exclusive preto", 107, "Estofos"),
        (15, 2000, "Pele exclusive castanho", 93, "Estofos"),
        (16, 2500, "Pele dinamica vermelha", 88, "Estofos"),
        (17, 2500, "Pele dinamica preto", 113, "Estofos"),
        
        (18, 650, "Liga Leve AMG multiraios de 20\"", 83, "Jantes"),
        (19, 650, "5 raios AMG de 20\"", 60, "Jantes"),
        (20, 650, "AMG com raios cruzados de 21\"", 55, "Jantes"),
        (21, 550, "AMG multiraios de 21\"", 73, "Jantes"),
        (22, 750, "Liga leve de 5 raios duplos de 20\"", 38, "Jantes"),
        (23, 1250, "Forjadas de raios cruzados de 21\"", 20, "Jantes");

SELECT * FROM obrigatório;






SET FOREIGN_KEY_CHECKS=0;
DROP TABLE opcional;
SET FOREIGN_KEY_CHECKS=1;
