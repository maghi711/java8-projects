package com.collections.lambdas.model;

public class Apple {

    public Apple(int id, String color, int weight) {
        this.id = id;
        this.color = color;
        this.weight = weight;
    }

    private final int id;

    private final String color;
    private final int weight;

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Apple{");
        sb.append("id=").append(id);
        sb.append(", color='").append(color).append('\'');
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }
}
