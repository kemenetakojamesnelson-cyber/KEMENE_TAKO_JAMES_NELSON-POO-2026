# TP10 - Application finale IPPlan-Manager

## Objectif
Assembler toutes les fonctionnalités développées dans les TPs précédents
afin de produire une application console complète de planification
d'adressage IP. L'application permet de saisir les exigences d'un projet
réseau, générer automatiquement un plan d'adressage VLSM, créer les VLANs
correspondants, valider le plan, afficher des recommandations, sauvegarder
les résultats et produire un rapport technique.


## Fonctionnalités réalisées
- Saisie interactive des besoins réseau via la console
- Chargement des besoins depuis un fichier CSV
- Génération automatique du plan d'adressage VLSM
- Création automatique des VLANs numérotés
- Validation de l'adresse IP de départ
- Détection des chevauchements de réseaux
- Génération de recommandations techniques personnalisées
- Sauvegarde des résultats en fichiers CSV
- Génération d'un rapport technique complet
- Menu interactif avec retour automatique après chaque traitement


## Organisation du projet

TP10_IPPlanManager_ApplicationFinale/
├── src/
│   └── ipplanmanager/
│       ├── model/         → Objets métier
│       ├── service/       → Logique métier
│       ├── repository/    → Lecture et écriture de fichiers
│       ├── exception/     → Exceptions personnalisées
│       ├── console/       → Interaction avec l'utilisateur
│       └── main/          → Classe de lancement
├── exports/               → Fichiers générés par l'application
├── build.xml
└── README.md

### Détail des packages

**ipplanmanager.model** : contient les objets métier de l'application.
- `BesoinReseau` : représente un besoin réseau avec un nom et un nombre d'hôtes
- `ResultatVLSM` : représente un sous-réseau calculé avec adresse, CIDR, masque, capacité et marge
- `VLAN` : représente un VLAN avec un numéro, un nom et une description
- `Recommandation` : représente une recommandation technique avec priorité, titre et message

**ipplanmanager.service** : contient toute la logique de traitement.
- `CalculateurReseau` : calculs IP (conversion, CIDR, masque, taille de bloc)
- `MoteurVLSM` : génère le plan d'adressage VLSM trié par taille décroissante
- `GestionnaireVLAN` : gère la liste des VLANs et détecte les conflits
- `ValidateurPlanAdressage` : valide l'adresse IP et détecte les chevauchements
- `MoteurRecommandation` : applique les règles et génère les recommandations
- `RecommandationWifiInvite` : règle pour les VLANs WiFi
- `RecommandationServeurs` : règle pour les VLANs serveurs
- `RecommandationGrandVLAN` : règle pour les grands VLANs
- `InterfaceRegleRecommandation` : interface commune pour toutes les règles
- `ApplicationIPPlanManager` : orchestrateur principal de l'application
- `RapportService` : génère le rapport technique en fichier texte

**ipplanmanager.repository** : contient la lecture et l'écriture de fichiers.
- `BesoinRepository` : charge les besoins depuis un fichier CSV
- `FichierPlanRepository` : sauvegarde le plan, les VLANs et les recommandations

**ipplanmanager.exception** : contient les exceptions personnalisées.
- `AdresseIPInvalideException` : adresse IP incorrecte
- `ConflitVLANException` : numéro de VLAN déjà utilisé
- `ChevauchementReseauException` : deux sous-réseaux se chevauchent

**ipplanmanager.console** : contient l'interaction avec l'utilisateur.
- `ConsoleService` : saisie de texte, d'entiers, des besoins et affichage du menu

**ipplanmanager.main** : contient le point d'entrée de l'application.
- `Main` : lance l'application en créant une instance d'ApplicationIPPlanManager


## Scénarios testés

### Scénario 1 — Campus IRT
- Adresse de départ : 10.10.0.0
- Besoins : ETUDIANTS (500), WIFI_INVITES (200), ENSEIGNANTS (120),
  LABORATOIRES (60), SERVEURS (30)
