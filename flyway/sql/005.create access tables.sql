-- Table: public.action

-- DROP TABLE IF EXISTS public.action;

CREATE TABLE IF NOT EXISTS public.action
(
    id bigint NOT NULL,
    name character varying(1000) COLLATE pg_catalog."default",
    CONSTRAINT action_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

-- Table: public.resource

-- DROP TABLE IF EXISTS public.resource;

CREATE TABLE IF NOT EXISTS public.resource
(
    id bigint NOT NULL,
    name character varying(1000) COLLATE pg_catalog."default",
    CONSTRAINT resource_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;


-- Table: public.access

-- DROP TABLE IF EXISTS public.access;

CREATE TABLE IF NOT EXISTS public.access
(
    id bigint NOT NULL,
    role_id bigint,
    resource_id bigint,
    action_id bigint,
    CONSTRAINT access_pkey PRIMARY KEY (id),
    CONSTRAINT fk_access_action FOREIGN KEY (action_id)
        REFERENCES public.action (id) MATCH SIMPLE
        ON UPDATE SET NULL
        ON DELETE SET NULL,
    CONSTRAINT fk_access_resource FOREIGN KEY (resource_id)
        REFERENCES public.resource (id) MATCH SIMPLE
        ON UPDATE SET NULL
        ON DELETE SET NULL,
    CONSTRAINT fk_access_role FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE SET NULL
        ON DELETE SET NULL
)

    TABLESPACE pg_default;
