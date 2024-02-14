package org.spaceship.backend.controller.dto;

public record ShipStatusControllerDto(PowerPlantControllerDto powerPlantControllerDto, EngineControllerDto engineControllerDto, ShieldControllerDto shieldControllerDto) {

}
