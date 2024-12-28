create table perfil(
    id bigint not null auto_increment,
    nombre varchar(45) not null unique,

    primary key(id)
);