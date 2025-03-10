import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;
public class MakeTable {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // Get database credentials from environment variables
            Dotenv dotenv = Dotenv.load();
            String url = dotenv.get("DB_URL");
            String user = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASSWORD");

            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database");

            // Create a statement
            st = conn.createStatement();

            // Create table if not exists
            String createTableSQL = "CREATE TABLE IF NOT EXISTS contacts ("
                    + "id SERIAL PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "address VARCHAR(150), "
                    + "phoneNumber VARCHAR(15))";
            st.executeUpdate(createTableSQL);
            System.out.println("Table created or already exists");

            // Insert data
            String insertDataSQL = "INSERT INTO contacts (name, address, phoneNumber) VALUES "
                    + "('John Doe', '123 Main St', '555-1234'), "
                    + "('Jane Smith', '456 Elm St', '555-5678'), "
                    + "('Alice Johnson', '789 Oak St', '555-9012')";
            int numberOfRowsAffected = st.executeUpdate(insertDataSQL);
            System.out.println(numberOfRowsAffected + " rows inserted");

            // Retrieve data
            rs = st.executeQuery("SELECT * FROM contacts");
            while (rs.next()) {
                System.out.println("Contact's name is " + rs.getString("name") + " and the phone number is "
                        + rs.getString("phoneNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                if (conn != null)
                    conn.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
