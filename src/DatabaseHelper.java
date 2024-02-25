import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    private String url = "jdbc:mysql://localhost:3306/lavadero";
    private String usuario = "root";
    private String contrasena = "12345";

    public Connection conectarConDB() {
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión establecida.");
            return conexion;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar con la base de datos.");
            return null;
        }
    }
}
