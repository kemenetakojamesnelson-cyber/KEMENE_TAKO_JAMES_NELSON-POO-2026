package gsm.model;

import java.util.ArrayList;
import java.util.List;

public class BTS {

    private int numero;
    private String emplacement;
    private double hauteur;
    private String typeMilieu;
    private double puissanceEmission;
    private int maxUtilisateurs;

    private List<MS> utilisateurs;
    private List<Cellule> cellules;

    public BTS(int numero, String emplacement, double hauteur,
               String typeMilieu, double puissanceEmission,
               int maxUtilisateurs) {

        this.numero = numero;
        this.emplacement = emplacement;
        this.hauteur = hauteur;
        this.typeMilieu = typeMilieu;
        this.puissanceEmission = puissanceEmission;
        this.maxUtilisateurs = maxUtilisateurs;

        this.utilisateurs = new ArrayList<>();
        this.cellules = new ArrayList<>();
    }

    // =========================
    // AJOUT MS
    // =========================
    public void ajouterMS(MS ms) {
        if (!estSature() && !utilisateurs.contains(ms)) {
            utilisateurs.add(ms);
        } else {
            System.out.println(" BTS saturée ou MS déjà présent");
        }
    }

    // =========================
    // SUPPRESSION MS
    // =========================
    public void supprimerMS(String msisdn) {
        utilisateurs.removeIf(ms ->
                ms != null && ms.getMsisdn().equals(msisdn));
    }

    // =========================
    // RECHERCHE MS
    // =========================
    public MS rechercherMS(String msisdn) {
        for (MS ms : utilisateurs) {
            if (ms != null && ms.getMsisdn().equals(msisdn)) {
                return ms;
            }
        }
        return null;
    }

    // =========================
    // SATURATION
    // =========================
    public boolean estSature() {
        return utilisateurs.size() >= maxUtilisateurs;
    }

    public int getNombreUtilisateurs() {
        return utilisateurs.size();
    }

    // =========================
    // CELLULES
    // =========================
    public void ajouterCellule(Cellule c) {
        cellules.add(c);
    }

    // =========================
    // GETTERS
    // =========================
    public String getTypeMilieu() { return typeMilieu; }
    public int getNumero() { return numero; }
    public String getEmplacement() { return emplacement; }

    public List<MS> getUtilisateurs() {
        return utilisateurs;
    }

    // =========================
    // AFFICHAGE
    // =========================
    public void afficherInfos() {
        System.out.println("BTS " + numero +
                " | " + emplacement +
                " | Utilisateurs : " + utilisateurs.size());
    }
}