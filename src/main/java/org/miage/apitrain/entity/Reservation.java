package org.miage.apitrain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation implements Serializable {
    private static final long serialVersionUID = 147319841351L;

    @Id
    @Column(name = "id_reservation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idReservation;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur idutil;

    @ManyToOne
    @JoinColumn(name = "id_trajet")
    private Trajet idTrajet;

    @Column(name = "etat")
    @Enumerated(EnumType.STRING)
    private EtatReservation etatReservation;

    @Column(name = "siege_fenetre")
    private boolean siegeFenetre;
}
