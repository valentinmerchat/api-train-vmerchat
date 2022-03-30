package org.miage.apitrain.boundary;

import org.miage.apitrain.entity.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrajetResource extends JpaRepository<Trajet, String> {
}
