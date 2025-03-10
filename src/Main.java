pack
import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        // Get database credentials from environment variables
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        try {
            // Establishing a connection to the database
            Connection conn = DriverManager.getConnection(url, user, password);

            // Insert a new student record
            String insertSql = "INSERT INTO student (name, marks) VALUES ('Sharlak', 01);";
            Statement insertStmt = conn.createStatement();
            insertStmt.executeUpdate(insertSql);
            insertStmt.close();

            // Update a student record
            String updateSql = "UPDATE student SET marks = 75 WHERE name = 'Sharlak';";
            Statement updateStmt = conn.createStatement();
            updateStmt.executeUpdate(updateSql);
            updateStmt.close();

            // Select and retrieve student records
            String selectSql = "SELECT * FROM student WHERE name = 'Sharlak';";
            Statement selectStmt = conn.createStatement();
            ResultSet rs = selectStmt.executeQuery(selectSql);

            // Process the result set
            while (rs.next()) {
                String name = rs.getString("name");
                int marks = rs.getInt("marks");
                System.out.println("Student Name: " + name + ", Marks: " + marks);
            }

            rs.close();
            selectStmt.close();

            // Delete a student record
            String deleteSql = "DELETE FROM student WHERE name = 'Sharlak';";
            Statement deleteStmt = conn.createStatement();
            deleteStmt.executeUpdate(deleteSql);
            deleteStmt.close();

            // Close the connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}