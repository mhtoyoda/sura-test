insert into categorias(idCategoria, categoria) values(1, 'Celular');
insert into categorias(idCategoria, categoria) values(2, 'Tablet');
insert into categorias(idCategoria, categoria) values(3, 'Relógio');
insert into categorias(idCategoria, categoria) values(4, 'Computador');

insert into clientes(idCliente, nome, email, senha, rua, cidade, cep, bairro, estado) values(nextval('cliente_seq'),
'Marcelo', 'marcelo@test.com', '123456', 'Rua Lopes, 200', 'São Paulo', '08010900', 'Centro', 'SP');

insert into produtos(idProduto, idCategoria, produto, preco, quantidade, descricao) values(nextval('produtos_seq'), 1, 'Iphone 11', 5000, 5, 'Iphone 11 128GB');
insert into produtos(idProduto, idCategoria, produto, preco, quantidade, descricao) values(nextval('produtos_seq'), 1, 'Iphone X', 3000, 2, 'Iphone X 64GB');
insert into produtos(idProduto, idCategoria, produto, preco, quantidade, descricao) values(nextval('produtos_seq'), 1, 'Samsung Galaxy Pro', 2000, 4, 'Samsung Galaxy Pro');
insert into produtos(idProduto, idCategoria, produto, preco, quantidade, descricao) values(nextval('produtos_seq'), 1, 'Motorola G8 plus', 2400, 10, 'Motorola G8 plus 64GB');
insert into produtos(idProduto, idCategoria, produto, preco, quantidade, descricao) values(nextval('produtos_seq'), 2, 'Ipad pro 128GB', 3000, 7, 'Ipad pro 128GB');
insert into produtos(idProduto, idCategoria, produto, preco, quantidade, descricao) values(nextval('produtos_seq'), 2, 'Samsung Galaxy Note', 2000, 15, 'Samsung Galaxy Note');
insert into produtos(idProduto, idCategoria, produto, preco, quantidade, descricao) values(nextval('produtos_seq'), 3, 'Apple watch', 2000, 3, 'Apple watch');
insert into produtos(idProduto, idCategoria, produto, preco, quantidade, descricao) values(nextval('produtos_seq'), 4, 'Dell', 4200, 3, 'Dell I7');
insert into produtos(idProduto, idCategoria, produto, preco, quantidade, descricao) values(nextval('produtos_seq'), 4, 'Sony Vaio', 5000, 4, 'Sony Vaio I5');
insert into produtos(idProduto, idCategoria, produto, preco, quantidade, descricao) values(nextval('produtos_seq'), 4, 'Macbook pro', 100000, 2, 'MacBook Pro I5 Retina');



