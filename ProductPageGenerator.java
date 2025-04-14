import java.sql.*;
import java.io.*;
public class ProductPageGenerator {

        public static void main(String[] args) {
            try (Connection conn = DBConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 PrintWriter writer = new PrintWriter("products.html")) {

                ResultSet rs = stmt.executeQuery("SELECT * FROM products");

                writer.println("<!DOCTYPE html>");
                writer.println("<html lang='en'>");
                writer.println("<head>");
                writer.println("<meta charset='UTF-8'>");
                writer.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                writer.println("<title>Product List</title>");
                writer.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
                writer.println("</head>");
                writer.println("<body>");
                writer.println("<div class='container mt-5'>");
                writer.println("<h1 class='mb-4 text-center'>Available Products</h1>");
                writer.println("<div class='row row-cols-1 row-cols-md-3 g-4'>");

                while (rs.next()) {
                    writer.println("<div class='col'>");
                    writer.println("  <div class='card h-100 shadow'>");
                    writer.println("    <div class='card-body'>");
                    writer.println("      <h5 class='card-title'>" + rs.getString("name") + "</h5>");
                    writer.println("      <p class='card-text'>Price: ₹" + rs.getDouble("price") + "</p>");
                    writer.println("      <a href='#' class='btn btn-primary'>Add to Cart</a>");
                    writer.println("    </div>");
                    writer.println("  </div>");
                    writer.println("</div>");
                }

                writer.println("</div></div></body></html>");

                System.out.println("✅ products.html generated successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
