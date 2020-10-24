package com.aadavan.check.basic;

import java.util.*;

public class SetCheck {
    public static void main(String[] args) {
        Set<Student> names = new HashSet<>();
        Student first = new Student(1, "Aadavan");
        names.add(first);
        Student second = new Student(1, "Aadavan 2");
        names.add(second);
        System.out.println("names = " + names);
        Map<Integer, Student> nameMap = new HashMap<>();
        nameMap.put(first.getId(), first);
        nameMap.put(second.getId(), second);
        System.out.println("nameMap = " + nameMap);
        Collection<Student> values = nameMap.values();
        System.out.println(values);
    }

    static class Student {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return id == student.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
