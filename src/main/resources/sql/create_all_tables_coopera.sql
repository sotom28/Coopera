-- Script DDL Oracle para crear las tablas del sistema COOPERA
-- Generado a partir del DDL proporcionado por el usuario

CREATE TABLE PRODUCTO_INVERSION
(cod_prod_inv  NUMBER(3) NOT NULL,
 nombre_prod_inv VARCHAR2(50) NOT NULL,
 meses_minimo_ahorro NUMBER(2),
 porc_maximo_rescate NUMBER(3),
 CONSTRAINT PK_PRODUCTO_INVERSION PRIMARY KEY(cod_prod_inv));
 
CREATE TABLE CREDITO
(cod_credito  NUMBER(3) NOT NULL,
 nombre_credito VARCHAR2(50) NOT NULL,
 desc_credito VARCHAR2(400) NOT NULL,
 tasa_interes_anual NUMBER(4,2),
 nro_maximo_cuotas NUMBER(3),
 CONSTRAINT PK_CREDITO PRIMARY KEY(cod_credito)); 
 
 CREATE TABLE TIPO_SOCIO
(cod_tipo_socio  NUMBER(3) NOT NULL,
 nombre_tipo_socio VARCHAR2(30) NOT NULL,
 promedio_renta VARCHAR2(40) NOT NULL,
 CONSTRAINT PK_TIPO_SOCIO PRIMARY KEY(cod_tipo_socio));  

 CREATE TABLE REGION
(cod_region  NUMBER(3) NOT NULL,
 nombre_region VARCHAR2(50) NOT NULL,
 CONSTRAINT PK_REGION PRIMARY KEY(cod_region));  
 
 CREATE TABLE PROVINCIA
(cod_region  NUMBER(3) NOT NULL,
 cod_provincia NUMBER(3) NOT NULL,
 nombre_provincia VARCHAR2(50) NOT NULL,
 CONSTRAINT PK_PROVINCIA PRIMARY KEY(cod_region,cod_provincia),
 CONSTRAINT FK_PROVINCIA_REGION FOREIGN KEY(cod_region) REFERENCES REGION(cod_region)); 
 
 CREATE TABLE COMUNA
(cod_region  NUMBER(3) NOT NULL,
 cod_provincia NUMBER(3) NOT NULL,
 cod_comuna NUMBER(3) NOT NULL,
 nombre_comuna VARCHAR2(50) NOT NULL,
 CONSTRAINT PK_COMUNA PRIMARY KEY(cod_region,cod_provincia,cod_comuna),
 CONSTRAINT FK_COMUNA_PROVINCIA FOREIGN KEY(cod_region, cod_provincia) REFERENCES PROVINCIA(cod_region,cod_provincia)); 
 
 CREATE TABLE PROFESION_OFICIO
(cod_prof_ofic  NUMBER(3) NOT NULL,
 nombre_prof_ofic VARCHAR2(50) NOT NULL,
 CONSTRAINT PK_PROFESION_OFICIO PRIMARY KEY(cod_prof_ofic));  
 
CREATE TABLE TIPO_MOVIMIENTO
(cod_tipo_mov  NUMBER(3) NOT NULL,
 nombre_tipo_mov VARCHAR2(50) NOT NULL,
 CONSTRAINT PK_TIPO_MOVIMIENTO PRIMARY KEY(cod_tipo_mov));   
 
 CREATE TABLE FORMA_PAGO
(cod_forma_pago   NUMBER(2) NOT NULL ,
 nombre_forma_pago VARCHAR2 (50) NOT NULL) ;
ALTER TABLE FORMA_PAGO ADD CONSTRAINT PK_FORMA_PAGO PRIMARY KEY (cod_forma_pago);
  
