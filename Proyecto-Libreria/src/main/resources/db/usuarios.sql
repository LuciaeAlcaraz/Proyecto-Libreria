CREATE SEQUENCE public.usuarios_id_usuario_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.usuarios_id_usuario_seq;
    
-- Table: public.usuarios

-- DROP TABLE public.usuarios;

CREATE TABLE public.usuarios
(
    id_usuario integer NOT NULL DEFAULT nextval('usuarios_id_usuario_seq'::regclass),
    nombre text COLLATE pg_catalog."default" NOT NULL,
    contrasenia text COLLATE pg_catalog."default" NOT NULL,
    url_foto text COLLATE pg_catalog."default",
    codigo text COLLATE pg_catalog."default",
    CONSTRAINT usuarios_pkey PRIMARY KEY (id_usuario)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.usuarios;
    
INSERT INTO usuarios( nombre, contrasenia, url_foto) VALUES ('Maria', '12345', 'https://cdn.filestackcontent.com/bU7TFjI8QP2B1j7jSf8g');
INSERT INTO usuarios( nombre, contrasenia, url_foto) VALUES ('Valen', 'contrasenia123', 'https://cdn.filestackcontent.com/SlKbdPDISUqRPo0yyl55');
INSERT INTO usuarios( nombre, contrasenia, url_foto) VALUES ('Fabian', 'hola', 'https://cdn.filestackcontent.com/XFuZu1cNRlmQNOBxE61e');