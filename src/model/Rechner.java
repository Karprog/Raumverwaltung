package model;

public class Rechner extends Hardware {


    private String imagepfad;

    public Rechner() {
        super("Rechner");
    }

    public void neuesImage(String pfad) {
        System.out.println("Es wird ein neues Image benötigt. Das Image liegt auf: " + pfad);
    }

    public String getImagepfad() {
        return imagepfad;
    }

    public void setImagepfad(String imagepfad) {
        this.imagepfad = imagepfad;
    }

    @Override
    public String toString() {
        return super.toString() + "Rechner{" +
                "imagepfad='" + imagepfad + '\'' +
                '}';
    }
}
