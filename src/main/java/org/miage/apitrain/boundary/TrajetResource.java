package org.miage.apitrain.boundary;

import org.miage.apitrain.entity.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TrajetResource extends JpaRepository<Trajet, String> {
    List<Trajet> findAllByVilleDepartAndVilleArriveeAndDateDepart(String villeA, String villeB, LocalDateTime dateDepart) ;
}
