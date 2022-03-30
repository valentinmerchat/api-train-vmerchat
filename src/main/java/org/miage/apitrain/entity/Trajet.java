package org.miage.apitrain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trajet implements Serializable {
    private static final long serialVersionUID = 541194739137L;

    @Id
    @Column(name = "id_trajet")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "ville_depart")
    private String villeDepart;

    @Column(name = "ville_arrivee")
    private String villeArrive;

    @Column(name = "date_depart")
    private String dateDepart;

    @Column(name = "date_arrivee")
    private String dateArrive;

    @Column(name = "nb_places_fenetres")
    private int nbPlacesFenetres;

    @Column(name = "nb_places_couloirs")
    private int nbPlacesCouloirs;

}
