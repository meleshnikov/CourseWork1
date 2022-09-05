package employee;

public class Employee {

    private static int nextId;
    private final int id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private int department;
    private double salary;

    public Employee(String firstName, String middleName, String lastName, int department, double salary) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        if (department > 5 || department < 1)
            throw new IllegalArgumentException("Номер отдела должен быть от 1 до 5");
        this.department = department;
        if (salary < 0)
            throw new IllegalArgumentException("Зарплата не может быть отрицательной");
        this.salary = salary;
        id = nextId++;
    }

    // Конструктор на случай, если у человека нет отчества
    public Employee(String firstName, String lastName, int department, double salary) {
        this(firstName, "", lastName, department, salary);
    }

    @Override
    public String toString() {
        return String.format("id: %3d| %35s| Отдел: %d| ЗП: %.1f руб", id, getFullName(), department, salary);
    }

    public String getFullName() {
        return String.join(" ", lastName, firstName, middleName).trim();
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public int getDepartment() {
        return department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100.0;
        salary += raise;
    }

    public boolean isFromDepartment(int department) {
        return this.department == department;
    }

    public boolean isSalaryEqual(double salary) {
        return this.salary == salary;
    }


}
