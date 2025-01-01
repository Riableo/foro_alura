create table respuesta(
    id bigint not null auto_increment,
    mensaje varchar(500) not null,
    topico_id bigint not null,
    fecha_creacion datetime not null,
    usuario_id bigint not null,
    solucion boolean not null,

    primary key(id),
    constraint fk_respuesta_usuario_id foreign key(usuario_id) references usuario(id),
    constraint fk_respuesta_topico_id foreign key(topico_id) references topico(id)
);