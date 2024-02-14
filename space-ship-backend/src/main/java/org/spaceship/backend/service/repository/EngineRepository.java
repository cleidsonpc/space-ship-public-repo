package org.spaceship.backend.service.repository;

import org.spaceship.backend.service.entity.EngineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineRepository extends JpaRepository<EngineEntity, Long> {
}
