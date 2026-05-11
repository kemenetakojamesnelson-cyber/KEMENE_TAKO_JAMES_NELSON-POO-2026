package gsm.model;

public class Smartphone extends MS {

    private String marque;
    private String os;
    private double ecran;

    public Smartphone(String nom, String prenom,
                      String msisdn, String imsi,
                      String marque, String os, double ecran) {

        super(nom, prenom, msisdn, imsi);
        this.marque = marque;
        this.os = os;
        this.ecran = ecran;
    }

    @Override
    public void afficherCaracteristiques() {
        System.out.println("=== SMARTPHONE ===");
        System.out.println("Nom: " + nom + " " + prenom);
        System.out.println("MSISDN: " + msisdn);
        System.out.println("IMSI: " + imsi);
        System.out.println("Marque: " + marque);
        System.out.println("OS: " + os);
        System.out.println("Écran: " + ecran + " pouces");
        System.out.println("BTS: " +
                (btsAttache != null ? btsAttache.getNumero() : "Aucune"));
    }
}