CREATE TABLE SOCIO
(nro_socio NUMBER(5) NOT NULL,
 numrun NUMBER(10) NOT NULL,
 dvrun VARCHAR2(1) NOT NULL,
 pnombre VARCHAR2(50) NOT NULL, 
 snombre VARCHAR2(50), 
 appaterno VARCHAR2(50) NOT NULL, 
 apmaterno VARCHAR2(50),
 foto BLOB,
 fecha_nacimiento DATE NOT NULL,
 fecha_inscripcion DATE NOT NULL,
 correo VARCHAR2(20),
 fono_contacto NUMBER(10),
 direccion VARCHAR2(50) NOT NULL,
 cod_region  NUMBER(3) NOT NULL,
 cod_provincia NUMBER(3) NOT NULL,
 cod_comuna NUMBER(3) NOT NULL,
 cod_prof_ofic  NUMBER(3) NOT NULL,
 cod_tipo_socio  NUMBER(3) NOT NULL,
 CONSTRAINT PK_SOCIO PRIMARY KEY(nro_socio),
 CONSTRAINT FK_SOCIO_COMUNA FOREIGN KEY(cod_region, cod_provincia,cod_comuna) REFERENCES COMUNA(cod_region,cod_provincia,cod_comuna),
 CONSTRAINT FK_SOCIO_PROFESION_OFICIO FOREIGN KEY(cod_prof_ofic) REFERENCES PROFESION_OFICIO(cod_prof_ofic),
 CONSTRAINT FK_SOCIO_TIPO_SOCIO FOREIGN KEY(cod_tipo_socio) REFERENCES TIPO_SOCIO(cod_tipo_socio)); 


CREATE TABLE PRODUCTO_INVERSION_SOCIO
(nro_solic_prod NUMBER(10) NOT NULL,
 nro_socio NUMBER(5) NOT NULL,
 fecha_solic_prod DATE NOT NULL,
 ahorro_minimo_mensual NUMBER(10) NOT NULL,
 dia_pago_mensual NUMBER(2) NOT NULL,
 monto_total_ahorrado NUMBER(10) NOT NULL,
 cod_prod_inv NUMBER(3) NOT NULL,
CONSTRAINT PK_PRODUCTO_INVERSION_SOCIO PRIMARY KEY(nro_solic_prod),
CONSTRAINT FK_PRODINVSOC_SOCIO FOREIGN KEY(nro_socio) REFERENCES SOCIO(nro_socio),
CONSTRAINT FK_PRODINVSOC_PRODINV FOREIGN KEY(cod_prod_inv) REFERENCES PRODUCTO_INVERSION(cod_prod_inv)); 

CREATE TABLE MOVIMIENTO 
(nro_movimiento NUMBER(5) NOT NULL,
 nro_solic_prod NUMBER(10) NOT NULL,
 nro_socio NUMBER(5) NOT NULL,
 cod_prod_inv NUMBER(3) NOT NULL,
 fecha_movimiento DATE NOT NULL,
 monto_movimiento NUMBER(10) NOT NULL,
 cod_tipo_mov NUMBER(3) NOT NULL,
 CONSTRAINT PK_MOVIMIENTO PRIMARY KEY(nro_movimiento),
 CONSTRAINT FK_MOVIMIENTO_PROINVSOC FOREIGN KEY(nro_solic_prod) REFERENCES PRODUCTO_INVERSION_SOCIO(nro_solic_prod),
 CONSTRAINT FK_MOVIMIENTO_TIPOMOV FOREIGN KEY(cod_tipo_mov) REFERENCES TIPO_MOVIMIENTO(cod_tipo_mov)); 
 
 CREATE TABLE CREDITO_SOCIO
(nro_solic_credito NUMBER(10) NOT NULL,
 nro_socio NUMBER(5) NOT NULL,
 fecha_solic_cred DATE NOT NULL,
 fecha_otorga_cred DATE NOT NULL,
 monto_solicitado NUMBER(10) NOT NULL,
 monto_total_credito NUMBER(10) NOT NULL,
 total_cuotas_credito NUMBER(2) NOT NULL,
 cod_credito NUMBER(3) NOT NULL,
CONSTRAINT PK_CREDITO_SOCIO PRIMARY KEY(nro_solic_credito),
CONSTRAINT FK_CREDSOC_SOCIO FOREIGN KEY(nro_socio) REFERENCES SOCIO(nro_socio),
CONSTRAINT FK_CREDSOC_CREDITO FOREIGN KEY(cod_credito) REFERENCES CREDITO(cod_credito)); 

