-- View: public.migr_user_full_view

-- DROP VIEW IF EXISTS public.migr_user_full_view;

CREATE OR REPLACE VIEW public.migr_user_full_view
AS
SELECT m.id,
       m.name,
       m.login,
       m.start_date,
       m.end_date,
       m.role_id,
       r.name as role_name
FROM migr_user m
         LEFT JOIN role r ON r.id = m.role_id
;
