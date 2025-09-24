public class TestEmployee {
    public static void main(String[] args) {
        // Crear un empleado
        Employee emp1 = new Employee(101, "Carlos", "Taquez", 2000);

        // Probar métodos
        System.out.println(emp1);  // toString()

        System.out.println("ID: " + emp1.getID());
        System.out.println("First Name: " + emp1.getFirstName());
        System.out.println("Last Name: " + emp1.getLastName());
        System.out.println("Full Name: " + emp1.getName());
        System.out.println("Salary: " + emp1.getSalary());
        System.out.println("Annual Salary: " + emp1.getAnnualSalary());

        // Subir salario en un 10%
        System.out.println("\n--- Aplicando aumento del 10% ---");
        emp1.raiseSalary(10);
        System.out.println("Nuevo salario: " + emp1.getSalary());
        System.out.println("Nuevo salario anual: " + emp1.getAnnualSalary());

        // Modificar salario directamente
        emp1.setSalary(3000);
        System.out.println("\n--- Modificando salario directamente ---");
        System.out.println("Salario actualizado: " + emp1.getSalary());
        System.out.println(emp1);  // toString()
    }
}
