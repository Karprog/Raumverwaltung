package model;

import java.util.ArrayList;

public class Raum {

    private ArrayList<Hardware> hardwareArrayList = new ArrayList<>();
    private int raumid;
    private String bezeichnung;
    private String typ;
    private int anzahlArbeitsplätze;

    @Override
    public String toString() {
        return  "Raum" +
                "{" +
                "raumid=" + raumid +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", typ='" + typ + '\'' +
                ", anzahlArbeitsplätze=" + anzahlArbeitsplätze +
                "}" +
                hardwareArrayList + "\n";
    }

    public ArrayList<Hardware> getHardwareArrayList() {
        return hardwareArrayList;
    }

    public void setHardwareArrayList(ArrayList<Hardware> hardwareArrayList) {
        this.hardwareArrayList = hardwareArrayList;
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

    public int getAnzahlArbeitsplätze() {
        return anzahlArbeitsplätze;
    }

    public void setAnzahlArbeitsplätze(int anzahlArbeitsplätze) {
        this.anzahlArbeitsplätze = anzahlArbeitsplätze;
    }
}
