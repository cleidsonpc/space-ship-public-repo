package org.spaceship.backend.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spaceship.backend.service.ManagementsService;
import org.springframework.beans.factory.annotation.Autowired;

public class ShipManagementThread implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(ShipManagementThread.class);

    private final ManagementsService managementsService;

    @Autowired
    public ShipManagementThread(ManagementsService managementsService) {
        this.managementsService = managementsService;
    }

    @Override
    public void run() {
        // TODO - review loop and thread sleep
        while(true) {
            try {
                managementsService.powerManagements();
                managementsService.energyManagements();
                Thread.sleep(5000);

            } catch (InterruptedException ie) {
                LOG.error(ie.getMessage());
                throw new RuntimeException(ie.getMessage());
            }
        }
    }


}
