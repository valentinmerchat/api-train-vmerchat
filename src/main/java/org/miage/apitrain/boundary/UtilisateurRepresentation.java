package org.miage.apitrain.boundary;

import org.miage.apitrain.assembler.UtilisateurAssembler;
import org.miage.apitrain.entity.Utilisateur;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value="/utilisateurs", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Utilisateur.class)
public class UtilisateurRepresentation {

    private final UtilisateurResource ur;
    private final UtilisateurAssembler ua;
    //private final UtilisateurValidator uv;

    public UtilisateurRepresentation(UtilisateurResource ur, UtilisateurAssembler ua) {
        this.ur = ur;
        this.ua = ua;
        //this.uv = uv;
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


    /*
    @GetMapping(value="/{utilisateurId}/reservations")
    public ResponseEntity<CollectionModel<EntityModel<Reservation>>> getOneUtilisateurReservations(@PathVariable("utilisateurId") String id){

    }

     */
}
