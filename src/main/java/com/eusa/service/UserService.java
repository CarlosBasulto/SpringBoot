package com.eusa.service;

import com.eusa.model.users;
import conexion.*;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // Método para obtener todos los usuarios
    public List<users> getAllUsers() {
        List<users> users = new ArrayList<>();
        try (Connection conn = ConexionDB.obtenerConexion()) {
            String query = "SELECT * FROM usuarios"; // Consulta a la base de datos
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    users.add(new users(id, name, email));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Obtener un usuario por su ID
    public Optional<users> getUserById(Long id) {
        users user = null;
        try (Connection conn = ConexionDB.obtenerConexion()) {
            String query = "SELECT * FROM usuarios WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String name = rs.getString("name");
                        String email = rs.getString("email");
                        user = new users(id, name, email);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    // Crear un nuevo usuario
    public users createUser(users user) {
        try (Connection conn = ConexionDB.obtenerConexion()) {
            String query = "INSERT INTO usuarios (name, email) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getEmail());
                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            user.setId(generatedKeys.getLong(1));  // Asignar el ID generado
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Actualizar un usuario
    public users updateUser(Long id, users user) {
        try (Connection conn = ConexionDB.obtenerConexion()) {
            String query = "UPDATE usuarios SET name = ?, email = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getEmail());
                stmt.setLong(3, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Eliminar un usuario
    public boolean deleteUser(Long id) {
        try (Connection conn = ConexionDB.obtenerConexion()) {
            String query = "DELETE FROM usuarios WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setLong(1, id);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
