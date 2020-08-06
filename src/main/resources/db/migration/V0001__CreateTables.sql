CREATE SEQUENCE categorias_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE categorias (
    idCategoria integer NOT NULL,
    categoria character varying(100),
    CONSTRAINT categoria_pkey PRIMARY KEY (idCategoria)
);

CREATE SEQUENCE cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE clientes (
    idCliente integer NOT NULL,
    nome character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    senha character varying(300) NOT NULL,
    rua character varying(100) NOT NULL,
    cidade character varying(60) NOT NULL,
    cep character varying(8) NOT NULL,
    bairro character varying(60) NOT NULL,
    estado character varying(2) NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (idCliente)
);

CREATE SEQUENCE produtos_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE produtos (
    idProduto integer NOT NULL,
    idCategoria integer NOT NULL,
    produto character varying(120) NOT NULL,
    preco float8 NOT NULL,
    quantidade integer NOT NULL,
    descricao character varying(160) NOT NULL,
    foto character varying(160),
    CONSTRAINT produto_pkey PRIMARY KEY (idProduto),
    CONSTRAINT idCategoria_fkey FOREIGN KEY (idCategoria) REFERENCES categorias(idCategoria)
);

CREATE SEQUENCE pedido_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE pedidos (
    idPedido integer NOT NULL,
    idCliente integer NOT NULL,
    sessao character varying(100) NOT NULL,
    data timestamp without time zone NOT NULL,
    status character varying(20) NOT NULL,
    CONSTRAINT pedido_pkey PRIMARY KEY (idPedido),
    CONSTRAINT idCliente_fkey FOREIGN KEY (idCliente) REFERENCES clientes(idCliente)
);

CREATE SEQUENCE pedidoItens_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE pedidoItens (
    idItem integer NOT NULL,
    idPedido integer NOT NULL,
    idProduto integer NOT NULL,
    produto character varying(120) NOT NULL,
    valor float8 NOT NULL,
    subtotal float8 NOT NULL,
    quantidade integer NOT NULL,
    CONSTRAINT item_pkey PRIMARY KEY (idItem),
    CONSTRAINT idProd_fkey FOREIGN KEY (idPedido) REFERENCES pedidos(idPedido),
    CONSTRAINT idPed_fkey FOREIGN KEY (idProduto) REFERENCES produtos(idProduto)
);