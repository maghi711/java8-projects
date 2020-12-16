package com.aadavan.methodreferences;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.Arrays;
import java.util.List;

public class Person {

    public enum Sex {
        MALE, FEMALE
    }

    Person(String nameArg, LocalDate birthdayArg,
           Sex genderArg, String emailArg) {
        name = nameArg;
        birthday = birthdayArg;
        gender = genderArg;
        emailAddress = emailArg;
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return birthday
                .until(IsoChronology.INSTANCE.dateNow())
                .getYears();
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    public void printPerson() {
        System.out.println(name + ", " + this.getAge());
    }

    public static List<Person> createRoster() {
        List<Person> roster = Arrays.asList(
            new Person("Fred",
                    IsoChronology.INSTANCE.date(1980, 6, 20),
                    Person.Sex.MALE,
                    "fred@example.com"),
            new Person("Jane",
                    IsoChronology.INSTANCE.date(1990, 7, 15),
                    Person.Sex.FEMALE, "jane@example.com"),
            new Person("George",
                    IsoChronology.INSTANCE.date(1991, 8, 13),
                    Person.Sex.MALE, "george@example.com"),
            new Person("Bob",
                    IsoChronology.INSTANCE.date(2000, 9, 12),
                    Person.Sex.MALE, "bob@example.com"));
        return roster;
    }
}
