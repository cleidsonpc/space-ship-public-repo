package org.spaceship.backend.controller;

import org.spaceship.backend.controller.dto.HealthAppStatusControllerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling requests related to the health check of the application.
 * This controller handles the "/health" endpoint.
 */
@RestController
@RequestMapping(path = "/health")
public class HealthCheckController {

    private static final Logger LOG = LoggerFactory.getLogger(HealthCheckController.class);

    /**
     * Endpoint for performing a health check on the application.
     * @return A ResponseEntity containing a HealthAppStatusControllerDto with the status of the application.
     */
//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/check", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<HealthAppStatusControllerDto> healthCheck() {
        LOG.info("healthCheck performed.");

        HealthAppStatusControllerDto healthAppStatusControllerDto = new HealthAppStatusControllerDto(Boolean.TRUE, "App is online");
        return ResponseEntity.ok().body(healthAppStatusControllerDto);
    }
}
