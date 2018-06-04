CREATE SEQUENCE public.libros_id_libros_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
    
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
    genero text COLLATE pg_catalog."default",
    CONSTRAINT libros_pkey PRIMARY KEY (id_libros)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
    
    INSERT INTO libros(titulo, autor_a, editorial, anio, sinopsis, url_fotolibro, genero) VALUES('Percy Jackson y los dioses del Olimpo I: El ladrón del rayo', 'Rick Riordan', 'Salamandra', '2009', '¿Qué pasaría si un día descubrieras que, en realidad, eres hijo de un dios griego que debe cumplir una misión secreta? Pues eso es lo que le pasa a Percy Jackson.', 'https://cdn.filestackcontent.com/ej9fmcU3TTO11RsffDeR', 'Literatura fantástica');
    INSERT INTO libros(titulo, autor_a, editorial, anio, sinopsis, url_fotolibro, genero) VALUES('Cronicas Lunares I: Cinder', 'Marissa Meyer', 'V&R', '2015', 'Érase una vez, en el futuro... El destino de la Tierra depende de una sola chica.Pero nadie, ni siquiera ella, lo sabe.', 'https://cdn.filestackcontent.com/A9mb2urZS8KXBiW1C3ZG', 'Ficción utópica y distópica');
    INSERT INTO libros(titulo, autor_a, editorial, anio, sinopsis, url_fotolibro, genero) VALUES('Asylum', 'Madeleine', 'V&R', '2016', 'Para Dan Crawford, el programa de verano para alumnos sobresalientes es una oportunidad única, antes de asistir a la Universidad', 'https://cdn.filestackcontent.com/KVAJBOoqR9OaMSNrw46v',  'Ficción paranormal');