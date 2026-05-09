package ipplanmanager;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== TP2 : Encapsulation KEMENE TAKO JAMES=====");

        // =========================
        // Données de base
        // =========================
        AdresseIP ip1 = new AdresseIP("192.168.1.1");
        AdresseIP ip2 = new AdresseIP("");
        AdresseIP ip3 = new AdresseIP(null);

        InterfaceReseau interface1 = new InterfaceReseau("eth0", ip1);
        InterfaceReseau interface2 = new InterfaceReseau("", ip2);

        interface1.activer();

        Equipement routeur = new Equipement("R1_EDGE", "Routeur", interface1);
        Equipement serveur = new Equipement("", "", interface2);

        ReseauIP reseau1 = new ReseauIP("192.168.1.0", 24, "Reseau principal");
        ReseauIP reseau2 = new ReseauIP("", 55, "");

        // =========================
        // Affichage réseaux
        // =========================
        System.out.println("\n----- Reseau 1 -----");
        reseau1.afficher();

        System.out.println("\n----- Reseau 2 -----");
        reseau2.afficher();

        // =========================
        // Affichage équipements
        // =========================
        System.out.println("\n----- Equipement 1 -----");
        routeur.afficher();

        System.out.println("\n----- Equipement 2 -----");
        serveur.afficher();

        // =========================
        // PARTIE 14 : Adresse locale
        // =========================
        System.out.println("\n===== TEST Adresse Locale() =====");

        System.out.println(ip1.getValeur() + " -> " + ip1.estAdresseLocale());
        System.out.println(ip2.getValeur() + " -> " + ip2.estAdresseLocale());
        System.out.println(ip3.getValeur() + " -> " + ip3.estAdresseLocale());

        // =========================
        // PARTIE 13 : Tests supplémentaires
        // =========================
        System.out.println("\n===== PARTIE 13 : TESTS =====");

        Equipement sw1 = new Equipement("SW1", "Switch", interface1);
        Equipement sw2 = new Equipement("SW2", "Switch", interface2);
        Equipement fw1 = new Equipement("FW1", "Firewall", interface1);

        sw1.afficher();
        sw2.afficher();
        fw1.afficher();

        // Cas invalides
        System.out.println("\n===== CAS INVALIDES =====");

        AdresseIP ipInvalid = new AdresseIP("");
        InterfaceReseau ifaceInvalid = new InterfaceReseau("", ipInvalid);
        Equipement equipInvalid = new Equipement("", "", ifaceInvalid);
        ReseauIP reseauInvalid = new ReseauIP("", 99, "");

        equipInvalid.afficher();
        reseauInvalid.afficher();

        // =========================
        // TEST SETTERS
        // =========================
        System.out.println("\n===== TEST SETTERS =====");

        ip1.setValeur("10.0.0.1");
        interface1.setNom("eth1");
        routeur.setNom("ROUTEUR_CORE");

        routeur.afficher();
    }
}