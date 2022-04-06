package org.miage.apitrain.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;


@Service
public class CompteDelegate {

    @Autowired
    @Lazy
    RestTemplate restTemp;

    public ResponseEntity<String> appelRetirerMontant(String idClient) throws URISyntaxException {
        String url = "http://api-banque/comptes/"+idClient+"/pay";
        URI uri = new URI(url);
        ResponseEntity<String> reponse = restTemp.getForEntity(uri, String.class);
        return reponse ;

    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