CREATE TABLE CUOTA_CREDITO_SOCIO
(nro_solic_credito NUMBER(10) NOT NULL,
 nro_cuota NUMBER(2) NOT NULL,
 fecha_venc_cuota DATE NOT NULL,
 valor_cuota NUMBER(10) NOT NULL,
 fecha_pago_cuota DATE,
 monto_pagado NUMBER(10),
 saldo_por_pagar NUMBER(10),
 cod_forma_pago NUMBER(2),
CONSTRAINT PK_CUOTA_CREDITO_SOCIO PRIMARY KEY(nro_solic_credito,nro_cuota),
CONSTRAINT FK_CUOCREDSOC_FORMA_PAGO FOREIGN KEY(cod_forma_pago) REFERENCES FORMA_PAGO(cod_forma_pago),
CONSTRAINT FK_CUOCREDSOC_CREDSOC FOREIGN KEY(nro_solic_credito) REFERENCES CREDITO_SOCIO(nro_solic_credito));

CREATE TABLE PAGO_MENSUAL_CREDITO
(fecha_proceso VARCHAR2(7) NOT NULL,
 nro_socio NUMBER(5) NOT NULL,
 run_socio VARCHAR2(12) NOT NULL,
 nro_solic_credito NUMBER(10) NOT NULL,
 tipo_credito VARCHAR2(40) NOT NULL,
 monto_total_credito NUMBER(15) NOT NULL,
 nro_total_cuotas NUMBER(2) NOT NULL,
 nro_cuota_mes NUMBER(2) NOT NULL,
 valor_cuota_mes NUMBER(15) NOT NULL,
 fecha_venc_cuota_mes DATE NOT NULL,
 saldo_pago_mes_ant NUMBER(15) NOT NULL,
 dias_atraso_pago_mes_ant NUMBER(3) NOT NULL,
 multa_atraso_pago_mes_ant NUMBER(15) NOT NULL,
 valor_rebajar_65_annos NUMBER(8) NOT NULL,
 valor_total_cuota_mes NUMBER(15) NOT NULL,
 CONSTRAINT PK_PAGOS_MENSUAL_CREDITOS PRIMARY KEY(nro_socio,nro_solic_credito),
 CONSTRAINT FK_PMCREDITOS_CREDITO FOREIGN KEY(nro_solic_credito) REFERENCES CREDITO_SOCIO(nro_solic_credito),
 CONSTRAINT FK_PMCREDITOS_SOCIO FOREIGN KEY(nro_socio) REFERENCES SOCIO(nro_socio));

CREATE TABLE ERROR_PROCESO
(correl_error NUMBER(5) NOT NULL,
 sentencia_error VARCHAR2(120) NOT NULL,
 descrip_error VARCHAR2(255) NOT NULL,
 CONSTRAINT PK_ERROR_PROCESO PRIMARY KEY(correl_error));

CREATE TABLE USUARIO_CLAVE
(nro_socio NUMBER(6) NOT NULL,
 numrun_socio VARCHAR2(15) NOT NULL,
 nombre_socio VARCHAR2(60) NOT NULL,
 nombre_usuario VARCHAR2(20) NOT NULL,
 clave_usuario VARCHAR2(20) NOT NULL,
 CONSTRAINT PK_USUARIO_CLAVE PRIMARY KEY(nro_socio));

CREATE TABLE TRAMO_3RA_EDAD
(rango_edad_min NUMBER(2) NOT NULL,
 rango_edad_max NUMBER(2) NOT NULL,
 factor NUMBER(2) NOT NULL,
 CONSTRAINT PK_TRAMO_3RA_EDAD PRIMARY KEY(rango_edad_min,rango_edad_max));
 
CREATE TABLE MULTA_MORA
(tramo_dia_min_atraso NUMBER(2) NOT NULL,
 tramo_dia_max_atraso NUMBER(3) NOT NULL,
 porc_multa NUMBER(2) NOT NULL,
 CONSTRAINT PK_MULTA_MORA PRIMARY KEY(tramo_dia_min_atraso,tramo_dia_max_atraso));
