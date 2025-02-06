package com.eusa.controllers;

import com.eusa.model.users;
import com.eusa.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DAW2
 */
@RestController
@RequestMapping("/usuarios") // Ruta base para todos los endpoints de usuarios
public class usersControllers {

    private final UserService userService; // Se inyecta el servicio que manejará la lógica

    // Constructor con inyección de dependencias
    public usersControllers(UserService userService) {
        this.userService = userService;
    }

    // Controlador REST para obtener todos los usuarios
    @GetMapping("/")
    public List<users> getUsers() {
        return userService.getAllUsers(); // Llamada al servicio para obtener todos los usuarios
    }
    
   @GetMapping("/{id}")
    public Optional<users> getUserById(@PathVariable Long id) {
        return userService.getUserById(id); // Llamada al servicio para obtener el usuario por ID
    }

    
  
}


/*

@RestController
@RequestMapping("/usuarios") // Ruta base para todos los endpoints de usuarios
public class UsersControllers {

    private final UserService userService;

    // Inyección de dependencias
    public UsersControllers(UserService userService) {
        this.userService = userService;
    }

    // Obtener todos los usuarios
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers(); // Llamada al servicio para obtener la lista de usuarios
    }

    // Obtener un usuario por su ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id); // Llamada al servicio para obtener el usuario por ID
    }

    // Crear un nuevo usuario
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED) // Responde con código 201 (creado) cuando el usuario se crea
    public User createUser(@RequestBody User user) {
        return userService.createUser(user); // Llamada al servicio para crear un nuevo usuario
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user); // Llamada al servicio para actualizar el usuario
    }

    // Eliminar un usuario por su ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Responde con código 204 (sin contenido) cuando el usuario se elimina
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id); // Llamada al servicio para eliminar el usuario
    }
}
*/