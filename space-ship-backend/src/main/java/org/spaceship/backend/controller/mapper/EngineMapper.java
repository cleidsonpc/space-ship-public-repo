package org.spaceship.backend.controller.mapper;

import org.spaceship.backend.controller.dto.EngineControllerDto;
import org.spaceship.backend.service.dto.EngineServiceDto;

public class EngineMapper {

    public static EngineServiceDto controllerToService(EngineControllerDto engineControllerDto) {
        return new EngineServiceDto(engineControllerDto.powerStatus(), engineControllerDto.powerConsumption());
    }

    public static EngineControllerDto serviceToController(EngineServiceDto engineServiceDto) {
        return new EngineControllerDto(engineServiceDto.powerStatus(), engineServiceDto.powerConsumption());
    }

}
