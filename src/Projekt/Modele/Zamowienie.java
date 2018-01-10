package Projekt.Modele;

import javafx.beans.property.*;

import java.sql.Date;

/**
 * Deklaracja Tabeli zamóweinia i innych tabel opartych na tabeli zamówienia.
 *
 * Created by Tomek on 2017-12-13.
 */
public class Zamowienie {

    private IntegerProperty zamowienia_id;
    private IntegerProperty klient_id;
    private IntegerProperty pakiet_id;
    private IntegerProperty pracownik_id;
    private SimpleObjectProperty<Date> umowa_od;
    private SimpleObjectProperty<Date> umowa_do;
    private StringProperty status;
    private StringProperty imieKZ;
    private StringProperty nazwiskoKZ;
    private StringProperty miejscowoscKZ;
    private StringProperty ulicaKZ;
    private StringProperty nr_domKZ;
    private StringProperty nr_telefonKZ;
    private StringProperty technologiaPakietZ;
    private StringProperty predkoscPakietZ;
    private StringProperty nazwaPakietZ;
    private StringProperty pracownikImieZ;
    private StringProperty pracownikNazwiskoZ;
    private DoubleProperty cenaU;
    private IntegerProperty iloscU;
    private DoubleProperty cenaZbU;

    /**
     * Kostruktor
     */
    public Zamowienie(){
        this.zamowienia_id = new SimpleIntegerProperty();
        this.klient_id = new SimpleIntegerProperty();
        this.pakiet_id = new SimpleIntegerProperty();
        this.pracownik_id = new SimpleIntegerProperty();
        this.umowa_od = new SimpleObjectProperty<>();
        this.umowa_do =new SimpleObjectProperty<>();
        this.status =new SimpleStringProperty();
        this.imieKZ = new SimpleStringProperty();
        this.nazwiskoKZ = new SimpleStringProperty();
        this.miejscowoscKZ = new SimpleStringProperty();
        this.ulicaKZ = new SimpleStringProperty();
        this.nazwaPakietZ = new SimpleStringProperty();
        this.pracownikImieZ = new SimpleStringProperty();
        this.pracownikNazwiskoZ = new SimpleStringProperty();
        //Tabela ilości sprzedanych usług - zakł. Finanse
        this.cenaU = new SimpleDoubleProperty();
        this.cenaZbU = new SimpleDoubleProperty();
        this.iloscU = new SimpleIntegerProperty();
        this.predkoscPakietZ = new SimpleStringProperty();
        this.technologiaPakietZ = new SimpleStringProperty();
        this.nr_domKZ = new SimpleStringProperty();
        this.nr_telefonKZ = new SimpleStringProperty();
    }

    /**
     * Pobierz imie klienta zakupujacego usługę.
     * @return imieKZ
     */
    public String getImieKZ() {
        return imieKZ.get();
    }

    /**
     * Imie Klienta który zakupi pakiet w Tabeli KlientZamowienia
     * @return imieKZ
     */
    public StringProperty imieKZProperty() {
        return imieKZ;
    }

    /**
     * Ustaw Imie Klienta zakupujacego usluge
     * @param imieKZ
     */
    public void setImieKZ(String imieKZ) {
        this.imieKZ.set(imieKZ);
    }

    /**
     * Pobierz nazwisko klienta zakupujacego usługę.
     * @return nazwiskoKZ
     */
    public String getNazwiskoKZ() {
        return nazwiskoKZ.get();
    }

    /**
     * Nazwisko klienta który zakupił pakiet w Tabeli KlientZamowienia
     * @return nazwiskoKZ
     */
    public StringProperty nazwiskoKZProperty() {
        return nazwiskoKZ;
    }

    /**
     * Ustaw nazwisko klienta zakupującego usluge
     * @param nazwiskoKZ
     */
    public void setNazwiskoKZ(String nazwiskoKZ) {
        this.nazwiskoKZ.set(nazwiskoKZ);
    }

