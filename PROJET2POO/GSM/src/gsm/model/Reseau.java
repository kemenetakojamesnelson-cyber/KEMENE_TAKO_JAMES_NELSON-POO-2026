package gsm.model;

import gsm.exception.BTSIntrouvableException;
import gsm.interfaces.IReseau;

import java.util.ArrayList;

public class Reseau implements IReseau {

    private String nom;

    private ArrayList<BTS> btsList;

    // =========================
    // CONSTRUCTEUR
    // =========================
    public Reseau() {

        this.nom = "RESEAU GSM";

        this.btsList = new ArrayList<>();
    }

    public Reseau(String nom) {

        this.nom = nom;

        this.btsList = new ArrayList<>();
    }

    // =========================
    // AJOUT BTS
    // =========================
    public void ajouterBTS(BTS bts) {

        btsList.add(bts);
    }

    // =========================
    // RECHERCHE BTS
    // =========================
    public BTS rechercherBTS(int numero)
            throws BTSIntrouvableException {

        for (BTS b : btsList) {

            if (b.getNumero() == numero) {

                return b;
            }
        }

        throw new BTSIntrouvableException(
                "BTS introuvable");
    }

    // =========================
    // LOCALISATION UTILISATEUR
    // =========================
    public BTS localiserUtilisateur(String msisdn) {

        for (BTS bts : btsList) {

            if (bts.rechercherMS(msisdn) != null) {

                return bts;
            }
        }

        return null;
    }

    // =========================
    // NOMBRE ABONNES
    // =========================
    @Override
    public int calculerNombreAbonnes() {

        int total = 0;

        for (BTS b : btsList) {

            total += b.getNombreUtilisateurs();
        }

        return total;
    }

    // =========================
    // AFFICHER PERFORMANCES
    // =========================
    @Override
    public void afficherPerformances() {

        System.out.println("\n====================================");
        System.out.println("      PERFORMANCES DU RESEAU GSM");
        System.out.println("====================================");

        System.out.println("Nom du réseau        : "
                + nom);

        System.out.println("Nombre total BTS     : "
                + btsList.size());

        System.out.println("Nombre total abonnés : "
                + calculerNombreAbonnes());

        int urbain = 0;
        int rural = 0;

        for (BTS b : btsList) {

            if (b.getTypeMilieu()
                    .equalsIgnoreCase("urbain")) {

                urbain++;

            } else {

                rural++;
            }
        }

        System.out.println("BTS en zone urbaine  : "
                + urbain);

        System.out.println("BTS en zone rurale   : "
                + rural);

        System.out.println("====================================");
    }

    // =========================
    // GETTERS
    // =========================
    public ArrayList<BTS> getListeBTS() {

        return btsList;
    }

    public String getNom() {

        return nom;
    }
}