package com.example;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;


// Al Quba Street actor
public class QubaActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    public QubaActor() {
        receive(ReceiveBuilder.
                        matchEquals("Go", s -> {
                            log.info("Case_1 processing ");
                            Thread.sleep(30000);
                            log.info("Case_1 has finished processing ,now we will switch to case_2 ");
                            sender().tell("case_2", self());
                        }).
                        matchEquals("Stop", s -> {
                            log.info("Quba Street has been stopped");
                        }).
                        matchAny(o -> log.info("received unknown message")).build()
        );
    }
}
