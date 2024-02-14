package org.spaceship.backend.controller;

import org.spaceship.backend.controller.dto.PowerPlantControllerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spaceship.backend.service.PowerPlantService;
import org.spaceship.backend.service.dto.PowerPlantServiceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class PowerPlantControllerTest {

    private PowerPlantController victim;

    @Mock
    private PowerPlantService powerPlantService;

    @BeforeEach
    public void before() {
        this.victim = new PowerPlantController(powerPlantService);
    }

    @Test
    void shouldUpdatePowerPlantToDisabled() {
        PowerPlantServiceDto powerPlantServiceDto = new PowerPlantServiceDto(Boolean.TRUE, 5000);
        Mockito.when(powerPlantService.update(Mockito.any())).thenReturn(powerPlantServiceDto);

        PowerPlantControllerDto powerPlantControllerDto = new PowerPlantControllerDto(Boolean.TRUE, 100);
        ResponseEntity<PowerPlantControllerDto> obtained = victim.update(powerPlantControllerDto);

        Mockito.verify(powerPlantService).update(Mockito.any());
        Assertions.assertEquals(HttpStatus.OK, obtained.getStatusCode());
        Assertions.assertNotNull(obtained.getBody());
        assertPowerPlantDto(obtained.getBody());
    }

    private void assertPowerPlantDto(PowerPlantControllerDto obtained) {
        PowerPlantControllerDto expected = new PowerPlantControllerDto(Boolean.TRUE, 5000);
        Assertions.assertEquals(expected.powerStatus(), obtained.powerStatus());
        Assertions.assertEquals(expected.energyAvailable(), obtained.energyAvailable());
    }
}
