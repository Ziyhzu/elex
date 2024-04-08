SET sql_mode = 'STRICT_ALL_TABLES';

DROP DATABASE IF EXISTS elex;

CREATE DATABASE IF NOT EXISTS elex
CHARACTER SET utf8mb4
COLLATE utf8mb4_spanish_ci;

USE elex;

CREATE TABLE IF NOT EXISTS tipos_expediente
(
	id INT NOT NULL AUTO_INCREMENT,
    tipo VARCHAR(50) NOT NULL,
    descripcion VARCHAR (255) NOT NULL,
    borrado BOOLEAN DEFAULT 0 NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS expedientes
(
	id INT NOT NULL AUTO_INCREMENT,
    tipo_expediente_FK INT NOT NULL,
    codigo VARCHAR(50) NOT NULL,
    responsable VARCHAR(50) NOT NULL,
    estado VARCHAR(11) DEFAULT "activo" NOT NULL,
    opciones VARCHAR(70) DEFAULT "",
    borrado BOOLEAN DEFAULT 0 NOT NULL,
    FOREIGN KEY (tipo_expediente_FK) REFERENCES tipos_expediente (id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS actuaciones 
(
    id INT NOT NULL AUTO_INCREMENT,
    expediente_FK INT NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    responsable VARCHAR(50) NOT NULL,
    tasa DECIMAL(6,2) NOT NULL,
    fecha DATE NOT NULL,
    estado VARCHAR(11) DEFAULT "activo" NOT NULL,
    borrado BOOLEAN DEFAULT 0 NOT NULL,
    FOREIGN KEY (expediente_FK) REFERENCES expedientes (id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS documentos
(
    id INT NOT NULL AUTO_INCREMENT,
    actuacion_FK INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    ruta VARCHAR(255) NOT NULL,
    borrado BOOLEAN DEFAULT 0 NOT NULL,
    FOREIGN KEY (actuacion_FK) REFERENCES actuaciones (id),
    PRIMARY KEY (id)
);

INSERT INTO tipos_expediente (tipo, descripcion)
VALUES 
    ('Judicial', 'Expediente de naturaleza judicial'),
    ('Asistencia', 'Expediente de asistencia legal'),
    ('Informe', 'Expediente de informe legal'),
    ('Moción', 'Expediente de moción legal');

INSERT INTO expedientes (tipo_expediente_FK, codigo, responsable, estado, opciones)
VALUES 
    (1, '20241105', 'Juan Pérez', 'activo', 'en seguimiento, urgente'),
    (1, '20241106', 'María Gómez', 'activo', 'en seguimiento, confidencial'),
    (1, '20241107', 'Luis Martínez', 'archivado', 'confidencial'),
    (2, '20241108', 'Ana Rodríguez', 'activo', 'en seguimiento, urgente'),
    (2, '20241109', 'Carlos López', 'activo', 'en seguimiento, confidencial'),
    (2, '20241110', 'Elena Sánchez', 'archivado', 'urgente'),
    (3, '20241111', 'David Fernández', 'activo', 'en seguimiento, urgente'),
    (3, '20241112', 'Sara Pérez', 'activo', 'en seguimiento, confidencial'),
    (3, '20241113', 'Laura Gutiérrez', 'archivado', 'urgente'),
    (4, '20241114', 'José González', 'activo', 'en seguimiento, urgente'),
    (4, '20241115', 'Carmen Ruiz', 'activo', 'en seguimiento, confidencial'),
    (4, '20241116', 'Pedro García', 'archivado', 'confidencial');

INSERT INTO actuaciones (expediente_FK, descripcion, responsable, tasa, fecha, estado)
VALUES
    (1, 'Apertura de expediente', 'Juan Pérez', 50.00, '2024-03-20', 'archivado'),
    (1, 'Revisión de documentos', 'María Gómez', 60.00, '2024-03-21', 'archivado'),
    (1, 'Entrevista con cliente', 'Luis Martínez', 70.00, '2024-03-22', 'activo'),
    (2, 'Apertura de expediente', 'María Gómez', 55.00, '2024-03-20', 'archivado'),
    (2, 'Recopilación de información', 'Carlos López', 65.00, '2024-03-21', 'archivado'),
    (2, 'Elaboración de informe legal', 'Elena Sánchez', 75.00, '2024-03-22', 'activo'),
    (3, 'Apertura de expediente', 'Luis Martínez', 55.00, '2024-03-20', 'archivado'),
    (3, 'Análisis de jurisprudencia', 'Sara Pérez', 65.00, '2024-03-21', 'activo'),
    (4, 'Apertura de expediente', 'Ana Rodríguez', 60.00, '2024-03-20', 'archivado'),
    (4, 'Preparación de argumentos', 'Carmen Ruiz', 70.00, '2024-03-21', 'archivado'),
    (4, 'Presentación ante el tribunal', 'Pedro García', 80.00, '2024-03-22', 'activo'),
    (5, 'Apertura de expediente', 'Carlos López', 70.00, '2024-03-20', 'archivado'),
    (5, 'Revisión de documentos', 'María Rodríguez', 80.00, '2024-03-21', 'activo'),
    (6, 'Apertura de expediente', 'Elena Sánchez', 75.00, '2024-03-20', 'archivado'),
    (6, 'Entrevista con cliente', 'Marta González', 85.00, '2024-03-21', 'activo'),
    (7, 'Apertura de expediente', 'David Fernández', 80.00, '2024-03-20', 'archivado'),
    (7, 'Preparación de informe', 'Cristina Pérez', 90.00, '2024-03-21', 'archivado'),
    (7, 'Reunión con abogados', 'Laura Gómez', 100.00, '2024-03-22', 'activo'),
    (8, 'Apertura de expediente', 'Sara Pérez', 90.00, '2024-03-20', 'archivado'),
    (8, 'Revisión de documentos', 'Carlos Martínez', 110.00, '2024-03-21', 'activo'),
    (9, 'Apertura de expediente', 'Laura Gutiérrez', 100.00, '2024-03-20', 'archivado'),
    (9, 'Entrevista con cliente', 'Diego Rodríguez', 120.00, '2024-03-21', 'archivado'),
    (9, 'Elaboración de informe', 'Ana Martín', 140.00, '2024-03-22', 'activo'),
    (10, 'Apertura de expediente', 'José González', 110.00, '2024-03-20', 'archivado'),
    (10, 'Preparación de documentos legales', 'Elena López', 130.00, '2024-03-21', 'activo'),
    (11, 'Apertura de expediente', 'Carmen Ruiz', 120.00, '2024-03-20', 'archivado'),
    (11, 'Revisión de antecedentes', 'Mario Rodríguez', 140.00, '2024-03-21', 'activo'),
    (12, 'Apertura de expediente', 'Pedro García', 130.00, '2024-03-20', 'archivado'),
    (12, 'Consulta con experto legal', 'Andrés López', 150.00, '2024-03-21', 'archivado'),
    (12, 'Preparación para audiencia', 'Natalia Sánchez', 160.00, '2024-03-22', 'activo');

INSERT INTO documentos (actuacion_FK, nombre, ruta)
VALUES
    (1, 'Documento de apertura', 'static/pdfs/documento_apertura_1.pdf'),
    (2, 'Documento de revisión', 'static/pdfs/documento_revision_1.pdf'),
    (3, 'Documento de entrevista', 'static/pdfs/documento_entrevista_1.pdf'),
    (4, 'Documento de apertura', 'static/pdfs/documento_apertura_2.pdf'),
    (5, 'Documento de recopilación', 'static/pdfs/documento_recopilacion_1.pdf'),
    (6, 'Documento de informe', 'static/pdfs/documento_informe_1.pdf'),
    (7, 'Documento de apertura', 'static/pdfs/documento_apertura_3.pdf'),
    (8, 'Documento de preparación', 'static/pdfs/documento_preparacion_1.pdf'),
    (9, 'Documento de análisis', 'static/pdfs/documento_analisis_1.pdf'),
    (10, 'Documento de apertura', 'static/pdfs/documento_apertura_4.pdf'),
    (11, 'Documento de revisión', 'static/pdfs/documento_revision_2.pdf'),
    (12, 'Documento de apertura', 'static/pdfs/documento_apertura_5.pdf'),
    (13, 'Documento de entrevista', 'static/pdfs/documento_entrevista_2.pdf'),
    (14, 'Documento de apertura', 'static/pdfs/documento_apertura_6.pdf'),
    (15, 'Documento de preparación', 'static/pdfs/documento_preparacion_2.pdf'),
    (16, 'Documento de análisis', 'static/pdfs/documento_analisis_2.pdf'),
    (17, 'Documento de apertura', 'static/pdfs/documento_apertura_7.pdf'),
    (18, 'Documento de revisión', 'static/pdfs/documento_revision_3.pdf'),
    (19, 'Documento de preparación', 'static/pdfs/documento_preparacion_3.pdf'),
    (20, 'Documento de apertura', 'static/pdfs/documento_apertura_8.pdf'),
    (21, 'Documento de entrevista', 'static/pdfs/documento_entrevista_3.pdf'),
    (22, 'Documento de apertura', 'static/pdfs/documento_apertura_9.pdf'),
    (23, 'Documento de revisión', 'static/pdfs/documento_revision_4.pdf'),
    (24, 'Documento de preparación', 'static/pdfs/documento_preparacion_4.pdf'),
    (25, 'Documento de análisis', 'static/pdfs/documento_analisis_3.pdf'),
    (26, 'Documento de apertura', 'static/pdfs/documento_apertura_10.pdf'),
    (27, 'Documento de entrevista', 'static/pdfs/documento_entrevista_4.pdf'),
    (28, 'Documento de revisión', 'static/pdfs/documento_revision_5.pdf'),
    (29, 'Documento de preparación', 'static/pdfs/documento_preparacion_5.pdf'),
    (30, 'Documento de análisis', 'static/pdfs/documento_analisis_4.pdf');

SELECT * FROM tipos_expediente;
SELECT * FROM expedientes;
SELECT * FROM actuaciones;
SELECT * FROM documentos;