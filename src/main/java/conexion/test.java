/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DAW2
 */
public class test {
    
    public static void main(String[] args) {
     /*   try (Connection conn = ConexionDB.obtenerConexion()) {
            String query = "SELECT * FROM empleados";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Empleado: " + rs.getString("nombre"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Al final de la aplicación, cerramos el pool (opcional)
            ConexionDB.cerrarPool();
        }*/
    }
    
}
