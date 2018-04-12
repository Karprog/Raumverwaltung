package database.implementation;

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

    public Raum(
                String bezeichnung,
                String typ,
                int anzahlArbeitsplaetze) {
        this.bezeichnung = bezeichnung;
        this.typ = typ;
        this.anzahlArbeitsplaetze = anzahlArbeitsplaetze;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public String getTyp() {
        return typ;
    }

    public int getAnzahlArbeitsplaetze() {
        return anzahlArbeitsplaetze;
    }

    public ArrayList<Hardware> getHardwareliste() {
        return this.hardwareliste;
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
        String hardwareString = "";

        if (!this.hardwareliste.isEmpty()) {
            for (Hardware hardware : this.hardwareliste) {
                hardwareString += (hardware.getTyp() + ",\n");
            }
        } else {
            hardwareString += "Es gibt keine src.Hardware in src.Raum " + this.bezeichnung + "\n";
        }

        return "Raum ID: " + this.raumid + "\n" +
                "Bezeichnung: " + this.bezeichnung + "\n" +
                "Typ: " + this.typ + "\n" +
                "Anzahl der Arbeitsplätze: " + this.anzahlArbeitsplaetze + "\n" +
                "Hardwareliste:\n\n " + hardwareString + "\n\n";
    }
}
