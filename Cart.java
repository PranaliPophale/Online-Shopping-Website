import java.util.ArrayList;

public class Cart {
    ArrayList<Product> items = new ArrayList<>();

    public void add(Product p) {
        items.add(p);
    }

    public void showCart() {
        double total = 0;
        System.out.println("\n🛒 Your Cart:");
        for (Product p : items) {
            p.display();
            total += p.price;
        }
        System.out.println("Total: ₹" + total);
    }
}
