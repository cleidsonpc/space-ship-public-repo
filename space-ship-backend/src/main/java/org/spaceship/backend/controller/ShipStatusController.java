package org.spaceship.backend.controller;

import org.spaceship.backend.controller.dto.EngineControllerDto;
import org.spaceship.backend.controller.dto.PowerPlantControllerDto;
import org.spaceship.backend.controller.dto.ShieldControllerDto;
import org.spaceship.backend.controller.dto.ShipStatusControllerDto;
import org.spaceship.backend.controller.mapper.EngineMapper;
import org.spaceship.backend.controller.mapper.PowerPlantMapper;
import org.spaceship.backend.controller.mapper.ShieldMapper;
import org.spaceship.backend.service.EngineService;
import org.spaceship.backend.service.PowerPlantService;
import org.spaceship.backend.service.ShieldService;
import org.spaceship.backend.service.dto.EngineServiceDto;
import org.spaceship.backend.service.dto.PowerPlantServiceDto;
import org.spaceship.backend.service.dto.ShieldServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@CrossOrigin(origins = "http://localhost:3000")
public class ShipStatusController extends AbstractController {

    private final ShieldService shieldService;
    private final PowerPlantService powerPlantService;
    private final EngineService engineService;

    /**
     * Constructor for the ShipStatusController.
     * @param shieldService The service for handling shield-related operations.
     * @param powerPlantService The service for handling power plant-related operations.
     * @param engineService The service for handling engine-related operations.
     */
    @Autowired
    public ShipStatusController(ShieldService shieldService, PowerPlantService powerPlantService, EngineService engineService) {
        this.shieldService = shieldService;
        this.powerPlantService = powerPlantService;
        this.engineService = engineService;
    }

    /**
     * Endpoint for getting the status of the spaceship.
     * This includes the status of the shield, power plant, and engine.
     * @return A ResponseEntity containing a ShipStatusControllerDto with the status of the spaceship.
     */
//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value="/ship_status", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ShipStatusControllerDto> getShipStatus() {
        // Shield data
        ShieldServiceDto shield = shieldService.get();
        ShieldControllerDto shieldControllerDto = ShieldMapper.serviceToController(shield);

        // Power plant data
        PowerPlantServiceDto powerPlant = powerPlantService.get();
        PowerPlantControllerDto powerPlantControllerDto = PowerPlantMapper.serviceToController(powerPlant);

        // Engine data
        EngineServiceDto engine = engineService.get();
        EngineControllerDto engineControllerDto = EngineMapper.serviceToController(engine);

        ShipStatusControllerDto shipStatusControllerDto = new ShipStatusControllerDto(powerPlantControllerDto, engineControllerDto, shieldControllerDto);

        return successMessage(shipStatusControllerDto);
    }

}
