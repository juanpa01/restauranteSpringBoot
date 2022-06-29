create table producto (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 valor DECIMAL(10,2) not null,
 primary key (id)
);

create table factura (
 id int(11) not null auto_increment,
 valor_imp_iva DECIMAL(10,2) not null,
 valor_imp_consumo   DECIMAL(10,2) not null,
 valor_propina DECIMAL(10,2) not null,
 valor_total DECIMAL(10,2) not null,
 fecha_creacion TIMESTAMP not null,
 fecha_actualizacion TIMESTAMP, 
 aplica_propina BOOLEAN not null,
 primary key (id)
);

create table producto_facturar (
 id int(11) not null auto_increment,
 id_factura int(11) not null,
 id_producto int(11) not null,
 cantidad int(11) not null,
 primary key (id)
);

ALTER TABLE producto_facturar
ADD CONSTRAINT producto_fk
  FOREIGN KEY (id_producto)
  REFERENCES producto (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE producto_facturar
ADD CONSTRAINT factura_fk
  FOREIGN KEY (id_factura)
  REFERENCES factura (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;