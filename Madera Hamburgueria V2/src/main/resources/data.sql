-- Inserir algumas mesas de exemplo
INSERT INTO mesa (numero, ocupada, conta_aberta, valor_total, taxa_garcom)
VALUES 
(1, false, false, 0.00, 0.00),
(2, false, false, 0.00, 0.00),
(3, false, false, 0.00, 0.00),
(4, false, false, 0.00, 0.00),
(5, false, false, 0.00, 0.00);

-- Inserir alguns produtos de exemplo
INSERT INTO produto (nome, descricao, preco, categoria, disponivel)
VALUES 
('X-Burger', 'Hambúrguer com queijo, alface, tomate e maionese especial', 25.90, 'HAMBURGUER', true),
('X-Salada', 'Hambúrguer com queijo, alface, tomate, cebola e maionese especial', 27.90, 'HAMBURGUER', true),
('Coca-Cola', 'Refrigerante Coca-Cola 350ml', 6.00, 'REFRIGERANTE', true),
('Guaraná', 'Refrigerante Guaraná 350ml', 6.00, 'REFRIGERANTE', true),
('Pudim', 'Pudim de leite com calda de caramelo', 8.00, 'SOBREMESA', true),
('Sorvete', 'Sorvete de creme com calda de chocolate', 10.00, 'SOBREMESA', true); 