package org.miage.apitrain.assembler;

import org.miage.apitrain.boundary.ReservationRepresentation;
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

@Component
public class ReservationAssembler implements RepresentationModelAssembler<Reservation, EntityModel<Reservation>>{

    @Override
    public EntityModel<Reservation> toModel(Reservation reservation) {
        EntityModel<Reservation> reservationEntityModel = EntityModel.of(reservation);
        reservationEntityModel.add(linkTo(methodOn(ReservationRepresentation.class).getOneReservation(reservation.getIdReservation())).withSelfRel());
        reservationEntityModel.add(linkTo(methodOn(ReservationRepresentation.class).getAllReservations()).withRel("collection"));
        return reservationEntityModel;
    }

    @Override
    public CollectionModel<EntityModel<Reservation>> toCollectionModel(Iterable<? extends Reservation> reservations) {
        List<EntityModel<Reservation>> reservationModel = StreamSupport
                .stream(reservations.spliterator(), false)
                .map(i -> toModel(i))
                .collect(Collectors.toList());
        return CollectionModel.of(reservationModel,
                linkTo(methodOn(ReservationRepresentation.class)
                        .getAllReservations()).withSelfRel());
    }
}
