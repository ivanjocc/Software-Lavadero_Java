create database lavadero;

create table info(
	id int primary key auto_increment,
    placa varchar(50),
    propietario varchar(50),
    telefono int,
    fecha date,
    valor int,
    tamano varchar(50),
    tipo_lavado varchar(50),
    adicional varchar(50)
);