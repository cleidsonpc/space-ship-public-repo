package org.cleidson.controller;

import org.spaceship.backend.controller.dto.PowerPlantControllerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PowerPlantControllerTest {

    @LocalServerPort
    private int port;

    final private TestRestTemplate testRestTemplate;

    @Autowired
    public PowerPlantControllerTest(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    private String getServiceURL() {
        return "http://localhost:" + port + "/power_plant";
    }

    @Test
    void shouldUpdatePowerPlantToDisabled() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setOrigin("http://localhost:3000");

        final String url = getServiceURL() + "/update";

        PowerPlantControllerDto powerPlantControllerDtoExpected = new PowerPlantControllerDto(Boolean.TRUE, 100);
        HttpEntity<PowerPlantControllerDto> request = new HttpEntity<>(powerPlantControllerDtoExpected, headers);

        ResponseEntity<PowerPlantControllerDto> httpEntityObtained = testRestTemplate.postForEntity(url, request, PowerPlantControllerDto.class);

        Assertions.assertEquals(HttpStatus.OK, httpEntityObtained.getStatusCode());
        Assertions.assertEquals(powerPlantControllerDtoExpected, httpEntityObtained.getBody());


        // TODO: 08/08/2023 Add database validation
    }
}
