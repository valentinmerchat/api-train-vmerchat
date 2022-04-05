package org.miage.apitrain.boundary;

import org.apache.tomcat.jni.Local;
import org.miage.apitrain.assembler.ReservationAssembler;
import org.miage.apitrain.assembler.UtilisateurAssembler;
import org.miage.apitrain.entity.Reservation;
import org.miage.apitrain.entity.Utilisateur;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/utilisateurs", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Utilisateur.class)
public class UtilisateurRepresentation {

    private final UtilisateurResource ur;
    private final UtilisateurAssembler ua;
    private final ReservationResource rr;
    private final ReservationAssembler ra;
    //private final TrajetRepresentation tre;
    private final TrajetResource tr;

    public UtilisateurRepresentation(UtilisateurResource ur, UtilisateurAssembler ua,TrajetResource tr, ReservationResource rr, ReservationAssembler ra) {
        this.ur = ur;
        this.ua = ua;
        this.tr = tr;
        this.rr = rr;
        this.ra = ra;
    }

    @GetMapping
    public ResponseEntity<?> getAllUtilisateurs() {
        return ResponseEntity.ok(ua.toCollectionModel(ur.findAll()));
    }

    @GetMapping(value="/{utilisateurId}")
    public ResponseEntity<?> getOneUtilisateur(@PathVariable("utilisateurId") String id) {
        return Optional.ofNullable(ur.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(ua.toModel(i.get())))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping(value="/{utilisateurId}/utilReservations")
    public ResponseEntity<?> getOneUtilisateurReservations(@PathVariable("utilisateurId") String id) {
        Optional<Utilisateur> util = ur.findById(id);

        if(util.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        System.out.println(util.get().getNomUtilisateur());

        //On sélectionne et vérifie si la liste des réservations de utilisateur existe
        List<Reservation> listRes = rr.findAllByidutil(util.get());

        if(listRes.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ra.toCollectionModel(listRes));
    }



  /*  @GetMapping(value="/{utilisateurId}/preferences")
    public ResponseEntity<CollectionModel<EntityModel<Reservation>>> getOneUtilisateurPreferences(@PathVariable("utilisateurId") String id){
        //On sélectionne et vérifie si l'utilisateur existe
        Optional<Utilisateur> util = ur.findById(id);


        if(util.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        System.out.println(util.get().getNomUtilisateur());

        //On sélectionne et vérifie si la liste des réservations de utilisateur existe
        List<Reservation> listRes = rr.findAllByIdUtil(util.get());

        if(listRes.isEmpty())
        {
           return ResponseEntity.notFound().build();
        }


        listRes.stream().filter(i -> i.get)
        //On garde que les reservations pour lesquels on a fait au moins 3 trajets et sur une période de 1 mois maxi auparavant

        //On regarde les trajets disponibles qui ont la même ville de départ et de destination que les réservations en favoris
        //findAllByVilleDepartAndVilleArriveeAndDateDepart(

        //listRes.stream().filter(i -> i.getHeure.equals(date)).toCollectionModel.stream();
        return ResponseEntity.ok(ra.toCollectionModel(listRes));

        *//*
        * SI (reservation.getDateDepart > currentDate.before(6 mois) ET reservations.foreach(i -> i.getvilleA()
        *
        *
        * *//*

    }*/



}
