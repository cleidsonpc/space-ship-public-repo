package org.spaceship.backend.controller;

import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class AbstractController {

    protected static final Logger LOG = LoggerFactory.getLogger(EngineController.class);

    public <T> ResponseEntity<T> successMessage(@NotNull T body) {
        LOG.info("Success message: body={}", body);
        return ResponseEntity.ok().body(body);
    }

}
