package gsm.model;

public class Tablet extends MS {

    private String marque;
    private double ecran;
    private boolean wifi;

    public Tablet(String nom,
                  String prenom,
                  String motDePasse,
                  String msisdn,
                  String imsi,
                  String marque,
                  double ecran,
                  boolean wifi) {

        super(nom, prenom, motDePasse, msisdn, imsi);

        this.marque = marque;
        this.ecran = ecran;
        this.wifi = wifi;
    }

    @Override
    public void afficherCaracteristiques() {

        System.out.println("===== TABLET =====");

        System.out.println("Nom : " + nom);

        System.out.println("Prénom : " + prenom);

        System.out.println("MSISDN : " + msisdn);

        System.out.println("Marque : " + marque);

        System.out.println("Ecran : " + ecran);

        System.out.println("WiFi : "
                + (wifi ? "Oui" : "Non"));
    }
}