    /**
     * Pobierz miejscowosc zamawiającego usługę.
     * @return miejscowoscKZ
     */
    public String getMiejscowoscKZ() {
        return miejscowoscKZ.get();
    }

    /**
     * Miejscowosc klienta który zamowił(zakupił) pakiet w Tabeli KlientZamowienia
     * @return miejscowoscKZ
     */
    public StringProperty miejscowoscKZProperty() {
        return miejscowoscKZ;
    }

    /**
     * Ustaw miejscowosc klienta zakupującego usluge
     * @param miejscowoscKZ
     */
    public void setMiejscowoscKZ(String miejscowoscKZ) {
        this.miejscowoscKZ.set(miejscowoscKZ);
    }

    /**
     * Pobierz ulice klienta zamawiającego usługę.
     * @return ulicaKZ
     */
    public String getUlicaKZ() {
        return ulicaKZ.get();
    }

    /**
     * Ulica klienta ktoryz zakupił pakiet w Tabeli KlientZamowienia
     * @return ulicaKZ
     */
    public StringProperty ulicaKZProperty() {
        return ulicaKZ;
    }

    /**
     * Ustaw ulice klienta zakupującego usluge
     * @param ulicaKZ
     */
    public void setUlicaKZ(String ulicaKZ) {
        this.ulicaKZ.set(ulicaKZ);
    }

    /**
     * Pobierz nazwe uslugi, ktora jest zakupiona.
     * @return nazwaZ
     */
    public String getNazwaPakietZ() {
        return nazwaPakietZ.get();
    }

    /**
     * Nazwa zakupionego pakietu w Tabeli Zamowienia
     * @return nazwaPakietZ
     */
    public StringProperty nazwaPakietZProperty() {
        return nazwaPakietZ;
    }

    /**
     * Ustaw nazwe uslugi(pakietu) ktora zostala zakupiona
     * @param nazwaPakietZ
     */
    public void setNazwaPakietZ(String nazwaPakietZ) {
        this.nazwaPakietZ.set(nazwaPakietZ);
    }

    /**
     * Pobierz imie pracownika obslugujacego instalacjie pakietu (usługi).
     * @return pracownikImieZ
     */
    public String getPracownikImieZ() {
        return pracownikImieZ.get();
    }

    /**
     * Imie pracownika ktory zamowił pakiet w Tabeli Zamowienia
     * @return pracownikImieZ
     */
    public StringProperty pracownikImieZProperty() {
        return pracownikImieZ;
    }

    /**
     *  Ustaw imie pracownika ktory obsluzy zakupiony pakiet
     * @param pracownikImieZ
     */
    public void setPracownikImieZ(String pracownikImieZ) {
        this.pracownikImieZ.set(pracownikImieZ);
    }

    /**
     * Pobierz nazwisko pracownika obslugujacego instalacjie pakietu (usługi).
     * @return pracownikNazwiskoZ
     */
    public String getPracownikNazwiskoZ() {
        return pracownikNazwiskoZ.get();
    }

    /**
     * Nazwisko pracownika ktory obsluguje zamowiony pakiet(usluge) w Tabeli Zamowienia
     * @return
     */
    public StringProperty pracownikNazwiskoZProperty() {
        return pracownikNazwiskoZ;
    }

    /**
     * Ustaw nazwisko pracownika ktory obsluzy zakupioną usluge
     * @param pracownikNazwiskoZ
     */
    public void setPracownikNazwiskoZ(String pracownikNazwiskoZ) {
        this.pracownikNazwiskoZ.set(pracownikNazwiskoZ);
    }

    /**
     *  Pobierz wartość id zamowienia
     * @return zamowienia_id
     */
    public int getZamowienia_id() {
        return zamowienia_id.get();
    }

    /**
     * Zamowienie id w tabeli Zamowienia
     * @return zamowienia_id
     */
    public IntegerProperty zamowienia_idProperty() {
        return zamowienia_id;
    }

