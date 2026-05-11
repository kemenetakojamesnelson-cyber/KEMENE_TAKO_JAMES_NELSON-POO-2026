package gsm.model;

import gsm.exception.BTSSatureException;
import gsm.exception.MSDejaAttacheException;

import java.util.ArrayList;
import java.util.List;

public abstract class MS {

    protected String nom;
    protected String prenom;
    protected String msisdn;
    protected String imsi;
    protected BTS btsAttache;
    protected ArrayList<Appel> appelsRecus;

    public MS(String nom, String prenom,
              String msisdn, String imsi) {

        this.nom = nom;
        this.prenom = prenom;
        this.msisdn = msisdn;
        this.imsi = imsi;
        this.appelsRecus = new ArrayList<>();
    }

    // =========================
    // ATTACHEMENT BTS
    // =========================
    public void attacherBTS(BTS bts)
            throws BTSSatureException, MSDejaAttacheException {

        if (this.btsAttache != null) {
            throw new MSDejaAttacheException("Déjà attaché à une BTS");
        }

        if (bts.estSature()) {
            throw new BTSSatureException("BTS saturée");
        }

        bts.ajouterMS(this);
        this.btsAttache = bts;
    }

    // =========================
    // DETACHEMENT
    // =========================
    public void detacher() {
        if (btsAttache != null) {
            btsAttache.supprimerMS(msisdn);
            btsAttache = null;
        }
    }

    // =========================
    // APPEL
    // =========================
    public void appeler(MS dest, String duree, String date) {
        Appel a = new Appel(msisdn, nom + " " + prenom, duree, date);
        dest.recevoirAppel(a);
    }

    // =========================
    // RECEPTION APPEL
    // =========================
    public void recevoirAppel(Appel a) {
        appelsRecus.add(a);
    }

    // =========================
    // GETTERS
    // =========================
    public String getMsisdn() { return msisdn; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public BTS getBtsAttache() { return btsAttache; }

    public List<Appel> getAppelsRecus() {
        return appelsRecus;
    }

    // =========================
    // POLYMORPHISME
    // =========================
    public abstract void afficherCaracteristiques();
}