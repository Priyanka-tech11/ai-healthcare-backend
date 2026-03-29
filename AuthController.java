package ai.healthcare.example.healthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import ai.healthcare.example.healthcare.entity.User;
import ai.healthcare.example.healthcare.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService service;

    // ✅ SIGNUP
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        return ResponseEntity.ok(service.register(user));
    }

   
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User loggedInUser = service.login(user.getEmail(), user.getPassword());

        if (loggedInUser != null) {

            return ResponseEntity.ok(Map.of(
                "status", "success",
                "name", loggedInUser.getFullname(), 
                "email", loggedInUser.getEmail()
            ));

        } else {
            return ResponseEntity.status(401).body(Map.of(
                "status", "error",
                "message", "Invalid credentials"
            ));
        }
    }
}