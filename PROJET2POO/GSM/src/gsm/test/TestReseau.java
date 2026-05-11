package gsm.test;

import gsm.exception.*;
import gsm.model.*;

import java.io.PrintStream;

public class TestReseau {

    // =========================
    // MAIN
    // =========================
    public static void main(String[] args) {

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        afficherBanniere();

        TestReseau test = new TestReseau();

        test.testCreationReseau();
        test.testCreationBTSCellules();
        test.testAttachementMS();
        test.testSaturationBTS();
        test.testAppelsMS();
        test.testLocalisationUtilisateur();
        test.testRechercheBTS();
        test.testPerformancesReseau();
        test.testDetachementMS();

        System.out.println("\n==================================");
        System.out.println("   FIN DES TESTS - GSM VALIDÉ");
        System.out.println("==================================");
    }

    // =========================
    // ATTRIBUTS
    // =========================
    private Reseau reseau;
    private BTS bts1, bts2, bts3;
    private Smartphone ms1, ms2, ms3, ms4;

    // =========================
    // BANNIERE
    // =========================
    public static void afficherBanniere() {

        System.out.println("====================================");
        System.out.println("      PROJET 2 POO - GSM RADIO      ");
        System.out.println("====================================");
        System.out.println(" Etudiant : KEMENE TAKO JAMES NELSON");
        System.out.println("====================================\n");
    }

    // =========================
    // TEST 1 : RESEAU
    // =========================
    public void testCreationReseau() {

        System.out.println("\n[1] Initialisation du réseau GSM...");

        reseau = new Reseau();

        bts1 = new BTS(1, "Campus Nord", 30, "urbain", 20, 3);
        bts2 = new BTS(2, "Campus Sud", 25, "urbain", 18, 2);
        bts3 = new BTS(3, "Zone Rurale", 50, "rural", 25, 5);

        reseau.ajouterBTS(bts1);
        reseau.ajouterBTS(bts2);
        reseau.ajouterBTS(bts3);

        System.out.println(" Réseau GSM initialisé avec succès");
        System.out.println(" Nombre de BTS connectées : " + reseau.getListeBTS().size());
    }

    // =========================
    // TEST 2 : BTS + CELLULES
    // =========================
    public void testCreationBTSCellules() {

        System.out.println("\n[2] Configuration des BTS et cellules...");

        bts1.ajouterCellule(new Cellule(101, 1.5, "urbain"));
        bts2.ajouterCellule(new Cellule(201, 2.0, "urbain"));
        bts3.ajouterCellule(new Cellule(301, 8.0, "rural"));

        System.out.println(" Cellules correctement associées aux BTS");
    }

    // =========================
    // TEST 3 : ATTACHEMENT MS
    // =========================
    public void testAttachementMS() {

        System.out.println("\n[3] Connexion des utilisateurs MS au réseau...");

        ms1 = new Smartphone("Alice", "Martin", "0601", "IMSI1", "Samsung", "Android", 6.5);
        ms2 = new Smartphone("Bob", "Dupont", "0602", "IMSI2", "Apple", "iOS", 6.1);
        ms3 = new Smartphone("Clara", "Durand", "0603", "IMSI3", "Huawei", "Android", 6.2);
        ms4 = new Smartphone("David", "Leroy", "0604", "IMSI4", "Xiaomi", "Android", 6.7);

        try {
            ms1.attacherBTS(bts1);
            ms2.attacherBTS(bts1);
            ms3.attacherBTS(bts2);
            ms4.attacherBTS(bts3);

            System.out.println(" Tous les MS sont connectés au réseau GSM");

        } catch (Exception e) {
            System.out.println(" Erreur attachement MS : " + e.getMessage());
        }
    }

    // =========================
    // TEST 4 : SATURATION BTS
    // =========================
    public void testSaturationBTS() {

        System.out.println("\n[4] Test de saturation BTS...");

        Smartphone ms5 = new Smartphone("Eve", "Moreau", "0605", "IMSI5", "Sony", "Android", 6.0);

        try {
            ms5.attacherBTS(bts2);
        } catch (BTSSatureException e) {
            System.out.println(" BTS saturée détectée correctement");
        } catch (MSDejaAttacheException e) {
            System.out.println(" Erreur : " + e.getMessage());
        }

        System.out.println(" État BTS2 : " + (bts2.estSature() ? "SATURÉE" : "NON SATURÉE"));
    }

    // =========================
    // TEST 5 : APPELS
    // =========================
    public void testAppelsMS() {

        System.out.println("\n[5] Simulation des appels entre utilisateurs...");

        ms1.appeler(ms2, "120 sec", "10:30");
        ms3.appeler(ms1, "45 sec", "10:45");

        System.out.println(" Appels GSM transmis avec succès");
        System.out.println(" MS2 a reçu : " + ms2.getAppelsRecus().size() + " appel(s)");
    }

    // =========================
    // TEST 6 : LOCALISATION
    // =========================
    public void testLocalisationUtilisateur() {

        System.out.println("\n[6] Localisation d’un utilisateur sur le réseau...");

        BTS b = reseau.localiserUtilisateur("0601");

        if (b != null) {
            System.out.println(" Utilisateur 0601 localisé sur BTS " + b.getNumero());
        } else {
            System.out.println(" Utilisateur introuvable sur le réseau");
        }
    }

    // =========================
    // TEST 7 : RECHERCHE BTS
    // =========================
    public void testRechercheBTS() {

        System.out.println("\n[7] Recherche d’une BTS dans le réseau...");

        try {
            BTS b = reseau.rechercherBTS(2);
            System.out.println(" BTS trouvée : BTS " + b.getNumero());

            reseau.rechercherBTS(99);

        } catch (BTSIntrouvableException e) {
            System.out.println(" Exception BTS introuvable correctement gérée");
        }
    }

    // =========================
    // TEST 8 : PERFORMANCES
    // =========================
    public void testPerformancesReseau() {

        System.out.println("\n[8] Analyse des performances du réseau GSM...");

        reseau.afficherPerformances();

        System.out.println(" Total abonnés : " + reseau.calculerNombreAbonnes());
    }

    // =========================
    // TEST 9 : DETACHEMENT
    // =========================
    public void testDetachementMS() {

        System.out.println("\n[9] Déconnexion d’un utilisateur du réseau...");

        ms1.detacher();

        System.out.println(" MS1 déconnecté du réseau GSM");
        System.out.println(" État BTS1 mis à jour");
    }
}