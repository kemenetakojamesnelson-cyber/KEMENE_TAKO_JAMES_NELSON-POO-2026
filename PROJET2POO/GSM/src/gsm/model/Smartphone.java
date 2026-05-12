package gsm.model;

public class Smartphone extends MS {

    private String marque;
    private String os;
    private double ecran;

    public Smartphone(String nom,
                      String prenom,
                      String motDePasse,
                      String msisdn,
                      String imsi,
                      String marque,
                      String os,
                      double ecran) {

        super(nom, prenom, motDePasse, msisdn, imsi);

        this.marque = marque;
        this.os = os;
        this.ecran = ecran;
    }

    @Override
    public void afficherCaracteristiques() {

        System.out.println("===== SMARTPHONE =====");

        System.out.println("Nom : " + nom);

        System.out.println("Prénom : " + prenom);

        System.out.println("MSISDN : " + msisdn);

        System.out.println("IMSI : " + imsi);

        System.out.println("Marque : " + marque);

        System.out.println("OS : " + os);

        System.out.println("Taille écran : "
                + ecran + " pouces");

        if (btsAttache != null) {

            System.out.println("BTS : "
                    + btsAttache.getNumero());

        } else {

            System.out.println("Non attaché à une BTS");
        }
    }
}