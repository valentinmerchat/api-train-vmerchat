package org.miage.apitrain;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.miage.apitrain.boundary.ReservationResource;
import org.miage.apitrain.boundary.UtilisateurResource;
import org.miage.apitrain.entity.Reservation;
import org.miage.apitrain.entity.Trajet;
import org.miage.apitrain.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.UUID;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UtilisateurTests {


    @LocalServerPort
    int port;

    @Autowired
    UtilisateurResource ur;

    @Autowired
    ReservationResource rr;

    @BeforeEach
    public void setupContext() {
        ur.deleteAll();
        rr.deleteAll();
        RestAssured.port = port;
    }

    @Test
    public void pingApi() {
        when().get("/utilisateurs").then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getOneUtilisateur() {
        Utilisateur util = new Utilisateur(UUID.randomUUID().toString(), "Chloe", new ArrayList<Reservation>());
        ur.save(util);
        Response response = when().get("/utilisateurs/"+util.getIdUtilisateur())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        String jsonAsString = response.asString();
        assertThat(jsonAsString,containsString("Chloe"));
    }

    @Test
    public void getAllUtilisateur() {
        Utilisateur util = new Utilisateur(UUID.randomUUID().toString(), "Jeremy", new ArrayList<Reservation>());
        ur.save(util);
        util = new Utilisateur(UUID.randomUUID().toString(), "Elias", new ArrayList<Reservation>());
        ur.save(util);

        when().get("/utilisateurs/")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("size()",equalTo(2));
    }


    @Test
    public void getNotFound() {
        when().get("/intervenants/2300").then().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
