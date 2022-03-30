package org.miage.apitrain.boundary;

import org.miage.apitrain.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurResource extends JpaRepository<Utilisateur, String> {
}
