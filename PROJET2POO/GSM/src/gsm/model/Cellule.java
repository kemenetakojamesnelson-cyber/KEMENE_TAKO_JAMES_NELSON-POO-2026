package gsm.model;

public class Cellule {

    private int numero;
    private double rayon;
    private String typeMilieu;

    public Cellule(int numero, double rayon, String typeMilieu) {
        this.numero = numero;
        this.rayon = (rayon > 0) ? rayon : 1;
        this.typeMilieu = typeMilieu.toLowerCase().trim();
    }

    public int getNumero() { return numero; }
    public String getTypeMilieu() { return typeMilieu; }

    public double calculerSurface() {
        return Math.PI * rayon * rayon;
    }

    public void afficher() {
        System.out.println("Cellule " + numero +
                " | Rayon : " + rayon +
                " km | Milieu : " + typeMilieu +
                " | Surface : " + calculerSurface());
    }
}