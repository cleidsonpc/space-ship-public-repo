package org.spaceship.backend.controller;

import jakarta.validation.Valid;
import org.spaceship.backend.controller.dto.EngineControllerDto;
import org.spaceship.backend.controller.mapper.EngineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spaceship.backend.service.EngineService;
import org.spaceship.backend.service.dto.EngineServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/engine")
public class EngineController {

    private static final Logger LOG = LoggerFactory.getLogger(EngineController.class);

    final EngineService engineService;

    @Autowired
    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path="/update", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EngineControllerDto> update(@RequestBody @Valid EngineControllerDto engineControllerDto) {
        LOG.info("update with parameters: engineDto={}", engineControllerDto);

        EngineServiceDto engineServiceDto = EngineMapper.controllerToService(engineControllerDto);
        EngineServiceDto responseEntity = engineService.update(engineServiceDto);
        EngineControllerDto response = EngineMapper.serviceToController(responseEntity);
        return ResponseEntity.ok().body(response);
    }
}
