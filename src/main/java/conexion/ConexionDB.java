package conexion;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionDB {
    // Instancia estática de HikariDataSource (pool de conexiones)
    private static HikariDataSource dataSource;

    // Bloque estático que inicializa el pool de conexiones
    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/sprint");
            config.setUsername("root");
            config.setPassword("");
            config.setMaximumPoolSize(10);  // Tamaño máximo del pool de conexiones
            config.setConnectionTimeout(30000);  // Tiempo máximo para obtener una conexión
            
            // Crear el DataSource
            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obtener una conexión del pool de forma transparente
    public static Connection obtenerConexion() throws SQLException {
        return dataSource.getConnection();
    }

    // Método para cerrar el pool de conexiones (normalmente se llama al final de la aplicación)
    public static void cerrarPool() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
