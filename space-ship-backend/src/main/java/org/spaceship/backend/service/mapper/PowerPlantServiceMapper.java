package org.spaceship.backend.service.mapper;

import org.spaceship.backend.service.dto.PowerPlantServiceDto;
import org.spaceship.backend.service.entity.PowerPlantEntity;

public class PowerPlantServiceMapper {

    public static PowerPlantEntity dtoToEntity(PowerPlantServiceDto powerPlantServiceDto) {
        PowerPlantEntity powerPlantEntity = new PowerPlantEntity();
        powerPlantEntity.setPowerStatus(powerPlantServiceDto.powerStatus());
        powerPlantEntity.setEnergyAvailable(powerPlantServiceDto.energyAvailable());
        return powerPlantEntity;
    }

    public static PowerPlantServiceDto entityToDto(PowerPlantEntity powerPlantEntity) {
        return new PowerPlantServiceDto(powerPlantEntity.getPowerStatus(), powerPlantEntity.getEnergyAvailable());
    }

}
