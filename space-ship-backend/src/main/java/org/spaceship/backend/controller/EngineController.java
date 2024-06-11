package org.spaceship.backend.controller;

import jakarta.validation.Valid;
import org.spaceship.backend.controller.dto.EngineControllerDto;
import org.spaceship.backend.controller.mapper.EngineMapper;
import org.spaceship.backend.service.EngineService;
import org.spaceship.backend.service.dto.EngineServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling requests related to the spaceship's engine.
 * This controller handles the "/engine" endpoint.
 */
@RestController
@RequestMapping(path = "/engine")
public class EngineController extends AbstractController {

    final EngineService engineService;

    /**
     * Constructor for the EngineController.
     * @param engineService The service for handling engine-related operations.
     */
    @Autowired
    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    /**
     * Endpoint for getting the status of the spaceship's engine.
     * @return A ResponseEntity containing an EngineControllerDto with the status of the engine.
     */
    @GetMapping(value="/status", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EngineControllerDto> getStatus() {
        EngineServiceDto engine = engineService.get();
        EngineControllerDto engineControllerDto = EngineMapper.serviceToController(engine);
        return successMessage(engineControllerDto);
    }

    /**
     * Endpoint for updating the status of the spaceship's engine.
     * @param engineControllerDto The new status of the engine.
     * @return A ResponseEntity containing an EngineControllerDto with the updated status of the engine.
     */
//    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path="/update", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EngineControllerDto> update(@RequestBody @Valid @NonNull EngineControllerDto engineControllerDto) {
        LOG.info("update with parameters: engineDto={}", engineControllerDto);

        EngineServiceDto engineServiceDto = EngineMapper.controllerToService(engineControllerDto);
        EngineServiceDto responseEntity = engineService.update(engineServiceDto);
        EngineControllerDto response = EngineMapper.serviceToController(responseEntity);
        return successMessage(response);
    }
}
