package org.spaceship.backend.controller.mapper;

import org.spaceship.backend.controller.dto.ShieldControllerDto;
import org.spaceship.backend.service.dto.ShieldServiceDto;

public class ShieldMapper {

    public static ShieldServiceDto controllerToService(ShieldControllerDto shieldControllerDto) {
        return new ShieldServiceDto(shieldControllerDto.powerStatus(), shieldControllerDto.powerConsumption(), shieldControllerDto.capacity());
    }

    public static ShieldControllerDto serviceToController(ShieldServiceDto shieldServiceDto) {
        return new ShieldControllerDto(shieldServiceDto.powerStatus(), shieldServiceDto.powerConsumption(), shieldServiceDto.capacity());
    }

}
