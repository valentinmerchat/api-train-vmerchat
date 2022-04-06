# api-train-vmerchat

## Descriptif :

Ce projet consiste à la création d'une API métier permettant la gestion des réservations de trajets entre des villes de départ de d'arrivées pour les utilisateurs.

## Choix de conception

### Définition des entités 

J'ai décidé de créer 3 entities :

- Utilisateur : représente l'utilisateur qui va effectuer des opération de selection, recherche, etc. sur le service de réservation
- Trajet : représente un voyage entre une ville A et une ville B.
- Reservation : représente une réservation attribué à un utilisateur donné pour un trajet donné.

Concernant le train, je n'ai pas eu besoin de le modéliser sous forme d'entity car un trajet représente implicitement un train pour lequel il y aurait une ville de départ et une ville d'arrivée définie.


## Descriptif des entités

### Utilisateur :

- idUtilisateur: idenfitiant de l'utilisateur
- nomUtilisateur : nom de l'utilisateur
- listReservations : la liste de toutes les réservations effectués par l'utilisateur

### Trajet :

- id : identifiant du trajet
- villeDepart : ville où le trajet commence
- villeArrivee : ville où le trajet se termine
- dateDepart : date et heure à laquelle le trajet commence
- dateArrivee : date et heure à laquelle le trajet se termine
- prix : prix du trajet à l'aller
- nbPlacesFenetres : nombre de places disponibles côté fenêtre du trajet
- nbPlacesCouloirs : nombre de places disponibles côté couloir du trajet

### Reservation :

- idReservation : identifiant d'une réservation
- idutil : identifiant de l'utilisateur ayant été attribué à la réservation
- idTrajet : identifiant du trajet concerné par la réservation
- etat : représente l'état de la réservation (prend les valeurs "Paye", "Attente" et "Confirme")
- siegeFenetre : permet de savoir si on a prit un siege côté fenêtre ou non

## Données

Pour le stockage des données, j'ai décidé de créer une base de données h2. Pour peupler la base de données, j'utilise un CommandLineRunner qui va permettre de créer les objets qui seront ensuite mapper dans la base de données.
Le CommandLineRunner se trouve dans le fichier ApiTrainApplication.

## Les différentes chemins :

#### Chemin principal :

- **http://localhost:8082/**

### Chemin vers la base de données :

- **/console** : compte -> valentin et mdp -> password


### Swagger documentation :

- **http://127.0.0.1:8082/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config**
-

### Utilisateur :

- **/utilisateurs** : ce chemin retourne la liste des utilisateurs existants
- **/utilisateurs/{utilisateurId}** : ce chemin retourne un utilisateur à partir d'un identifiant
- **/utilisateurs/{utilisateurId}/utilReservations** : ce chemin retourne la liste des réservations d'un utilisateur en fonction de son identifiant


### Trajet :

- **/trajets** : ce chemin retourne la liste des trajets existants
- **/trajets/{trajetId}** ce chemin retourne un trajet à partir d'un identifiant
- **/trajets/{villeDepart}/{villeArrivee}/{date}/{fenetre}/{trier}** : ce chemin retourne la liste des trajets en fonction de la ville de départ, la ville d'arrivée, pour une date donnée, côté couloir ou fenêtre et triée en fonction du prix si voulu 
(Exemple à tester : **/trajets/nancy/metz/2022-03-30T09:15:00/true/true)

[Remarques :]

Pour ****/trajets/{villeDepart}/{villeArrivee}/{date}/{fenetre}/{trier}**, j'ai décidé d'afficher les trajets en fonction de la journée renseignée et sur un interval de temps égal à 1 heure autour de la date renseignée car même s'il n'y a pas un trajet à l'instant t donné, il peut y en avoir un sur un interval de minutes.
En effet, en sélectionnant en fonction de la date et du temps comme je faisais auparavant, j'ai remarqué qu'il serait vraiment difficile à l'utilisateur de trouver un trajet à une précise, j'ai donc décidé de faire comme ceci.

### Reservation :

- **/reservations** : ce chemin retourne la liste des réservations
- [POST] **/reservations** : ce chemin permet de créer une nouvelle réservation pour un utilisateur donné et pour un trajet donné
- **/reservations/{utilisateurId}** : ce chemin retourne une réservation à partir d'un identifiant
- [DELETE] **/reservations/{utilisateurId}**  : ce chemin permet de supprimer une réservation de la base de données à partir de son identifiant (à condition qu'elle n'est pas était confirmé ou payé par l'utilisateur)
- [PATCH] **/reservations/{utilisateurId}** : ce chemin permet de modifier l'état d'une réservation de "En Attente" à "Confirmé" par l'utilisateur






