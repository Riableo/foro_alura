create table topico(
    id bigint not null auto_increment,
    titulo varchar(45) not null,
    mensaje varchar(500) not null,
    fecha_creacion datetime not null,
    status varchar(20) not null,

    usuario_id bigint not null,

    primary key(id),
    constraint fk_topico_usuario_id foreign key(usuario_id) references usuario(id)
);