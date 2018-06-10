-- Table: public.favoritos

-- DROP TABLE public.favoritos;

CREATE TABLE public.favoritos
(
    id_libro integer NOT NULL,
    id_usuario integer NOT NULL
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;