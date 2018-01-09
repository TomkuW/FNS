package Projekt.Modele;

import javafx.beans.property.*;

import java.sql.Date;


/**
 * Deklaracja Tabeli Pracownicy.
 *
 * Created by Tomek on 2017-11-25.
 */
public class Pracownik {

    private IntegerProperty pracownik_id;
    private StringProperty imie;
    private StringProperty nazwisko;
    private StringProperty zawod;
    private StringProperty PESEL;
    private StringProperty ulica;
    private StringProperty nr_domu;
    private StringProperty miejscowosc;
    private StringProperty email;
    private StringProperty nr_telefon;
    private DoubleProperty wynagrodzenie;
    private StringProperty login;
    private SimpleObjectProperty<Date> data_zatrudnienia;
    private StringProperty haslo;
    private StringProperty typ_pracownika;

    private DoubleProperty wynagrodzenieRoczne;

    /**
     * Konstruktor
     */
    public Pracownik() {
        this.pracownik_id = new SimpleIntegerProperty();
        this.imie = new SimpleStringProperty();
        this.nazwisko = new SimpleStringProperty();
        this.zawod = new SimpleStringProperty();
        this.PESEL = new SimpleStringProperty();
        this.ulica = new SimpleStringProperty();
        this.nr_domu = new SimpleStringProperty();
        this.miejscowosc = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.nr_telefon = new SimpleStringProperty();
        this.wynagrodzenie = new SimpleDoubleProperty();
        this.login = new SimpleStringProperty();
        this.data_zatrudnienia = new SimpleObjectProperty<>();
        this.haslo = new SimpleStringProperty();
        this.typ_pracownika = new SimpleStringProperty();
        this.wynagrodzenieRoczne = new SimpleDoubleProperty();
		

    }

    /**
     * Pobierz wartość id pracownika
     * @return pracownik_id
     */
    public int getPracownik_id() {
        return pracownik_id.get();
    }

    /**
     * Pracownik ID dla Tabeli
     * @return pracownik_id
     */
    public IntegerProperty pracownik_idProperty() {
        return pracownik_id;
    }

    /**
     * Ustaw pracownik ID
     * @param pracownik_id
     */
    public void setPracownik_id(int pracownik_id) {
        this.pracownik_id.set(pracownik_id);
    }

    /**
     * Pobierz wartość imie
     * @return imie
     */
    public String getImie() {
        return imie.get();
    }

    /**
     * Imie dla Tabela
     * @return imie
     */
    public StringProperty imieProperty() {
        return imie;
    }

    /**
     * Ustaw imie pracownika
     * @param imie
     */
    public void setImie(String imie) {
        this.imie.set(imie);
    }

    /**
     * Pobierz wartość nazwisko
     * @return nazwisko
     */
    public String getNazwisko() {
        return nazwisko.get();
    }

    /**
     * Nazwisko dla Tabeli
     * @return nazwisko
     */
    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    /**
     * Ustaw nazwisko pracownika
     * @param nazwisko
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    /**
     * Pobierz wartość zawodu
     * @return zawod
     */
    public String getZawod() {
        return zawod.get();
    }

    /**
     * Zawod dla Tabeli
     * @return zawod
     */
    public StringProperty zawodProperty() {
        return zawod;
    }

    /**
     * Ustaw zawod pracownika
     * @param zawod
     */
    public void setZawod(String zawod) {
        this.zawod.set(zawod);
    }

    /**
     * Pobierz wartość PESEL
     * @return PESEL
     */
    public String getPESEL() {
        return PESEL.get();
    }

    /**
     * PESEL dla Tabeli
     * @return PESEL
     */
    public StringProperty PESELProperty() {
        return PESEL;
    }

    /**
     * Ustaw PESEL pracownika
     * @param PESEL
     */
    public void setPESEL(String PESEL) {
        this.PESEL.set(PESEL);
    }

    /**
     * Pobierz wartość ulica
     * @return ulica
     */
    public String getUlica() {
        return ulica.get();
    }

    /**
     * Ulica dla Tabeli
     * @return ulica
     */
    public StringProperty ulicaProperty() {
        return ulica;
    }

    /**
     * Ustaw ulice pracownika
     * @param ulica
     */
    public void setUlica(String ulica) {
        this.ulica.set(ulica);
    }

    /**
     * Pobierz wartość nr_domu
     * @return nr_domu
     */
    public String getNr_domu() {
        return nr_domu.get();
    }

    /**
     * Numer domu dla Tabeli
     * @return nr_domu
     */
    public StringProperty nr_domuProperty() {
        return nr_domu;
    }

    /**
     * Ustaw nr domu pracownika
     * @param nr_domu
     */
    public void setNr_domu(String nr_domu) {
        this.nr_domu.set(nr_domu);
    }

