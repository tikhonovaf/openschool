-- View: public.cluster_for_jenkins_view

DROP VIEW IF EXISTS public.animal_view;

CREATE OR REPLACE VIEW public.animal_view
AS
SELECT
    a.id,
    a.name,
    a.legs,
    a.animal_type_id,
    at.name as animal_type_name
FROM animal a
         JOIN animal_type at on at.id = a.animal_type_id
;
