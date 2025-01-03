UPDATE usuario SET activo = 1 WHERE activo IS NULL;

ALTER TABLE respuesta ADD activo tinyint;
UPDATE respuesta SET activo = 1;