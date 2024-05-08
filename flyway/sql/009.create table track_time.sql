CREATE TABLE IF NOT EXISTS public.track_time_store
(
    id bigint NOT NULL,
    method_name character varying(1000) COLLATE pg_catalog."default",
    execution_time bigint,
    CONSTRAINT track_time_store_pkey PRIMARY KEY (id)
)
    TABLESPACE pg_default;

