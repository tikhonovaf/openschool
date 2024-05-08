-- View: public.cluster_for_jenkins_view

DROP VIEW IF EXISTS public.track_time_store_view;

CREATE OR REPLACE VIEW public.track_time_store_view
AS
SELECT
    method_name, count(*) as exec_count, sum(execution_time) as exec_sum,
    min(execution_time) as exec_min, max(execution_time) as exec_max, avg(execution_time) as exec_avg
FROM track_time_store
GROUP by method_name
order by max(execution_time) DESC
;

