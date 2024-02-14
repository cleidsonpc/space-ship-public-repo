package org.spaceship.backend.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spaceship.backend.service.EngineService;
import org.spaceship.backend.service.PowerPlantService;
import org.spaceship.backend.service.ShieldService;
import org.spaceship.backend.service.dto.EngineServiceDto;
import org.spaceship.backend.service.dto.PowerPlantServiceDto;
import org.spaceship.backend.service.dto.ShieldServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInit {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseInit.class);

    private final ShieldService shieldService;
    private final PowerPlantService powerPlantService;
    private final EngineService engineService;

    @Autowired
    public DatabaseInit(ShieldService shieldService, PowerPlantService powerPlantService, EngineService engineService) {
        this.shieldService = shieldService;
        this.powerPlantService = powerPlantService;
        this.engineService = engineService;
    }

    @Bean
    CommandLineRunner initDatabase() {

        return args -> {
            ShieldServiceDto shieldServiceDto = new ShieldServiceDto(Boolean.FALSE, 1000, 5000);
            ShieldServiceDto shieldSaved = shieldService.create(shieldServiceDto);

            PowerPlantServiceDto powerPlantServiceDto = new PowerPlantServiceDto(Boolean.FALSE, 5000);
            PowerPlantServiceDto powerPlantSaved = powerPlantService.create(powerPlantServiceDto);

            EngineServiceDto engineServiceDto = new EngineServiceDto(Boolean.FALSE, 1000);
            EngineServiceDto engineSaved = engineService.create(engineServiceDto);

            LOG.info("initDatabase => Shield={}, PowerPlant={}, Engine={}",
                    shieldSaved.toString(),
                    powerPlantSaved.toString(),
                    engineSaved.toString()
            );
        };

    }
}
