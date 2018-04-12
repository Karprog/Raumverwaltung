package database.implementation;

/**
 * Created by pfleiderer.stefanie on 17.11.2017.
 */
public class Hardware {

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

    public Hardware() {};

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
        return "ID: " + this.id + "\n" +
                "Typ: " + this.typ + "\n" +
                "Seriennummer: " + this.seriennummer + "\n" +
                "Inventarnummer: " + this.inventarnummer + "\n" +
                "Hersteller: " + this.hersteller + "\n" +
                "Modell: " + this.modell + "\n" +
                "Status: " + this.status + "\n";
    }

    public void wechsleRaum(Raum raum) {
        raum.addToHardwareList(this);
    }
}
