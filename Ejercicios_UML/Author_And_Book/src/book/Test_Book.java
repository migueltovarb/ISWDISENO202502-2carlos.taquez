package book;

import author.Author;  // Importamos Author para poder usarlo

public class Test_Book {
    public static void main(String[] args) {
        // Creamos un autor
        Author author = new Author("J. K. Rowling", "jkrowling@email.com", 'F');

        // Creamos un libro con ese autor
        Book book1 = new Book("Harry Potter", author, 39.99, 100);

        // Probamos los métodos
        System.out.println(book1);  // Usa el toString()

        // Modificamos el precio y la cantidad
        book1.setPrice(29.99);
        book1.setQty(200);

        // Volvemos a imprimir
        System.out.println("Nombre del libro: " + book1.getName());
        System.out.println("Autor: " + book1.getAuthor().getName());
        System.out.println("Nuevo precio: " + book1.getPrice());
        System.out.println("Nueva cantidad: " + book1.getQty());
    }
}
