package org.cleidson.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.spaceship.backend.service.PowerPlantService;
import org.spaceship.backend.service.dto.PowerPlantServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class PowerPlantServiceTest {

    final PowerPlantService victim;

    @Autowired
    public PowerPlantServiceTest(PowerPlantService powerPlantService) {
        this.victim = powerPlantService;
    }

    @Test
    @Order(1)
    void shouldCreatePowerPlant() {
        PowerPlantServiceDto powerPlantExpected = new PowerPlantServiceDto(Boolean.TRUE, 5000);
        PowerPlantServiceDto powerPlantObtained = victim.create(powerPlantExpected);
        assertPowerPlant(powerPlantExpected, powerPlantObtained);
    }

    @Test
    @Order(2)
    void shouldGetPowerPlantData() {
        PowerPlantServiceDto powerPlantExpected = new PowerPlantServiceDto(Boolean.FALSE, 5000);
        PowerPlantServiceDto powerPlantObtained = victim.get();
        assertPowerPlant(powerPlantExpected, powerPlantObtained);
    }

    @Test
    @Order(3)
    void shouldUpdatePowerPlantData() {
        PowerPlantServiceDto powerPlantExpected = new PowerPlantServiceDto(Boolean.TRUE, 5000);
        PowerPlantServiceDto powerPlantObtained = victim.update(powerPlantExpected);
        assertPowerPlant(powerPlantExpected, powerPlantObtained);
    }

    private void assertPowerPlant(PowerPlantServiceDto powerPlantExpected, PowerPlantServiceDto powerPlantObtained) {
        Assertions.assertEquals(powerPlantExpected.powerStatus(), powerPlantObtained.powerStatus());
        Assertions.assertEquals(powerPlantExpected.energyAvailable(), powerPlantObtained.energyAvailable());
    }
}
