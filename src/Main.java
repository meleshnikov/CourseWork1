import crsWork.Employee;
import crsWork.RandomEmployee;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Employee[] employees = new Employee[100];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = RandomEmployee.genRandomEmployee();
        }
        printEmployees(employees);
        System.out.println(getSumOfSalaries(employees));
        System.out.println(findEmployeeWithMinSalary(employees));
        System.out.println(findEmployeeWithMaxSalary(employees));
        System.out.println(getAvarageSalary(employees));
        //printFullNames(employees);

        Random rd = new Random();



    }

    public static void printEmployees(Employee[] employees) {
        for (Employee e : employees) {
            if (e != null) {
                System.out.println(e);
            }
        }
    }

    public static double getSumOfSalaries(Employee[] employees) {
        double sum = 0.0;
        for (Employee e : employees) {
            if (e != null) {
                sum += e.getSalary();
            }
        }
        return sum;
    }

    public static Employee findEmployeeWithMinSalary(Employee[] employees) {
        double min = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (min > employees[i].getSalary()) {
                    min = employees[i].getSalary();
                    index = i;
                }
            }
        }
        return employees[index];
    }

    public static Employee findEmployeeWithMaxSalary(Employee[] employees) {
        double max = 0.0;
        int index = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (max < employees[i].getSalary()) {
                    max = employees[i].getSalary();
                    index = i;
                }
            }
        }
        return employees[index];
    }

    public static double getAvarageSalary(Employee[] employees) {
        int employeeCount = 0;
        double sum = 0.0;
        for (Employee e : employees) {
            if (e != null) {
                sum += e.getSalary();
                employeeCount++;
            }
        }
        return sum / employeeCount;
    }

    public static void printFullNames(Employee[] employees) {
        for (Employee e : employees) {
            if (e != null)
                System.out.println(e.getFullName());
        }
    }

}