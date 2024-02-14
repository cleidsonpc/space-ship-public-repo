package org.cleidson.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.spaceship.backend.service.EngineService;
import org.spaceship.backend.service.dto.EngineServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class EngineServiceTest {

    final EngineService victim;

    @Autowired
    public EngineServiceTest(EngineService engineService) {
        this.victim = engineService;
    }

    @Test
    @Order(1)
    void shouldCreateEngine() {
        EngineServiceDto engineExpected = new EngineServiceDto(Boolean.TRUE, 5000);
        EngineServiceDto engineObtained = victim.create(engineExpected);
        assertEngine(engineExpected, engineObtained);
    }

    @Test
    @Order(2)
    void shouldGetEngineData() {
        EngineServiceDto engineExpected = new EngineServiceDto(Boolean.FALSE, 1000);
        EngineServiceDto engineObtained = victim.get();
        assertEngine(engineExpected, engineObtained);
    }

    @Test
    @Order(3)
    void shouldUpdateEngineData() {
        EngineServiceDto engineExpected = new EngineServiceDto(Boolean.TRUE, 5000);
        EngineServiceDto engineObtained = victim.update(engineExpected);
        assertEngine(engineExpected, engineObtained);
    }

    private void assertEngine(EngineServiceDto engineExpected, EngineServiceDto engineObtained) {
        Assertions.assertEquals(engineExpected.powerStatus(), engineObtained.powerStatus());
        Assertions.assertEquals(engineExpected.powerConsumption(), engineObtained.powerConsumption());
    }
}
