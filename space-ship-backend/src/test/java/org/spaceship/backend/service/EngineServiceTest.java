package org.spaceship.backend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spaceship.backend.service.dto.EngineServiceDto;
import org.spaceship.backend.service.entity.EngineEntity;
import org.spaceship.backend.service.repository.EngineRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EngineServiceTest {

    private EngineService victim;

    @Mock
    private EngineRepository engineRepository;

    @BeforeEach
    public void beforeEach() {
        victim = new EngineService(engineRepository);
    }

    @Test
    void shouldCreateEngine() {
        EngineServiceDto engineExpected = buildEngineServiceDto();

        EngineEntity engineEntityMock = buildEngineEntity();
        engineEntityMock.setId(null);
        Mockito.when(engineRepository.save(Mockito.eq(engineEntityMock))).thenReturn(engineEntityMock);

        EngineServiceDto engineObtained = victim.create(engineExpected);
        assertEngine(engineExpected, engineObtained);
    }

    @Test
    void shouldGetEngineData() {
        EngineServiceDto engineExpected = buildEngineServiceDto();

        EngineEntity engineEntityMock = buildEngineEntity();
        Mockito.when(engineRepository.findById(1L)).thenReturn(Optional.of(engineEntityMock));

        EngineServiceDto engineObtained = victim.get();
        assertEngine(engineExpected, engineObtained);
    }

    @Test
    void shouldUpdateEngineData() {
        EngineServiceDto engineExpected = buildEngineServiceDto();

        EngineEntity engineEntityMockOriginal = buildEngineEntity();
        engineEntityMockOriginal.setPowerStatus(Boolean.FALSE);
        engineEntityMockOriginal.setPowerConsumption(0);
        EngineEntity engineEntityMockUpdated = buildEngineEntity();
        Mockito.when(engineRepository.findById(1L)).thenReturn(Optional.of(engineEntityMockOriginal));
        Mockito.when(engineRepository.save(Mockito.eq(engineEntityMockUpdated))).thenReturn(engineEntityMockUpdated);

        EngineServiceDto engineObtained = victim.update(engineExpected);
        assertEngine(engineExpected, engineObtained);
    }

    private EngineServiceDto buildEngineServiceDto() {
        return new EngineServiceDto(Boolean.TRUE, 5000);
    }

    private EngineEntity buildEngineEntity() {
        EngineEntity engineEntity = new EngineEntity();
        engineEntity.setId(1L);
        engineEntity.setPowerStatus(Boolean.TRUE);
        engineEntity.setPowerConsumption(5000);
        return engineEntity;
    }

    private void assertEngine(EngineServiceDto engineExpected, EngineServiceDto engineObtained) {
        Assertions.assertEquals(engineExpected.powerStatus(), engineObtained.powerStatus());
        Assertions.assertEquals(engineExpected.powerConsumption(), engineObtained.powerConsumption());
    }
}
