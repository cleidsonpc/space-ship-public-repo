package org.spaceship.backend.service;

public interface ServiceInterface<IN, OUT> {
    OUT get();

    OUT create(IN entity);

    OUT update(IN entity);
}
