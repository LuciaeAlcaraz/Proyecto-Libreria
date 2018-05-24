CREATE SEQUENCE public.libros_id_libros_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.libros_id_libros_seq
    OWNER TO postgres;
    
-- Table: public.libros

-- DROP TABLE public.libros;

CREATE TABLE public.libros
(
    id_libros integer NOT NULL DEFAULT nextval('libros_id_libros_seq'::regclass),
    titulo text COLLATE pg_catalog."default" NOT NULL,
    autor_a text COLLATE pg_catalog."default" NOT NULL,
    editorial text COLLATE pg_catalog."default",
    anio numeric,
    sinopsis text COLLATE pg_catalog."default",
    url_fotolibro text COLLATE pg_catalog."default",
    CONSTRAINT libros_pkey PRIMARY KEY (id_libros)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.libros
    OWNER to postgres;
    
    INSERT INTO libros(titulo, autor_a, editorial, anio, sinopsis, url_fotolibro) VALUES("Percy Jackson y los dioses del Olimpo I: El ladrón del rayo", "Rick Riordan", "Salamandra", "2009", "¿Qué pasaría si un día descubrieras que, en realidad, eres hijo de un dios griego que debe cumplir una misión secreta? Pues eso es lo que le pasa a Percy Jackson.", "https://cdn.filestackcontent.com/ej9fmcU3TTO11RsffDeR");
    INSERT INTO libros(titulo, autor_a, editorial, anio, sinopsis, url_fotolibro) VALUES(?, ?, ?, ?, ?, ?);
    INSERT INTO libros(titulo, autor_a, editorial, anio, sinopsis, url_fotolibro) VALUES(?, ?, ?, ?, ?, ?);