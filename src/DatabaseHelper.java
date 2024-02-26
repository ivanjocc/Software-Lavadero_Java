import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

//    private String url = "jdbc:mysql://localhost:3306/lavadero";
//    private String usuario = "root";
//    private String contrasena = "12345";
    private String url = "jdbc:mysql://bcohbd0oyjx7z9rwzyqx-mysql.services.clever-cloud.com:3306/bcohbd0oyjx7z9rwzyqx?useSSL=false&serverTimezone=UTC";
    private String usuario = "u4gmjzzubc4wdijg";
    private String contrasena = "LlVgKVWuHYMWMXkXOpxd";

//    public Connection conectarConDB() {
//        try {
//            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
//            System.out.println("Conexión establecida.");
//            return conexion;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Error al conectar con la base de datos.");
//            return null;
//        }
//    }

    public Connection conectarConDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión establecida.");
            return conexion;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar con la base de datos.");
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("No se encontró el driver de MySQL.");
            return null;
        }
    }
}
