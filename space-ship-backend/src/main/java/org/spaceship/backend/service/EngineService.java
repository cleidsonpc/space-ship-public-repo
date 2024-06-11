package org.spaceship.backend.service;

import org.spaceship.backend.service.dto.EngineServiceDto;
import org.spaceship.backend.service.entity.EngineEntity;
import org.spaceship.backend.service.mapper.EngineServiceMapper;
import org.spaceship.backend.service.repository.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EngineService implements ServiceInterface<EngineServiceDto, EngineServiceDto> {

    private final EngineRepository engineRepository;

    @Autowired
    public EngineService(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    @Override
    public EngineServiceDto get() {
        EngineServiceDto result = null;

        Optional<EngineEntity> engineOptional = engineRepository.findById(1L);
        if(engineOptional.isPresent()) {
            result = EngineServiceMapper.entityToDto(engineOptional.get());
        }
        return result;
    }

    @Override
    public EngineServiceDto create(EngineServiceDto engineServiceDto) {
        EngineEntity engineEntity = EngineServiceMapper.dtoToEntity(engineServiceDto);
        EngineEntity engineEntityCreate = engineRepository.save(engineEntity);
        return  EngineServiceMapper.entityToDto(engineEntityCreate);
    }

    @Override
    public EngineServiceDto update(EngineServiceDto engineServiceDto) {

        EngineServiceDto result = null;

        // Identifier fixed because there is only one engine, for now.
        Optional<EngineEntity> engineOptional = engineRepository.findById(1L);
        if(engineOptional.isPresent()) {
            EngineEntity engineEntityToUpdate = fillEntityUpdate(engineOptional.get(), engineServiceDto);
            result = EngineServiceMapper.entityToDto(engineRepository.save(engineEntityToUpdate));
        }
        return result;
    }

    private EngineEntity fillEntityUpdate(EngineEntity newEngineEntityUpdate, EngineServiceDto engineServiceDto) {
        if(engineServiceDto.powerStatus() != null)
            newEngineEntityUpdate.setPowerStatus(engineServiceDto.powerStatus());

        if(engineServiceDto.powerConsumption() != null)
            newEngineEntityUpdate.setPowerConsumption(engineServiceDto.powerConsumption());

        return newEngineEntityUpdate;
    }
}
