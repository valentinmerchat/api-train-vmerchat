package org.miage.apitrain;

import lombok.extern.slf4j.Slf4j;
import org.miage.apitrain.boundary.ReservationResource;
import org.miage.apitrain.boundary.TrajetResource;
import org.miage.apitrain.boundary.UtilisateurResource;
import org.miage.apitrain.entity.EtatReservation;
import org.miage.apitrain.entity.Reservation;
import org.miage.apitrain.entity.Trajet;
import org.miage.apitrain.entity.Utilisateur;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import java.time.LocalDateTime;
import java.util.ArrayList;


@SpringBootApplication
@Slf4j
public class ApiTrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTrainApplication.class, args);
	}


	/*
	 * http://127.0.0.1:8082/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
	 */
	@Bean
	public OpenAPI intervenantAPI() {
		return new OpenAPI().info(new Info()
				.title("Reservation train API")
				.version("1.0")
				.description("Documentation sommaire de API reservation train 1.0"));
	}


	@Bean
	CommandLineRunner run(UtilisateurResource utilResource, TrajetResource trajetResource, ReservationResource reservationResource) {
		return args -> {

			Utilisateur util1 = new Utilisateur("1", "Valentin", new ArrayList<Reservation>());
			utilResource.save(util1);
			Utilisateur util2 = new Utilisateur("2", "Jeremy", new ArrayList<Reservation>());
			utilResource.save(util2);
			Utilisateur util3 = new Utilisateur("3", "Chloe", new ArrayList<Reservation>());
			utilResource.save(util3);
			Utilisateur util4 = new Utilisateur("4", "Elias", new ArrayList<Reservation>());
			utilResource.save(util4);


			Trajet trajet1 = new Trajet("1", "nancy", "metz", LocalDateTime.parse("2022-03-30T09:15:00"), LocalDateTime.parse("2022-03-30T10:15:00"), 10.50, 50, 50);
			trajetResource.save(trajet1);
			Trajet trajet2 = new Trajet("2", "metz", "nancy", LocalDateTime.parse("2022-03-30T17:30:00"), LocalDateTime.parse("2022-03-30T18:30:00"), 9.0, 50, 50);
			trajetResource.save(trajet2);
			Trajet trajet3 = new Trajet("3", "metz", "thionville", LocalDateTime.parse("2022-03-31T08:30:00"), LocalDateTime.parse("2022-03-31T09:30:00"), 8.0, 0, 50);
			trajetResource.save(trajet3);
			Trajet trajet4 = new Trajet("4", "nancy", "paris", LocalDateTime.parse("2022-03-25T14:23:00"), LocalDateTime.parse("2022-03-25T14:40:00"), 5.50, 50, 0);
			trajetResource.save(trajet4);
			Trajet trajet5 = new Trajet("5", "nancy", "metz", LocalDateTime.parse("2022-07-25T09:07:00"), LocalDateTime.parse("2022-07-25T09:45:00"), 10.50, 50, 50);
			trajetResource.save(trajet5);
			Trajet trajet6 = new Trajet("6", "thionville", "metz", LocalDateTime.parse("2022-07-26T14:23:00"), LocalDateTime.parse("2022-07-26T14:40:00"), 5.20, 50, 50);
			trajetResource.save(trajet6);
			Trajet trajet7 = new Trajet("7", "nancy", "metz", LocalDateTime.parse("2022-03-30T12:00:00"), LocalDateTime.parse("2022-03-30T10:15:00"), 10.50, 50, 50);
			trajetResource.save(trajet7);
			Trajet trajet8 = new Trajet("8", "nancy", "metz", LocalDateTime.parse("2022-03-30T12:00:00"), LocalDateTime.parse("2022-03-30T10:15:00"), 10.50, 0, 0);
			trajetResource.save(trajet8);
			Trajet trajet9 = new Trajet("9", "nancy", "metz", LocalDateTime.parse("2022-03-30T10:00:00"), LocalDateTime.parse("2022-03-30T11:00:00"), 10.50, 50, 50);
			trajetResource.save(trajet9);
			Trajet trajet10 = new Trajet("10", "nancy", "metz", LocalDateTime.parse("2022-03-30T10:10:00"), LocalDateTime.parse("2022-03-30T10:40:00"), 9.50, 31, 24);
			trajetResource.save(trajet10);
			Trajet trajet11 = new Trajet("11", "nancy", "metz", LocalDateTime.parse("2022-03-30T09:30:00"), LocalDateTime.parse("2022-03-30T10:00:00"), 8.25, 0, 40);
			trajetResource.save(trajet11);
			Trajet trajet12 = new Trajet("12", "nancy", "metz", LocalDateTime.parse("2022-03-30T19:30:00"), LocalDateTime.parse("2022-03-30T20:30:00"), 4.50, 3, 0);
			trajetResource.save(trajet12);

			Reservation res1 = new Reservation("1", util1, trajet1, EtatReservation.Paye, true);
			reservationResource.save(res1);
			Reservation res2 = new Reservation("2", util1, trajet2, EtatReservation.Paye, true);
			reservationResource.save(res2);
			Reservation res3 = new Reservation("3", util2, trajet3, EtatReservation.Attente, false);
			reservationResource.save(res3);
			Reservation res4 = new Reservation("4", util3, trajet4, EtatReservation.Attente, false);
			reservationResource.save(res4);
			Reservation res5 = new Reservation("5", util2, trajet1, EtatReservation.Paye, true);
			reservationResource.save(res5);
		};
	}


}
