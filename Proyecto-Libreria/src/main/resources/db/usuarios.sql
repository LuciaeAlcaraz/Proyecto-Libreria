CREATE SEQUENCE public.usuarios_id_usuario_seq
    INCREMENT 1
    START 3
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.usuarios_id_usuario_seq
    OWNER TO postgres;
    
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

ALTER TABLE public.usuarios
    OWNER to postgres;
    
INSERT INTO usuarios( nombre, contrasenia) VALUES ('Maria', '123');
INSERT INTO usuarios( nombre, contrasenia) VALUES ('Ana', 'hjaogb323');
INSERT INTO usuarios( nombre, contrasenia) VALUES ('Fabian', 'hola456');