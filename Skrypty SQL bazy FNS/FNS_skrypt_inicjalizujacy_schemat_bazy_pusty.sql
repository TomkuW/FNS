DROP DATABASE IF EXISTS fns_db;
CREATE DATABASE fns_db;
USE fns_db;

CREATE TABLE klienci (
  `klient_id` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(45) NOT NULL,
  `nazwisko` varchar(95) NOT NULL,
  `PESEL` varchar(11) NOT NULL,
  `miejscowosc` varchar(45) NOT NULL,
  `ulica` varchar(45) NOT NULL,
  `nr_dom` varchar(10) NOT NULL,
  `nr_telefon` varchar(15) NOT NULL,
  PRIMARY KEY (`klient_id`)
  )
  ENGINE = InnoDB;
  COMMIT;
   
  CREATE TABLE pakiety (
  `pakiet_id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(80) NOT NULL,
  `technologia` varchar(10) NOT NULL,
  `predkosc` varchar(45) NOT NULL,
  `cena` float(8) NOT NULL,
  `okres` int(6) NOT NULL,
  PRIMARY KEY (`pakiet_id`)
  )
  ENGINE = InnoDB;
COMMIT;

CREATE TABLE pracownicy (
  `pracownik_id` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(45),
  `nazwisko` varchar(100) NOT NULL,
  `zawod` varchar(45) NOT NULL,
  `PESEL` varchar(11),
  `ulica` varchar(45),
  `nr_domu` varchar(20),
  `miejscowosc` varchar(65),
  `email` varchar(45),
  `nr_telefon` varchar(15),
  `wynagrodzenie` float(20),
  `login` varchar(45) NOT NULL,
  `data_zatrudnienia` date,
  `haslo` varchar(120) DEFAULT NULL,
  `typ_pracownika` varchar(120) NOT NULL,
  PRIMARY KEY (`pracownik_id`),
  UNIQUE KEY `pracownik_id_UNIQUE` (`pracownik_id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
  )
  ENGINE = InnoDB ;
  COMMIT;
CREATE TABLE zamowienia (
  `zamowienia_id` int(11) NOT NULL AUTO_INCREMENT,
  `klient_id` int(11) NOT NULL,
  `pakiet_id` int(11) NOT NULL,
  `pracownik_id` int(11) NOT NULL,
  `umowa_od` date NOT NULL,
  `umowa_do` date NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`zamowienia_id`),
  KEY `fk_zamowienia_klienci1_idx` (`klient_id`),
  KEY `fk_zamowienia_pakiety1_idx` (`pakiet_id`),
  KEY `fk_zamowienia_pracownicy1_idx` (`pracownik_id`),
  CONSTRAINT `fk_zamowienia_klienci1` FOREIGN KEY (`klient_id`) REFERENCES `klienci` (`klient_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_zamowienia_pakiety1` FOREIGN KEY (`pakiet_id`) REFERENCES `pakiety` (`pakiet_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_zamowienia_pracownicy1` FOREIGN KEY (`pracownik_id`) REFERENCES `pracownicy` (`pracownik_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
  )
  ENGINE = InnoDB ;
  COMMIT;
  
-- Konto administratora
INSERT INTO pracownicy (pracownik_id, imie, nazwisko, zawod, PESEL, ulica, nr_domu, miejscowosc, email, nr_telefon, wynagrodzenie, login, data_zatrudnienia, haslo, typ_pracownika)
VALUES(null,'Andrzej','Kochanowski','Informatyk','90120365433','Alejkowa','15','Straszydle','andriupapaja@gmail.com','785226547','4099','admin','2017-01-04','admin','Administrator');




  
