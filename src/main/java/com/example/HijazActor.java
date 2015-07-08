package com.example;


import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

// Al-Hijaz Street actor
public class HijazActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    public HijazActor() {
        receive(ReceiveBuilder.
                        matchEquals("Go", s -> {
                            log.info("Case_2 processing ");
                            Thread.sleep(30000);
                            log.info("Case_2 has finished processing ,now we will switch to case_3 ");
                            sender().tell("case_3", self());
                        }).
                        matchEquals("Stop", s -> {
                            log.info("Hijaz Street has been stopped");
                        }).
                        matchAny(o -> log.info("received unknown message")).build()
        );
    }
}
