public class Employee {
    // Atributos privados
    private int id;
    private String firstName;
    private String lastName;
    private int salary;

    // Constructor
    public Employee(int id, String firstName, String lastName, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    // Getters
    public int getID() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public int getSalary() {
        return salary;
    }

    // Setter
    public void setSalary(int salary) {
        this.salary = salary;
    }

    // Otros métodos
    public int getAnnualSalary() {
        return salary * 12;
    }

    /**
     * Aumenta el salario en el porcentaje dado y devuelve el nuevo salario.
     * Usa aritmética entera (ej: aumento = salary * percent / 100).
     */
    public int raiseSalary(int percent) {
        int increase = salary * percent / 100;
        salary += increase;
        return salary;
    }

    @Override
    public String toString() {
        return "Employee[id=" + id +
                ",name=" + getName() +
                ",salary=" + salary + "]";
    }
}
