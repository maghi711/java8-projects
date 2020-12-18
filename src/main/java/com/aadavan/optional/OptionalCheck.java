package com.aadavan.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalCheck {
    public static void main(String[] args) {
        List<String> string = Arrays.asList(null, "String");
        for (String st : string) {
            Optional<String> name = Optional.ofNullable(st);
            if (name.isPresent()) {
                System.out.println(name.get());
            } else {
                System.out.println("No value present");
            }
        }
    }
}
