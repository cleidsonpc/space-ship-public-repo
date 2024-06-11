package org.spaceship.backend.controller;

import org.spaceship.backend.controller.dto.EngineControllerDto;
import org.spaceship.backend.controller.dto.ShieldControllerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spaceship.backend.service.ShieldService;
import org.spaceship.backend.service.dto.EngineServiceDto;
import org.spaceship.backend.service.dto.ShieldServiceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ShieldControllerTest {

    private ShieldController victim;

    @Mock
    private ShieldService shieldService;

    @BeforeEach
    public void before() {
        this.victim = new ShieldController(shieldService);
    }

    @Test
    void shouldGetEngineStatus() {
        ShieldServiceDto shieldServiceDto = new ShieldServiceDto(Boolean.TRUE, 1000, 5000);
        Mockito.when(shieldService.get()).thenReturn(shieldServiceDto);

        ResponseEntity<ShieldControllerDto> obtained = victim.getStatus();

        Assertions.assertEquals(HttpStatus.OK, obtained.getStatusCode());
        Assertions.assertNotNull(obtained.getBody());
        assertShieldDto(obtained.getBody());
    }

    @Test
    void shouldUpdateShieldToDisabled() {
        ShieldServiceDto shieldServiceDto = new ShieldServiceDto(Boolean.TRUE, 1000, 5000);
        Mockito.when(shieldService.update(Mockito.any())).thenReturn(shieldServiceDto);

        ShieldControllerDto shieldControllerDto = new ShieldControllerDto(Boolean.TRUE, 1000, 5000);
        ResponseEntity<ShieldControllerDto> obtained = victim.update(shieldControllerDto);

        Mockito.verify(shieldService).update(Mockito.any());
        Assertions.assertEquals(HttpStatus.OK, obtained.getStatusCode());
        Assertions.assertNotNull(obtained.getBody());
        assertShieldDto(obtained.getBody());
    }

    private void assertShieldDto(ShieldControllerDto obtained) {
        ShieldControllerDto expected = new ShieldControllerDto(Boolean.TRUE, 1000, 5000);
        Assertions.assertEquals(expected.powerStatus(), obtained.powerStatus());
        Assertions.assertEquals(expected.powerConsumption(), obtained.powerConsumption());
        Assertions.assertEquals(expected.capacity(), obtained.capacity());
    }

}