    /**
     * Pobierz wartość miejscowosc
     * @return miejsowosc
     */
    public String getMiejscowosc() {
        return miejscowosc.get();
    }

    /**
     * Miejscowosc dla Tabeli
     * @return miejscowosc
     */
    public StringProperty miejscowoscProperty() {
        return miejscowosc;
    }

    /**
     * Ustaw miejscowosc
     * @param miejscowosc
     */
    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc.set(miejscowosc);
    }

    /**
     * Pobierz wartość email
     * @return email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * Email dla Tabeli
     * @return email
     */
    public StringProperty emailProperty() {
        return email;
    }

    /**
     * Ustaw email pracownika
     * @param email
     */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * Pobierz wartość telefon
     * @return nr_telefon
     */
    public String getNr_telefon() {
        return nr_telefon.get();
    }

    /**
     * Numer telefonu dla Tabeli
     * @return nr_telefon
     */
    public StringProperty nr_telefonProperty() {
        return nr_telefon;
    }

    /**
     * Ustaw nr telefonu pracownika
     * @param nr_telefon
     */
    public void setNr_telefon(String nr_telefon) {
        this.nr_telefon.set(nr_telefon);
    }

    /**
     * Pobierz wartość wynagrodzenie
     * @return wynagrodzenie
     */
    public Double getWynagrodzenie() {
        return wynagrodzenie.get();
    }

    /**
     * Wynagrodzenie dla Tabeli
     * @return wynagrodzenie
     */
    public DoubleProperty wynagrodzenieProperty() {
        return wynagrodzenie;
    }

    /**
     * Ustaw wynagrodzenie pracownika
     * @param wynagrodzenie
     */
    public void setWynagrodzenie(double wynagrodzenie) {
        this.wynagrodzenie.set(wynagrodzenie);
    }

    /**
     * Pobierz wartość Login
     * @return login
     */
    public String getLogin() {
        return login.get();
    }

    /**
     * Login dla Tabeli
     * @return login
     */
    public StringProperty loginProperty() {
        return login;
    }

    /**
     * Ustaw login pracownika
     * @param login
     */
    public void setLogin(String login) {
        this.login.set(login);
    }

    /**
     * Pobierz wartość daty zatrodnienia
     * @return data_zatrudnienia
     */
    public Date getData_zatrudnienia() {
        return data_zatrudnienia.get();
    }

    /**
     * Data zatrudnienia dla Tabeli
     * @return data_zatrudnienia
     */
    public SimpleObjectProperty<Date> data_zatrudnieniaProperty() {
        return data_zatrudnienia;
    }

    /**
     * Ustaw data zatrudneia pracownika
     * @param data_zatrudnienia
     */
    public void setData_zatrudnienia(Date data_zatrudnienia) {
        this.data_zatrudnienia.set(data_zatrudnienia);
    }

    /**
     * Pobierz wartość haslo
     * @return haslo
     */
    public String getHaslo() {
        return haslo.get();
    }

    /**
     * Haslo dla Tabeli
     * @return haslo
     */
    public StringProperty hasloProperty() {
        return haslo;
    }

    /**
     * Ustaw haslo pracownikowi
     * @param haslo
     */
    public void setHaslo(String haslo) {
        this.haslo.set(haslo);
    }

    /**
     * Pobierz wartość typ_pracownika
     * @return typ_pracownika
     */
    public String getTyp_pracownika() {
        return typ_pracownika.get();
    }

    /**
     * Typ pracownika dla Tabeli
     * @return typ_pracownika
     */
    public StringProperty typ_pracownikaProperty() {
        return typ_pracownika;
    }

    /**
     * Ustaw typ pracownika
     * @param typ_pracownika
     */
    public void setTyp_pracownika(String typ_pracownika) {
        this.typ_pracownika.set(typ_pracownika);
    }

    /**
     * Ustaw wynagrodzenie brutto pracownika
     * @param wynagrodzenie
     */
    public void setWynagrodzenie(int wynagrodzenie) {
        this.wynagrodzenie.set(wynagrodzenie);
    }

    /**
     * Pobierz wartość wynagrodzenia
     * @return wynagrodzenie
     */
    public Double getWynagrodzenieRoczne() {
        return wynagrodzenieRoczne.get();
    }

    /**
     * Wynagrodzenie Roczne dla Tabeli
     * @return wynagrodzenieRoczne
     */
    public DoubleProperty wynagrodzenieRoczneProperty() {
        return wynagrodzenieRoczne;
    }

    /**
     * Ustaw wynagrodzenie roczne pracownika
     * @param wynagrodzenieRoczne
     */
    public void setWynagrodzenieRoczne(double wynagrodzenieRoczne) { this.wynagrodzenieRoczne.set(wynagrodzenieRoczne);}

}
