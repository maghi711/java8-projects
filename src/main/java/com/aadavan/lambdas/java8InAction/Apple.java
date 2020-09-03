package com.aadavan.lambdas.java8InAction;

public class Apple {
    private String color;
    private int weight;

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Apple() {

    }
    public Apple(String color) {
        this.color = color;
    }

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("color='").append(color).append('\'');
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }
}
