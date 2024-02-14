package org.spaceship.backend.controller;

import org.spaceship.backend.controller.dto.EngineControllerDto;
import org.spaceship.backend.controller.dto.PowerPlantControllerDto;
import org.spaceship.backend.controller.dto.ShieldControllerDto;
import org.spaceship.backend.controller.dto.ShipStatusControllerDto;
import org.spaceship.backend.controller.mapper.EngineMapper;
import org.spaceship.backend.controller.mapper.PowerPlantMapper;
import org.spaceship.backend.controller.mapper.ShieldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spaceship.backend.service.EngineService;
import org.spaceship.backend.service.ManagementsService;
import org.spaceship.backend.service.PowerPlantService;
import org.spaceship.backend.service.ShieldService;
import org.spaceship.backend.service.dto.EngineServiceDto;
import org.spaceship.backend.service.dto.PowerPlantServiceDto;
import org.spaceship.backend.service.dto.ShieldServiceDto;
import org.spaceship.backend.threads.ShipManagementThread;
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
public class ShipStatusController {

    private static final Logger LOG = LoggerFactory.getLogger(ShipStatusController.class);
    private final ShieldService shieldService;
    private final PowerPlantService powerPlantService;
    private final EngineService engineService;

    @Autowired
    public ShipStatusController(ShieldService shieldService, PowerPlantService powerPlantService, EngineService engineService) {
        this.shieldService = shieldService;
        this.powerPlantService = powerPlantService;
        this.engineService = engineService;

        ManagementsService managementsService = new ManagementsService();
        ShipManagementThread shipManagementThread = new ShipManagementThread(managementsService);
        Thread thread = new Thread(shipManagementThread);
        thread.start();
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value="/ship_status", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ShipStatusControllerDto> getShipStatus() {
//        LOG.info("getShipStatus performed.");

        ShieldServiceDto shield = shieldService.get();
        PowerPlantServiceDto powerPlant = powerPlantService.get();
        EngineServiceDto engine = engineService.get();

        ShieldControllerDto shieldControllerDto = ShieldMapper.serviceToController(shield);
        PowerPlantControllerDto powerPlantControllerDto = PowerPlantMapper.serviceToController(powerPlant);
        EngineControllerDto engineControllerDto = EngineMapper.serviceToController(engine);

        ShipStatusControllerDto shipStatusControllerDto = new ShipStatusControllerDto(powerPlantControllerDto, engineControllerDto, shieldControllerDto);

        return ResponseEntity.ok().body(shipStatusControllerDto);
    }

}
