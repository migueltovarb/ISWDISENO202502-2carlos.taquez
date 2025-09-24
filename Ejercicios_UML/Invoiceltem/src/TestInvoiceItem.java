public class TestInvoiceItem {
    public static void main(String[] args) {
        // Crear un ítem de factura
        InvoiceItem item1 = new InvoiceItem("A101", "Laptop", 2, 1500.0);

        // Probar métodos
        System.out.println(item1);  // toString()

        System.out.println("ID: " + item1.getID());
        System.out.println("Description: " + item1.getDesc());
        System.out.println("Quantity: " + item1.getQty());
        System.out.println("Unit Price: " + item1.getUnitPrice());
        System.out.println("Total: " + item1.getTotal());

        // Cambiar cantidad
        item1.setQty(3);
        System.out.println("\n--- Después de cambiar la cantidad ---");
        System.out.println("Quantity: " + item1.getQty());
        System.out.println("Total: " + item1.getTotal());

        // Cambiar precio unitario
        item1.setUnitPrice(1600.0);
        System.out.println("\n--- Después de cambiar el precio unitario ---");
        System.out.println("Unit Price: " + item1.getUnitPrice());
        System.out.println("Total: " + item1.getTotal());

        // Mostrar nuevamente con toString
        System.out.println("\n" + item1);
    }
}
