package org.spaceship.backend.service;

import org.spaceship.backend.service.dto.PowerPlantServiceDto;
import org.spaceship.backend.service.entity.PowerPlantEntity;
import org.spaceship.backend.service.mapper.PowerPlantServiceMapper;
import org.spaceship.backend.service.repository.PowerPlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PowerPlantService implements ServiceInterface<PowerPlantServiceDto, PowerPlantServiceDto> {

    private final PowerPlantRepository powerPlantRepository;

    @Autowired
    public PowerPlantService(PowerPlantRepository powerPlantRepository) {
        this.powerPlantRepository = powerPlantRepository;
    }

    @Override
    public PowerPlantServiceDto get() {
        PowerPlantServiceDto result = null;

        Optional<PowerPlantEntity> powerPlantEntityOptional = powerPlantRepository.findById(1L);
        if(powerPlantEntityOptional.isPresent()) {
            result = PowerPlantServiceMapper.entityToDto(powerPlantEntityOptional.get());
        }
        return result;
    }

    @Override
    public PowerPlantServiceDto create(PowerPlantServiceDto powerPlantServiceDto) {
        PowerPlantEntity powerPlantEntity = PowerPlantServiceMapper.dtoToEntity(powerPlantServiceDto);
        PowerPlantEntity powerPlantEntityCreated = powerPlantRepository.save(powerPlantEntity);
        return PowerPlantServiceMapper.entityToDto(powerPlantEntityCreated);
    }

    @Override
    public PowerPlantServiceDto update(PowerPlantServiceDto powerPlantServiceDto) {
        PowerPlantServiceDto result = null;

        // Identifier fixed because there is only one power plant, for now.
        Optional<PowerPlantEntity> powerPlantEntityObtained = powerPlantRepository.findById(1L);
        if (powerPlantEntityObtained.isPresent()) {
            PowerPlantEntity powerPlantEntityToUpdate = fillEntityUpdate(powerPlantEntityObtained.get(), powerPlantServiceDto);
            result = PowerPlantServiceMapper.entityToDto(powerPlantRepository.save(powerPlantEntityToUpdate));
        }
        return result;
    }

    private PowerPlantEntity fillEntityUpdate(PowerPlantEntity newPowerPlantEntityUpdate, PowerPlantServiceDto powerPlantServiceDto) {
        if(powerPlantServiceDto.powerStatus() != null)
            newPowerPlantEntityUpdate.setPowerStatus(powerPlantServiceDto.powerStatus());

        if(powerPlantServiceDto.energyAvailable() != null)
            newPowerPlantEntityUpdate.setEnergyAvailable(powerPlantServiceDto.energyAvailable());

        return newPowerPlantEntityUpdate;
    }
}
