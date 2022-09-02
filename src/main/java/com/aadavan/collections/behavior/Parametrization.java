package com.aadavan.collections.behavior;

import com.aadavan.lambdas.predicate.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parametrization {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Aadavan M", 560001),
                new Employee(2, "Mahesh J", 560021),
                new Employee(3, "Swetha R", 560040)
        );
        List<Employee> employees1 = getEmployees(employees);
        System.out.println("employees1 = " + employees1);
        List<Employee> employeesByName = getEmployeesByName(employees, "Aadavan M");
        System.out.println("employeesByName = " + employeesByName);

        Predicate<Employee> nameCondition = employee -> employee.getName().startsWith("A");
        Predicate<Employee> zipCondition2 = employee -> employee.getZipcode() == 560001;

        List<Employee> nameSearch = getEmployees(employees, nameCondition.andThen(zipCondition2));
        System.out.println("nameSearch = " + nameSearch);

        nameSearch = getEmployees(employees, employee -> "Aadavan M".equalsIgnoreCase(employee.getName()));
        System.out.println("nameSearch = " + nameSearch);

        int zipCode = 560040;
        Predicate<Employee> zipCondition = employee -> zipCode == employee.getZipcode();
        List<Employee> zipSearch = getEmployees(employees, zipCondition);
        System.out.println(zipCode + " Zip Search Matches = " + zipSearch);
    }

    private static List<Employee> getEmployees(List<Employee> inventory) {
        List<Employee> empList = new ArrayList<>();
        for (Employee e : inventory) {
            if (560001 == e.getZipcode()) {
                empList.add(e);
            }
        }
        return empList;
    }

    private static List<Employee> getEmployeesByName(List<Employee> inventory, String name) {
        List<Employee> empList = new ArrayList<>();
        for (Employee e : inventory) {
            if (name.equalsIgnoreCase(e.getName())) {
                empList.add(e);
            }
        }
        return empList;
    }

    private static List<Employee> getEmployees(List<Employee> employees, Predicate<Employee> condition) {
        List<Employee> emp = new ArrayList<>();
        for (Employee e: employees) {
            if (condition.test(e)) {
                emp.add(e);
            }
        }
        return emp;
    }
}
