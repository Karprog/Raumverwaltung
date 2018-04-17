package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by pfleiderer.stefanie on 17.11.2017.
 */
@Entity
@Table(name = "drucker")
public class Drucker extends Hardware {

    @Id
    private int id;
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
