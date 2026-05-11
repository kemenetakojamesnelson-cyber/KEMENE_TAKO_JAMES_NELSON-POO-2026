package gsm.model;

import gsm.exception.BTSIntrouvableException;
import gsm.interfaces.IReseau;

import java.util.ArrayList;

public class Reseau implements IReseau {

    private String nom;
    private ArrayList<BTS> btsList;

    // constructeur ajouté (IMPORTANT)
    public Reseau() {
        this.nom = "RESEAU GSM";
        this.btsList = new ArrayList<>();
    }

    public Reseau(String nom) {
        this.nom = nom;
        this.btsList = new ArrayList<>();
    }

    public void ajouterBTS(BTS bts) {
        btsList.add(bts);
    }

    public BTS rechercherBTS(int id)
            throws BTSIntrouvableException {

        for (BTS b : btsList) {
            if (b.getNumero() == id) return b;
        }

        throw new BTSIntrouvableException("BTS introuvable");
    }

    @Override
    public int calculerNombreAbonnes() {
        int total = 0;
        for (BTS b : btsList) {
            total += b.getNombreUtilisateurs();
        }
        return total;
    }

    @Override
    public void afficherPerformances() {
        System.out.println("Réseau: " + nom);
        System.out.println("BTS: " + btsList.size());
        System.out.println("Abonnés: " + calculerNombreAbonnes());
    }

    // ============================
    // CORRIGÉ (IMPORTANT)
    // ============================
    public BTS localiserUtilisateur(String msisdn) {

        for (BTS bts : btsList) {
            if (bts.rechercherMS(msisdn) != null) {
                return bts;
            }
        }
        return null;
    }

    public ArrayList<BTS> getListeBTS() {
        return btsList;
    }
}