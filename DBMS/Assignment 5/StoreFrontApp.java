import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreFrontApp {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/StoreFront";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Establish a connection to the database
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    // Assignment 1: Fetch shipped orders of a user
    public static List<Order> getShippedOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT OrderId, OrderTimestamp FROM Orders WHERE ShopperId = ? AND Status = 'Shipped' ORDER BY OrderTimestamp ASC";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("OrderId"));
                order.setOrderDate(rs.getTimestamp("OrderTimestamp"));
                order.setOrderTotal(rs.getDouble("OrderTotal"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Assignment 2: Batch insert images for a product
    public static void batchInsertImages(int productId, List<String> imageUrls) {
        String query = "INSERT INTO Image (ProductId, URL) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            for (String url : imageUrls) {
                ps.setInt(1, productId);
                ps.setString(2, url);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Assignment 3: Delete unordered products in the last year
    public static int deleteUnorderedProducts(int yearThreshold) {
        String query = "DELETE FROM Product WHERE ProductId NOT IN (" +
                       "SELECT DISTINCT ProductId FROM OrderItem JOIN Orders ON OrderItem.OrderId = Orders.OrderId " +
                       "WHERE Orders.OrderTimestamp >= DATE_SUB(CURDATE(), INTERVAL ? YEAR))";

        int deletedCount = 0;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, yearThreshold);
            deletedCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedCount;
    }

    // Assignment 4: Fetch parent categories and their child counts
    public static List<CategorySummary> getParentCategoriesWithChildCount() {
        List<CategorySummary> categories = new ArrayList<>();
        String query = "SELECT c1.Name AS ParentCategory, COUNT(c2.CategoryId) AS ChildCount " +
                       "FROM Category c1 LEFT JOIN Category c2 ON c1.CategoryId = c2.ParentCategoryId " +
                       "WHERE c1.ParentCategoryId IS NULL GROUP BY c1.CategoryId ORDER BY c1.Name ASC";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CategorySummary summary = new CategorySummary();
                summary.setParentCategory(rs.getString("ParentCategory"));
                summary.setChildCount(rs.getInt("ChildCount"));
                categories.add(summary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    // Static POJO for Order
    public static class Order {
        private int id;
        private Timestamp orderDate;
        private double orderTotal;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public Timestamp getOrderDate() { return orderDate; }
        public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }

        public double getOrderTotal() { return orderTotal; }
        public void setOrderTotal(double orderTotal) { this.orderTotal = orderTotal; }
    }

    // Static POJO for CategorySummary
    public static class CategorySummary {
        private String parentCategory;
        private int childCount;

        public String getParentCategory() { return parentCategory; }
        public void setParentCategory(String parentCategory) { this.parentCategory = parentCategory; }

        public int getChildCount() { return childCount; }
        public void setChildCount(int childCount) { this.childCount = childCount; }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Assignment 1
        List<Order> orders = getShippedOrdersByUserId(1);
        orders.forEach(order -> System.out.println("OrderId: " + order.getId() + ", Date: " + order.getOrderDate() + ", Total: " + order.getOrderTotal()));

        // Test Assignment 2
        List<String> imageUrls = List.of("image1.jpg", "image2.jpg", "image3.jpg");
        batchInsertImages(1, imageUrls);

        // Test Assignment 3
        int deletedCount = deleteUnorderedProducts(1);
        System.out.println("Products deleted: " + deletedCount);

        // Test Assignment 4
        List<CategorySummary> categories = getParentCategoriesWithChildCount();
        categories.forEach(category -> System.out.println("ParentCategory: " + category.getParentCategory() + ", ChildCount: " + category.getChildCount()));
    }
}