package nl.springboot.safar.controllers;

import nl.springboot.safar.models.User;
import nl.springboot.safar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllStudents() {
        List<User> users = null;
        users = userService.getAllUsers();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/{id}") //GET at http://localhost:XXXX/students/3
    public ResponseEntity<User> getStudentPath(@PathVariable(value = "id") int id) {
        User student = userService.getUserBy(id);

        if(student != null) {
            return ResponseEntity.ok().body(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
