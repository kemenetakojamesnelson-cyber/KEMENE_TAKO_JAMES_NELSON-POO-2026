package ipplanmanager;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== IPPlan-Manager : TP1 KEMENE TAKO JAMES NELSON");
        System.out.println("Decouverte des premieres classes du projet");
        System.out.println();

        // =========================
        // CREATION DES ADRESSES IP
        // =========================

        AdresseIP ipRouteur =
                new AdresseIP("192.168.1.1");

        AdresseIP ipServeur =
                new AdresseIP("192.168.1.10");

        AdresseIP ipClient =
                new AdresseIP("192.168.1.50");

        AdresseIP ipSwitch =
                new AdresseIP("192.168.1.2");

        AdresseIP ipWifi =
                new AdresseIP("192.168.2.1");

        AdresseIP ipClient2 =
                new AdresseIP("192.168.2.20");


        // =========================
        // CREATION DES INTERFACES
        // =========================

        InterfaceReseau interfaceRouteur =
                new InterfaceReseau("eth0", ipRouteur);

        InterfaceReseau interfaceServeur =
                new InterfaceReseau("eth0", ipServeur);

        InterfaceReseau interfaceClient =
                new InterfaceReseau("wlan0", ipClient);

        InterfaceReseau interfaceSwitch =
                new InterfaceReseau("eth1", ipSwitch);

        InterfaceReseau interfaceWifi =
                new InterfaceReseau("wlan1", ipWifi);

        InterfaceReseau interfaceClient2 =
                new InterfaceReseau("eth0", ipClient2);


        // =========================
        // INTERFACE SANS IP
        // =========================

        InterfaceReseau interfaceSansIP =
                new InterfaceReseau("eth2", null);


        // =========================
        // ACTIVATION DES INTERFACES
        // =========================

        interfaceRouteur.activer();
        interfaceServeur.activer();
        interfaceSwitch.activer();
        interfaceWifi.activer();

        // Interface client inactive volontairement
        // interfaceClient.activer();

        // Client 2 activé
        interfaceClient2.activer();


        // =========================
        // CREATION DES EQUIPEMENTS
        // =========================

        Equipement routeur =
                new Equipement(
                        "R1_EDGE",
                        "Routeur",
                        interfaceRouteur
                );

        Equipement serveur =
                new Equipement(
                        "SRV_DNS",
                        "Serveur",
                        interfaceServeur
                );

        Equipement client =
                new Equipement(
                        "PC_ADMIN_KEMENE",
                        "Poste client",
                        interfaceClient
                );

        Equipement switchReseau =
                new Equipement(
                        "SW_CORE",
                        "Switch",
                        interfaceSwitch
                );

        Equipement pointWifi =
                new Equipement(
                        "AP_WIFI",
                        "Point d'acces WiFi",
                        interfaceWifi
                );

        Equipement client2 =
                new Equipement(
                        "PC_DIRECTION",
                        "Poste client",
                        interfaceClient2
                );

        Equipement equipementSansIP =
                new Equipement(
                        "TEST_ETH",
                        "Equipement Test",
                        interfaceSansIP
                );


        // =========================
        // CREATION DES RESEAUX
        // =========================

        ReseauIP reseauPrincipal =
                new ReseauIP(
                        "192.168.1.0",
                        24,
                        "Reseau principal du laboratoire"
                );

        ReseauIP reseauWifi =
                new ReseauIP(
                        "192.168.2.0",
                        24,
                        "Reseau WiFi des utilisateurs"
                );


        // =========================
        // AFFICHAGE DES RESEAUX
        // =========================

        System.out.println("----- Reseaux crees -----");

        reseauPrincipal.afficher();

        System.out.println();

        reseauWifi.afficher();


        // =========================
        // AFFICHAGE DES EQUIPEMENTS
        // =========================

        System.out.println();
        System.out.println("----- Equipements crees -----");

        System.out.println();
        routeur.afficher();

        System.out.println();
        serveur.afficher();

        System.out.println();
        client.afficher();

        System.out.println();
        switchReseau.afficher();

        System.out.println();
        pointWifi.afficher();

        System.out.println();
        client2.afficher();

        System.out.println();
        equipementSansIP.afficher();
    }
}