package nl.springboot.safar.controllers;

import nl.springboot.safar.models.User;
import nl.springboot.safar.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = null;
        users = userService.findAll();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> GetUser(@PathVariable(value = "id", required = false) int id) {
        Optional<User> user = userService.findById(id);

        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> CreateUser(@RequestBody User user){
        User createdUser = userService.save(user);
        URI location = URI.create(String.format("/users/" + createdUser.getId()));
        return ResponseEntity.created(location).build();
    }
}
