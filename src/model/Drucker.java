package model;

/**
 * Created by pfleiderer.stefanie on 17.11.2017.
 */
public class Drucker extends Hardware {

    private String betriebsmittel;

    public Drucker(){ }

    public Drucker(
                   String typ,
                   String seriennummer,
                   String inventarnummer,
                   String hersteller,
                   String modell,
                   int status,
                   String betriebsmittel, String raumid) {
        super(typ, seriennummer, inventarnummer, hersteller, modell, status, raumid);
        this.betriebsmittel = betriebsmittel;
    }

    public String getBetriebsmittel() {
        return this.betriebsmittel;
    }

    public void setBetriebsmittel(String betriebsmittel) {
        this.betriebsmittel = betriebsmittel;
    }

    public void wechsleBetriebsmittel() {
        System.out.println(this.betriebsmittel + " muss erneuert werden.\n");
    }

    @Override
    public String toString() {
        return super.toString() +  "Drucker{" +
                "betriebsmittel='" + betriebsmittel + '\'' +
                '}';
    }
}
