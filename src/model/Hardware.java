package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by pfleiderer.stefanie on 17.11.2017.
 */
@Entity
@Table(name = "hardware")
public class Hardware {

    @Id
    private int id;
    private String typ;
    private String seriennummer;
    private String inventarnummer;
    private String hersteller;
    private String modell;
    private int status;
    private String raumid;

    public Hardware(
                    String typ,
                    String seriennummer,
                    String inventarnummer,
                    String hersteller,
                    String modell,
                    int status, String raumid) {
        this.typ = typ;
        this.seriennummer = seriennummer;
        this.inventarnummer = inventarnummer;
        this.hersteller = hersteller;
        this.modell = modell;
        this.status = status;
        this.raumid = raumid;
    }

    public Hardware() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getSeriennummer() {
        return seriennummer;
    }

    public void setSeriennummer(String seriennummer) {
        this.seriennummer = seriennummer;
    }

    public String getInventarnummer() {
        return inventarnummer;
    }

    public void setInventarnummer(String inventarnummer) {
        this.inventarnummer = inventarnummer;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\n" +
                "Hardware{" +
                "id=" + id +
                ", typ='" + typ + '\'' +
                ", seriennummer='" + seriennummer + '\'' +
                ", inventarnummer='" + inventarnummer + '\'' +
                ", hersteller='" + hersteller + '\'' +
                ", modell='" + modell + '\'' +
                ", status=" + status +
                '}';
    }

    public void wechsleRaum(Raum raum) {
        raum.addToHardwareList(this);
    }
}