- Résultats :
  - ETUDIANTS    -> 10.10.0.0/23   | Masque : 255.255.254.0   | Marge : 10
  - WIFI_INVITES -> 10.10.2.0/24   | Masque : 255.255.255.0   | Marge : 54
  - ENSEIGNANTS  -> 10.10.3.0/25   | Masque : 255.255.255.128 | Marge : 6
  - LABORATOIRES -> 10.10.3.128/26 | Masque : 255.255.255.192 | Marge : 2
  - SERVEURS     -> 10.10.3.192/27 | Masque : 255.255.255.224 | Marge : 0

### Scénario 2 — PME
- Adresse de départ : 192.168.1.0
- Besoins : WIFI_INVITES (80), ADMINISTRATION (50), VOIP (40),
  COMPTABILITE (20), SERVEURS (15)
- Résultats :
  - WIFI_INVITES   -> 192.168.1.0/25   | Masque : 255.255.255.128 | Marge : 46
  - ADMINISTRATION -> 192.168.1.128/26 | Masque : 255.255.255.192 | Marge : 12
  - VOIP           -> 192.168.1.192/26 | Masque : 255.255.255.192 | Marge : 22
  - COMPTABILITE   -> 192.168.2.0/27   | Masque : 255.255.255.224 | Marge : 10
  - SERVEURS       -> 192.168.2.32/27  | Masque : 255.255.255.224 | Marge : 15

### Scénario 3 — Entreprise Multi-Services
- Adresse de départ : 172.16.0.0
- Besoins : TECHNIQUE (120), INVITES (100), CAMERAS (60),
  SUPPORT (35), DIRECTION (25)
- Résultats :
  - TECHNIQUE -> 172.16.0.0/25   | Masque : 255.255.255.128 | Marge : 6
  - INVITES   -> 172.16.0.128/25 | Masque : 255.255.255.128 | Marge : 26
  - CAMERAS   -> 172.16.1.0/26   | Masque : 255.255.255.192 | Marge : 2
  - SUPPORT   -> 172.16.1.64/26  | Masque : 255.255.255.192 | Marge : 27
  - DIRECTION -> 172.16.1.128/27 | Masque : 255.255.255.224 | Marge : 5


## Fichiers générés

Tous les fichiers sont créés automatiquement dans le dossier `exports/`.

### Scénario 1 — Campus IRT
- `Campus_IRT_plan.csv` : plan d'adressage VLSM complet
- `Campus_IRT_vlans.csv` : liste des VLANs générés
- `Campus_IRT_recommandations.txt` : recommandations techniques
- `Campus_IRT_rapport.txt` : rapport technique complet

### Scénario 2 — PME
- `PME_plan.csv`
- `PME_vlans.csv`
- `PME_recommandations.txt`
- `PME_rapport.txt`

### Scénario 3 — Entreprise Multi-Services
- `Entreprise_Multi_Services_plan.csv`
- `Entreprise_Multi_Services_vlans.csv`
- `Entreprise_Multi_Services_recommandations.txt`
- `Entreprise_Multi_Services_rapport.txt`



## Difficultés rencontrées

- Organisation des packages en architecture professionnelle
- Gestion des imports après déplacement des classes
- Gestion des exceptions personnalisées
- Génération correcte des sous-réseaux VLSM
- Lecture et écriture de fichiers CSV
- Synchronisation entre services (VLSM, VLAN, recommandations)




## Réponses aux questions

**1. Pourquoi le TP10 représente-t-il une application plus complète
que les TPs précédents ?**
Les TPs précédents testaient chaque fonctionnalité de manière isolée :
une classe, un calcul, une sauvegarde. Le TP10 assemble toutes ces
fonctionnalités dans un flux cohérent et continu. L'utilisateur interagit
avec une vraie application qui va du début à la fin : saisie, calcul,
validation, recommandations, sauvegarde et rapport. C'est la différence
entre des pièces détachées et une machine qui fonctionne.

**2. Quel est le rôle de la classe ApplicationIPPlanManager ?**
C'est l'orchestrateur central de l'application. Elle coordonne tous les
composants : elle reçoit les données de ConsoleService, les transmet à
MoteurVLSM, passe les résultats à GestionnaireVLAN, appelle le
validateur, déclenche le moteur de recommandations, affiche les résultats
et demande la sauvegarde. Sans elle, chaque classe resterait isolée et
inutilisable dans un contexte global.

