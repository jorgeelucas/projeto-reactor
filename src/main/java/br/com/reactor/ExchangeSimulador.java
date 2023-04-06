package br.com.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class ExchangeSimulador {

    private final Sinks.Many<Order> orderSink = Sinks.many().multicast().directBestEffort();

    public ExchangeSimulador() {
        Flux<Order> orderFlux = orderSink.asFlux();
        orderFlux.buffer(Duration.ofSeconds(1))
                .flatMap(Flux::fromIterable)
                .subscribe(this::processOrder);
    }

    public void submitOrder(Order order) {
        orderSink.tryEmitNext(order);
    }

    private void processOrder(Order order) {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Ordem executada: " + order);
    }
}

