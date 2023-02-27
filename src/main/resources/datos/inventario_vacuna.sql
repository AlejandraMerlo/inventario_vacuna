
CREATE DATABASE inventario_vacuna WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';

ALTER DATABASE inventario_vacuna OWNER TO postgres;

/*==============================================================*/
/* Table: PERSONA                                               */
/*==============================================================*/
create table PERSONA (
   CODIGO_PERSONA       INT4                 not null,
   CEDULA               VARCHAR(10)          null,
   NOMBRES              VARCHAR(50)          null,
   APELLIDOS            VARCHAR(50)          null,
   CORREO               VARCHAR(30)          null,
   DIRECCION            VARCHAR(100)         null,
   TELEFONO             VARCHAR(11)          null,
   VACUNADO             BOOL                 null,
   FECHA_REGISTRO       TIMESTAMP WITH TIME ZONE null,
   FECHA_NACIMIENTO     DATE                 null,
   constraint PK_PERSONA primary key (CODIGO_PERSONA)
);

comment on table PERSONA is
'Tabla para el manejo de información de personas.';

/*==============================================================*/
/* Table: EMPLEADO                                              */
/*==============================================================*/
create table EMPLEADO (
   CODIGO_EMPLEADO      INT4                 not null,
   CODIGO_PERSONA       INT4                 null,
   ESTADO               BOOL                 null,
   FECHA_REGISTRO       TIMESTAMP WITH TIME ZONE null,
   constraint PK_EMPLEADO primary key (CODIGO_EMPLEADO)
);

/*==============================================================*/
/* Table: ROL                                                   */
/*==============================================================*/
create table ROL (
   CODIGO_ROL           INT4                 not null,
   NOMBRE_ROL           VARCHAR(20)          null,
   FECHA_REGISTRO       TIMESTAMP WITH TIME ZONE null,
   ESTADO               BOOL                 null,
   constraint PK_ROL primary key (CODIGO_ROL)
);

comment on table ROL is
'Tabla para el manejo de Roles.';

/*==============================================================*/
/* Table: TIPO_VACUNA                                           */
/*==============================================================*/
create table TIPO_VACUNA (
   CODIGO_TIPO          INT4                 not null,
   FECHA_REGISTRO       TIMESTAMP WITH TIME ZONE null,
   NOMBRE_VACUNA        VARCHAR(30)          null,
   ESTADO               BOOL                 null,
   constraint PK_TIPO_VACUNA primary key (CODIGO_TIPO)
);

comment on table TIPO_VACUNA is
'Tabla para el tipo de vacunas.';

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   CODIGO_USUARIO       INT4                 not null,
   CODIGO_EMPLEADO      INT4                 null,
   USUARIO              VARCHAR(20)          null,
   PASSWORD             VARCHAR(50)          null,
   FECHA_REGISTRO       TIMESTAMP WITH TIME ZONE null,
   ESTADO               BOOL                 null,
   constraint PK_USUARIO primary key (CODIGO_USUARIO)
);

comment on table USUARIO is
'Tabla para el manejo de usuarios.';

/*==============================================================*/
/* Table: USUARIO_ROL                                           */
/*==============================================================*/
create table USUARIO_ROL (
   USUARIO_ROL          INT4                 not null,
   CODIGO_USUARIO       INT4                 null,
   CODIGO_ROL           INT4                 null,
   constraint PK_USUARIO_ROL primary key (USUARIO_ROL)
);

comment on table USUARIO_ROL is
'Tabla para el manejo de los usuarios por rol.';

/*==============================================================*/
/* Table: VACUNA_PERSONA                                        */
/*==============================================================*/
create table VACUNA_PERSONA (
   VACUNA_PERSONA       INT4                 not null,
   CODIGO_PERSONA       INT4                 null,
   CODIGO_TIPO          INT4                 null,
   FECHA_VACUNACION     TIMESTAMP            null,
   DOSIS                INT4                 null,
   FECHA_REGISTRO       TIMESTAMP WITH TIME ZONE null,
   constraint PK_VACUNA_PERSONA primary key (VACUNA_PERSONA)
);

