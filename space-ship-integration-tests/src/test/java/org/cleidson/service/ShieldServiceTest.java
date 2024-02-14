package org.cleidson.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.spaceship.backend.service.ShieldService;
import org.spaceship.backend.service.dto.ShieldServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShieldServiceTest {

    final ShieldService victim;

    @Autowired
    public ShieldServiceTest(ShieldService shieldService) {
        this.victim = shieldService;
    }

    @Test
    @Order(1)
    void shouldCreateShieldData() {
        ShieldServiceDto shieldExpected = new ShieldServiceDto(Boolean.TRUE, 1000, 5000);
        ShieldServiceDto shieldObtained = victim.create(shieldExpected);
        assertShield(shieldExpected,shieldObtained);
    }

    @Test
    @Order(2)
    void shouldGetShieldsData() {
        ShieldServiceDto shieldExpected = new ShieldServiceDto(Boolean.FALSE, 1000, 5000);
        ShieldServiceDto shieldObtained = victim.get();
        assertShield(shieldExpected, shieldObtained);
    }

    @Test
    @Order(3)
    void shouldUpdateShieldData() {
        ShieldServiceDto shieldExpected = new ShieldServiceDto(Boolean.TRUE, 1000, 5000);
        ShieldServiceDto shieldObtained = victim.update(shieldExpected);
        assertShield(shieldExpected, shieldObtained);
    }

    private void assertShield(ShieldServiceDto shieldExpected, ShieldServiceDto shieldObtained) {
        Assertions.assertEquals(shieldExpected.powerStatus(), shieldObtained.powerStatus());
        Assertions.assertEquals(shieldExpected.powerConsumption(), shieldObtained.powerConsumption());
        Assertions.assertEquals(shieldExpected.capacity(), shieldObtained.capacity());
    }
}
