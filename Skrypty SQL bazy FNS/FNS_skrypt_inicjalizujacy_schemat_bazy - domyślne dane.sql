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
  `cena` varchar(8) NOT NULL,
  `okres` varchar(6) NOT NULL,
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
  `wynagrodzenie` varchar(45),
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

-- Tworzenie klientów

INSERT INTO klienci (klient_id, imie, nazwisko, PESEL , miejscowosc, ulica, nr_dom, nr_telefon) 
VALUES(null,'Jan','Koterski','91091691559','Rzeszow','Pigonia','11','677222566'); 

INSERT INTO klienci (klient_id, imie, nazwisko, PESEL , miejscowosc, ulica, nr_dom, nr_telefon) 
VALUES(null,'Jan','Kochanowski','90031691519','Krosno','Legionow','223/4','617222466'); 

INSERT INTO klienci (klient_id, imie, nazwisko, PESEL , miejscowosc, ulica, nr_dom,  nr_telefon) 
VALUES(null,'Kamil','Lewandowski','77391692559','Babica','null','141','645232566'); 

INSERT INTO klienci (klient_id, imie, nazwisko, PESEL , miejscowosc, ulica, nr_dom,  nr_telefon) 
VALUES(null,'Wojciech','Boruc','91451641559','Sanok','Deszczowa','143/6','677226466'); 

INSERT INTO klienci (klient_id, imie, nazwisko, PESEL , miejscowosc, ulica, nr_dom, nr_telefon) 
VALUES(null,'Marek','Grosicki','91391637551','Domaradz','null','345','871222566'); 

INSERT INTO klienci (klient_id, imie, nazwisko, PESEL , miejscowosc, ulica, nr_dom,  nr_telefon) 
VALUES(null,'Zygmunt','Wolski','68191691559','Rzeszow','Lwowska','142/7','627219966'); 

INSERT INTO klienci (klient_id, imie, nazwisko, PESEL , miejscowosc, ulica, nr_dom, nr_telefon) 
VALUES(null,'Renata','Kowalska','88341691559','Trzebownisko','null','414','644522566'); 

INSERT INTO klienci (klient_id, imie, nazwisko, PESEL , miejscowosc, ulica, nr_dom,  nr_telefon) 
VALUES(null,'Kamila','Dec','93739161559','Krosno','null','67C','566222566'); 

INSERT INTO klienci (klient_id, imie, nazwisko, PESEL , miejscowosc, ulica, nr_dom, nr_telefon) 
VALUES(null,'Mateusz','Piszczek','78192691559','Rzeszow','Lwowska','19/7','577122566'); 

INSERT INTO klienci (klient_id, imie, nazwisko, PESEL , miejscowosc, ulica, nr_dom, nr_telefon) 
VALUES(null,'Marta','Rybus','94391645551','Babica','null','121A','671777566'); 


-- Tworzenie pracowników
INSERT INTO pracownicy (pracownik_id, imie, nazwisko, zawod, PESEL, ulica, nr_domu, miejscowosc, email, nr_telefon, wynagrodzenie, login, data_zatrudnienia, haslo, typ_pracownika)
VALUES(null,'Andrzej','Kochanowski','Informatyk','90120365433','Alejkowa','15','Straszydle','andriupapaja@gmail.com','785226547','4099','admin123','2017-01-04','admin321','Administrator');
INSERT INTO pracownicy (pracownik_id, imie, nazwisko, zawod, PESEL, ulica, nr_domu, miejscowosc, email, nr_telefon, wynagrodzenie, login, data_zatrudnienia, haslo, typ_pracownika)
VALUES(null,'Filip','Pazyra','Kierownik','84112365844','Rejtana','16/12','Rzeszów','majonkamax@gmail.com','508456258','4499','kierownik123','2017-03-03','lenistwo321','Kierownik');
INSERT INTO pracownicy (pracownik_id, imie, nazwisko, zawod, PESEL, ulica, nr_domu, miejscowosc, email, nr_telefon, wynagrodzenie, login, data_zatrudnienia, haslo, typ_pracownika)
VALUES(null,'Tomasz','Karakan','Handlowiec','89031256999','Wierzbowa','22','Dynów','karakanypisowskie@gmail.com','625478569','3799','handel123','2017-04-11','hajsy321','Handlowiec');
INSERT INTO pracownicy (pracownik_id, imie, nazwisko, zawod, PESEL, ulica, nr_domu, miejscowosc, email, nr_telefon, wynagrodzenie, login, data_zatrudnienia, haslo, typ_pracownika)
VALUES(null,'Anna','Partacz','Ksiegowa','78032554776','Piecowa','55/1','Rzeszów','joannaloveme@gmail.com','506425777','3299','pieniadze123','2017-10-27','piec321','Ksiegowy');
INSERT INTO pracownicy (pracownik_id, imie, nazwisko, zawod, PESEL, ulica, nr_domu, miejscowosc, email, nr_telefon, wynagrodzenie, login, data_zatrudnienia, haslo, typ_pracownika)
VALUES(null,'Szymon','Baraniewicz','Pracownik','83101256987','Zaciszna','12/12','Kolbuszowa','polewajbosucho@gmail.com','808456632','2599','presja123','2017-10-10','zwolnionko321','Wykonawca');
INSERT INTO pracownicy (pracownik_id, imie, nazwisko, zawod, PESEL, ulica, nr_domu, miejscowosc, email, nr_telefon, wynagrodzenie, login, data_zatrudnienia, haslo, typ_pracownika)
VALUES(null,'Maciej','Nowak','Pracownik','80070765874','Podmostowa','666','Tarnobrzeg','nowakiburakia666@gmail.com','522458123','2599','dedline123','2017-10-26','pikus321','Wykonawca');
INSERT INTO pracownicy (pracownik_id, imie, nazwisko, zawod, PESEL, ulica, nr_domu, miejscowosc, email, nr_telefon, wynagrodzenie, login, data_zatrudnienia, haslo, typ_pracownika)
VALUES(null,'Jacek','Materialowy','Pracownik','75051365412','Makaronowa','45/12','Rzeszów','jacekwodka123@gmail.com','752654123','2599','maloczasu123','2017-10-23','wyplata321','Wykonawca');

-- Tworzenie usług
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'TANI NET','FTTH','20Mbit/s/2Mbit/s','40.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'Biznes NET','FTTH','60Mbit/s/2Mbit/s','80.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'SUPER TANI NET','ADSL2+','6Mbit/s/512Kbit/s','30.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'TANI NET','FTTH','20Mbit/s/2Mbit/s','40.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'SUPER TANI NET','ADSL2+','6Mbit/s/512Mbit/s','30.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'BIZNES NET','FTTH','60Mbit/s/2Mbit/s','80.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'BIZNES NET','ADSL2+','15Mbit/s/512Kbit/s','70.65','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'STANDARD NET','ADSL2+','10Mbit/s/512Kbit/s','45.45','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'SUPER TANI NET','ADSL2+','6Mbit/s/512Kbit/s','30.33','24');
INSERT INTO pakiety (pakiet_id,nazwa,technologia,predkosc,cena,okres)
VALUES ( null,'TANI NET','FTTH','20Mbit/s/2Mbit/s','40.33','24');


  
