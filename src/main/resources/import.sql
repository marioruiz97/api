-- SCRIPT NECESARIO EN PRODUCCION, YA QUE NO HAY CONFIGURADOR DE ROLES EN LA APLICACION, SOLO 2 PREDEFINIDOS
INSERT INTO roles(nombre_rol) VALUES ('ROLE_USUARIO');
INSERT INTO roles(nombre_rol) VALUES ('ROLE_PASEADOR');


-- DATOS DE PRUEBA, ELIMINAR AL DESPLEGAR A PRODUCCION
INSERT INTO contacto_usuario (celular, telefono_fijo, direccion) VALUES ('3015465445', '6045878566', 'calle falsa 123 # 2');
INSERT INTO contacto_usuario (celular, telefono_fijo, direccion) VALUES ('3015465445', '6045878566', 'calle falsa 123 # 2');
INSERT INTO contacto_usuario (celular, telefono_fijo, direccion) VALUES ('3015463322', '6045833222', 'calle falsa 123 # 2');
INSERT INTO contacto_usuario (celular, telefono_fijo, direccion) VALUES ('3015467766', '6045866663', 'calle falsa 123 # 2');
INSERT INTO usuarios (identificacion, apellido, contrasena, correo, foto, habilitado, nombre, nombre_usuario, tipo_documento, id_contacto) VALUES ('1017251545', 'ruiz', '$2a$10$lpPEyxyXCVKzMYdwXxoic.BZzCHIPihZQAZUNNIKzR1L2.VBPqPxe', 'mario@hotmail.com', NULL, true, 'mario', 'maruiz97',1,1);
INSERT INTO usuarios (identificacion, apellido, contrasena, correo, foto, habilitado, nombre, nombre_usuario, tipo_documento, id_contacto) VALUES ('1017214122', 'ruiz', '$2a$10$lpPEyxyXCVKzMYdwXxoic.BZzCHIPihZQAZUNNIKzR1L2.VBPqPxe', 'carolina@hotmail.com', NULL, true, 'caro', 'carito1',1,2);
INSERT INTO usuarios (identificacion, apellido, contrasena, correo, foto, habilitado, nombre, nombre_usuario, tipo_documento, id_contacto) VALUES ('1017938765', 'Ossa De Ruiz', '$2a$10$lpPEyxyXCVKzMYdwXxoic.BZzCHIPihZQAZUNNIKzR1L2.VBPqPxe', 'deisyossa@gmail.com', NULL, true, 'Deisy Carolina', 'deisy1',2,3);
INSERT INTO usuarios (identificacion, apellido, contrasena, correo, foto, habilitado, nombre, nombre_usuario, tipo_documento, id_contacto) VALUES ('1017214123', 'Correa', '$2a$10$lpPEyxyXCVKzMYdwXxoic.BZzCHIPihZQAZUNNIKzR1L2.VBPqPxe', 'manuel@hotmail.com', NULL, true, 'Manuel Alejandro', 'manuel1',3,4);

INSERT INTO usuarios_roles (id_usuario, rol_id) VALUES ('1017251545', 2);
INSERT INTO usuarios_roles (id_usuario, rol_id) VALUES ('1017214122', 1);
INSERT INTO usuarios_roles (id_usuario, rol_id) VALUES ('1017938765', 2);
INSERT INTO usuarios_roles (id_usuario, rol_id) VALUES ('1017214123', 2);

INSERT INTO paseadores (id_paseador, calificacion, estado, perfil_experiencia, tiempo_experiencia, identificacion) VALUES ('1017251545', NULL, true, 'perfil de prueba Mario', '1 año y 6 meses', '1017251545');
INSERT INTO paseadores (id_paseador, calificacion, estado, perfil_experiencia, tiempo_experiencia, identificacion) VALUES ('1017938765', NULL, true, 'perfil de prueba Deisy', '2 años', '1017938765');
INSERT INTO paseadores (id_paseador, calificacion, estado, perfil_experiencia, tiempo_experiencia, identificacion) VALUES ('1017214123', NULL, true, 'perfil de prueba Manuel', '3 año y 8 meses', '1017214123');

-- create table contactos_emergencia (celular_emergencia varchar(10) not null, nombre_contacto varchar(50) not null, telefono_fijo_emergencia varchar(10) not null, identificacion_usuario varchar(15) not null, primary key (identificacion_usuario))
