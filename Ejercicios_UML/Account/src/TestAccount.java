public class TestAccount {
    public static void main(String[] args) {
        // Crear dos cuentas
        Account acc1 = new Account("C001", "Carlos", 5000);
        Account acc2 = new Account("C002", "Andrés", 2000);

        // Mostrar cuentas iniciales
        System.out.println("--- Estado inicial de las cuentas ---");
        System.out.println(acc1);
        System.out.println(acc2);

        // Probar depósito (credit)
        acc1.credit(1500);
        System.out.println("\n--- Después de depositar 1500 en la cuenta de Carlos ---");
        System.out.println(acc1);

        // Probar retiro (debit)
        acc2.debit(500);
        System.out.println("\n--- Después de retirar 500 de la cuenta de Andrés ---");
        System.out.println(acc2);

        // Probar retiro mayor al saldo
        acc2.debit(5000);
        System.out.println("\n--- Intentando retirar 5000 (más que el saldo) ---");
        System.out.println(acc2);

        // Probar transferencia
        acc1.transferTo(acc2, 2000);
        System.out.println("\n--- Después de transferir 2000 de Carlos a Andrés ---");
        System.out.println(acc1);
        System.out.println(acc2);

        // Probar transferencia mayor al saldo
        acc2.transferTo(acc1, 10000);
        System.out.println("\n--- Intentando transferir 10000 de Andrés a Carlos ---");
        System.out.println(acc1);
        System.out.println(acc2);
    }
}
