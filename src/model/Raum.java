package model;

import java.util.ArrayList;

/**
 * Created by pfleiderer.stefanie on 17.11.2017.
 */
public class Raum {
    private int raumid;
    private String bezeichnung;
    private String typ;
    private int anzahlArbeitsplaetze;
    private ArrayList<Hardware> hardwareliste = new ArrayList<>();

    public Raum(){ }

    public Raum(
                String bezeichnung,
                String typ,
                int anzahlArbeitsplaetze) {
        this.bezeichnung = bezeichnung;
        this.typ = typ;
        this.anzahlArbeitsplaetze = anzahlArbeitsplaetze;
    }

    public int getRaumid() {
        return raumid;
    }

    public void setRaumid(int raumid) {
        this.raumid = raumid;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getAnzahlArbeitsplaetze() {
        return anzahlArbeitsplaetze;
    }

    public void setAnzahlArbeitsplaetze(int anzahlArbeitsplaetze) {
        this.anzahlArbeitsplaetze = anzahlArbeitsplaetze;
    }

    public ArrayList<Hardware> getHardwareliste() {
        return this.hardwareliste;
    }

    public void setHardwareliste(ArrayList<Hardware> hardwareliste) {
        this.hardwareliste = hardwareliste;
    }

    public void addToHardwareList(Hardware hardware) {
        this.hardwareliste.add(hardware);
    }

    public void druckeHardwareliste() {
        System.out.println("Informationen zur src.Hardware in src.Raum Nr. " + this.raumid + "\n");

        for (Hardware hardware : this.hardwareliste) {
            if (hardware != null ) {
                System.out.println(hardware.toString());
            }
        }
    }

    @Override
    public String toString() {
        return  "Raum" +
                "{" +
                "raumid=" + raumid +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", typ='" + typ + '\'' +
                ", anzahlArbeitsplätze=" + anzahlArbeitsplaetze +
                "}" +
                hardwareliste + "\n";
    }
}
