package crsWork;

import java.util.Arrays;

public class EmployeeBook {
    private Employee[] employees;

    public EmployeeBook(int size) {
        employees = new Employee[size];
    }

    public void add(Employee employee) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                return;
            }
        }

        employees = Arrays.copyOf(employees, employees.length + 1);
        employees[employees.length - 1] = employee;
    }


    public int length() {
        return employees.length;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Employee e : employees) {
            str.append(e).append('\n');
        }
        return str.toString();
    }

    public EmployeeBook findBySalary(double salary) {
        EmployeeBook filteredEmployees = new EmployeeBook(employees.length);
        int index = 0;
        for (int i = 0; i < employees.length; i++) {
            if ((employees[i] != null) && (employees[i].isSalaryEqual(salary))) {
                filteredEmployees.employees[index++] = employees[i];
            }
        }
        filteredEmployees.employees = Arrays.copyOf(filteredEmployees.employees, index);
        return filteredEmployees;
    }

    public EmployeeBook findBySalary(double from, double to) {
        EmployeeBook filteredEmployees = new EmployeeBook(employees.length);
        int index = 0;
        for (int i = 0; i < employees.length; i++) {
            if ((employees[i] != null) && (employees[i].getSalary() > from) && (employees[i].getSalary() <= to)) {
                filteredEmployees.employees[index++] = employees[i];
            }
        }
        filteredEmployees.employees = Arrays.copyOf(filteredEmployees.employees, index);
        return filteredEmployees;
    }

    public EmployeeBook findByDepartment(int department) {
        EmployeeBook filteredEmployees = new EmployeeBook(employees.length);
        int index = 0;
        for (int i = 0; i < employees.length; i++) {
            if ((employees[i] != null) && employees[i].isFromDepartment(department)) {
                filteredEmployees.employees[index++] = employees[i];
            }
        }
        filteredEmployees.employees = Arrays.copyOf(filteredEmployees.employees, index);
        return filteredEmployees;
    }

    public EmployeeBook findEmployeesWithMinSalary() {
        return findBySalary(getMinSalary());
    }

    public EmployeeBook findEmployeesWithMinSalary(int department) {
        return findByDepartment(department).findEmployeesWithMinSalary();
    }

    public EmployeeBook findEmployeesWithMaxSalary(int department) {
        return findByDepartment(department).findEmployeesWithMaxSalary();
    }


    public EmployeeBook findEmployeesWithMaxSalary() {
        return findBySalary(getMaxSalary());
    }


    public double getMinSalary() {
        double min = Double.MAX_VALUE;
        for (Employee e : employees) {
            if (e != null) {
                if (min > e.getSalary()) {
                    min = e.getSalary();
                }
            }
        }
        return min;
    }

    public double getMaxSalary() {
        double max = 0;
        for (Employee e : employees) {
            if (e != null) {
                if (max < e.getSalary()) {
                    max = e.getSalary();
                }
            }
        }
        return max;
    }

    public double getSumOfSalaries() {
        double sum = 0.0;
        for (Employee e : employees) {
            if (e != null) {
                sum += e.getSalary();
            }
        }
        return sum;
    }

    public double getSumOfSalaries(int department) {
        return findByDepartment(department).getSumOfSalaries();
    }

    public double getAverageSalary() {
        int count = 0;
        double sum = 0.0;
        for (Employee e : employees) {
            if (e != null) {
                sum += e.getSalary();
                count++;
            }
        }
        return (count == 0.0) ? 0.0 : sum / count;
    }

    public double getAverageSalary(int department) {
        return findByDepartment(department).getAverageSalary();
    }


}


