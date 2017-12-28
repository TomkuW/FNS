package Projekt.Okienka;

import javafx.beans.property.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
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

        this.cenaU = new SimpleDoubleProperty();
        this.cenaZbU = new SimpleDoubleProperty();
        this.iloscU = new SimpleIntegerProperty();

        this.predkoscPakietZ = new SimpleStringProperty();
        this.technologiaPakietZ = new SimpleStringProperty();
        this.nr_domKZ = new SimpleStringProperty();
        this.nr_telefonKZ = new SimpleStringProperty();





    }

    public String getImieKZ() {
        return imieKZ.get();
    }

    public StringProperty imieKZProperty() {
        return imieKZ;
    }

    public void setImieKZ(String imieKZ) {
        this.imieKZ.set(imieKZ);
    }

    public String getNazwiskoKZ() {
        return nazwiskoKZ.get();
    }

    public StringProperty nazwiskoKZProperty() {
        return nazwiskoKZ;
    }

    public void setNazwiskoKZ(String nazwiskoKZ) {
        this.nazwiskoKZ.set(nazwiskoKZ);
    }

    public String getMiejscowoscKZ() {
        return miejscowoscKZ.get();
    }

    public StringProperty miejscowoscKZProperty() {
        return miejscowoscKZ;
    }

    public void setMiejscowoscKZ(String miejscowoscKZ) {
        this.miejscowoscKZ.set(miejscowoscKZ);
    }

    public String getUlicaKZ() {
        return ulicaKZ.get();
    }

    public StringProperty ulicaKZProperty() {
        return ulicaKZ;
    }

    public void setUlicaKZ(String ulicaKZ) {
        this.ulicaKZ.set(ulicaKZ);
    }

    public String getNazwaPakietZ() {
        return nazwaPakietZ.get();
    }

    public StringProperty nazwaPakietZProperty() {
        return nazwaPakietZ;
    }

    public void setNazwaPakietZ(String nazwaPakietZ) {
        this.nazwaPakietZ.set(nazwaPakietZ);
    }

    public String getPracownikImieZ() {
        return pracownikImieZ.get();
    }

    public StringProperty pracownikImieZProperty() {
        return pracownikImieZ;
    }

    public void setPracownikImieZ(String pracownikImieZ) {
        this.pracownikImieZ.set(pracownikImieZ);
    }

    public String getPracownikNazwiskoZ() {
        return pracownikNazwiskoZ.get();
    }

    public StringProperty pracownikNazwiskoZProperty() {
        return pracownikNazwiskoZ;
    }

    public void setPracownikNazwiskoZ(String pracownikNazwiskoZ) {
        this.pracownikNazwiskoZ.set(pracownikNazwiskoZ);
    }

    public int getZamowienia_id() {
        return zamowienia_id.get();
    }

    public IntegerProperty zamowienia_idProperty() {
        return zamowienia_id;
    }

    public void setZamowienia_id(int zamowienia_id) {
        this.zamowienia_id.set(zamowienia_id);
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

    public int getPakiet_id() {
        return pakiet_id.get();
    }

    public IntegerProperty pakiet_idProperty() {
        return pakiet_id;
    }

    public void setPakiet_id(int pakiet_id) {
        this.pakiet_id.set(pakiet_id);
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

    public Date getUmowa_od() {
        return umowa_od.get();
    }

    public SimpleObjectProperty<Date> umowa_odProperty() {
        return umowa_od;
    }

    public void setUmowa_od(Date umowa_od) {
        this.umowa_od.set(umowa_od);
    }

    public Date getUmowa_do() {
        return umowa_do.get();
    }

    public SimpleObjectProperty<Date> umowa_doProperty() {
        return umowa_do;
    }

    public void setUmowa_do(Date umowa_do) {
        this.umowa_do.set(umowa_do);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public double getCenaU() {
        return cenaU.get();
    }

    public DoubleProperty cenaUProperty() {
        return cenaU;
    }

    public void setCenaU(double cenaU) {
        this.cenaU.set(cenaU);
    }

    public int getIloscU() {
        return iloscU.get();
    }

    public IntegerProperty iloscUProperty() {
        return iloscU;
    }

    public void setIloscU(int iloscU) {
        this.iloscU.set(iloscU);
    }

    public double getCenaZbU() {
        return cenaZbU.get();
    }

    public DoubleProperty cenaZbUProperty() {
        return cenaZbU;
    }

    public void setCenaZbU(double cenaZbU) {

        this.cenaZbU.set(cenaZbU);
    }

    public String getNr_domKZ() {
        return nr_domKZ.get();
    }

    public StringProperty nr_domKZProperty() {
        return nr_domKZ;
    }

    public void setNr_domKZ(String nr_domKZ) {
        this.nr_domKZ.set(nr_domKZ);
    }

    public String getNr_telefonKZ() {
        return nr_telefonKZ.get();
    }

    public StringProperty nr_telefonKZProperty() {
        return nr_telefonKZ;
    }

    public void setNr_telefonKZ(String nr_telefonKZ) {
        this.nr_telefonKZ.set(nr_telefonKZ);
    }

    public String getTechnologiaPakietZ() {
        return technologiaPakietZ.get();
    }

    public StringProperty technologiaPakietZProperty() {
        return technologiaPakietZ;
    }

    public void setTechnologiaPakietZ(String technologiaPakietZ) {
        this.technologiaPakietZ.set(technologiaPakietZ);
    }

    public String getPredkoscPakietZ() {
        return predkoscPakietZ.get();
    }

    public StringProperty predkoscPakietZProperty() {
        return predkoscPakietZ;
    }

    public void setPredkoscPakietZ(String predkoscPakietZ) {
        this.predkoscPakietZ.set(predkoscPakietZ);
    }
}
