package org.miage.apitrain.boundary;

import org.miage.apitrain.assembler.TrajetAssembler;
import org.miage.apitrain.entity.Trajet;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/trajets", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Trajet.class)
public class TrajetRepresentation {

    private final TrajetResource tr;
    private final TrajetAssembler ta;

    public TrajetRepresentation(TrajetResource tr, TrajetAssembler ta) {
        this.tr = tr;
        this.ta = ta;
    }

    @GetMapping
    public ResponseEntity<?> getAllTrajets() {
        return ResponseEntity.ok(ta.toCollectionModel(tr.findAll()));
    }

    @GetMapping(value="/{trajetId}")
    public ResponseEntity<?> getOneTrajet(@PathVariable("trajetId") String id) {
        return Optional.ofNullable(tr.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(ta.toModel(i.get())))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value="/{villeDepart}/{villeArrivee}/{date}/{fenetre}/{trier}")
    public ResponseEntity<?> getAllTrajetsParVilleDepartEtVilleArrivee(@PathVariable("villeDepart") String villeA,
                                                                       @PathVariable("villeArrivee") String villeB,
                                                                       @PathVariable("date")String date,
                                                                       @PathVariable("fenetre") boolean fenetre,
                                                                       @PathVariable("trier") boolean trier){

        //On regarde si le format de la date passer dans l'uri est le bon
        try{
            LocalDateTime.parse(date);
        }
        catch (DateTimeParseException e){
            System.out.println(e.getMessage());
        }

        //On stocke la date en final car il ne faut surtout pas qu'elle soit modifier dans l'opération
        final LocalDateTime dateTrajet = LocalDateTime.parse(date);

        //On récupère les trajets qui ont villeDepart , villeArrivee
        List<Trajet> trajets = tr.findAllByVilleDepartAndVilleArrivee(villeA.toLowerCase(), villeB.toLowerCase());

        //Si vide on retourne une structure JSON vide sur localhost
        if(trajets.isEmpty())
            return ResponseEntity.ok(ta.toCollectionModel(new ArrayList<Trajet>()));

        List<Trajet> trajetsValides = new ArrayList<Trajet>();


        trajetsValides = trajets.stream().filter(i -> {
            LocalDateTime uneHeureAvant = dateTrajet.minusHours(1);
            LocalDateTime uneHeureApres = dateTrajet.plusHours(1);
            boolean dateCondition = i.getDateDepart().isAfter(uneHeureAvant) && i.getDateDepart().isBefore(uneHeureApres);
            boolean emplacementCondition = (fenetre && i.getNbPlacesFenetres() > 0) || (!fenetre && i.getNbPlacesCouloirs() > 0);
            return dateCondition && emplacementCondition;
        }).collect(Collectors.toList());


        if(trier)
            trajetsValides.sort(Comparator.comparing(Trajet::getPrix));

        return ResponseEntity.ok(ta.toCollectionModel(trajetsValides));
    }



}
