package com.example;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

// Ibrahim al Laqani street actor
public class LaqaniActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    public LaqaniActor() {
        receive(ReceiveBuilder.
                        matchEquals("Go", s -> {
                            log.info("Case_3 processing ");
                            Thread.sleep(30000);
                            log.info("Case_3 has finished processing ,now we will switch to case_4 ");
                            sender().tell("case_4", self());
                        }).
                        matchEquals("Stop", s -> {
                            log.info("Laqani Street has been stopped");
                        }).
                        matchAny(o -> log.info("received unknown message")).build()
        );
    }
}
