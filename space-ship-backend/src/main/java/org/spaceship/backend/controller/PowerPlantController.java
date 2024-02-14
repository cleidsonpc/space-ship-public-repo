package org.spaceship.backend.controller;

import jakarta.validation.Valid;
import org.spaceship.backend.controller.dto.PowerPlantControllerDto;
import org.spaceship.backend.controller.mapper.PowerPlantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spaceship.backend.service.PowerPlantService;
import org.spaceship.backend.service.dto.PowerPlantServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/power_plant")
public class PowerPlantController {

    private static final Logger LOG = LoggerFactory.getLogger(PowerPlantController.class);

    final PowerPlantService powerPlantService;

    @Autowired
    public PowerPlantController(PowerPlantService powerPlantService) {
        this.powerPlantService = powerPlantService;
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/update", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PowerPlantControllerDto> update(@RequestBody @Valid PowerPlantControllerDto powerPlantControllerDto) {
        LOG.info("update with parameters: powerPlantDto={}", powerPlantControllerDto);

        PowerPlantServiceDto powerPlant = PowerPlantMapper.controllerToService(powerPlantControllerDto);
        PowerPlantServiceDto responseEntity = powerPlantService.update(powerPlant);
        PowerPlantControllerDto response = PowerPlantMapper.serviceToController(responseEntity);
        return ResponseEntity.ok().body(response);
    }

}
