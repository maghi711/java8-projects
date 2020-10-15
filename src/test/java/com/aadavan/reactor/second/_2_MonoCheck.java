package com.aadavan.reactor.second;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class _2_MonoCheck {

    @Test
    public void firstMono() {
        System.out.println("Mono test");
        Mono.just("A")
                .log()
                .subscribe(
                        a -> System.out.println(a)
                );
    }
}