    /**
     * Ustaw id zamowienia zakupionego pakietu(uslugi).
     * @param zamowienia_id
     */
    public void setZamowienia_id(int zamowienia_id) {
        this.zamowienia_id.set(zamowienia_id);
    }

    /**
     * Pobierz id Klienta
     * @return klient_id
     */
    public int getKlient_id() {
        return klient_id.get();
    }

    /**
     * Klient id w Tabeli
     * @return klient_id
     */
    public IntegerProperty klient_idProperty() {
        return klient_id;
    }

    /**
     *  Ustaw id klienta zakupionego pakietu(uslugi)
     * @param klient_id
     */
    public void setKlient_id(int klient_id) {
        this.klient_id.set(klient_id);
    }

    /**
     * Pobierz wartosc id pakietu
     * @return pakiet_id
     */
    public int getPakiet_id() {
        return pakiet_id.get();
    }

    /**
     * Pakiet_id w Tabeli Zamowienia
     * @return pakiet_id
     */
    public IntegerProperty pakiet_idProperty() {
        return pakiet_id;
    }

    /**
     * Ustaw Id pakietu sprzedanej uslugi
     * @param pakiet_id
     */
    public void setPakiet_id(int pakiet_id) {
        this.pakiet_id.set(pakiet_id);
    }

    /**
     * Pobierz wartosc id pracownika
     * @return pracownik_id
     */
    public int getPracownik_id() {
        return pracownik_id.get();
    }

    /**
     * Pracownik ID w Tabeli Zamowienia
     * @return pracownik_id
     */
    public IntegerProperty pracownik_idProperty() {
        return pracownik_id;
    }

    /**
     * Ustaw id Pracownika sprzedanej uslugi
     * @param pracownik_id
     */
    public void setPracownik_id(int pracownik_id) {
        this.pracownik_id.set(pracownik_id);
    }

    /**
     * Pobierz date poczatku umowy.
     * @return umowa_od
     */
    public Date getUmowa_od() {
        return umowa_od.get();
    }

    /**
     * Data zamowienia w Tabeli Zamowienia
     * @return umowa_od
     */
    public SimpleObjectProperty<Date> umowa_odProperty() {
        return umowa_od;
    }

    /**
     * Ustaw date sprzedania - rozpoczęcia umowy danego pakietu
     * @param umowa_od
     */
    public void setUmowa_od(Date umowa_od) {
        this.umowa_od.set(umowa_od);
    }

    /**
     * Pobierz date zakonczenia umowy.
     * @return umowa_do
     */
    public Date getUmowa_do() {
        return umowa_do.get();
    }

    /**
     * Data konca uslugi w tabeli Zamowienia
     * @return umowa_do
     */
    public SimpleObjectProperty<Date> umowa_doProperty() {
        return umowa_do;
    }

    /**
     * Ustaw date zakończenia umowy danego pakietu
     * @param umowa_do
     */
    public void setUmowa_do(Date umowa_do) {
        this.umowa_do.set(umowa_do);
    }

    /**
     * Pobierz status zamowienia obslugi zamowienia.
     * @return status
     */
    public String getStatus() {
        return status.get();
    }

    /**
     * Status zakupionej uslugi w Tabeli Zamowienia
     * @return status
     */
    public StringProperty statusProperty() {
        return status;
    }

    /**
     * Ustaw status sprzedanej uslugi
     * @param status
     */
    public void setStatus(String status) {
        this.status.set(status);
    }

    /**
     * Pobierz wartosc ceny zamowienia
     * @return cenaU
     */
    public double getCenaU() {
        return cenaU.get();
    }

    /**
     * Cena zamowienia w tabeli UslugiZamowinia
     * @return cenaU
     */
    public DoubleProperty cenaUProperty() {
        return cenaU;
    }

    /**
     * Ustaw cene uslugi(pakietu)
     * @param cenaU
     */
    public void setCenaU(double cenaU) {
        this.cenaU.set(cenaU);
    }

