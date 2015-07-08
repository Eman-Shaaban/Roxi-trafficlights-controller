package com.example;


import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

// Al-Khalifa alma2mon street actor
public class KhalifaActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    public KhalifaActor() {
        receive(ReceiveBuilder.
                        matchEquals("Go", s -> {
                            log.info("Case_4 processing ");
                            Thread.sleep(30000);
                            log.info("Case_4 has finished processing ,now we will switch to case_1 ");
                            sender().tell("case_1", self());
                        }).
                        matchEquals("Stop", s -> {
                            log.info("Khalifa Street has been stopped");
                        }).
                        matchAny(o -> log.info("received unknown message")).build()
        );
    }
}
