-- Table: public.animal_type

-- DROP TABLE IF EXISTS public.animal_type;

CREATE TABLE IF NOT EXISTS public.animal_type
(
    id bigint NOT NULL,
    name character varying(1000) COLLATE pg_catalog."default",
    CONSTRAINT animal_type_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS public.animal
(
    id bigint NOT NULL,
    name character varying(1000) COLLATE pg_catalog."default",
    legs bigint,
    animal_type_id bigint NOT NULL,
    CONSTRAINT animal_pkey PRIMARY KEY (id),
    CONSTRAINT fk_animal_animal_type FOREIGN KEY (animal_type_id)
        REFERENCES public.animal_type (id) MATCH SIMPLE
        ON UPDATE SET NULL
        ON DELETE SET NULL
)

    TABLESPACE pg_default;
