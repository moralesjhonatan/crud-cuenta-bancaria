CREATE DATABASE IF NOT EXISTS alineacion;
USE alineacion;
CREATE TABLE IF NOT EXISTS persona (
  identificacion float(20) NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  genero VARCHAR(25) NOT NULL,
  edad VARCHAR(3) NOT NULL,
  direccion VARCHAR(100) NOT NULL, 
  telefono VARCHAR(20) NOT NULL,
  primary key (identificacion)
);

CREATE TABLE IF NOT EXISTS cliente (
  identificacion float(20) NOT NULL,
  contrasena VARCHAR(100) NOT NULL,
  estado VARCHAR(25) NOT NULL,  
  foreign key (identificacion) references persona(identificacion) on delete cascade on update cascade  
);

ALTER TABLE cliente ADD PRIMARY KEY (identificacion);

CREATE TABLE IF NOT EXISTS cuenta (
  numero_cuenta float(20) NOT NULL,
  tipo_cuenta VARCHAR(100) NOT NULL,
  saldo_inicial decimal(20,2) NOT NULL,
  estado VARCHAR(20) NOT NULL,
  identificacion float(20) NOT NULL,  
  foreign key (identificacion) references cliente(identificacion) on delete cascade on update cascade, 
  primary key (numero_cuenta)
);

CREATE TABLE IF NOT EXISTS movimientos (
  id INTEGER NOT NULL AUTO_INCREMENT,
  fecha DATETIME NOT NULL,
  tipo_movimiento VARCHAR(25) NOT NULL,
  valor decimal(20,2) NOT NULL,
  saldo decimal(20,2) NOT NULL,  
  numero_cuenta float(20) NOT NULL,
  foreign key (numero_cuenta) references cuenta(numero_cuenta) on delete cascade on update cascade, 
  primary key (id)
);