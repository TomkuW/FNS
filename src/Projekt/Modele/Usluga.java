package Projekt.Modele;

import javafx.beans.property.*;

/**
 *  Deklaracja Tabeli Pakiety
 *
 * Created by Tomek on 2017-11-23.
 */
public class Usluga {

    private IntegerProperty pakiet_id;
    private StringProperty nazwa;
    private StringProperty technologia;
    private StringProperty predkosc;
    private DoubleProperty cena;
    private IntegerProperty okres;

    /**
     * Konstruktor
     */
    public Usluga() {
        this.pakiet_id = new SimpleIntegerProperty();
        this.nazwa = new SimpleStringProperty();
        this.technologia = new SimpleStringProperty();
        this.predkosc = new SimpleStringProperty();
        this.cena = new SimpleDoubleProperty();
        this.okres = new SimpleIntegerProperty();
    }

    /**
     *  Pobierz wartość id pakiet
     * @return pakiet_id
     */
    public int getPakiet_id() {
        return pakiet_id.get();
    }

    /**
     * Pakiet ID dla Tabeli
     * @return pakiet_id
     */
    public IntegerProperty pakiet_idProperty() {
        return pakiet_id;
    }

    /**
     * Ustaw pakiet ID
     * @param pakiet_id
     */
    public void setPakiet_id(int pakiet_id) {
        this.pakiet_id.set(pakiet_id);
    }

    /**
     * Pobierz wartość nazwy pakietu
     * @return pakiet_id
     */
    public String getNazwa() {
        return nazwa.get();
    }

    /**
     * Nazwa pakietu dla Tabli
     * @return nazwa
     */
    public StringProperty nazwaProperty() {
        return nazwa;
    }

    /**
     * Ustaw nazwa pakietu
     * @param nazwa
     */
    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    /**
     * Pobierz wartość technologii
     * @return technologia
     */
    public String getTechnologia() {
        return technologia.get();
    }

    /**
     * Technologia pakietu dla Tabeli
     * @return technologia
     */
    public StringProperty technologiaProperty() {
        return technologia;
    }

    /**
     * Ustaw technologie
     * @param technologia
     */
    public void setTechnologia(String technologia) {
        this.technologia.set(technologia);
    }

    /**
     * Pobierz wartość predkosc
     * @return predkosc
     */
    public String getPredkosc() {
        return predkosc.get();
    }

    /**
     * Predkosc dla Tabeli
     * @return predkosc
     */
    public StringProperty predkoscProperty() {
        return predkosc;
    }

    /**
     * Ustaw predkość
     * @param predkosc
     */
    public void setPredkosc(String predkosc) {
        this.predkosc.set(predkosc);
    }

    /**
     * Pobierz wartość pola cena
     * @return cena
     */
    public double getCena() {return cena.get();}

    /**
     * Cena dla Tabeli
     * @return cena
     */
    public DoubleProperty cenaProperty() {
        return cena;
    }

    /**
     * Ustaw cene
     * @param cena
     */
    public void setCena(double cena) {
        this.cena.set(cena);
    }

    /**
     * Pobierz wartość okresu
     * @return okres
     */
    public int getOkres() {
        return okres.get();
    }

    /**
     * Okres trwania pakietu dla Tabeli
     * @return okres
     */
    public IntegerProperty okresProperty() {
        return okres;
    }

    /**
     * Ustaw okres
     * @param okres
     */
    public void setOkres(int okres) {
        this.okres.set(okres);
    }
}