    /**
     * Pobierz ilosc zamowieonych uslug
     * @return iloscU
     */
    public int getIloscU() {
        return iloscU.get();
    }

    /**
     * Ilosc zamowionych uslug(pakietow) w Tabeli UslugiZamowienia
     * @return
     */
    public IntegerProperty iloscUProperty() {
        return iloscU;
    }

    /**
     * Ustaw ilosc sprzedanych uslug danego typu
     * @param iloscU
     */
    public void setIloscU(int iloscU) {
        this.iloscU.set(iloscU);
    }

    /**
     * Pobierz wartosc ceny wszystkich danego typu uslug które są zakupione
     * @return cenaZbU
     */
    public double getCenaZbU() {
        return cenaZbU.get();
    }

    /**
     * Cena zamowionych uslug (pakietow) w tabeli UslugiZamowienia
     * @return cenaZbU
     */
    public DoubleProperty cenaZbUProperty() {
        return cenaZbU;
    }

    /**
     * Ustaw cene zbiorczą (sume) sprzedanych pakietow danego typu.
     * @param cenaZbU
     */
    public void setCenaZbU(double cenaZbU) { this.cenaZbU.set(cenaZbU); }

    /**
     * Pobierz numer domu klienta który zamowił usluge internetu (pakiet).
     * @return nr_domuKZ
     */
    public String getNr_domKZ() {
        return nr_domKZ.get();
    }

    /**
     * Nr domu klienta kupujacego usluge w Tabeli KlienciZamowienia
     * @return nr_domKZ
     */
    public StringProperty nr_domKZProperty() {
        return nr_domKZ;
    }

    /**
     * Ustaw nr domu klienta ktory zakupil pakiet(usluge)
     * @param nr_domKZ
     */
    public void setNr_domKZ(String nr_domKZ) {
        this.nr_domKZ.set(nr_domKZ);
    }

    /**
     * Pobierz nr telefonu klienta, ktory zakupił usługe.
     * @return nr_telefonKZ
     */
    public String getNr_telefonKZ() {
        return nr_telefonKZ.get();
    }

    /**
     * Nr telefonu klienta kupujacego usluge(Pakiet) w Tabeli KlienciZamowienia
     * @return nr_telefonuKZ
     */
    public StringProperty nr_telefonKZProperty() {
        return nr_telefonKZ;
    }

    /**
     * Ustaw nr telefonu klienta ktory kupil usluge.
     * @param nr_telefonKZ
     */
    public void setNr_telefonKZ(String nr_telefonKZ) {
        this.nr_telefonKZ.set(nr_telefonKZ);
    }

    /**
     * Pobierz daną technologie zakupionego pakietu(uslugi).
     * @return technologiaPakietZ
     */
    public String getTechnologiaPakietZ() {
        return technologiaPakietZ.get();
    }

    /**
     * Technologia uslugi(pakietu) ktory zostal sprzedany w Tabeli PakietyZamowienia
     * @return technologiaPakietZ
     */
    public StringProperty technologiaPakietZProperty() {
        return technologiaPakietZ;
    }

    /**
     * Ustaw technologie sprzedanego pakietu
     * @param technologiaPakietZ
     */
    public void setTechnologiaPakietZ(String technologiaPakietZ) {
        this.technologiaPakietZ.set(technologiaPakietZ);
    }

    /**
     * Pobierz dana prednosci zakupionego pakietu.
     * @return predkoscPakietZ
     */
    public String getPredkoscPakietZ() {
        return predkoscPakietZ.get();
    }

    /**
     * Predkosc pakietu (uslugi) ktora zostala zakupiona w Tabeli PakietyZamowienia
     * @return
     */
    public StringProperty predkoscPakietZProperty() {
        return predkoscPakietZ;
    }

    /**
     * Ustaw predkosc sprzedanego pakietu
     * @param predkoscPakietZ
     */
    public void setPredkoscPakietZ(String predkoscPakietZ) {
        this.predkoscPakietZ.set(predkoscPakietZ);
    }

}
