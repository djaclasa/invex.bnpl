

select * from cliente;
select * from linea_
select * from esquema_;
select * from compra_cliente;

--DROP TABLE compra_cliente;
--DROP TABLE esquema_;
--DROP TABLE linea_;
--DROP TABLE cliente;

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cliente](
	[id_cliente] [bigint] IDENTITY(1, 1),
	[nombre] [varchar](250) NOT NULL,
	Constraint PK_Cliente Primary key (id_cliente))
GO
CREATE TABLE [dbo].[linea_](
	[id_linea] [bigint] IDENTITY(1, 1),
	[id_cliente] [bigint] NOT NULL,
	[monto] [numeric](16,6) NOT NULL,
	FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
	Constraint PK_Linea Primary key (id_linea)
)
GO
CREATE TABLE [dbo].[esquema_](
	[id_esquema_] [bigint]  IDENTITY(1, 1),
	[pagos] [int] NOT NULL,
	[periodicidad] [varchar](10) NOT NULL,
	[tasa_Interes] [numeric](3) NOT NULL,	
	Constraint PK_Esquema Primary key (id_esquema_)
)
GO
CREATE TABLE [dbo].[compra_cliente](
	[id_compra_cliente] [bigint] IDENTITY(1, 1),
	[id_cliente] [bigint] NOT NULL,
	[fecha_compra_cliente] [dateTime] NOT NULL,
	[fecha_primer_pago] [dateTime] NOT NULL,
	[id_esquema_] [bigint] NOT NULL,
	[monto_total] [numeric](16,6) NOT NULL,
	[monto_comision_total] [numeric](16,6) NOT NULL,
	[monto_cuota] [numeric](16,6) NOT NULL,
	[monto_comision_cuota] [numeric](16,6) NOT NULL,
	[sig_periodo] [BIT] NOT NULL DEFAULT 0,
	Constraint PK_Compra Primary key (id_compra_cliente),
	FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
	FOREIGN KEY (id_esquema_) REFERENCES esquema_(id_esquema_)
)
GO

insert INTO cliente values ('abel contreras');
insert INTO cliente values ('juan alvarado');
insert INTO cliente values ('sandra ortiz');

insert INTO esquema_ values (5,'semanal',10);
insert INTO esquema_ values (5,'semanal',14);
insert INTO esquema_ values (9,'semanal',18);