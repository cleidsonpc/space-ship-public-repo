package org.spaceship.backend.service.mapper;

import org.spaceship.backend.service.dto.EngineServiceDto;
import org.spaceship.backend.service.entity.EngineEntity;

public class EngineServiceMapper {

    public static EngineEntity dtoToEntity(EngineServiceDto engineServiceDto) {
        EngineEntity engineEntity = new EngineEntity();
        engineEntity.setPowerStatus(engineServiceDto.powerStatus());
        engineEntity.setPowerConsumption(engineServiceDto.powerConsumption());
        return engineEntity;
    }

    public static EngineServiceDto entityToDto(EngineEntity engineEntity) {
        return new EngineServiceDto(engineEntity.getPowerStatus(), engineEntity.getPowerConsumption());
    }

}
