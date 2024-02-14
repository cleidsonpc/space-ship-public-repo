package org.spaceship.backend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spaceship.backend.service.dto.PowerPlantServiceDto;
import org.spaceship.backend.service.entity.PowerPlantEntity;
import org.spaceship.backend.service.repository.PowerPlantRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PowerPlantServiceTest {

    // TODO: 09/08/2023 refactor to unit test

    private PowerPlantService victim;

    @Mock
    private PowerPlantRepository powerPlantRepository;

    @BeforeEach
    public void beforeEach() {
        victim = new PowerPlantService(powerPlantRepository);
    }

    @Test
    void shouldCreatePowerPlant() {
        PowerPlantServiceDto powerPlantExpected = buildPowerPlantServiceDto();

        PowerPlantEntity powerPlantEntityMock = buildPowerPlantEntity();
        powerPlantEntityMock.setId(null);
        Mockito.when(powerPlantRepository.save(Mockito.eq(powerPlantEntityMock))).thenReturn(powerPlantEntityMock);

        PowerPlantServiceDto powerPlantObtained = victim.create(powerPlantExpected);
        assertPowerPlant(powerPlantExpected, powerPlantObtained);
    }

    @Test
    void shouldGetPowerPlantData() {
        PowerPlantServiceDto powerPlantExpected = buildPowerPlantServiceDto();

        PowerPlantEntity powerPlantEntityMock = buildPowerPlantEntity();
        Mockito.when(powerPlantRepository.findById(1L)).thenReturn(Optional.of(powerPlantEntityMock));

        PowerPlantServiceDto powerPlantObtained = victim.get();
        assertPowerPlant(powerPlantExpected, powerPlantObtained);
    }

    @Test
    void shouldUpdatePowerPlantData() {
        PowerPlantServiceDto powerPlantExpected = buildPowerPlantServiceDto();

        PowerPlantEntity powerPlantEntityMockOriginal = buildPowerPlantEntity();
        powerPlantEntityMockOriginal.setPowerStatus(Boolean.TRUE);
        powerPlantEntityMockOriginal.setEnergyAvailable(0);
        PowerPlantEntity powerPlantEntityMockUpdated = buildPowerPlantEntity();
        Mockito.when(powerPlantRepository.findById(1L)).thenReturn(Optional.of(powerPlantEntityMockOriginal));
        Mockito.when(powerPlantRepository.save(Mockito.eq(powerPlantEntityMockUpdated))).thenReturn(powerPlantEntityMockUpdated);

        PowerPlantServiceDto powerPlantObtained = victim.update(powerPlantExpected);

        assertPowerPlant(powerPlantExpected, powerPlantObtained);
    }

    private PowerPlantServiceDto buildPowerPlantServiceDto() {
        return new PowerPlantServiceDto(Boolean.TRUE, 5000);
    }

    private PowerPlantEntity buildPowerPlantEntity() {
        PowerPlantEntity powerPlantEntity = new PowerPlantEntity();
        powerPlantEntity.setId(1L);
        powerPlantEntity.setPowerStatus(Boolean.TRUE);
        powerPlantEntity.setEnergyAvailable(5000);
        return powerPlantEntity;
    }

    private void assertPowerPlant(PowerPlantServiceDto powerPlantExpected, PowerPlantServiceDto powerPlantObtained) {
        Assertions.assertEquals(powerPlantExpected.powerStatus(), powerPlantObtained.powerStatus());
        Assertions.assertEquals(powerPlantExpected.energyAvailable(), powerPlantObtained.energyAvailable());
    }
}
