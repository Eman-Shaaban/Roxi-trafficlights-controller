package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;


public class Controller extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    ActorRef Quba = getContext().actorOf(Props.create(QubaActor.class), "quba");
    ActorRef Seal = getContext().actorOf(Props.create(SealActor.class), "seal");
    ActorRef Hijaz = getContext().actorOf(Props.create(HijazActor.class), "hijaz");
    ActorRef Laqani = getContext().actorOf(Props.create(LaqaniActor.class), "laqani");
    ActorRef Khalifa = getContext().actorOf(Props.create(KhalifaActor.class), "khalifa");

    public Controller() {
        receive(ReceiveBuilder.
                        matchEquals("case_1", s -> {
                            log.info("This is the first case From Quba to Seal and Laqani");
                            Hijaz.tell("Stop", self());
                            Khalifa.tell("Stop", self());
                            Quba.tell("Go", self());
                        }).
                        matchEquals("case_2", s -> {
                            log.info("This is the second case From Hijaz to Seal and Quba");
                            Laqani.tell("Stop", self());
                            Khalifa.tell("Stop", self());
                            Hijaz.tell("Go", self());
                        }).
                        matchEquals("case_3", s -> {
                            log.info("This is the third case From Laqani to Hijaz and Quba");
                            Seal.tell("Stop", self());
                            Khalifa.tell("Stop", self());
                            Laqani.tell("Go", self());
                        }).
                        matchEquals("case_4", s -> {
                            log.info("This is the fourth case From Khalifa to Hijaz and Laqani");
                            Quba.tell("Stop", self());
                            Seal.tell("Stop", self());
                            Khalifa.tell("Go", self());
                        }).
                        matchAny(o -> log.info("received unknown message")).build()
        );
        //getContext().system().shutdown();
    }

}
