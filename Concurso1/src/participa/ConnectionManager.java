package participa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/concurso_db";
    private static final String USER = "root";
    private static final String PASSWORD = "gabriel";
    
    private static Connection connection = null;

    private ConnectionManager() {} 

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException("Error al conectar con la base de datos.", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new RuntimeException("Error al cerrar la conexión.", e);
            }
        }
    }
}
