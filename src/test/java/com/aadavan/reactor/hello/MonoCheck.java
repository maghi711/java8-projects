package com.aadavan.reactor.hello;

//import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.Test;
import reactor.core.publisher.Mono;

public class MonoCheck {

    @Test
    public void firstMono() {
        System.out.println("Mono test");
        Mono.just("A").log().subscribe(
                a -> System.out.println(a)
        );
    }

    @Test
    public void test() {
        Assert.assertTrue(true);
    }
}
