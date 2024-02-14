package org.spaceship.backend.service.entity;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "POWER_PLANT")
public class PowerPlantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Boolean powerStatus;

    @Column
    private Integer energyAvailable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerPlantEntity that = (PowerPlantEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(powerStatus, that.powerStatus) && Objects.equals(energyAvailable, that.energyAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, powerStatus, energyAvailable);
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

    public Integer getEnergyAvailable() {
        return energyAvailable;
    }

    public void setEnergyAvailable(Integer energyAvailable) {
        this.energyAvailable = energyAvailable;
    }
}
