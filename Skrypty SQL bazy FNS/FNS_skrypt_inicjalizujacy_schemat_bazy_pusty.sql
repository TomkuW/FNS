DROP DATABASE IF EXISTS fns_db;
CREATE DATABASE fns_db;
USE fns_db;

CREATE TABLE klienci (
  `klient_id` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(45) NOT NULL,
  `nazwisko` varchar(95) NOT NULL,
  `data_ur` date NOT NULL,
  `PESEL` varchar(11) NOT NULL,
  `miejscowosc` varchar(45),
  `ulica` varchar(45) NOT NULL,
  `nr_dom` varchar(10) NOT NULL,
  `kod_poczta` varchar(7) NOT NULL,
  `nr_telefon` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`klient_id`),
  UNIQUE KEY `PESEL_UNIQUE` (`PESEL`)
  )
  ENGINE = InnoDB;
  COMMIT;
   
  CREATE TABLE pakiety (
  `pakiet_id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(80) NOT NULL,
  `technologia` varchar(10) NOT NULL,
  `predkosc` varchar(45) NOT NULL,
  `cena` decimal(5,2) NOT NULL,
  `okres` int(2) NOT NULL,
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
  `wynagrodzenie` int(45),
  `login` varchar(45) NOT NULL,
  `data_zatrudnienia` date,
  `haslo` varchar(120) DEFAULT NULL,
  `typ_pracownika` tinyint(1) DEFAULT 0,
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
  `status` enum('oczekuje','w trakcie','wykonana') NOT NULL DEFAULT 'oczekuje',
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
  
-- Tworzenie klientów


-- Tworzenie pracowników
INSERT INTO pracownicy (pracownik_id, nazwisko, zawod, login, data_zatrudnienia, haslo)
VALUES(null,'Admin','Informatyk','admin123','2017-09-26','admin321');

-- Tworzenie usług
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'Swiatlowodowy:"TANI NET"','FTTH','20Mbit/s/2Mbit/s','40.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'Swiatlowodowy:"Biznes NET','FTTH','60Mbit/s/2Mbit/s','80.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'Standardowy:"SUPER TANI NET"','ADSL2+','6Mbit/s/512Kbit/s','30.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'Swiatlowodowy: "TANI NET"','FTTH','20Mbit/s/2Mbit/s','40.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'Standardowy:"SUPER TANI NET"','ADSL2+','6Mbit/s/512Mbit/s','30.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'Swiatlowodowy:"BIZNES NET"','FTTH','60Mbit/s/2Mbit/s','80.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'Standardowy:"BIZNES NET"','ADSL2+','15Mbit/s/512Kbit/s','70.65','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'Standardowy:"STANDARD NET"','ADSL2+','10Mbit/s/512Kbit/s','45.45','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'Standardowy:"SUPER TANI NET"','ADSL2+','6Mbit/s/512Kbit/s','30.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'Swiatlowodowy:"TANI NET"','FTTH','20Mbit/s/2Mbit/s','40.33','24');


  
