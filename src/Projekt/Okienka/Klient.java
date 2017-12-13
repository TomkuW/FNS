package Projekt.Okienka;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Tomek on 2017-12-13.
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

    public Klient() {
        this.klient_id = new SimpleIntegerProperty();
        this.imie = new SimpleStringProperty();
        this.nazwisko = new SimpleStringProperty();
        this.PESEL= new SimpleStringProperty();
        this.miejscowosc = new SimpleStringProperty();
        this.ulica = new SimpleStringProperty();
        this.nr_dom = new SimpleStringProperty();
        this.kod_poczta = new SimpleStringProperty();
        this.nr_telefon = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
    }

    public int getKlient_id() {
        return klient_id.get();
    }

    public IntegerProperty klient_idProperty() {
        return klient_id;
    }

    public void setKlient_id(int klient_id) {
        this.klient_id.set(klient_id);
    }

    public String getImie() {
        return imie.get();
    }

    public StringProperty imieProperty() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public String getPESEL() {
        return PESEL.get();
    }

    public StringProperty PESELProperty() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL.set(PESEL);
    }

    public String getMiejscowosc() {
        return miejscowosc.get();
    }

    public StringProperty miejscowoscProperty() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc.set(miejscowosc);
    }

    public String getUlica() {
        return ulica.get();
    }

    public StringProperty ulicaProperty() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica.set(ulica);
    }

    public String getNr_dom() {
        return nr_dom.get();
    }

    public StringProperty nr_domProperty() {
        return nr_dom;
    }

    public void setNr_dom(String nr_dom) {
        this.nr_dom.set(nr_dom);
    }

    public String getKod_poczta() {
        return kod_poczta.get();
    }

    public StringProperty kod_pocztaProperty() {
        return kod_poczta;
    }

    public void setKod_poczta(String kod_poczta) {
        this.kod_poczta.set(kod_poczta);
    }

    public String getNr_telefon() {
        return nr_telefon.get();
    }

    public StringProperty nr_telefonProperty() {
        return nr_telefon;
    }

    public void setNr_telefon(String nr_telefon) {
        this.nr_telefon.set(nr_telefon);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
