public class InvoiceItem {
    // Atributos privados
    private String id;
    private String desc;
    private int qty;
    private double unitPrice;

    // Constructor
    public InvoiceItem(String id, String desc, int qty, double unitPrice) {
        this.id = id;
        this.desc = desc;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    // Métodos getter
    public String getID() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public int getQty() {
        return qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    // Métodos setter
    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    // Método para obtener el total
    public double getTotal() {
        return unitPrice * qty;
    }

    // Método toString
    @Override
    public String toString() {
        return "InvoiceItem[id=" + id +
                ",desc=" + desc +
                ",qty=" + qty +
                ",unitPrice=" + unitPrice + "]";
    }
}
