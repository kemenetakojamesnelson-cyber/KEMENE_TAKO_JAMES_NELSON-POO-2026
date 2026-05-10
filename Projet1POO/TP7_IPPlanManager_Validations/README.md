# TP7 - Validations avancées et détection des conflits

# REALISER PAR: KEMENE TAKO JAMES NELSON

## Objectif

L’objectif de ce TP est d’améliorer l’application IPPlan-Manager en ajoutant des validations avancées afin de détecter les incohérences dans un plan d’adressage réseau.

L’application doit être capable de :
- vérifier les adresses IP,
- détecter les chevauchements de sous-réseaux,
- empêcher les conflits VLAN,
- gérer les erreurs avec des exceptions personnalisées.


# Notions étudiées

- Exceptions personnalisées
- try / catch
- throw
- Validation réseau
- Détection de chevauchement
- Gestion des conflits VLAN
- Robustesse logicielle
- Vérification des adresses IP
- Validation d’un plan d’adressage


# Scénarios testés

## 1. Génération d’un plan VLSM
Le moteur VLSM génère automatiquement plusieurs sous-réseaux à partir des besoins suivants :
- TECHNIQUE
- WIFI
- ADMINISTRATION
- SERVEURS


## 2. Validation des adresses IP
Le programme vérifie que les adresses IP générées sont valides.

Exemple testé :
192.168.300.0

Résultat :
Adresse détectée comme invalide.


## 3. Détection de chevauchement réseau
Deux réseaux ont été créés volontairement :
- 192.168.1.0/25
- 192.168.1.64/26

Résultat :
Le programme détecte correctement le chevauchement.


## 4. Détection de conflit VLAN
Plusieurs VLANs ont été créés.

Un VLAN avec un identifiant déjà utilisé a ensuite été ajouté volontairement.

Résultat :
Le programme déclenche une exception ConflitVLANException.


# Résultats obtenus

Le programme :
- génère correctement un plan d’adressage,
- valide les adresses IP,
- détecte les conflits VLAN,
- détecte les chevauchements réseau,
- affiche des messages d’erreur compréhensibles,
- continue à fonctionner sans arrêt brutal grâce aux exceptions.

Exemple de résultat :

===== IPPlan-Manager : TP7 - Validations avancées =====

Plan généré :
TECHNIQUE -> 192.168.1.0/25
WIFI -> 192.168.1.128/25
ADMINISTRATION -> 192.168.2.0/26
SERVEURS -> 192.168.2.64/27

Validation terminée : aucun conflit critique détecté.

Test de conflit VLAN :
Erreur VLAN : Conflit VLAN : l'identifiant 10 est déjà utilisé.



## Réponses aux questions

### 1. Pourquoi les validations avancées sont-elles indispensables dans un outil IPAM ?
Un outil IPAM produit des plans d'adressage qui seront appliqués sur des
équipements réels. Une erreur dans le plan peut provoquer des conflits
d'adresses, des pannes de routage, ou des accès impossibles entre services.
Les validations garantissent que le plan est cohérent avant tout déploiement.

### 2. Quelle est la différence entre une erreur simple et une exception en Java ?
Une erreur simple peut être silencieuse (retourner false, ignorer un cas).
Une exception force le code appelant à traiter le problème, sinon le
programme s'arrête. Les exceptions rendent les erreurs visibles et
obligent à les gérer explicitement.

### 3. Pourquoi crée-t-on des exceptions personnalisées ?
Les exceptions Java génériques (Exception, RuntimeException) ne donnent
pas d'information sur le contexte métier. Une exception nommée
ConflitVLANException est immédiatement compréhensible par un développeur.
Elle documente le code et facilite la maintenance.

### 4. Quel est le rôle du bloc try/catch ?
Le bloc try contient le code qui peut provoquer une erreur.
Le bloc catch attrape l'exception et permet de la traiter (afficher un
message, corriger la donnée, continuer l'exécution) sans planter l'application.

### 5. Pourquoi deux VLANs ne doivent-ils pas avoir le même identifiant ?
L'identifiant VLAN (ID) est utilisé par les équipements réseau (switches)
pour séparer les trafics. Deux VLANs avec le même ID sur le même switch
créeraient une ambiguïté que l'équipement ne peut pas résoudre, provoquant
des incidents de trafic réseau.

### 6. Pourquoi deux sous-réseaux ne doivent-ils pas se chevaucher ?
Si deux sous-réseaux partagent des adresses communes, des machines de
services différents pourraient avoir la même adresse IP. Les routeurs ne
sauraient pas vers quel sous-réseau envoyer les paquets, causant des
routes incohérentes et des pannes de communication.

### 7. Pourquoi transforme-t-on les adresses IP en entiers pour comparer des plages ?
Une adresse IP est un nombre 32 bits. La convertir en entier permet des
comparaisons arithmétiques simples : debut1 <= fin2 && debut2 <= fin1.
Comparer des chaînes de caractères comme "192.168.1.0" ne permettrait pas
de détecter correctement si deux plages se croisent.

### 8. Pourquoi la classe ValidateurPlanAdressage doit-elle être séparée du moteur VLSM ?
Principe de responsabilité unique (SRP) : chaque classe a un rôle précis.
Le moteur VLSM génère le plan. Le validateur vérifie sa cohérence. Cette
séparation facilite la maintenance, les tests unitaires, et l'évolution
indépendante de chaque composant.
