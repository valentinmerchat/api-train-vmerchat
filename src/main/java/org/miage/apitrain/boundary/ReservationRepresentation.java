package org.miage.apitrain.boundary;

import org.miage.apitrain.assembler.ReservationAssembler;
import org.miage.apitrain.entity.*;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value="/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Reservation.class)
public class ReservationRepresentation {

    private final ReservationResource rr;
    private final ReservationAssembler ra;
    private final UtilisateurResource ur;
    private final TrajetResource tr;

    public ReservationRepresentation(ReservationResource rr, ReservationAssembler ra, UtilisateurResource ur, TrajetResource tr) {
        this.rr = rr;
        this.ra = ra;
        this.ur = ur;
        this.tr = tr;
    }

    @GetMapping
    public ResponseEntity<?> getAllReservations() {
        return ResponseEntity.ok(ra.toCollectionModel(rr.findAll()));
    }

    @GetMapping(value="/{reservationId}")
    public ResponseEntity<?> getOneReservation(@PathVariable("reservationId") String id) {
        return Optional.ofNullable(rr.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(ra.toModel(i.get())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveOneReservation(@RequestBody @Valid ReservationInput reservationInput) {
        Optional<ReservationInput> rqBody =  Optional.ofNullable(reservationInput);
        if(rqBody.isEmpty())
            ResponseEntity.badRequest().build();
        Optional<Trajet> trajet = tr.findById(rqBody.get().getIdTrajet());
        if(trajet.isEmpty())
            ResponseEntity.badRequest().build();
        Optional<Utilisateur> utilisateur = ur.findById(rqBody.get().getIdUtil());
        if(utilisateur.isEmpty())
            ResponseEntity.badRequest().build();
        Reservation reservation = new Reservation(
                UUID.randomUUID().toString(),
                ur.findById(reservationInput.getIdUtil()).get(),
                tr.findById(reservationInput.getIdTrajet()).get(),
                EtatReservation.Attente,
                reservationInput.isSiegeFenetre()
        );

        Reservation saved = rr.save(reservation);
        URI location = linkTo(ReservationRepresentation.class).slash(saved.getIdReservation()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/{reservationId}")
    @Transactional
    public ResponseEntity<?> deleteOneReservation(@PathVariable("utilisateurId") String idReservation) {
        Optional<Reservation> reservation = rr.findById(idReservation);
        if (reservation.isPresent() && reservation.get().getEtatReservation() == EtatReservation.Attente) {
            rr.delete(reservation.get());
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{reservationId}")
    @Transactional
    public ResponseEntity<?> confirmOneReservation(@PathVariable("utilisateurId") String idReservation) {
        Optional<Reservation> reservation = rr.findById(idReservation);
        if (reservation.isPresent() && reservation.get().getEtatReservation() == EtatReservation.Attente) {
            reservation.get().setEtatReservation(EtatReservation.Confirme);
            Reservation saved = rr.save(reservation.get());
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.noContent().build();
    }
}


