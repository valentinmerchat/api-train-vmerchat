package org.miage.apitrain.boundary;

import org.miage.apitrain.entity.Reservation;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value="/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Reservation.class)
public class ReservationRepresentation {

    private final ReservationResource rr;
    //private final ReservationAssembler ra;
    //private final ReservationValidator rv;

    public ReservationRepresentation(ReservationResource rr) {
        this.rr = rr;
        //this.ra = ra;
        //this.rv = rv;
    }

    @GetMapping
    public ResponseEntity<?> getAllReservations() {
        return ResponseEntity.ok(rr.findAll());
    }

    @GetMapping(value="/{reservationId}")
    public ResponseEntity<?> getOneReservation(@PathVariable("reservationId") String id) {
        return Optional.ofNullable(rr.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(i.get()))
                .orElse(ResponseEntity.notFound().build());
    }


}
