CREATE DATABASE IF NOT EXISTS mantenimiento_vehicular;
USE mantenimiento_vehicular;

-- Tabla EMPRESA
CREATE TABLE EMPRESA (
    ID_Empresa INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100),
    NIT VARCHAR(50),
    Telefono_Contacto VARCHAR(15)
);

-- Tabla VEHICULO
CREATE TABLE VEHICULO (
    ID_Vehiculo INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Operador VARCHAR(100),
    Clase_de_Vehiculo VARCHAR(50),
    Placa VARCHAR(20),
    Marca VARCHAR(50),
    Kilometraje INT,
    ID_Empresa INT,
    FOREIGN KEY (ID_Empresa) REFERENCES EMPRESA(ID_Empresa)
);

-- Tabla TECNICO
CREATE TABLE TECNICO (
    ID_Tecnico INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100),
    Apellido VARCHAR(100),
    Telefono VARCHAR(15)
);

-- Tabla MANTENIMIENTO
CREATE TABLE MANTENIMIENTO (
    Orden_de_Trabajo INT AUTO_INCREMENT PRIMARY KEY,
    ID_Tecnico INT,
    ID_Vehiculo INT,
    Fecha DATE,
    Hora TIME,
    Mantenimiento TEXT,
    Repuesto TEXT,
    Foto BLOB,
    FOREIGN KEY (ID_Tecnico) REFERENCES TECNICO(ID_Tecnico),
    FOREIGN KEY (ID_Vehiculo) REFERENCES VEHICULO(ID_Vehiculo)
);
