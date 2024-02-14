package org.spaceship.backend.service.mapper;

import org.spaceship.backend.service.dto.ShieldServiceDto;
import org.spaceship.backend.service.entity.ShieldEntity;

public class ShieldServiceMapper {

    public static ShieldEntity dtoToEntity(ShieldServiceDto shieldServiceDto) {
        ShieldEntity shieldEntity = new ShieldEntity();
        shieldEntity.setPowerStatus(shieldServiceDto.powerStatus());
        shieldEntity.setPowerConsumption(shieldServiceDto.powerConsumption());
        shieldEntity.setCapacity(shieldServiceDto.capacity());
        return shieldEntity;
    }

    public static ShieldServiceDto entityToDto(ShieldEntity shieldEntity) {
        return new ShieldServiceDto(shieldEntity.getPowerStatus(), shieldEntity.getPowerConsumption(), shieldEntity.getCapacity());
    }

}
