--/////////////////////////////////USUARIO/////////////////////////////////

-- USER SQL
CREATE USER "ALURA" IDENTIFIED BY "alura"  ;

-- QUOTAS

-- ROLES
GRANT "CONNECT" TO "ALURA" ;
GRANT "RESOURCE" TO "ALURA" ;

-- SYSTEM PRIVILEGES
GRANT CREATE TRIGGER TO "ALURA" ;
GRANT CREATE VIEW TO "ALURA" ;
GRANT CREATE TABLE TO "ALURA" ;
GRANT CREATE SEQUENCE TO "ALURA" ;
GRANT CREATE PROCEDURE TO "ALURA" ;

--////////////////////////////////////////////////////////////////////////

--/////////////////////////////////TABLAS/////////////////////////////////

CREATE TABLE RESERVAS(
id NUMBER(19, 0),
fech_entrada DATE,
fech_salida DATE,
valor VARCHAR2(50),
formaPago VARCHAR2(50)
);

alter table RESERVAS add constraint PK_id primary key (id);

CREATE TABLE HUESPEDES(
id NUMBER(19, 0),
nombre VARCHAR2(100),
apellido VARCHAR2(100),
fech_nacimiento DATE,
nacionalidad VARCHAR2(50),
telefono NUMBER(12, 0),
idReserva NUMBER(19, 0),
primary key (id)
);

alter table HUESPEDES add constraint FK_id_reserva foreign key (idReserva) references RESERVAS (id) on delete cascade;

--////////////////////////////////////////////////////////////////////////

--/////////////////////////////////SECUENCIAS/////////////////////////////////

CREATE SEQUENCE SQ_RESERVAS START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE SQ_HUESPEDES START WITH 1 INCREMENT BY 1 NOCACHE;

--////////////////////////////////////////////////////////////////////////////

--/////////////////////////////////TRIGGERS/////////////////////////////////

CREATE OR REPLACE TRIGGER TR_RESERVAS
BEFORE INSERT ON RESERVAS
FOR EACH ROW 
BEGIN
SELECT SQ_RESERVAS.NEXTVAL INTO :NEW.id FROM DUAL;
END;

CREATE OR REPLACE TRIGGER TR_HUESPEDES
BEFORE INSERT ON HUESPEDES
FOR EACH ROW 
BEGIN
SELECT SQ_HUESPEDES.NEXTVAL INTO :NEW.id FROM DUAL;
END;

--//////////////////////////////////////////////////////////////////////////

--/////////////////////////////////INSERCIONES/////////////////////////////

INSERT INTO RESERVAS (fech_entrada, fech_salida, valor, formaPago)
VALUES ('27/12/2022','29/12/2022','1234','tarjeta de credito');

SELECT * FROM RESERVAS;

INSERT INTO HUESPEDES (nombre, apellido, fech_nacimiento, nacionalidad, telefono, idReserva)
VALUES ('Jorge', 'Contreras', '01/01/1998', 'peruano', 1234567, 1);

SELECT * FROM HUESPEDES;

--SELECT SQ_RESERVAS.CURRVAL FROM DUAL;

--select * from user_sequences where sequence_name = 'SQ_RESERVAS';

--UPDATE RESERVAS SET FECH_ENTRADA='',FECH_SALIDA= TO_DATE('1989-12-09','YYYY-MM-DD'), VALOR='',FORMAPAGO='' WHERE ID = '1';

--UPDATE HUESPEDES SET NOMBRE = '', APELLIDO = '', FECH_NACIMIENTO = TO_DATE('1948-12-09','YYYY-MM-DD') , NACIONALIDAD = '', TELEFONO = '' WHERE ID='';

--SELECT * FROM RESERVAS WHERE (FECH_ENTRADA) LIKE ('%27%') OR (FECH_SALIDA) LIKE ('%27%') OR (VALOR) LIKE ('%27%') OR UPPER(FORMAPAGO) LIKE UPPER ('%27%');

--SELECT * FROM HUESPEDES WHERE UPPER(NOMBRE) LIKE UPPER('%Es%') OR UPPER(APELLIDO) LIKE UPPER('%Es%') OR (FECH_NACIMIENTO) LIKE ('%Es%') OR UPPER(NACIONALIDAD) LIKE UPPER('%Es%') OR (TELEFONO) LIKE ('%Es%') OR (IDRESERVA) LIKE ('%Es%');

COMMIT;
--/////////////////////////////////////////////////////////////////////////