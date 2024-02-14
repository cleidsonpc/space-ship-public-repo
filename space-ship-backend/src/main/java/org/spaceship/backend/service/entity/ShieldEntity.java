package org.spaceship.backend.service.entity;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "SHIELD")
public class ShieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Boolean powerStatus;

    @Column
    private Integer powerConsumption;

    @Column
    private Integer capacity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShieldEntity shieldEntity = (ShieldEntity) o;
        return Objects.equals(id, shieldEntity.id) && Objects.equals(powerStatus, shieldEntity.powerStatus) && Objects.equals(powerConsumption, shieldEntity.powerConsumption) && Objects.equals(capacity, shieldEntity.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, powerStatus, powerConsumption, capacity);
    }

    // TODO: 07/08/2023 Add ship relationship.

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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
