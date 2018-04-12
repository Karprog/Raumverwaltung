package model;

/**
 * Created by joel_thinkpad on 17.11.2017.
 */
public class Drucker extends Hardware {
    private String betriebsmittel;

    public Drucker() {
        super("Drucker");
    }

    public void wechsleBetriebsmittel() {
        System.out.println(betriebsmittel + " muss erneuert werden.");
    }

    @Override
    public String toString() {
        return super.toString() +  "Drucker{" +
                "betriebsmittel='" + betriebsmittel + '\'' +
                '}';
    }

    public String getBetriebsmittel() {
        return betriebsmittel;
    }

    public void setBetriebsmittel(String betriebsmittel) {
        this.betriebsmittel = betriebsmittel;
    }
}
