package com.aadavan.generics.zoos;

public class Zoo {
    public static void main(String[] args) {
        addAnimalsWithoutGenerics();
        addAnimalsWithGenerics();
        addAnimalsWithGenericsAndConstructor();
    }

    private static void addAnimalsWithGenericsAndConstructor() {
        CageWithType<Monkey> cageOfMonkeys = new CageWithType<>(new Monkey(), new Monkey());
        System.out.println("cageOfMonkeys = " + cageOfMonkeys);
    }

    private static void addAnimalsWithGenerics() {
        CageWithType<Monkey> monkeyCage = new CageWithType<>();
        monkeyCage.addAnimal1(new Monkey());
        monkeyCage.addAnimal2(new Monkey());
    }

    public static void addAnimalsWithoutGenerics() {
        Cage cage = new Cage();
        cage.addAnimal1(new Monkey());
        cage.addAnimal2(new Lion());
    }

}