comment on table VACUNA_PERSONA is
'Tabla para el maneji de las vacunas aplicadas por persona.';

alter table EMPLEADO
   add constraint FK_EMPLEADO_REFERENCE_PERSONA foreign key (CODIGO_PERSONA)
      references PERSONA (CODIGO_PERSONA)
      on delete restrict on update restrict;

alter table USUARIO
   add constraint FK_USUARIO_REFERENCE_EMPLEADO foreign key (CODIGO_EMPLEADO)
      references EMPLEADO (CODIGO_EMPLEADO)
      on delete restrict on update restrict;

alter table USUARIO_ROL
   add constraint FK_USUARIO__REFERENCE_USUARIO foreign key (CODIGO_USUARIO)
      references USUARIO (CODIGO_USUARIO)
      on delete restrict on update restrict;

alter table USUARIO_ROL
   add constraint FK_USUARIO__REFERENCE_ROL foreign key (CODIGO_ROL)
      references ROL (CODIGO_ROL)
      on delete restrict on update restrict;

alter table VACUNA_PERSONA
   add constraint FK_VACUNA_P_REFERENCE_PERSONA foreign key (CODIGO_PERSONA)
      references PERSONA (CODIGO_PERSONA)
      on delete restrict on update restrict;

alter table VACUNA_PERSONA
   add constraint FK_VACUNA_P_REFERENCE_TIPO_VAC foreign key (CODIGO_TIPO)
      references TIPO_VACUNA (CODIGO_TIPO)
      on delete restrict on update restrict;

/*
   Insert
*/

INSERT INTO public.rol (codigo_rol, nombre_rol, fecha_registro, estado) VALUES (1, 'Administrador', '2023-02-25 09:49:34.586449-05', true);
INSERT INTO public.rol (codigo_rol, nombre_rol, fecha_registro, estado) VALUES (2, 'Empleado', '2023-02-25 09:50:01.702891-05', true);

INSERT INTO public.tipo_vacuna (codigo_tipo, fecha_registro, nombre_vacuna, estado) VALUES (2, '2023-02-25 21:01:27.769266-05', ' AstraZeneca', true);
INSERT INTO public.tipo_vacuna (codigo_tipo, fecha_registro, nombre_vacuna, estado) VALUES (3, '2023-02-25 21:01:27.769266-05', 'Pfizer', true);
INSERT INTO public.tipo_vacuna (codigo_tipo, fecha_registro, nombre_vacuna, estado) VALUES (4, '2023-02-25 21:01:27.769266-05', 'Jhonson&Jhonson', true);
INSERT INTO public.tipo_vacuna (codigo_tipo, fecha_registro, nombre_vacuna, estado) VALUES (1, '2023-02-26 21:08:34.492337-05', 'Sputnik', true);

INSERT INTO public.persona (codigo_persona, cedula, nombres, apellidos, correo, direccion, telefono, vacunado, fecha_registro, fecha_nacimiento) VALUES (1, '1002345871', 'Nombre', 'Apellido', 'persona@mail.com', 'Quito', '59394325318', false, '2023-02-26 19:06:32.658-05', '2000-02-02 19:06:32.658583');

INSERT INTO public.empleado (codigo_empleado, codigo_persona, estado, fecha_registro) VALUES (1, 1, true, '2023-02-26 19:09:16.910207-05');

INSERT INTO public.usuario (codigo_usuario, codigo_empleado, usuario, password, fecha_registro, estado) VALUES (1, 1, 'usuario1', 'admin', '2023-02-25 21:15:10.707511-05', true);

INSERT INTO public.usuario_rol(usuario_rol, codigo_usuario, codigo_rol)	VALUES (1, 1, 1);