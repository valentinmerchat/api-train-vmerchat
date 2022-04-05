package org.miage.apitrain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 273591723841L;

    @Id
    @Column(name = "id_utilisateur", length=50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idUtilisateur;

    @Column(name = "nom", length=50)
    private String nomUtilisateur;

    @OneToMany(mappedBy = "idutil")
    private List<Reservation> listReservations;
}
