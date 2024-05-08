INSERT INTO public.action(id, name) 	VALUES (1, 'Чтение');
INSERT INTO public.action(id, name) 	VALUES (2, 'Полный');

INSERT INTO public.resource(id, name) 	VALUES (1, 'Plant');
INSERT INTO public.resource(id, name) 	VALUES (2, 'PlantType');

INSERT INTO public.access(id, role_id, resource_id, action_id) VALUES (1, 0, 1, 2);
INSERT INTO public.access(id, role_id, resource_id, action_id) VALUES (2, 1, 1, 1);
