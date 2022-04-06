package org.miage.apitrain.assembler;

import org.miage.apitrain.boundary.TrajetRepresentation;
import org.miage.apitrain.entity.Trajet;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TrajetAssembler implements RepresentationModelAssembler<Trajet, EntityModel<Trajet>> {

    @Override
    public EntityModel<Trajet> toModel(Trajet trajet) {
        EntityModel<Trajet> trajetEntityModel = EntityModel.of(trajet);
        trajetEntityModel.add(linkTo(methodOn(TrajetRepresentation.class).getOneTrajet(trajet.getId())).withSelfRel());
        trajetEntityModel.add(linkTo(methodOn(TrajetRepresentation.class).getAllTrajets()).withRel("collection"));
        return trajetEntityModel;
    }

    public CollectionModel<EntityModel<Trajet>> toCollectionModel(Iterable<? extends Trajet> entities) {
        List<EntityModel<Trajet>> trajetModel = StreamSupport
                .stream(entities.spliterator(), false)
                .map(i -> toModel(i))
                .collect(Collectors.toList());
        return CollectionModel.of(trajetModel,
                linkTo(methodOn(TrajetRepresentation.class)
                        .getAllTrajets()).withSelfRel());
    }
}
