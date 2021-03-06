package org.miage.apitrain;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.miage.apitrain.boundary.ReservationResource;
import org.miage.apitrain.boundary.TrajetResource;
import org.miage.apitrain.boundary.TrajetResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrajetTests {


    @LocalServerPort
    int port;

    @Autowired
    TrajetResource tr;

    @Autowired
    ReservationResource rr;

    @BeforeEach
    public void setupContext() {
        rr.deleteAll();
        tr.deleteAll();
        RestAssured.port = port;
    }

    @Test
    public void pingApi() {
        when().get("/trajets").then().statusCode(HttpStatus.SC_OK);
    }

}
