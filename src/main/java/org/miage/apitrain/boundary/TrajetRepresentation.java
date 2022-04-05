package org.miage.apitrain.boundary;

import org.miage.apitrain.assembler.TrajetAssembler;
import org.miage.apitrain.entity.Trajet;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/trajets", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Trajet.class)
public class TrajetRepresentation {

    private final TrajetResource tr;
    private final TrajetAssembler ta;
    //private final TrajetValidator tv;

    public TrajetRepresentation(TrajetResource tr, TrajetAssembler ta) {
        this.tr = tr;
        this.ta = ta;
        //this.tv = tv;
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

    @GetMapping(value="/{villeDepart}/{villeArrivee}/{date}")
    public ResponseEntity<?> getAllTrajetsParVilleDepartEtVilleArrivee(@PathVariable("villeDepart") String villeA,
                                                                       @PathVariable("villeArrivee") String villeB,
                                                                       @PathVariable("date")String date) {

        System.out.println(date);
        //On regarde si le format de la date passer dans l'uri est le bon
        try{
            System.out.println(LocalDateTime.parse(date));
        }
        catch (DateTimeParseException e){
            System.out.println(e.getMessage());
        }

        //On récupère les trajets qui ont villeDepart , villeArrivee et pour lesquels la daté équivaut à la date renseigné en paramètre
        List<Trajet> trajets = tr.findAllByVilleDepartAndVilleArriveeAndDateDepart(villeA.toLowerCase(), villeB.toLowerCase(), LocalDateTime.parse(date));

        //Si vide on retourne une structure vide sur localhost
        if(trajets.isEmpty())
            return ResponseEntity.ok(ta.toCollectionModel(new ArrayList<Trajet>()));

        List<Trajet> trajetsValides = new ArrayList<Trajet>();

        for (Trajet t: trajets) {
            if (t.getNbPlacesFenetres() > 0 && t.getNbPlacesCouloirs() > 0){
                trajetsValides.add(t);
            }
        }

        return ResponseEntity.ok(ta.toCollectionModel(trajetsValides));
    }
}