**3. Pourquoi la classe Main doit-elle rester courte ?**
Main est uniquement le point d'entrée du programme. Si toute la logique
y était placée, le code deviendrait illisible et impossible à maintenir.
En déléguant tout à ApplicationIPPlanManager, on respecte le principe
de responsabilité unique : chaque classe fait une seule chose. Main
lance, ApplicationIPPlanManager gère.

**4. Pourquoi est-il important de séparer les packages model, service,
repository, exception, console et main ?**
Cette séparation reflète l'architecture en couches d'un vrai logiciel
professionnel. Chaque package a une responsabilité précise et ne mélange
pas les préoccupations. Cela facilite la maintenance, les tests, la
réutilisation et la compréhension du code. Si on veut changer la façon
de sauvegarder les fichiers, on touche uniquement repository sans
affecter le reste.

**5. Pourquoi la saisie utilisateur est-elle placée dans ConsoleService ?**
Pour séparer l'interface utilisateur de la logique métier. Si demain on
veut remplacer la console par une interface graphique, on remplace
uniquement ConsoleService sans toucher à ApplicationIPPlanManager ni
aux autres classes. C'est le principe d'isolation des couches.

**6. Pourquoi faut-il valider l'adresse réseau avant de générer le
plan VLSM ?**
Si l'adresse est invalide, tous les calculs qui suivent produiront des
résultats incorrects ou feront planter l'application. Valider en premier
permet d'arrêter le traitement immédiatement et d'informer l'utilisateur
avec un message clair, sans gaspiller de ressources sur des calculs
basés sur une donnée erronée.

**7. Pourquoi le moteur de recommandations est-il exécuté après la
génération des VLANs ?**
Les recommandations sont basées sur les caractéristiques des VLANs :
leur nom, leur taille, leur type. Sans VLANs créés au préalable, le
moteur de recommandations n'aurait rien à analyser. L'ordre est donc
logique : d'abord calculer les réseaux, créer les VLANs, puis analyser
ces VLANs pour produire des conseils pertinents.

**8. Pourquoi la sauvegarde des résultats rend-elle l'application
réellement exploitable ?**
Un résultat affiché à l'écran disparaît dès que la console se ferme.
La sauvegarde en fichiers CSV et en rapport texte permet de conserver
les résultats, de les partager avec une équipe, de les intégrer dans
un outil tiers ou de les archiver pour un projet réseau réel. Sans
sauvegarde, l'application est un simple affichage sans valeur durable.

**9. Pourquoi le rapport technique est-il important dans un contexte
professionnel ?**
Dans un contexte professionnel, les décisions réseau doivent être
documentées et communicables. Le rapport technique rassemble en un seul
document les besoins, le plan d'adressage, les VLANs et les
recommandations. Il peut être remis à un client, un chef de projet ou
une équipe d'infrastructure sans qu'ils aient besoin d'accéder à
l'application elle-même.

**10. Quelles améliorations pourraient être ajoutées à IPPlan-Manager
dans une version future ?**
- Interface graphique avec JavaFX pour remplacer la console
- Support de l'IPv6 en plus de l'IPv4
- Export du rapport en format PDF
- Authentification utilisateur avec gestion de plusieurs projets
- Visualisation graphique du plan d'adressage sous forme de tableau
- Détection automatique des sous-réseaux qui se chevauchent de façon
  plus avancée
- Connexion à une base de données pour stocker les projets
- Génération automatique des configurations pour routeurs Cisco



## Conclusion personnelle

Ce projet m'a permis de comprendre concrètement que la Programmation
Orientée Objet ne se résume pas à créer des classes avec des attributs
et des méthodes. Elle permet d'organiser une application entière autour
d'objets ayant des responsabilités claires et séparées.

Chaque TP construisait une brique. Le TP10 a montré comment assembler
toutes ces briques pour produire quelque chose de fonctionnel et
professionnel. La séparation en packages, l'utilisation des exceptions,
la délégation des responsabilités entre les classes : tout cela donne
un code lisible, maintenable et extensible.

Appliquée aux réseaux, la POO permet de modéliser des concepts réels
comme les besoins réseau, les sous-réseaux, les VLANs et les
recommandations, et de les faire interagir de façon structurée. C'est
exactement ce que font les outils professionnels de gestion réseau.
