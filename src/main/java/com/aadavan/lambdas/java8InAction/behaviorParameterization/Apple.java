package com.aadavan.lambdas.java8InAction.behaviorParameterization;

public class Apple {

    private String color;

    private Integer weight;

    private Integer size;

    public String getColor() {
        return color;
    }

    public Apple() {
    }

    public Apple(String color, Integer weight, Integer size) {
        this.color = color;
        this.weight = weight;
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public static Apple newApple(String color, Integer weight, Integer size) {
        return new Apple(color, weight, size);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Apple{");
        sb.append("color='").append(color).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}
