package org.spaceship.backend.service.repository;

import org.spaceship.backend.service.entity.PowerPlantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PowerPlantRepository extends JpaRepository<PowerPlantEntity, Long> {

}
