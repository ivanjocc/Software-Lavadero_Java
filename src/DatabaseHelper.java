import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseHelper {
    private String url;
    private String usuario;
    private String contrasena;

    public DatabaseHelper() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("No se pudo encontrar el archivo config.properties");
                return;
            }
            prop.load(input);
            url = prop.getProperty("db.url");
            usuario = prop.getProperty("db.usuario");
            contrasena = prop.getProperty("db.contrasena");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection conectarConDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión establecida.");
            return conexion;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al conectar con la base de datos.");
            return null;
        }
    }
}
