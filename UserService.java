package ai.healthcare.example.healthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.healthcare.example.healthcare.entity.User;
import ai.healthcare.example.healthcare.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    // ✅ REGISTER
    public String register(User user) {

        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return "Email already registered!";
        }

        repo.save(user);
        return "Signup successful!";
    }

    // ✅ LOGIN (FIXED)
    public User login(String email, String password) {

        User user = repo.findByEmail(email).orElse(null);

        if (user == null) {
            System.out.println("❌ User not found");
            return null;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("❌ Wrong password");
            return null;
        }

        System.out.println("✅ Login success: " + user.getFullname());

        return user; // ✅ RETURN FULL USER OBJECT
    }
}