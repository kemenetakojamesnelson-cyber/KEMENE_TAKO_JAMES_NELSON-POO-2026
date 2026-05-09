# TP2 - Encapsulation

### Realiser par : KEMENE TAKO JAMES NELSON

##  Objectif

Introduction de l’encapsulation et des validations dans le projet IPPlan-Manager afin de sécuriser et fiabiliser les données réseau.



##  Notions étudiées

- private  
- getters  
- setters  
- validation des données  
- mot-clé this  
- encapsulation  



##  Tests réalisés

- Création d’adresses IP valides et invalides  
- Vérification des adresses locales (192.x.x.x)  
- Tests des réseaux avec masques CIDR invalides  
- Tests des équipements avec champs vides  
- Modification des données via setters  
- Vérification des comportements automatiques de correction  



##  Difficultés rencontrées

- Gestion des valeurs null et vides  
- Compréhension de l’encapsulation  
- Utilisation correcte des setters dans les constructeurs  
- Organisation des validations dans chaque classe  
- Vérification du comportement des objets liés (interfaces, équipements)  



##  Réponses aux questions

### 1. Pourquoi utilise-t-on private dans les classes ?

On utilise `private` pour protéger les attributs afin d’empêcher leur modification directe depuis l’extérieur de la classe.



### 2. Quelle différence existe entre un attribut public et un attribut privé ?

Un attribut public est accessible partout dans le programme, tandis qu’un attribut privé est accessible uniquement dans sa classe.



### 3. Pourquoi utilise-t-on des getters et setters ?

Les getters et setters permettent de contrôler l’accès et la modification des attributs tout en appliquant des règles de validation.



### 4. Pourquoi les validations sont-elles importantes dans un logiciel réseau ?

Elles permettent d’éviter les erreurs de configuration, les incohérences et garantissent la fiabilité des données réseau.



### 5. Quel est le rôle du mot-clé this ?

Le mot-clé `this` permet de faire référence à l’objet courant et de différencier les attributs des paramètres.



### 6. Pourquoi le constructeur appelle-t-il les setters ?

Le constructeur appelle les setters afin de réutiliser les règles de validation déjà définies dans le code.



### 7. Pourquoi la validation du masque CIDR est-elle importante ?

Elle garantit que le masque reste dans une plage valide (0 à 32), évitant ainsi des erreurs de calcul réseau.



### 8. Pourquoi l’encapsulation améliore-t-elle la sécurité logicielle ?

L’encapsulation empêche les accès directs aux données et force leur modification contrôlée, ce qui rend le système plus sûr et stable.
