package org.miage.apitrain.boundary;

import org.miage.apitrain.assembler.TrajetAssembler;
import org.miage.apitrain.entity.Trajet;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
 /*
    @GetMapping(value="/{villeA}/{villeB}")
    public ResponseEntity<?> getOneTrajet(@PathVariable("villeA") String villeA,
                                          @PathVariable("villeB") String villeB) {




       return Optional.ofNullable(tr.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(i.get()))
                .orElse(ResponseEntity.notFound().build());
    }
*/
}
