CREATE VIEW scenes_View AS
SELECT s.id AS scene_id, s.description AS scene_description, s.minutes, s.location, s.date_shot, f.title AS film_title
FROM scene s
         JOIN film f ON s.film_id = f.id;


