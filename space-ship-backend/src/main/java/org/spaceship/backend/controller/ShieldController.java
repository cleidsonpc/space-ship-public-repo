package org.spaceship.backend.controller;

import jakarta.validation.Valid;
import org.spaceship.backend.controller.dto.ShieldControllerDto;
import org.spaceship.backend.controller.mapper.ShieldMapper;
import org.spaceship.backend.service.ShieldService;
import org.spaceship.backend.service.dto.ShieldServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling requests related to the spaceship's shield.
 * This controller handles the "/shield" endpoint.
 */
@RestController
@RequestMapping(path = "/shield")
public class ShieldController extends AbstractController {

    final ShieldService shieldService;

    /**
     * Constructor for the ShieldController.
     * @param shieldService The service for handling shield-related operations.
     */
    @Autowired
    public ShieldController(ShieldService shieldService) {
        this.shieldService = shieldService;
    }

    /**
     * Endpoint for getting the status of the spaceship's shield.
     * @return A ResponseEntity containing a ShieldControllerDto with the status of the shield.
     */
    @GetMapping(value="/status", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ShieldControllerDto> getStatus() {
        ShieldServiceDto shieldServiceDto = shieldService.get();
        ShieldControllerDto shieldControllerDto = ShieldMapper.serviceToController(shieldServiceDto);
        return successMessage(shieldControllerDto);
    }

    /**
     * Endpoint for updating the status of the spaceship's shield.
     * @param shieldControllerDto The new status of the shield.
     * @return A ResponseEntity containing a ShieldControllerDto with the updated status of the shield.
     */
//    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path="/update", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ShieldControllerDto> update(@RequestBody @Valid @NonNull ShieldControllerDto shieldControllerDto) {
        LOG.info("update with parameters: shieldDto={}", shieldControllerDto);

        ShieldServiceDto shield = ShieldMapper.controllerToService(shieldControllerDto);
        ShieldServiceDto responseEntity = shieldService.update(shield);
        ShieldControllerDto response = ShieldMapper.serviceToController(responseEntity);
        return successMessage(response);
    }
}
