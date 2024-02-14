package org.spaceship.backend.controller.mapper;

import org.spaceship.backend.controller.dto.PowerPlantControllerDto;
import org.spaceship.backend.service.dto.PowerPlantServiceDto;

public class PowerPlantMapper {

    public static PowerPlantServiceDto controllerToService(PowerPlantControllerDto powerPlantControllerDto) {
        return new PowerPlantServiceDto(powerPlantControllerDto.powerStatus(), powerPlantControllerDto.energyAvailable());
    }

    public static PowerPlantControllerDto serviceToController(PowerPlantServiceDto powerPlantServiceDto) {
        return new PowerPlantControllerDto(powerPlantServiceDto.powerStatus(), powerPlantServiceDto.energyAvailable());
    }

}
