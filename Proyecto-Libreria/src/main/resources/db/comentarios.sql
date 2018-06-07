CREATE SEQUENCE public.comentarios_id_comentario_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
    
-- Table: public.comentarios

-- DROP TABLE public.comentarios;

-- Table: public.comentarios

-- DROP TABLE public.comentarios;

CREATE TABLE public.comentarios
(
    id_comentarios integer NOT NULL DEFAULT nextval('comentarios_id_comentarios_seq'::regclass),
    id_libro integer NOT NULL,
    id_usuario integer NOT NULL,
    comentario text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT comentarios_pkey PRIMARY KEY (id_comentarios)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

INSERT INTO comentarios(id_libros, id_usuario, comentario) VALUES ( '1', '2', 'Este libro me gusta');
INSERT INTO comentarios(id_libros, id_usuario, comentario) VALUES ( '1', '3', 'Este libro me gusta mucho');
INSERT INTO comentarios(id_libros, id_usuario, comentario) VALUES ( '3', '1', 'Voy a comprarlo');