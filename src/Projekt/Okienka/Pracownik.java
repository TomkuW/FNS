package Projekt.Okienka;

import javafx.beans.property.*;

import java.sql.Date;

/**
 * Created by Kamil&Maciej  on 2017-12-18.
 */

public class Pracownik {

    private IntegerProperty pracownik_id;
    private StringProperty imie;
    private StringProperty nazwisko;
    private StringProperty zawod;
    private StringProperty PESEL;
    private StringProperty ulica;
    private StringProperty nr_dom;
    private StringProperty miejscowosc;
    private StringProperty email;
    private StringProperty nr_telefon;
    private StringProperty wynagrodzenie;
    private StringProperty login;
    private StringProperty haslo;
    private StringProperty typ_pracownika;
    private SimpleObjectProperty<Date> data_zatrudnienia;



    public Pracownik() {
        this.pracownik_id = new SimpleIntegerProperty();
        this.imie = new SimpleStringProperty();
        this.nazwisko = new SimpleStringProperty();
        this.zawod = new SimpleStringProperty();
        this.PESEL = new SimpleStringProperty();
        this.ulica = new SimpleStringProperty();
        this.nr_dom = new SimpleStringProperty();
        this.miejscowosc = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.nr_telefon = new SimpleStringProperty();
        this.wynagrodzenie = new SimpleStringProperty();
        this.login = new SimpleStringProperty();
        this.haslo = new SimpleStringProperty();
        this.typ_pracownika = new SimpleStringProperty();
        this.data_zatrudnienia = new SimpleObjectProperty<>();
    }

    public int getPracownik_id() {
        return pracownik_id.get();
    }

    public IntegerProperty pracownik_idProperty() {
        return pracownik_id;
    }

    public void setPracownik_id(int pracownik_id) {
        this.pracownik_id.set(pracownik_id);
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


    public String getZawod() {
        return zawod.get();
    }

    public StringProperty zawodProperty() {
        return zawod;
    }

    public void setZawod(String zawod) {
        this.zawod.set(zawod);
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


    public String getMiejscowosc() {
        return miejscowosc.get();
    }

    public StringProperty miejscowoscProperty() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc.set(miejscowosc);
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


    public String getNr_telefon() {
        return nr_telefon.get();
    }

    public StringProperty nr_telefonProperty() {
        return nr_telefon;
    }

    public void setNr_telefon(String nr_telefon) {
        this.nr_telefon.set(nr_telefon);
    }


    public String getWynagrodzenie() {
        return wynagrodzenie.get();
    }

    public StringProperty wynagrodzenieProperty() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(String wynagrodzenie) {
        this.wynagrodzenie.set(wynagrodzenie);
    }


    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }


    public String getHaslo() {
        return haslo.get();
    }

    public StringProperty hasloProperty() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo.set(haslo);
    }


    public String getTyp_pracownika() {
        return typ_pracownika.get();
    }

    public StringProperty typ_pracownikaProperty() {
        return typ_pracownika;
    }

    public void setTyp_pracownika(String typ_pracownika) {
        this.typ_pracownika.set(typ_pracownika);
    }


    public Date getData_zatrudnienia() {
        return data_zatrudnienia.get();
    }

    public SimpleObjectProperty<Date> data_zatrudnieniaProperty() {
        return data_zatrudnienia;
    }

    public void setData_zatrudnienia(Date data_zatrudnienia) {
        this.data_zatrudnienia.set(data_zatrudnienia);
    }
}