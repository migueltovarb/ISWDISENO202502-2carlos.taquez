package book;

import author.Author;

public class Book {
    private String name;
    private Author author;   // aquí debe ser Author, no String
    private double price;
    private int qty = 0;

    public Book(String name, Author author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public Book(String name, Author author, double price, int qty) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.qty = qty;
    }

    public String getName() { return name; }
    public Author getAuthor() { return author; }  // devuelve un Author
    public double getPrice() { return price; }
    public int getQty() { return qty; }

    public void setPrice(double price) { this.price = price; }
    public void setQty(int qty) { this.qty = qty; }

    @Override
    public String toString() {
        return "Book[name=" + name + "," + author.toString() + ",price=" + price + ",qty=" + qty + "]";
    }
}

