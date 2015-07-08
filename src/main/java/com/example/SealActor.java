package com.example;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

// Seal street Actor
public class SealActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    public SealActor() {
        receive(ReceiveBuilder.
                        matchEquals("Go", s -> {
                            log.info("Seal street is moving");
                        }).
                        matchEquals("Stop", s -> {
                            log.info("Seal Street has been stopped");
                        }).
                        matchAny(o -> log.info("received unknown message")).build()
        );
    }
}
