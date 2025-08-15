import java.sql.*;
import java.util.*;

public class EmployeeApp {

    private static final String URL = "jdbc:mysql://localhost:3306/employees_db"; // or jdbc:postgresql://localhost:5432/employees_db
    private static final String USER = "root"; // DB username
    private static final String PASS = "password"; // DB password

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("✅ Connected to Database");

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n=== Employee Database Menu ===");
                System.out.println("1. Insert Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. Update Employee Salary");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> insertEmployee(conn, sc);
                    case 2 -> viewEmployees(conn);
                    case 3 -> updateSalary(conn, sc);
                    case 4 -> deleteEmployee(conn, sc);
                    case 5 -> {
                        System.out.println("🚪 Exiting...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("❌ Invalid choice!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter role: ");
        String role = sc.nextLine();
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        String sql = "INSERT INTO employee (name, role, salary) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, role);
            pst.setDouble(3, salary);
            pst.executeUpdate();
            System.out.println("✅ Employee added successfully!");
        }
    }

    private static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employee";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\nID | Name | Role | Salary");
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getDouble("salary"));
            }
        }
    }

    private static void updateSalary(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        System.out.print("Enter new salary: ");
        double salary = sc.nextDouble();

        String sql = "UPDATE employee SET salary = ? WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setDouble(1, salary);
            pst.setInt(2, id);
            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("✅ Salary updated!");
            else System.out.println("❌ Employee not found!");
        }
    }

    private static void deleteEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM employee WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("✅ Employee deleted!");
            else System.out.println("❌ Employee not found!");
        }
    }
}
