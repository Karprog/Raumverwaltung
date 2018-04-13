CREATE DATABASE IF NOT EXISTS reparatur;

USE reparatur;

CREATE TABLE IF NOT EXISTS raum (
    raumid INT PRIMARY KEY AUTO_INCREMENT,
    bezeichnung VARCHAR(200),
    typ VARCHAR(200),
    anzahl_arbeitsplaetze INT
);

CREATE TABLE IF NOT EXISTS hardware (
    id INT PRIMARY KEY AUTO_INCREMENT,
    typ VARCHAR(200),
    seriennummer VARCHAR(200),
    invantarnummer VARCHAR(200),
    hersteller VARCHAR(200),
    modell VARCHAR(200),
    status INT,
    raumid INT
);

CREATE TABLE IF NOT EXISTS rechner (
    id INT PRIMARY KEY AUTO_INCREMENT,
    imagepfad VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS drucker (
    id INT PRIMARY KEY AUTO_INCREMENT,
    betriebsmittel VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS hardware_drucker (
    id INT PRIMARY KEY AUTO_INCREMENT,
    hardware_id INT,
    drucker_id INT
);

CREATE TABLE IF NOT EXISTS hardware_rechner (
    id INT PRIMARY KEY AUTO_INCREMENT,
    hardware_id INT,
    rechner_id INT
);