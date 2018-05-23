
/**
 * Author:  Giancarlo
 */

connect system/manager as sysdba;
 
-- ----------------- USUARIO ----------------- 
create user servidor identified by servidor;

grant all privileges to servidor identified by servidor;

connect servidor/servidor;

-- ----------------- CREACION DE UN CURSOR -----------------
CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/

-- ################################### USUARIOS ###################################
-- ----------------- TABLA DE USUARIO -----------------
CREATE TABLE Usuario(
    username VARCHAR(100),
    nombre VARCHAR(100),
    apellidos VARCHAR(100),
    email VARCHAR(100),
    clave VARCHAR(100),
    fechaNac VARCHAR(100),
    direccion VARCHAR(100),
    telefono VARCHAR(100),    
    rol int,
    CONSTRAINTS pkUsuario PRIMARY KEY (username)
);

-- ----------------- INSERTAR USUARIO ----------------- 
CREATE OR REPLACE PROCEDURE insertarUsuario(username IN Usuario.username%TYPE, nombre IN Usuario.nombre%TYPE, apellidos IN Usuario.apellidos%TYPE, email IN Usuario.email%TYPE, clave IN Usuario.clave%TYPE, fechaNac IN Usuario.fechaNac%TYPE, direccion IN Usuario.direccion%TYPE, telefono IN Usuario.telefono%TYPE, rol IN Usuario.rol%TYPE)
AS
BEGIN
	INSERT INTO Usuario VALUES(username,nombre,apellidos,email,clave,fechaNac,direccion,telefono,rol);
END;
/

-- ----------------- ACTUALIZAR USUARIO ----------------- 
CREATE OR REPLACE PROCEDURE actualizarUsuario(usernamein IN Usuario.username%TYPE, nombrein IN Usuario.nombre%TYPE, apellidosin IN Usuario.apellidos%TYPE, emailin IN Usuario.email%TYPE, clavein IN Usuario.clave%TYPE, fechaNacin IN Usuario.fechaNac%TYPE, direccionin IN Usuario.direccion%TYPE, telefonoin IN Usuario.telefono%TYPE)
AS
BEGIN
	UPDATE Usuario SET nombre=nombrein,apellidos=apellidosin,email=emailin,clave=clavein,fechaNac=fechaNacin,direccion=direccionin,telefono=telefonoin WHERE username=usernamein;
END;
/

-- ----------------- BUSCAR USUARIO ----------------- 
CREATE OR REPLACE FUNCTION buscarUsuario(usernamein IN Usuario.username%TYPE)
RETURN Types.ref_cursor 
AS 
        USUARIO_cursor types.ref_cursor; 
BEGIN 
  OPEN USUARIO_cursor FOR 
       SELECT * FROM Usuario WHERE username=usernamein; 
RETURN USUARIO_cursor; 
END;
/

-- ----------------- LISTAR USUARIO ----------------- 
CREATE OR REPLACE FUNCTION listarUsuario
RETURN Types.ref_cursor 
AS 
        USUARIO_cursor types.ref_cursor; 
BEGIN 
  OPEN USUARIO_cursor FOR 
       SELECT * FROM Usuario ; 
RETURN USUARIO_cursor; 
END;
/

-- ----------------- ELIMINAR USUARIO ----------------- 
create or replace procedure eliminarUsuario(usernamein IN Usuario.username%TYPE)
as
begin
    delete from Usuario where username=usernamein;
end;
/

-- ################################### TIPOAVIONES ###################################
-- ----------------- TABLA DE TIPOAVIONES -----------------
CREATE TABLE TipoAvion(
    id int,
    annio VARCHAR(15),
    modelo VARCHAR(15),
    marca VARCHAR(15),
    capacidad int,
    filas int,
    asientosXFila int,
    CONSTRAINTS pkTipoAvion PRIMARY KEY (id)
);

-- ----------------- INSERTAR TIPOAVION ----------------- 
CREATE OR REPLACE PROCEDURE insertarTipoAvion(id IN TipoAvion.id%TYPE,annio IN TipoAvion.annio%TYPE,modelo IN TipoAvion.modelo%TYPE,marca IN TipoAvion.marca%TYPE,capacidad IN TipoAvion.capacidad%TYPE,filas IN TipoAvion.filas%TYPE,asientosXFila IN TipoAvion.asientosXFila%TYPE)
AS
BEGIN
	INSERT INTO TipoAvion VALUES(id,annio,modelo,marca,capacidad,filas,asientosXFila);
END;
/

