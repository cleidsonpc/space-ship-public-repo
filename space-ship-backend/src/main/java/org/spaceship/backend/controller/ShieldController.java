package org.spaceship.backend.controller;

import jakarta.validation.Valid;
import org.spaceship.backend.controller.dto.ShieldControllerDto;
import org.spaceship.backend.controller.mapper.ShieldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spaceship.backend.service.ShieldService;
import org.spaceship.backend.service.dto.ShieldServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/shield")
public class ShieldController {

    private static final Logger LOG = LoggerFactory.getLogger(ShieldController.class);

    final ShieldService shieldService;

    @Autowired
    public ShieldController(ShieldService shieldService) {
        this.shieldService = shieldService;
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path="/update", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ShieldControllerDto> update(@RequestBody @Valid ShieldControllerDto shieldControllerDto) {
        LOG.info("update with parameters: shieldDto={}", shieldControllerDto);

        ShieldServiceDto shield = ShieldMapper.controllerToService(shieldControllerDto);
        ShieldServiceDto responseEntity = shieldService.update(shield);
        ShieldControllerDto response = ShieldMapper.serviceToController(responseEntity);
        return ResponseEntity.ok().body(response);
    }
}
