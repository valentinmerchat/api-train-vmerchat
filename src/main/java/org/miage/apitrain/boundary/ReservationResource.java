package org.miage.apitrain.boundary;

import org.miage.apitrain.entity.Reservation;
import org.miage.apitrain.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationResource extends JpaRepository<Reservation, String> {
    List<Reservation> findAllByidutil(Utilisateur idutil);
}