-- ----------------- ACTUALIZAR TIPOAVION ----------------- 
CREATE OR REPLACE PROCEDURE actualizarTipoAvion(idin IN TipoAvion.id%TYPE,annioin IN TipoAvion.annio%TYPE,modeloin IN TipoAvion.modelo%TYPE,marcain IN TipoAvion.marca%TYPE,capacidadin IN TipoAvion.capacidad%TYPE,filasin IN TipoAvion.filas%TYPE,asientosXFilain IN TipoAvion.asientosXFila%TYPE)
AS
BEGIN
	UPDATE TipoAvion SET annio=annioin,modelo=modeloin,marca=marcain,capacidad=capacidadin,filas=filasin,asientosXFila=asientosXFilain WHERE id=idin;
END;
/

-- ----------------- BUSCAR TIPOAVION ----------------- 
CREATE OR REPLACE FUNCTION buscarTipoAvion(idin IN TipoAvion.id%TYPE)
RETURN Types.ref_cursor 
AS 
        tipoavion_cursor types.ref_cursor; 
BEGIN 
  OPEN tipoavion_cursor FOR 
       SELECT * FROM TipoAvion WHERE id=idin; 
RETURN tipoavion_cursor; 
END;
/

-- ----------------- LISTAR TIPOAVION ----------------- 
CREATE OR REPLACE FUNCTION listarTipoAvion
RETURN Types.ref_cursor 
AS 
        tipoavion_cursor types.ref_cursor; 
BEGIN 
  OPEN tipoavion_cursor FOR 
       SELECT * FROM TipoAvion; 
RETURN tipoavion_cursor; 
END;
/

-- ----------------- ELIMINAR TIPOAVION ----------------- 
create or replace procedure eliminarTipoAvion(idin IN TipoAvion.id%TYPE)
as
begin
    delete from TipoAvion where id=idin;
end;
/

-- ################################### RUTAS ###################################
-- ----------------- TABLA DE RUTAS -----------------
CREATE TABLE Rutas(
    id int,
    ruta VARCHAR(100),
    duracion VARCHAR(30),
    CONSTRAINTS pkRutas PRIMARY KEY (id)
);

-- ----------------- INSERTAR RUTA ----------------- 
CREATE OR REPLACE PROCEDURE insertarRuta(id IN Rutas.id%TYPE,ruta IN Rutas.ruta%TYPE,duracion IN Rutas.duracion%TYPE)
AS
BEGIN
	INSERT INTO Rutas VALUES(id,ruta,duracion);
END;
/

-- ----------------- ACTUALIZAR RUTA ----------------- 
CREATE OR REPLACE PROCEDURE actualizarRuta(idin IN Rutas.id%TYPE,rutain IN Rutas.ruta%TYPE,duracionin IN Rutas.duracion%TYPE)
AS
BEGIN
	UPDATE Rutas SET ruta=rutain, duracion=duracionin WHERE id=idin;
END;
/

-- ----------------- BUSCAR RUTA ----------------- 
CREATE OR REPLACE FUNCTION buscarRuta(idin IN Rutas.id%TYPE)
RETURN Types.ref_cursor 
AS 
        rutas_cursor types.ref_cursor; 
BEGIN 
  OPEN rutas_cursor FOR 
       SELECT * FROM Rutas WHERE id=idin; 
RETURN rutas_cursor; 
END;
/

-- ----------------- LISTAR RUTAS ----------------- 
CREATE OR REPLACE FUNCTION listarRutas
RETURN Types.ref_cursor 
AS 
        rutas_cursor types.ref_cursor; 
BEGIN 
  OPEN rutas_cursor FOR 
       SELECT * FROM Rutas; 
RETURN rutas_cursor; 
END;
/

-- ----------------- ELIMINAR RUTA ----------------- 
create or replace procedure eliminarRuta(idin IN Rutas.id%TYPE)
as
begin
    delete from Rutas where id=idin;
end;
/

-- ################################### HORARIOS ###################################
-- ----------------- TABLA DE HORARIOS -----------------
CREATE TABLE Horarios(
    id int,
    diaSalida VARCHAR(15),
    diaLlegada VARCHAR(15),
    horaSalida VARCHAR(15),
    horaLlegada VARCHAR(15),
    precio int,
    CONSTRAINTS pkHorarios PRIMARY KEY (id)
);

-- ----------------- INSERTAR HORARIO ----------------- 
CREATE OR REPLACE PROCEDURE insertarHorario(id IN Horarios.id%TYPE,diaSalida IN Horarios.diaSalida%TYPE,diaLlegada IN Horarios.diaLlegada%TYPE,horaSalida IN Horarios.horaSalida%TYPE,horaLlegada IN Horarios.horaLlegada%TYPE,precio IN Horarios.precio%TYPE)
AS
BEGIN
	INSERT INTO Horarios VALUES(id,diaSalida,diaLlegada,horaSalida,horaLlegada,precio);
END;
/

