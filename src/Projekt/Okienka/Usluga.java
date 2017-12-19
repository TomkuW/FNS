package Projekt.Okienka;

import javafx.beans.property.*;

/**
 * Created by Fabian on 2017-11-17.
 */
public class Usluga {

    private IntegerProperty pakiet_id;
    private StringProperty nazwa;
    private StringProperty technologia;
    private StringProperty predkosc;
    private StringProperty cena;
    private StringProperty okres;


    public Usluga() {
        this.pakiet_id = new SimpleIntegerProperty();
        this.nazwa = new SimpleStringProperty();
        this.technologia = new SimpleStringProperty();
        this.predkosc = new SimpleStringProperty();
        this.cena = new SimpleStringProperty();
        this.okres = new SimpleStringProperty();

    }

    public int getPakiet_id() {
        return pakiet_id.get();
    }

    public IntegerProperty pakiet_idProperty() {
        return pakiet_id;
    }

    public void setPakiet_id(int pakiet_id) {
        this.pakiet_id.set(pakiet_id);
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public StringProperty nazwaProperty() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public String getTechnologia() {
        return technologia.get();
    }

    public StringProperty technologiaProperty() {
        return technologia;
    }

    public void setTechnologia(String technologia) {
        this.technologia.set(technologia);
    }

    public String getPredkosc() {
        return predkosc.get();
    }

    public StringProperty predkoscProperty() {
        return predkosc;
    }

    public void setPredkosc(String predkosc) {
        this.predkosc.set(predkosc);
    }

    public String getCena() {
        return cena.get();
    }

    public StringProperty cenaProperty() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena.set(cena);
    }

    public String getOkres() {
        return okres.get();
    }

    public StringProperty okresProperty() {
        return okres;
    }

    public void setOkres(String okres) {
        this.okres.set(okres);
    }
}

