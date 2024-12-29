ALTER TABLE Topico
ADD COLUMN curso_id bigint not null;

ALTER TABLE Topico
ADD CONSTRAINT fk_topico_curso_id
FOREIGN KEY (curso_id) REFERENCES curso(id);