-- SEQUENCE: public.hibernate_sequence

-- DROP SEQUENCE IF EXISTS public.hibernate_sequence;

CREATE SEQUENCE IF NOT EXISTS public.hibernate_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


-- Table: public.role

-- DROP TABLE IF EXISTS public.role;

CREATE TABLE IF NOT EXISTS public.role
(
    id bigint NOT NULL,
    name character varying(1000) COLLATE pg_catalog."default",
    CONSTRAINT role_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS public.migr_user
(
    id bigint NOT NULL,
    name character varying(1000) COLLATE pg_catalog."default",
    login character varying(100) COLLATE pg_catalog."default",
    password character varying(4000) COLLATE pg_catalog."default",
    start_date date,
    end_date date,
    role_id bigint NOT NULL,
    email character varying(100) COLLATE pg_catalog."default",
    phone character varying(100) COLLATE pg_catalog."default",
    telegram character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user_role FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE SET NULL
        ON DELETE SET NULL
)

    TABLESPACE pg_default;
