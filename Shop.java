import java.sql.*;
import java.util.*;
public class Shop {


        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            Cart cart = new Cart();
            int choice;

            do {
                System.out.println("\n--- JavaShop Menu ---");
                System.out.println("1. View Products");
                System.out.println("2. Add to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        displayProducts();
                        break;
                    case 2:
                        System.out.print("Enter product ID to add: ");
                        int id = sc.nextInt();
                        Product p = getProductById(id);
                        if (p != null) {
                            cart.add(p);
                            System.out.println(p.name + " added to cart.");
                        } else {
                            System.out.println("Product not found.");
                        }
                        break;
                    case 3:
                        cart.showCart();
                        break;
                    case 4:
                        System.out.println("Thanks for shopping!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } while (choice != 4);
        }

        public static void displayProducts() {
            try (Connection conn = DBConnection.getConnection();
                 Statement stmt = conn.createStatement()) {

                ResultSet rs = stmt.executeQuery("SELECT * FROM products");
                System.out.println("\nAvailable Products:");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ". " +
                            rs.getString("name") + " - ₹" + rs.getDouble("price"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static Product getProductById(int id) {
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id = ?")) {

                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
