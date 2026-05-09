# TP1 - IPPlan-Manager

# Realiser par : KEMENE TAKO JAMES NELSON

## Objectif du TP

Ce TP permet de découvrir les premières classes Java du projet
IPPlan-Manager.

L’objectif est de comprendre les bases de la Programmation
Orientée Objet (POO) en représentant des éléments d’un réseau
informatique sous forme d’objets Java.



## Classes créées

Les classes suivantes ont été créées :

- AdresseIP
- ReseauIP
- InterfaceReseau
- Equipement
- Main



## Travail réalisé

Le programme développé permet de :

- créer des adresses IP ;
- créer des réseaux IP ;
- créer des interfaces réseau ;
- créer des équipements réseau ;
- afficher les informations des équipements ;
- gérer des interfaces actives et inactives ;
- gérer une interface sans adresse IP.

Les équipements suivants ont été créés :

- un routeur ;
- un serveur ;
- un switch ;
- un point d’accès WiFi ;
- deux postes clients.

Deux réseaux IP ont également été créés :

- 192.168.1.0/24
- 192.168.2.0/24

Des tests d’affichage ont été effectués dans la console Java.

---

## Réponses aux questions

### 1. Pourquoi une adresse IP a-t-elle été représentée par une classe ?

Une adresse IP a été représentée par une classe afin de mieux
organiser le programme et permettre l’ajout futur de nouvelles
fonctionnalités comme la validation des adresses IP.



### 2. Quelle est la différence entre une classe et un objet ?

Une classe est un modèle de création tandis qu’un objet est une
instance créée à partir de cette classe.



### 3. Quel est le rôle du constructeur dans une classe Java ?

Le constructeur permet d’initialiser les attributs d’un objet au
moment de sa création.



### 4. Pourquoi la classe InterfaceReseau contient-elle un objet de type AdresseIP ?

Parce qu’une interface réseau possède une adresse IP. Cela permet
de représenter correctement la réalité d’un réseau informatique.



### 5. Pourquoi la classe Equipement contient-elle un objet de type InterfaceReseau ?

Parce qu’un équipement réseau communique grâce à une interface
réseau.



### 6. Quelle est la limite actuelle de la classe Equipement dans ce TP ?

La classe Equipement ne possède qu’une seule interface réseau.
Dans un vrai réseau, un équipement peut avoir plusieurs interfaces.



### 7. Pourquoi cette première version n’est-elle pas encore suffisante pour produire automatiquement un plan d’adressage IP ?

Parce que cette version ne réalise pas encore les calculs de
sous-réseaux, les calculs CIDR, les adresses broadcast et les
plages d’adresses IP.
