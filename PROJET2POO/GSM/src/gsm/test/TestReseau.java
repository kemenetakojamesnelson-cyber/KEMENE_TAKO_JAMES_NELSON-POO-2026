package gsm.test;

import gsm.exception.BTSIntrouvableException;
import gsm.exception.BTSSatureException;
import gsm.exception.MSDejaAttacheException;
import gsm.model.BTS;
import gsm.model.Cellule;
import gsm.model.Reseau;
import gsm.model.Smartphone;
import gsm.model.Tablet;

import java.io.PrintStream;

public class TestReseau {

    // =========================================
    // ATTRIBUTS GLOBAUX
    // =========================================
    private Reseau reseau;

    private BTS bts1;
    private BTS bts2;
    private BTS bts3;

    private Smartphone ms1;
    private Smartphone ms2;
    private Tablet ms3;
    private Smartphone ms4;

    // =========================================
    // MAIN
    // =========================================
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

        System.out.println("\n=================================================");
        System.out.println("        FIN DES TESTS DU PROJET GSM");
        System.out.println("=================================================");
    }

    // =========================================
    // BANNIERE
    // =========================================
    public static void afficherBanniere() {

        System.out.println("=================================================");
        System.out.println("         PROJET 2 POO - RESEAU GSM");
        System.out.println("=================================================");
        System.out.println(" ETUDIANT : KEMENE TAKO JAMES NELSON");
        System.out.println(" FILIERE  : INGENIERIE RESEAUX ET TELECOMS");
        System.out.println("=================================================");
    }

    // =========================================
    // TEST 1 : CREATION RESEAU
    // =========================================
    public void testCreationReseau() {

        System.out.println("\n[TEST 1] CREATION DU RESEAU GSM");

        reseau = new Reseau();

        System.out.println("Réseau GSM initialisé");

        bts1 = new BTS(1,
                "Campus Nord",
                30,
                "urbain",
                20,
                3);

        bts2 = new BTS(2,
                "Campus Sud",
                25,
                "urbain",
                18,
                2);

        bts3 = new BTS(3,
                "Zone Rurale",
                50,
                "rural",
                25,
                5);

        reseau.ajouterBTS(bts1);
        reseau.ajouterBTS(bts2);
        reseau.ajouterBTS(bts3);

        System.out.println("BTS ajoutées au réseau :");

        System.out.println("- BTS "
                + bts1.getNumero()
                + " | "
                + bts1.getEmplacement());

        System.out.println("- BTS "
                + bts2.getNumero()
                + " | "
                + bts2.getEmplacement());

        System.out.println("- BTS "
                + bts3.getNumero()
                + " | "
                + bts3.getEmplacement());

        System.out.println("Nombre total BTS : "
                + reseau.getListeBTS().size());
    }

    // =========================================
    // TEST 2 : CELLULES
    // =========================================
    public void testCreationBTSCellules() {

        System.out.println("\n[TEST 2] CONFIGURATION DES CELLULES");

        Cellule c1 = new Cellule(101, 1.5, "urbain");
        Cellule c2 = new Cellule(201, 2.0, "urbain");
        Cellule c3 = new Cellule(301, 8.0, "rural");

        bts1.ajouterCellule(c1);
        bts2.ajouterCellule(c2);
        bts3.ajouterCellule(c3);

        System.out.println("Cellule "
                + c1.getNumero()
                + " ajoutée à BTS "
                + bts1.getNumero());

        System.out.println("Cellule "
                + c2.getNumero()
                + " ajoutée à BTS "
                + bts2.getNumero());

        System.out.println("Cellule "
                + c3.getNumero()
                + " ajoutée à BTS "
                + bts3.getNumero());

        System.out.println("Configuration des cellules terminée");
    }

    // =========================================
    // TEST 3 : ATTACHEMENT
    // =========================================
    public void testAttachementMS() {

        System.out.println("\n[TEST 3] ATTACHEMENT DES MS AUX BTS");

        ms1 = new Smartphone(
                "kemene",
                "Martin",
                "pass1",
                "0601",
                "IMSI001",
                "Samsung",
                "Android",
                6.5);

        ms2 = new Smartphone(
                "Bob",
                "Dupont",
                "pass2",
                "0602",
                "IMSI002",
                "Apple",
                "iOS",
                6.1);

        ms3 = new Tablet(
                "TAKO",
                "Durand",
                "pass3",
                "0603",
                "IMSI003",
                "Huawei",
                10.5,
                true);

        ms4 = new Smartphone(
                "David",
                "Leroy",
                "pass4",
                "0604",
                "IMSI004",
                "Xiaomi",
                "Android",
                6.7);

        try {

            ms1.attacherBTS(bts1);
            ms2.attacherBTS(bts1);
            ms3.attacherBTS(bts2);
            ms4.attacherBTS(bts3);

            System.out.println(ms1.getNom()
                    + " attaché à BTS "
                    + ms1.getBtsAttache().getNumero());

            System.out.println(ms2.getNom()
                    + " attaché à BTS "
                    + ms2.getBtsAttache().getNumero());

            System.out.println(ms3.getNom()
                    + " attaché à BTS "
                    + ms3.getBtsAttache().getNumero());

            System.out.println(ms4.getNom()
                    + " attaché à BTS "
                    + ms4.getBtsAttache().getNumero());

            System.out.println("Nombre utilisateurs BTS1 : "
                    + bts1.getNombreUtilisateurs());

        } catch (BTSSatureException e) {

            System.out.println("ERREUR SATURATION : "
                    + e.getMessage());

        } catch (MSDejaAttacheException e) {

            System.out.println("ERREUR ATTACHEMENT : "
                    + e.getMessage());
        }
    }

    // =========================================
    // TEST 4 : SATURATION
    // =========================================
    public void testSaturationBTS() {

        System.out.println("\n[TEST 4] TEST DE SATURATION BTS");

        Smartphone ms5 = new Smartphone(
                "Eve",
                "Moreau",
                "pass5",
                "0605",
                "IMSI005",
                "Sony",
                "Android",
                6.0);

        Smartphone ms6 = new Smartphone(
                "Frank",
                "Petit",
                "pass6",
                "0606",
                "IMSI006",
                "Nokia",
                "Android",
                5.5);

        try {

            ms5.attacherBTS(bts2);

            System.out.println(ms5.getNom()
                    + " attaché à BTS "
                    + bts2.getNumero());

            System.out.println("Utilisateurs BTS2 : "
                    + bts2.getNombreUtilisateurs());

            ms6.attacherBTS(bts2);

        } catch (BTSSatureException e) {

            System.out.println("EXCEPTION DETECTEE : "
                    + e.getMessage());

            System.out.println("Etat BTS2 : SATURÉE");

        } catch (MSDejaAttacheException e) {

            System.out.println("ERREUR : "
                    + e.getMessage());
        }
    }

    // =========================================
    // TEST 5 : APPELS
    // =========================================
    public void testAppelsMS() {

        System.out.println("\n[TEST 5] SIMULATION DES APPELS GSM");

        ms1.appeler(ms2,
                "120 sec",
                "12/05/2026 10:30");

        ms3.appeler(ms1,
                "45 sec",
                "12/05/2026 10:45");

        System.out.println("Appel envoyé par : "
                + ms1.getNom());

        System.out.println("Appel reçu par : "
                + ms2.getNom());

        System.out.println("Nombre appels reçus par "
                + ms2.getNom()
                + " : "
                + ms2.getAppelsRecus().size());

        System.out.println("Nombre appels reçus par "
                + ms1.getNom()
                + " : "
                + ms1.getAppelsRecus().size());
    }

    // =========================================
    // TEST 6 : LOCALISATION
    // =========================================
    public void testLocalisationUtilisateur() {

        System.out.println("\n[TEST 6] LOCALISATION D'UN UTILISATEUR");

        BTS bts = reseau.localiserUtilisateur("0601");

        if (bts != null) {

            System.out.println("Utilisateur trouvé");

            System.out.println("MSISDN : 0601");

            System.out.println("BTS : "
                    + bts.getNumero());

            System.out.println("Emplacement : "
                    + bts.getEmplacement());

        } else {

            System.out.println("Utilisateur introuvable");
        }
    }

    // =========================================
    // TEST 7 : RECHERCHE BTS
    // =========================================
    public void testRechercheBTS() {

        System.out.println("\n[TEST 7] RECHERCHE BTS");

        try {

            BTS b = reseau.rechercherBTS(2);

            System.out.println("BTS trouvée : "
                    + b.getNumero());

            System.out.println("Emplacement : "
                    + b.getEmplacement());

            reseau.rechercherBTS(99);

        } catch (BTSIntrouvableException e) {

            System.out.println("EXCEPTION CAPTUREE : "
                    + e.getMessage());
        }
    }

    // =========================================
    // TEST 8 : PERFORMANCES
    // =========================================
    public void testPerformancesReseau() {

        System.out.println("\n[TEST 8] PERFORMANCES DU RESEAU");

        reseau.afficherPerformances();

        System.out.println("Nombre total abonnés : "
                + reseau.calculerNombreAbonnes());
    }

    // =========================================
    // TEST 9 : DETACHEMENT
    // =========================================
    public void testDetachementMS() {

        System.out.println("\n[TEST 9] DETACHEMENT D'UN MS");

        System.out.println("Avant détachement : "
                + bts1.getNombreUtilisateurs()
                + " utilisateur(s)");

        ms1.detacher();

        System.out.println(ms1.getNom()
                + " déconnecté du réseau");

        System.out.println("Après détachement : "
                + bts1.getNombreUtilisateurs()
                + " utilisateur(s)");

        if (ms1.getBtsAttache() == null) {

            System.out.println("MS1 n'est plus attaché à une BTS");
        }
    }
}