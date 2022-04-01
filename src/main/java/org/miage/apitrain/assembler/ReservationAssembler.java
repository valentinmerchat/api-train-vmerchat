package org.miage.apitrain.assembler;

import org.miage.apitrain.entity.Reservation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ReservationAssembler implements RepresentationModelAssembler<Reservation, EntityModel<Reservation>>{


    @Override
    public EntityModel<Reservation> toModel(Reservation reservation) {
        return null;
    }

    @Override
    public CollectionModel<EntityModel<Reservation>> toCollectionModel(Iterable<? extends Reservation> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
