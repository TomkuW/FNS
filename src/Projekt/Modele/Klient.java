package Projekt.Modele;

import javafx.beans.property.*;

/**
 * Deklaracja Tabeli Klienci
 *
 * Created by TomaszWaberski on 2017-11-17.
 */
public class Klient {

    private IntegerProperty klient_id;
    private StringProperty imie;
    private StringProperty nazwisko;
    private StringProperty PESEL;
    private StringProperty miejscowosc;
    private StringProperty ulica;
    private StringProperty nr_dom;
    private StringProperty kod_poczta;
    private StringProperty nr_telefon;
    private StringProperty email;

    /**
     * Konstruktor
     */
    public Klient() {
        this.klient_id = new SimpleIntegerProperty();
        this.imie = new SimpleStringProperty();
        this.nazwisko = new SimpleStringProperty();
        this.PESEL = new SimpleStringProperty();
        this.miejscowosc = new SimpleStringProperty();
        this.ulica = new SimpleStringProperty();
        this.nr_dom = new SimpleStringProperty();
        this.kod_poczta = new SimpleStringProperty();
        this.nr_telefon = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
    }

    /**
     * Pobierz wartość id klienta.
     *
     * @return klient_id
     */
    public int getKlient_id() {
        return klient_id.get();
    }

    /**
     * Klient ID dla Tabeli
     *
     * @return klient_id
     */
    public IntegerProperty klient_idProperty() {
        return klient_id;
    }


    /**
     * Ustaw klient ID
     *
     * @param klient_id
     */
    public void setKlient_id(int klient_id) {
        this.klient_id.set(klient_id);
    }

    /**
     * Weź imię klienta
     *
     * @return imie
     */
    public String getImie() {
        return imie.get();
    }

    /**
     * Imie Klienta dla Tabeli
     *
     * @return imie
     */
    public StringProperty imieProperty() {
        return imie;
    }

    /**
     * Ustaw imie klienta
     *
     * @param imie
     */
    public void setImie(String imie) {
        this.imie.set(imie);
    }

    /**
     * @return Imie klienta dla Tabeli Klienci
     */
    public String getNazwisko() {
        return nazwisko.get();
    }

    /**
     * Nazwisko klienta dla Tabeli Klienci
     *
     * @return nazwisko
     */
    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    /**
     * Ustaw nazwisko klienta
     *
     * @param nazwisko
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    /**
     * Weź PESEL klienta
     *
     * @return PESEL
     */
    public String getPESEL() {
        return PESEL.get();
    }

    /**
     * PESEL klienta dla Tabeli Klienci
     *
     * @return PESEL
     */
    public StringProperty PESELProperty() {
        return PESEL;
    }

    /**
     * Ustaw PESEL klienta
     *
     * @param PESEL
     */
    public void setPESEL(String PESEL) {
        this.PESEL.set(PESEL);
    }

    /**
     * Weź miejscowość klienta
     *
     * @return miejscowosc
     */
    public String getMiejscowosc() {
        return miejscowosc.get();
    }

    /**
     * Miejscowość klienta dla Tabeli Klienci
     *
     * @return miejscowość
     */
    public StringProperty miejscowoscProperty() {
        return miejscowosc;
    }

    /**
     * Ustaw miejscowosć klienta
     *
     * @param miejscowosc
     */
    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc.set(miejscowosc);
    }

    /**
     * Weź ulica klienta
     *
     * @return ulica
     */
    public String getUlica() {
        return ulica.get();
    }

    /**
     * Ulica klienta dla Tabeli Klienci
     *
     * @return ulica
     */
    public StringProperty ulicaProperty() {
        return ulica;
    }

    /**
     * Ustaw ulice klienta
     *
     * @param ulica
     */
    public void setUlica(String ulica) {
        this.ulica.set(ulica);
    }

    /**
     * Weź nr domu klienta
     *
     * @return nr_dom
     */
    public String getNr_dom() {
        return nr_dom.get();
    }

    /**
     * Nr domu klienta dla Tabeli Klienci
     *
     * @return nr_dom
     */
    public StringProperty nr_domProperty() {
        return nr_dom;
    }

    /**
     * Ustaw Nr domu klienta
     *
     * @param nr_dom
     */
    public void setNr_dom(String nr_dom) {
        this.nr_dom.set(nr_dom);
    }

    /**
     * Weź kod pocztowy klienta
     *
     * @return kod_poczta
     */
    public String getKod_poczta() {
        return kod_poczta.get();
    }

    /**
     * Kod pocztowy klienta dla Tabeli Klienci
     *
     * @return kod_poczta
     */
    public StringProperty kod_pocztaProperty() {
        return kod_poczta;
    }

    /**
     * Ustaw kod pocztowy klienta
     *
     * @param kod_poczta
     */
    public void setKod_poczta(String kod_poczta) {
        this.kod_poczta.set(kod_poczta);
    }

    /**
     * Wex nr telefonu klienta
     *
     * @return nr_telefon
     */
    public String getNr_telefon() {
        return nr_telefon.get();
    }

    /**
     * Nr telefonu klienta dla Tabeli Klienci
     *
     * @return nr_telefon
     */
    public StringProperty nr_telefonProperty() {
        return nr_telefon;
    }

    /**
     * Ustaw Nr telefonu klienta
     *
     * @param nr_telefon
     */
    public void setNr_telefon(String nr_telefon) {
        this.nr_telefon.set(nr_telefon);
    }

    /**
     * Weź email klienta
     *
     * @return email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * Email klienta dla Tabeli Klienci
     *
     * @return email
     */
    public StringProperty emailProperty() {
        return email;
    }

    /**
     * Ustaw email klienta
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email.set(email);
    }
}
