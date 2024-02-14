package org.spaceship.backend.service;

import org.spaceship.backend.service.dto.ShieldServiceDto;
import org.spaceship.backend.service.entity.ShieldEntity;
import org.spaceship.backend.service.mapper.ShieldServiceMapper;
import org.spaceship.backend.service.repository.ShieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShieldService implements ServiceInterface<ShieldServiceDto, ShieldServiceDto> {

    private final ShieldRepository shieldRepository;

    @Autowired
    public ShieldService(ShieldRepository shieldRepository) {
        this.shieldRepository = shieldRepository;
    }

    @Override
    public ShieldServiceDto get() {
        ShieldServiceDto result = null;

        Optional<ShieldEntity> shieldOptional = shieldRepository.findById(1L);
        if(shieldOptional.isPresent()) {
            result = ShieldServiceMapper.entityToDto(shieldOptional.get());
        }
        return result;
    }

    @Override
    public ShieldServiceDto create(ShieldServiceDto shieldServiceDto) {
        ShieldEntity shieldEntity = ShieldServiceMapper.dtoToEntity(shieldServiceDto);
        ShieldEntity shieldEntityCreated = shieldRepository.save(shieldEntity);
        return ShieldServiceMapper.entityToDto(shieldEntityCreated);
    }

    @Override
    public ShieldServiceDto update(ShieldServiceDto shieldServiceDto) {
        ShieldServiceDto result = null;

        // Identifier fixed because there is only one shield, for now.
        Optional<ShieldEntity> shieldEntityOptional = shieldRepository.findById(1L);
        if (shieldEntityOptional.isPresent()) {
            ShieldEntity shieldEntityToUpdate = shieldEntityOptional.get();
            if(shieldServiceDto.powerStatus() != null)
                shieldEntityToUpdate.setPowerStatus(shieldServiceDto.powerStatus());
            if(shieldServiceDto.powerConsumption() != null)
                shieldEntityToUpdate.setPowerConsumption(shieldServiceDto.powerConsumption());
            if(shieldServiceDto.capacity() != null)
                shieldEntityToUpdate.setCapacity(shieldServiceDto.capacity());
            result = ShieldServiceMapper.entityToDto(
                    shieldRepository.save(shieldEntityToUpdate));
        }
        return result;
    }

}
