# api-train-vmerchat

## Descriptif :

Ce projet consiste à la création d'une API métier permettant la gestion des réservations de trajets entre des villes de départ de d'arrivées pour les utilisateurs.

## Choix de conception

J'ai décidé de créer 3 entities :

- Utilisateur : représente l'utilisateur qui va effectuer des opération de selection, recherche, etc. sur le service de réservation
- Trajet : représente un voyage entre une ville A et une ville B.
- Reservation : représente une réservation attribué à un utilisateur donné pour un trajet donné.

Concernant le train, je n'ai pas eu besoin de le modéliser sous forme d'entity car un trajet représente implicitement un train pour lequel il y aurait une ville de départ et une ville d'arrivée définie.

## Descriptif des entities

Utilisateur :

- idUtilisateur: idenfitiant de l'utilisateur
- nomUtilisateur : nom de l'utilisateur
- listReservations : la liste de toutes les réservations effectués par l'utilisateur

Trajet :

- id : identifiant du trajet
- villeDepart : ville où le trajet commence
- villeArrivee : ville où le trajet se termine
- dateDepart : date et heure à laquelle le trajet commence
- dateArrivee : date et heure à laquelle le trajet se termine
- prix : prix du trajet à l'aller
- nbPlacesFenetres : nombre de places disponibles côté fenêtre du trajet
- nbPlacesCouloirs : nombre de places disponibles côté couloir du trajet

Reservation :

- idReservation : identifiant d'une réservation
- idutil : identifiant de l'utilisateur ayant été attribué à la réservation
- idTrajet : identifiant du trajet concerné par la réservation
- etat : représente l'état de la réservation (prend les valeurs "Paye", "Attente" et "Confirme")
- siegeFenetre : permet de savoir si on a prit un siege côté fenêtre ou non


## Les différentes chemins :

Chemin principal :

- **http://localhost:8082/**

Chemin vers la base de données :

- **/console** : compte -> valentin et mdp -> password

Utilisateur :

- **/utilisateurs** : ce chemin retourne la liste des utilisateurs existants
- **/utilisateurs/{utilisateurId}** : ce chemin retourne un utilisateur à partir d'un identifiant
- **/utilisateurs/{utilisateurId}/utilReservations** : ce chemin retourne la liste des réservations d'un utilisateur en fonction de son identifiant


Trajet :

- **/trajets** : ce chemin retourne la liste des trajets existants
- **/trajets/{utilisateurId}** ce chemin retourne un trajet à partir d'un identifiant
- **/trajets/{villeDepart}/{villeArrivee}/{date}** : ce chemin retourne la liste des trajets en fonction de la ville de départ, la ville d'arrivée et la date pour laquelle on souhaite voir les trajets


Reservation :

- **/reservations** : ce chemin retourne la liste des réservations
- **/reservations/{utilisateurId}** : ce chemin retourne une réservation à partir d'un identifiant


Swagger documentation :

- **http://127.0.0.1:8082/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config**


## Données

Pour les données dans la base de données h2, j'utilise un CommandLineRunner qui va permettre de créer les objets qui seront ensuite mapper dans la base de données.

