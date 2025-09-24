package author;

public class TestAuthor {
    public static void main(String[] args) {
        // Crear un autor
        Author author1 = new Author("Gabriel García Márquez", "gabo@email.com", 'm');

        // Mostrar autor usando toString()
        System.out.println(author1);

        // Probar getters
        System.out.println("Name: " + author1.getName());
        System.out.println("Email: " + author1.getEmail());
        System.out.println("Gender: " + author1.getGender());

        // Cambiar email
        author1.setEmail("ggmarquez@literatura.com");
        System.out.println("\n--- Después de actualizar el email ---");
        System.out.println("Email actualizado: " + author1.getEmail());

        // Mostrar nuevamente con toString()
        System.out.println(author1);
    }
}
