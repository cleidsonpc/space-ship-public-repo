package org.spaceship.backend.controller;

import org.spaceship.backend.controller.dto.EngineControllerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spaceship.backend.service.EngineService;
import org.spaceship.backend.service.dto.EngineServiceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class EngineControllerTest {

    private EngineController victim;

    @Mock
    private EngineService engineService;

    @BeforeEach
    public void before() {
        this.victim = new EngineController(engineService);
    }

    @Test
    void shouldGetEngineStatus() {
        EngineServiceDto engineServiceDto = new EngineServiceDto(Boolean.TRUE, 1000);
        Mockito.when(engineService.get()).thenReturn(engineServiceDto);

        ResponseEntity<EngineControllerDto> obtained = victim.getStatus();

        Assertions.assertEquals(HttpStatus.OK, obtained.getStatusCode());
        Assertions.assertNotNull(obtained.getBody());
        assertEngineDto(obtained.getBody());
    }

    @Test
    void shouldUpdateEngineToDisabled() {
        EngineServiceDto engineServiceDto = new EngineServiceDto(Boolean.TRUE, 1000);
        Mockito.when(engineService.update(Mockito.any())).thenReturn(engineServiceDto);

        EngineControllerDto engineControllerDto = new EngineControllerDto(Boolean.TRUE, 1000);
        ResponseEntity<EngineControllerDto> obtained = victim.update(engineControllerDto);

        Mockito.verify(engineService).update(Mockito.any());
        Assertions.assertEquals(HttpStatus.OK, obtained.getStatusCode());
        Assertions.assertNotNull(obtained.getBody());
        assertEngineDto(obtained.getBody());
    }

    private void assertEngineDto(EngineControllerDto obtained) {
        EngineControllerDto expected = new EngineControllerDto(Boolean.TRUE, 1000);
        Assertions.assertEquals(expected.powerStatus(), obtained.powerStatus());
        Assertions.assertEquals(expected.powerConsumption(), obtained.powerConsumption());
    }
}
