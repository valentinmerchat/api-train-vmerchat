package org.miage.apitrain;

import org.miage.apitrain.boundary.ReservationResource;
import org.miage.apitrain.boundary.TrajetResource;
import org.miage.apitrain.boundary.UtilisateurResource;
import org.miage.apitrain.entity.Utilisateur;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiTrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTrainApplication.class, args);
	}

	/*


	@Bean
	public OpenAPI intervenantAPI() {
		return new OpenAPI().info(new Info()
				.title("Intervenant API")
				.version("1.0")
				.description("Documentation sommaire de API Intervenant 1.0"));
	}

	 */

}
