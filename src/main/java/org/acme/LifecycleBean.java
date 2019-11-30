package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class LifecycleBean {

    private static final Logger LOGGER = LoggerFactory.getLogger("LifecycleBean");

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The application is starting...");
    }


    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
    }

}