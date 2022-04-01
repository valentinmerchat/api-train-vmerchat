package org.miage.apitrain.assembler;

import org.miage.apitrain.boundary.UtilisateurRepresentation;
import org.miage.apitrain.entity.Utilisateur;
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
public class UtilisateurAssembler implements RepresentationModelAssembler<Utilisateur, EntityModel<Utilisateur>> {

    @Override
    public EntityModel<Utilisateur> toModel(Utilisateur utilisateur) {
        EntityModel<Utilisateur> utilisateurEntityModel = EntityModel.of(utilisateur);
        utilisateurEntityModel.add(linkTo(methodOn(UtilisateurRepresentation.class).getOneUtilisateur(utilisateur.getIdUtilisateur())).withSelfRel());
        utilisateurEntityModel.add(linkTo(methodOn(UtilisateurRepresentation.class).getAllUtilisateurs()).withRel("collection"));
        return utilisateurEntityModel;
    }

    public CollectionModel<EntityModel<Utilisateur>> toCollectionModel(Iterable<? extends Utilisateur> entities) {
        List<EntityModel<Utilisateur>> clientModel = StreamSupport
                .stream(entities.spliterator(), false)
                .map(i -> toModel(i))
                .collect(Collectors.toList());
        return CollectionModel.of(clientModel,
                linkTo(methodOn(UtilisateurRepresentation.class)
                        .getAllUtilisateurs()).withSelfRel());
    }
}
