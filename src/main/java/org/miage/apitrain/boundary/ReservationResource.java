package org.miage.apitrain.boundary;

import org.miage.apitrain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationResource extends JpaRepository<Reservation, String> {
}