-- ----------------- ACTUALIZAR HORARIO ----------------- 
CREATE OR REPLACE PROCEDURE actualizarHorario(idin IN Horarios.id%TYPE,diaSalidain IN Horarios.diaSalida%TYPE,diaLlegadain IN Horarios.diaLlegada%TYPE,horaSalidain IN Horarios.horaSalida%TYPE,horaLlegadain IN Horarios.horaLlegada%TYPE,precioin IN Horarios.precio%TYPE)
AS
BEGIN
	UPDATE Horarios SET diaSalida=diaSalidain, diaLlegada=diaLlegadain, horaSalida=horaSalidain, horaLlegada=horaLlegadain, precio=precioin WHERE id=idin;
END;
/

-- ----------------- BUSCAR HORARIO ----------------- 
CREATE OR REPLACE FUNCTION buscarHorario(idin IN Horarios.id%TYPE)
RETURN Types.ref_cursor 
AS 
        horarios_cursor types.ref_cursor; 
BEGIN 
  OPEN horarios_cursor FOR 
       SELECT * FROM Horarios WHERE id=idin; 
RETURN horarios_cursor; 
END;
/

-- ----------------- LISTAR HORARIOS ----------------- 
CREATE OR REPLACE FUNCTION listarHorarios
RETURN Types.ref_cursor 
AS 
        horarios_cursor types.ref_cursor; 
BEGIN 
  OPEN horarios_cursor FOR 
       SELECT * FROM Horarios; 
RETURN horarios_cursor; 
END;
/

-- ----------------- ELIMINAR HORARIO ----------------- 
create or replace procedure eliminarHorario(idin IN Horarios.id%TYPE)
as
begin
    delete from Horarios where id=idin;
end;
/

-- ################################### AVIONES ###################################
-- ----------------- TABLA DE AVIONES -----------------
CREATE TABLE Aviones(
    id int,
    ruta int,
    horario int,
    tipoAvion int,
    CONSTRAINTS pkAviones PRIMARY KEY (id),
    CONSTRAINTS fkRuta FOREIGN KEY (ruta) REFERENCES Rutas (id),
    CONSTRAINTS fkHorario FOREIGN KEY (horario) REFERENCES Horarios (id),
    CONSTRAINTS fkTipoAvion FOREIGN KEY (tipoAvion) REFERENCES TipoAvion (id)
);

-- ----------------- INSERTAR AVION ----------------- 
CREATE OR REPLACE PROCEDURE insertarAvion(id IN Aviones.id%TYPE,ruta IN Aviones.ruta%TYPE,horario IN Aviones.horario%TYPE,tipoAvion IN Aviones.tipoAvion%TYPE)
AS
BEGIN
	INSERT INTO Aviones VALUES(id,ruta,horario,tipoAvion);
END;
/

-- ----------------- ACTUALIZAR AVION ----------------- 
CREATE OR REPLACE PROCEDURE actualizarAvion(idin IN Aviones.id%TYPE,rutain IN Aviones.ruta%TYPE,horarioin IN Aviones.horario%TYPE,tipoAvionin IN Aviones.tipoAvion%TYPE)
AS
BEGIN
	UPDATE Aviones SET ruta=rutain, horario=horarioin, tipoAvion=tipoAvionin WHERE id=idin;
END;
/

-- ----------------- BUSCAR AVION ----------------- 
CREATE OR REPLACE FUNCTION buscarAvion(idin IN Aviones.id%TYPE)
RETURN Types.ref_cursor 
AS 
        aviones_cursor types.ref_cursor; 
BEGIN 
  OPEN aviones_cursor FOR 
       SELECT * FROM Aviones WHERE id=idin; 
RETURN aviones_cursor; 
END;
/

-- ----------------- LISTAR AVION ----------------- 
CREATE OR REPLACE FUNCTION listarAviones
RETURN Types.ref_cursor 
AS 
        aviones_cursor types.ref_cursor; 
BEGIN 
  OPEN aviones_cursor FOR 
       SELECT * FROM Aviones; 
RETURN aviones_cursor; 
END;
/

-- ----------------- ELIMINAR AVION ----------------- 
create or replace procedure eliminarAvion(idin IN Aviones.id%TYPE)
as
begin
    delete from Aviones where id=idin;
end;
/

-- #############################################################################

-- PRUEBAS NO EJECUTAR

-- -----------------  DROPS -----------------
DROP PROCEDURE insertarUsuario;
DROP PROCEDURE actualizarUsuario;
DROP FUNCTION buscarUsuario;
DROP FUNCTION listarUsuario;
DROP PROCEDURE eliminarUsuario;
DROP TABLE Usuario;

DROP PROCEDURE insertarProduct;
DROP PROCEDURE actualizarProduct;
DROP FUNCTION buscarProduct;
DROP FUNCTION listarProduct;
DROP PROCEDURE eliminarProduct;
DROP TABLE Product;

DROP PACKAGE types;
DROP USER servidor CASCADE; 

DECLARE
BEGIN
    insertarProduct();
END;
/

-- #############################################################################
