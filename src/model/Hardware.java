package model;
public class Hardware {
    private int id;
    private String typ;
    private String seriennummer;
    private String inventarnummer;
    private String hersteller;
    private String modell;
    private int status;

    public Hardware(String typ) {
        this.typ = typ;
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

    }


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
}
