package br.com.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public class Principal {

    public static void main(String[] args) {

        // Mono
        // Flux

//        Mono<String> mono = Mono
//                .just("meu texto")
//                .doOnSubscribe(subscription -> System.out.println("alguém se inscreveu"))
//                .doOnNext(s -> System.out.println(s.toUpperCase()))
//                .doOnError(Throwable::printStackTrace)
//                .doOnSuccess(s -> System.out.println("finalizou com sucesso " + s))
//                .log();
//
//        mono.subscribe();

        List<String> lista = List.of("texto 1", "texto 2", "texto 3");

        Scheduler scheduler = Schedulers.newParallel("minhas-threads", 2);

        Flux<String> flux = Flux
                .fromIterable(lista)
                .subscribeOn(scheduler)
                .log();


        flux.subscribe(
                s -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(s.toUpperCase());
                },
                err -> err.printStackTrace(),
                () -> System.out.println("Todos os elementos processados!"),
                subscription -> subscription.request(3)
        );


        scheduler.dispose();


    }

}
