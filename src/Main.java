import crsWork.Employee;
import crsWork.EmployeeBook;
import crsWork.RandomEmployee;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // генерируем сотрудников
        var employeeBook = new EmployeeBook(50);

        for (int i = 0; i < employeeBook.length(); i++) {
            employeeBook.add(RandomEmployee.genRandomEmployee());
        }

        //System.out.println(employeeBook);
        //System.out.println(employeeBook.findBySalary(90_000.0));
        //System.out.println(employeeBook.findBySalary(60_000, 90_000));
        //System.out.println(employeeBook.findByDepartment(1).findBySalary(60_000, 80_000));
        System.out.println(employeeBook);
        System.out.println(employeeBook.getMinSalary());
        System.out.println(employeeBook.getMaxSalary());
        //System.out.println(employeeBook.findEmployeesWithMinSalary());
        //System.out.println(employeeBook.findEmployeesWithMaxSalary());
        System.out.println(employeeBook.findEmployeesWithMinSalary(3));
        System.out.println(employeeBook.findEmployeesWithMaxSalary(3));
        System.out.println(employeeBook.getSumOfSalaries());
        System.out.println(employeeBook.getSumOfSalaries(3));
        System.out.println(employeeBook.getAverageSalary());
        System.out.println(employeeBook.getAverageSalary(3));

    }

    public static Employee[] findBySalary(Employee[] employees, double salary) {
        Employee[] filteredEmployees = new Employee[employees.length];
        int length = 0;
        for (int i = 0; i < employees.length; i++) {
            if ((employees[i] != null) && (employees[i].isSalaryEqual(salary))) {
                filteredEmployees[length++] = employees[i];
            }
        }
        filteredEmployees = Arrays.copyOf(filteredEmployees, length);
        return filteredEmployees;
    }

    public static Employee[] findBySalary(Employee[] employees, double from, double to) {
        Employee[] filteredEmployees = new Employee[employees.length];
        int length = 0;
        for (int i = 0; i < employees.length; i++) {
            if ((employees[i] != null) && (employees[i].getSalary() > from) && (employees[i].getSalary() <= to)) {
                filteredEmployees[length++] = employees[i];
            }
        }
        filteredEmployees = Arrays.copyOf(filteredEmployees, length);
        return filteredEmployees;
    }

    public static Employee[] findBySalaryLess(Employee[] employees, double salary) {
        Employee[] filteredEmployees = new Employee[employees.length];
        int length = 0;
        for (int i = 0; i < employees.length; i++) {
            if ((employees[i] != null) && (employees[i].getSalary() < salary)) {
                filteredEmployees[length++] = employees[i];
            }
        }
        filteredEmployees = Arrays.copyOf(filteredEmployees, length);
        return filteredEmployees;
    }

    public static Employee[] findBySalaryMore(Employee[] employees, double salary) {
        Employee[] filteredEmployees = new Employee[employees.length];
        int length = 0;
        for (int i = 0; i < employees.length; i++) {
            if ((employees[i] != null) && (employees[i].getSalary() > salary)) {
                filteredEmployees[length++] = employees[i];
            }
        }
        filteredEmployees = Arrays.copyOf(filteredEmployees, length);
        return filteredEmployees;
    }


    public static Employee[] findByDepartment(Employee[] employees, int department) {
        Employee[] filteredEmployees = new Employee[employees.length];
        int length = 0;
        for (int i = 0; i < employees.length; i++) {
            if ((employees[i] != null) && (employees[i].isFromDepartment(department))) {
                filteredEmployees[length++] = employees[i];
            }
        }
        filteredEmployees = Arrays.copyOf(filteredEmployees, length);
        return filteredEmployees;
    }

    public static Employee[] findEmployeesWithMinSalary(Employee[] employees) {
        return findBySalary(employees, findMinSalary(employees));
    }

    public static Employee[] findEmployeesWithMinSalary(Employee[] employees, int department) {
        return findEmployeesWithMinSalary(findByDepartment(employees, department));
    }

    public static Employee[] findEmployeesWithMaxSalary(Employee[] employees) {
        return findBySalary(employees, findMaxSalary(employees));
    }

    public static Employee[] findEmployeesWithMaxSalary(Employee[] employees, int department) {
        return findEmployeesWithMaxSalary(findByDepartment(employees, department));
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

    public static double getSumOfSalaries(Employee[] employees, int department) {
        return getSumOfSalaries(findByDepartment(employees, department));
    }

    public static double getAverageSalary(Employee[] employees) {
        int employeeCount = 0;
        double sum = 0.0;
        for (Employee e : employees) {
            if (e != null) {
                sum += e.getSalary();
                employeeCount++;
            }
        }
        return (employeeCount == 0.0) ? 0.0 : sum / employeeCount;
    }

    public static double getAverageSalary(Employee[] employees, int department) {
        return getAverageSalary(findByDepartment(employees, department));
    }

    public static void raiseSalary(Employee[] employees, double byPercent) {
        for (Employee e : employees) {
            e.raiseSalary(byPercent);
        }
    }

    public static void raiseSalary(Employee[] employees, int department, double byPercent) {
        raiseSalary(findByDepartment(employees, department), byPercent);
    }

    public static void printEmployees(Employee[] employees) {
        for (Employee e : employees) {
            if (e != null) {
                System.out.println(e);
            }
        }
    }

    public static void printEmployees(Employee[] employees, int department) {
        Employee[] filteredEmployees = findByDepartment(employees, department);
        for (Employee e : filteredEmployees) {
            System.out.println(e.toString().replace(String.format("| Отдел: %d", e.getDepartment()), ""));
        }
    }


    public static double findMinSalary(Employee[] employees) {
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


    public static double findMaxSalary(Employee[] employees) {
        double max = 0.0;
        for (Employee e : employees) {
            if (e != null) {
                if (max < e.getSalary()) {
                    max = e.getSalary();
                }
            }
        }
        return max;
    }

    public static void printFullNames(Employee[] employees) {
        for (Employee e : employees) {
            if (e != null)
                System.out.println(e.getFullName());
        }
    }

    public static void printDelimiter(Employee employee) {
        for (int i = 0; i < employee.toString().length(); i++) {
            System.out.print("-");
        }
        System.out.println();
    }


}