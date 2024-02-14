package org.spaceship.backend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spaceship.backend.service.dto.ShieldServiceDto;
import org.spaceship.backend.service.entity.ShieldEntity;
import org.spaceship.backend.service.repository.ShieldRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ShieldServiceTest {

    // TODO: 09/08/2023 refactor to unit test

    ShieldService victim;

    @Mock
    private ShieldRepository shieldRepository;

    @BeforeEach
    public void beforeEach() {
        victim = new ShieldService(shieldRepository);
    }

    @Test
    void shouldCreateShieldData() {
        ShieldServiceDto shieldExpected = buildShieldServiceDto();

        ShieldEntity shieldEntityMock = buildShieldEntity();
        shieldEntityMock.setId(null);
        Mockito.when(shieldRepository.save(Mockito.eq(shieldEntityMock))).thenReturn(shieldEntityMock);

        ShieldServiceDto shieldObtained = victim.create(shieldExpected);
        assertShield(shieldExpected, shieldObtained);
    }

    @Test
    void shouldGetAllShieldsData() {
        ShieldServiceDto shieldExpected = buildShieldServiceDto();

        ShieldEntity shieldEntityMock = buildShieldEntity();
        Mockito.when(shieldRepository.findById(1L)).thenReturn(Optional.of(shieldEntityMock));

        ShieldServiceDto shieldObtained = victim.get();
        assertShield(shieldExpected, shieldObtained);
    }

    @Test
    void shouldUpdateShieldData() {
        ShieldServiceDto shieldExpected = buildShieldServiceDto();

        ShieldEntity shieldEntityMockOriginal = buildShieldEntity();
        shieldEntityMockOriginal.setPowerStatus(Boolean.FALSE);
        shieldEntityMockOriginal.setPowerConsumption(0);
        shieldEntityMockOriginal.setCapacity(0);
        ShieldEntity shieldEntityMockUpdated = buildShieldEntity();
        Mockito.when(shieldRepository.findById(1L)).thenReturn(Optional.of(shieldEntityMockOriginal));
        Mockito.when(shieldRepository.save(Mockito.eq(shieldEntityMockUpdated))).thenReturn(shieldEntityMockUpdated);

        ShieldServiceDto shieldObtained = victim.update(shieldExpected);
        assertShield(shieldExpected, shieldObtained);
    }

    private ShieldServiceDto buildShieldServiceDto() {
        return new ShieldServiceDto(Boolean.TRUE, 1000, 5000);
    }

    private ShieldEntity buildShieldEntity() {
        ShieldEntity shieldEntity = new ShieldEntity();
        shieldEntity.setId(1L);
        shieldEntity.setPowerStatus(Boolean.TRUE);
        shieldEntity.setPowerConsumption(1000);
        shieldEntity.setCapacity(5000);
        return shieldEntity;
    }

    private void assertShield(ShieldServiceDto shieldExpected, ShieldServiceDto shieldObtained) {
        Assertions.assertEquals(shieldExpected.powerStatus(), shieldObtained.powerStatus());
        Assertions.assertEquals(shieldExpected.powerConsumption(), shieldObtained.powerConsumption());
        Assertions.assertEquals(shieldExpected.capacity(), shieldObtained.capacity());
    }
}
