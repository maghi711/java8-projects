package com.aadavan.generics.zoos;

public class CageWithType<T extends Animal & Eatable & Runner> {

    CageWithType() {

    }

    CageWithType(T animal1, T animal2) {
        this.animal1 = animal1;
        this.animal2 = animal2;
    }

    private T animal1;
    private T animal2;

    public void addAnimal1(T animal1) {
        this.animal1 = animal1;
    }

    public void addAnimal2(T animal2) {
        this.animal2 = animal2;
    }
}
