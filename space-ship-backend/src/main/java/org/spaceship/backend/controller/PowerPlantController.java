package org.spaceship.backend.controller;

import jakarta.validation.Valid;
import org.spaceship.backend.controller.dto.PowerPlantControllerDto;
import org.spaceship.backend.controller.mapper.PowerPlantMapper;
import org.spaceship.backend.service.PowerPlantService;
import org.spaceship.backend.service.dto.PowerPlantServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoint for performing a health check on the application.
 * @return A ResponseEntity containing a HealthAppStatusControllerDto with the status of the application.
 */
@RestController
@RequestMapping(path = "/power_plant")
public class PowerPlantController extends AbstractController {

    final PowerPlantService powerPlantService;

    /**
     * Constructor for the PowerPlantController.
     * @param powerPlantService The service for handling power plant-related operations.
     */
    @Autowired
    public PowerPlantController(PowerPlantService powerPlantService) {
        this.powerPlantService = powerPlantService;
    }

    /**
     * Endpoint for getting the status of the spaceship's power plant.
     * @return A ResponseEntity containing a PowerPlantControllerDto with the status of the power plant.
     */
    @GetMapping(value="/status", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PowerPlantControllerDto> getStatus() {
        PowerPlantServiceDto powerPlantServiceDto = powerPlantService.get();
        PowerPlantControllerDto powerPlantControllerDto = PowerPlantMapper.serviceToController(powerPlantServiceDto);
        return successMessage(powerPlantControllerDto);
    }

    /**
     * Endpoint for updating the status of the spaceship's power plant.
     * @param powerPlantControllerDto The new status of the power plant.
     * @return A ResponseEntity containing a PowerPlantControllerDto with the updated status of the power plant.
     */
//    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/update", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PowerPlantControllerDto> update(@RequestBody @Valid @NonNull PowerPlantControllerDto powerPlantControllerDto) {
        LOG.info("update with parameters: powerPlantDto={}", powerPlantControllerDto);

        PowerPlantServiceDto powerPlant = PowerPlantMapper.controllerToService(powerPlantControllerDto);
        PowerPlantServiceDto responseEntity = powerPlantService.update(powerPlant);
        PowerPlantControllerDto response = PowerPlantMapper.serviceToController(responseEntity);
        return successMessage(response);
    }

}
