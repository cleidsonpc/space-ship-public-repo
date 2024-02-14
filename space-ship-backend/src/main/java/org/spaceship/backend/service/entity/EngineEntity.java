package org.spaceship.backend.service.entity;

import jakarta.persistence.*;

import java.util.Objects;

// TODO: 08/08/2023 Rename to "thrusters".

@Entity
@Table(name = "ENGINE")
public class EngineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Boolean powerStatus;

    @Column
    private Integer powerConsumption;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngineEntity engineEntity = (EngineEntity) o;
        return Objects.equals(id, engineEntity.id) && Objects.equals(powerStatus, engineEntity.powerStatus) && Objects.equals(powerConsumption, engineEntity.powerConsumption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, powerStatus, powerConsumption);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPowerStatus() {
        return powerStatus;
    }

    public void setPowerStatus(Boolean powerStatus) {
        this.powerStatus = powerStatus;
    }

    public Integer getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(Integer powerConsumption) {
        this.powerConsumption = powerConsumption;
    }
}
