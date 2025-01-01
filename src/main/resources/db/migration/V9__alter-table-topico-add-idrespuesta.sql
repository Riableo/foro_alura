ALTER TABLE Topico ADD COLUMN respuesta_id bigint;

ALTER TABLE Topico
ADD CONSTRAINT fk_topico_respuesta_id
FOREIGN KEY (respuesta_id)
REFERENCES respuesta(id);