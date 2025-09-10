import javax.swing.*;

public class AdministradorSupermercado {
    private static final int MAX_PRODUCTOS = 5;

    public static void main(String[] args) {
        String[] productos = new String[MAX_PRODUCTOS];
        int[] cantidades = new int[MAX_PRODUCTOS];

        int totalProductos = 0;
        int opcion = 0;

        for (int i = 0; i < MAX_PRODUCTOS; i++) {
            productos[i] = JOptionPane.showInputDialog("Ingrese el nombre del producto " + (i + 1) + ":");
            cantidades[i] = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad disponible de " + productos[i] + ":"));
            if (cantidades[i] < 0) {
                JOptionPane.showMessageDialog(null, "Cantidad no puede ser negativa, se ajusta a 0.");
                cantidades[i] = 0;
            }
            totalProductos += cantidades[i];
        }

        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    " Administrador de Supermercado \n" +
                            "1. Mostrar todos los productos y sus existencias\n" +
                            "2. Buscar un producto por nombre y ver su cantidad\n" +
                            "3. Actualizar inventario (aumentar/disminuir stock)\n" +
                            "4. Generar alerta de productos con cantidad menor a 10\n" +
                            "5. Salir\n\n" +
                            "Total en inventario: " + totalProductos));

            switch (opcion) {
                case 1:
                    StringBuilder inventario = new StringBuilder("Inventario de productos:\n");
                    for (int i = 0; i < MAX_PRODUCTOS; i++) {
                        inventario.append(productos[i]).append(": ").append(cantidades[i]).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, inventario.toString());
                    break;

                case 2:
                    String buscar = JOptionPane.showInputDialog("Ingrese el nombre del producto a buscar:");
                    boolean encontrado = false;
                    for (int i = 0; i < MAX_PRODUCTOS; i++) {
                        if (productos[i].equalsIgnoreCase(buscar)) {
                            JOptionPane.showMessageDialog(null, "Producto: " + productos[i] + "\nCantidad: " + cantidades[i]);
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                    }
                    break;

                case 3:

                    String prodActualizar = JOptionPane.showInputDialog("Ingrese el nombre del producto a actualizar:");
                    boolean existe = false;
                    for (int i = 0; i < MAX_PRODUCTOS; i++) {
                        if (productos[i].equalsIgnoreCase(prodActualizar)) {
                            int cambio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad a sumar (positivo) o restar (negativo):"));
                            if (cantidades[i] + cambio < 0) {
                                JOptionPane.showMessageDialog(null, "No es posible tener cantidad negativa.");
                            } else {
                                cantidades[i] += cambio;
                                totalProductos = 0;
                                for (int j = 0; j < MAX_PRODUCTOS; j++) {
                                    totalProductos += cantidades[j];
                                }
                                JOptionPane.showMessageDialog(null, "Inventario actualizado. Nueva cantidad de " + productos[i] + ": " + cantidades[i]);
                            }
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                    }
                    break;

                case 4:
                    StringBuilder alerta = new StringBuilder("Productos con menos de 10 unidades:\n");
                    boolean hayAlertas = false;
                    for (int i = 0; i < MAX_PRODUCTOS; i++) {
                        if (cantidades[i] < 10) {
                            alerta.append(productos[i]).append(": ").append(cantidades[i]).append("\n");
                            hayAlertas = true;
                        }
                    }
                    if (hayAlertas) {
                        JOptionPane.showMessageDialog(null, alerta.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay productos con baja existencia.");
                    }
                    break;

                case 5:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el Administrador de Supermercado. ¡Hasta luego!");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 5);
    }
}
