CREATE VIEW characters_with_scene AS
SELECT c.id AS character_id, c.description AS character_description, c.cost, c.actor, c.stock, c.name, s.description AS scene_description
FROM character c
         JOIN scene s ON c.scene_id=s.id;