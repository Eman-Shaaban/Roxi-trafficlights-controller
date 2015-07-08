package com.example;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.actor.Props;

public class ApplicationMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MyActorSystem");


        ActorRef controller = system.actorOf(Props.create(Controller.class), "controller");
        controller.tell("case_1", null);
        system.awaitTermination();
        //  system.shutdown();

    }

}