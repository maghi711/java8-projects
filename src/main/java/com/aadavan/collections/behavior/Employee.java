package com.aadavan.collections.behavior;

public class Employee {

    private int id;
    private String name;
    private int zipcode;

    public Employee(int id, String name, int zipcode) {
        this.id = id;
        this.name = name;
        this.zipcode = zipcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", zipcode=" + zipcode +
                '}';
    }
}
