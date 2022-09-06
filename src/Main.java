import employee.Employee;
import employee.EmployeeBook;
import random.RandomEmployee;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        test1(10); // тест для заданий базовой и повышенной сложности
        test2(10); // тест для класса EmployeeBook

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
            if ((employees[i] != null) && (employees[i].getSalary() >= from) && (employees[i].getSalary() < to)) {
                filteredEmployees[length++] = employees[i];
            }
        }
        filteredEmployees = Arrays.copyOf(filteredEmployees, length);
        return filteredEmployees;
    }

    public static Employee[] findBySalaryLess(Employee[] employees, double salary) {
        return findBySalary(employees, 0, salary);
    }

    public static Employee[] findBySalaryMore(Employee[] employees, double salary) {
        return findBySalary(employees, salary, Double.MAX_VALUE);
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

    // метод возвращает сотрудников с минимальной ЗП
    public static Employee[] findEmployeesWithMinSalary(Employee[] employees) {
        return findBySalary(employees, findMinSalary(employees));
    }

    // метод возвращает сотрудников с минимальной ЗП по отделу
    public static Employee[] findEmployeesWithMinSalary(Employee[] employees, int department) {
        return findEmployeesWithMinSalary(findByDepartment(employees, department));
    }

    // метод возвращает сотрудников с максимальной ЗП
    public static Employee[] findEmployeesWithMaxSalary(Employee[] employees) {
        return findBySalary(employees, findMaxSalary(employees));
    }

    // метод возвращает сотрудников с максимальной ЗП по отделу
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

    public static void raiseSalaryByDepartment(Employee[] employees, int department, double byPercent) {
        for (int i = 0; i < employees.length; i++) {
            if ((employees[i] != null) && (employees[i].isFromDepartment(department))) {
                employees[i].raiseSalary(byPercent);
            }
        }
    }

    // метод выводит в консоль список всех сотрудников со всеми имеющимися по ним данными
    public static void printEmployees(Employee[] employees) {
        for (Employee e : employees) {
            if (e != null) {
                System.out.println(e);
            }
        }
    }

    // метод выводит в консоль всех сотрудников отдела (все данные, кроме отдела)
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

    public static void printDelimiter() {
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    // метод для тестирования заданий базавой и повышенной сложности
    // параметр eCount - количество сотрудников в отделе
    public static void test1(int eCount) {

        Employee[] employees = new Employee[eCount];

        // заполняем массив рандомными сотрудниками
        for (int i = 0; i < employees.length; i++) {
            employees[i] = RandomEmployee.generateRandomEmployee();
        }

        // 1
        // а. печатаем список всех сотрудников со всеми имеющимися по ним данными
        printEmployees(employees);
        printDelimiter(); // разделитель для удобства чтения

        // b. сумма затрат на ЗП в месяц
        System.out.printf("Сумма затрат на ЗП в месяц: %.1f руб\n", getSumOfSalaries(employees));
        printDelimiter();

        // c. поиск сотрудников с минимальной ЗП
        System.out.println("Сотрудники с минимальной ЗП:");
        printEmployees(findEmployeesWithMinSalary(employees));
        printDelimiter();

        // d. поиск сотрудников с максимальной ЗП
        System.out.println("Сотрудники с максимальной ЗП:");
        printEmployees(findEmployeesWithMaxSalary(employees));
        printDelimiter();

        // e. средняя ЗП
        System.out.printf("Среднее значение ЗП: %.1f руб\n", getAverageSalary(employees));
        printDelimiter();

        // f. печатаем Ф.И.О. всех сотрудников
        printFullNames(employees);
        printDelimiter();

        double percent = 10.0;

        // 2.1 Индексируем ЗП
        raiseSalary(employees, percent);
        System.out.printf("ЗП после индексации на %.1f%%\n", percent);
        printEmployees(employees);
        printDelimiter();

        // 2.2 тест методов по отделу
        for (int department = 1; department <= 5; department++) {

            // a. поиск сотрудников с минимальной ЗП по отделу
            System.out.printf("Сотрудники с минимальной ЗП в %d отделе:\n", department);
            printEmployees(findEmployeesWithMinSalary(employees, department));
            printDelimiter();

            // b. поиск сотрудников с максимальной ЗП по отделу
            System.out.printf("Сотрудники с максимальной ЗП в %d отделе:\n", department);
            printEmployees(findEmployeesWithMaxSalary(employees, department));
            printDelimiter();

            // c. сумма затрат на ЗП по отделу
            System.out.printf("Сумма затрат на ЗП в %d отделе: %.1f руб\n", department, getSumOfSalaries(employees, department));
            printDelimiter();

            // d. средняя ЗП по отделу
            System.out.printf("Среднее значение ЗП по %d отделу: %.1f руб\n", department, getAverageSalary(employees, department));
            printDelimiter();

            // e. Индексируем ЗП в отделе
            raiseSalaryByDepartment(employees, department, percent);
            System.out.printf("ЗП в %d отделе после индексации на %.1f%%\n", department, percent);

            // печатаем всех сотрудников отдела (все данные, кроме отдела).
            printEmployees(employees, department);
            printDelimiter();
        }

        // 2.3
        double salary = 120_000.0;

        // список сотрудников после всех индексаций
        printEmployees(employees);
        printDelimiter();

        // a. поиск всех сотрудников с зарплатой меньше числа
        System.out.printf("Сотрудники с ЗП <= %.1f руб\n", salary);
        printEmployees(findBySalaryLess(employees, salary));
        printDelimiter();

        // b. поиск всех сотрудников с зарплатой больше числа
        System.out.printf("Сотрудники с ЗП > %.1f руб\n", salary);
        printEmployees(findBySalaryMore(employees, salary));
        printDelimiter();
    }

    // метод для тестирования класса EmployeeBook
    // параметр eCount - количество сотрудников в отделе
    public static void test2(int eCount) {

        var eBook = new EmployeeBook(eCount);

        // заполняем объект рандомными сотрудниками
        for (int i = 0; i < eBook.length(); i++) {
            eBook.add(RandomEmployee.generateRandomEmployee());
        }

        // 1
        // а. печатаем список всех сотрудников со всеми имеющимися по ним данными
        eBook.printEmployees();
        printDelimiter(); // разделитель для удобства чтения

        // b. сумма затрат на ЗП в месяц
        System.out.printf("Сумма затрат на ЗП в месяц: %.1f руб\n", eBook.getSumOfSalaries());
        printDelimiter();

        // c. поиск сотрудников с минимальной ЗП
        System.out.println("Сотрудники с минимальной ЗП:");
        System.out.println(eBook.findEmployeesWithMinSalary());
        printDelimiter();

        // d. поиск сотрудников с максимальной ЗП
        System.out.println("Сотрудники с максимальной ЗП:");
        System.out.println(eBook.findEmployeesWithMaxSalary());
        printDelimiter();

        // e. средняя ЗП
        System.out.printf("Среднее значение ЗП: %.1f руб\n", eBook.getAverageSalary());
        printDelimiter();

        // f. печатаем Ф.И.О. всех сотрудников
        eBook.printFullNames();
        printDelimiter();

        double percent = 10.0;

        // 2.1 Индексируем ЗП
        eBook.raiseSalary(percent);
        System.out.printf("ЗП после индексации на %.1f%%\n", percent);
        System.out.println(eBook);
        printDelimiter();

        // 2.2 тест методов по отделу
        for (int department = 1; department <= 5; department++) {

            // a. поиск сотрудников с минимальной ЗП по отделу
            System.out.printf("Сотрудники с минимальной ЗП в %d отделе:\n", department);
            System.out.println(eBook.findEmployeesWithMinSalary(department));
            printDelimiter();

            // b. поиск сотрудников с максимальной ЗП по отделу
            System.out.printf("Сотрудники с максимальной ЗП в %d отделе:\n", department);
            System.out.println(eBook.findEmployeesWithMaxSalary(department));
            printDelimiter();

            // c. сумма затрат на ЗП по отделу
            System.out.printf("Сумма затрат на ЗП в %d отделе: %.1f руб\n", department, eBook.getSumOfSalaries(department));
            printDelimiter();

            // d. средняя ЗП по отделу
            System.out.printf("Среднее значение ЗП по %d отделу: %.1f руб\n", department, eBook.getAverageSalary(department));
            printDelimiter();

            // e. Индексируем ЗП в отделе
            eBook.raiseSalaryByDepartment(department, percent);
            System.out.printf("ЗП в %d отделе после индексации на %.1f%%\n", department, percent);

            // печатаем всех сотрудников отдела (все данные, кроме отдела).
            eBook.printEmployees(department);
            printDelimiter();
        }

        // 2.3
        double salary = 120_000.0;

        // список сотрудников после всех индексаций
        eBook.printEmployees();
        printDelimiter();

        // a. поиск всех сотрудников с зарплатой меньше числа
        System.out.printf("Сотрудники с ЗП <= %.1f руб\n", salary);
        System.out.println(eBook.findBySalaryLess(salary));
        printDelimiter();

        // b. поиск всех сотрудников с зарплатой больше числа
        System.out.printf("Сотрудники с ЗП > %.1f руб\n", salary);
        System.out.println(eBook.findBySalaryMore(salary));
        printDelimiter();

        // 4
        // удаляем сотрудника по id
        eBook.removeById(2);
        System.out.println(eBook.findById(2));
        printDelimiter();


        // добавдяем сотрудника
        eBook.add("Иван", "Петрович", "Сидоров", 2, 100_000);
        System.out.println(eBook);
        printDelimiter();

        // удаляем сотрудника по ФИО
        eBook.removeByName("Сидоров Иван Петрович");
        System.out.println(eBook);
        printDelimiter();

        eBook.add("Иван", "Иванович", "Иванов", 2, 100_000);
        System.out.println(eBook);
        printDelimiter();

        //5
        // a. изменяем ЗП по ФИО
        eBook.changeSalaryByName("Иванов Иван Иванович", 150_000);
        System.out.println(eBook.findByName("Иванов Иван Иванович"));

        // b. изменяем отдел по ФИО
        eBook.changeDepartmentByName("Иванов Иван Иванович", 5);
        System.out.println(eBook.findByName("Иванов Иван Иванович"));
        printDelimiter();

        // 6. печатаем Ф. И. О. всех сотрудников по отделам
        eBook.printFullNamesByDepartments();


    }

}