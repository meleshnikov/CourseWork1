package employee;

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

    public void add(String fName, String mName, String lName, int department, double salary) {
        Employee e = new Employee(fName, mName, lName, department, salary);
        add(e);
    }


    public void removeById(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getId() == id) {
                    employees[i] = null;
                    return;
                }
            }
        }
    }

    public void removeByName(String name) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getFullName().equals(name)) {
                    employees[i] = null;
                    return;
                }
            }
        }
    }

    public Employee findByName(String name) {
        for (Employee e : employees) {
            if (e != null) {
                if (e.getFullName().equals(name)) {
                    return e;
                }
            }
        }
        return null;
    }

    public Employee findById(int id) {
        for (Employee e : employees) {
            if (e != null) {
                if (e.getId() == id) {
                    return e;
                }
            }
        }
        return null;
    }

    public void changeSalaryByName(String name, double newSalary) {
        findByName(name).setSalary(newSalary);
    }

    public void changeDepartmentByName(String name, int newDepartment) {
        findByName(name).setDepartment(newDepartment);
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
        return str.toString().trim();
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

    public EmployeeBook findBySalaryLess(double salary) {
        return findBySalary(0, salary);
    }

    public EmployeeBook findBySalaryMore(double salary) {
        return findBySalary(salary, Double.MAX_VALUE);
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

    public EmployeeBook findEmployeesWithMaxSalary() {
        return findBySalary(getMaxSalary());
    }

    public EmployeeBook findEmployeesWithMaxSalary(int department) {
        return findByDepartment(department).findEmployeesWithMaxSalary();
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
        for (Employee e : employees) {
            if (e != null) {
                count++;
            }
        }
        return (count == 0.0) ? 0.0 : getSumOfSalaries() / count;
    }

    public double getAverageSalary(int department) {
        return findByDepartment(department).getAverageSalary();
    }

    public void raiseSalary(double byPercent) {
        for (Employee e : employees) {
            e.raiseSalary(byPercent);
        }
    }

    public void raiseSalaryByDepartment(int department, double byPercent) {
        for (Employee e : employees) {
            if ((e != null) && (e.isFromDepartment(department))) {
                e.raiseSalary(byPercent);
            }
        }
    }

    public void printFullNames() {
        for (Employee e : employees) {
            if (e != null) {
                System.out.println(e.getFullName());
            }
        }
    }

    public void printEmployees() {
        System.out.println(this);
    }

    public void printEmployees(int department) {
        EmployeeBook filteredEmployees = findByDepartment(department);
        for (Employee e : filteredEmployees.employees) {
            System.out.println(e.toString().replace(String.format("| Отдел: %d", e.getDepartment()), ""));
        }
    }

    public void printFullNamesByDepartments() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Отдел: " + i);
            findByDepartment(i).printFullNames();
            System.out.println();
        }
    }


}


