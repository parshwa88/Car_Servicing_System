import java.sql.*;

public class dbconectivity {

    public static void main(String[] args) {

        try {

            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "pr@12345"
            );

            System.out.println("Connected to PostgreSQL!");

            Statement stmt = conn.createStatement();

            String createTable =
                    "CREATE TABLE IF NOT EXISTS demo1(" +
                    "id INT PRIMARY KEY, " +
                    "name VARCHAR(50))";

            stmt.executeUpdate(createTable);

            System.out.println("Table Created!");

            String insert =
                    "INSERT INTO demo1 VALUES (1, 'ABC') " +
                    "ON CONFLICT (id) DO NOTHING";

            stmt.executeUpdate(insert);

            System.out.println("Data Inserted!");

            ResultSet rs = stmt.executeQuery("SELECT * FROM demo1");

            while (rs.next()) {

                System.out.println(
                        "ID: " + rs.getInt("id") +
                        " Name: " + rs.getString("name")
                );
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}