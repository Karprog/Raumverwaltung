package model;

/**
 * Created by pfleiderer.stefanie on 17.11.2017.
 */
public class Rechner extends Hardware {

    private String imagepfad;

    public Rechner(){ }

    public Rechner(

                   String typ,
                   String seriennummer,
                   String inventarnummer,
                   String hersteller,
                   String modell,
                   int status, String imagepfad, String raumid) {
        super(typ, seriennummer, inventarnummer, hersteller, modell, status, raumid);
        this.imagepfad = imagepfad;
    }

    public Rechner(int id, String typ, String seriennummer, String inventarnummer, String hersteller, String modell,
                   int status, String raumid, String imagepfad) {
        super(id, typ, seriennummer, inventarnummer, hersteller, modell, status, raumid);
        this.imagepfad = imagepfad;
    }

    public String getImagepfad() {
        return this.imagepfad;
    }

    public void setImagepfad(String imagepfad) {
        this.imagepfad = imagepfad;
    }

    public void neuesImage(String pfad) {
        System.out.println("Es wird ein neues Image benötigt. Das Image liegt auf " + this.imagepfad + "\n");
    }

    @Override
    public String toString() {
        return super.toString() + "Rechner{" +
                "imagepfad='" + imagepfad + '\'' +
                '}';
    }
}
