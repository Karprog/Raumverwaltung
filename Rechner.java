package database.implementation;

/**
 * Created by pfleiderer.stefanie on 17.11.2017.
 */
public class Rechner extends Hardware {

    private String imagepfad;

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

    public String getImagepfad() {
        return this.imagepfad;
    }

    public void neuesImage(String pfad) {
        System.out.println("Es wird ein neues Image benötigt. Das Image liegt auf " + this.imagepfad + "\n");
    }

    @Override
    public String toString() {
        return super.toString() +
                "Imagepfad: " + this.imagepfad + "\n";
    }
}
