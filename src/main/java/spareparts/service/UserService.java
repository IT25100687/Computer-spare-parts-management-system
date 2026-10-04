package spareparts.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import spareparts.repository.UserRepository;
import spareparts.model.User;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initDefaultAdmin() {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User(null, "admin", "admin123", "System Administrator", "admin@spareparts.com", "ADMIN");
            userRepository.save(admin);
        }
        if (!userRepository.existsByUsername("inventory")) {
            User inventory = new User(null, "inventory", "inventory123", "Inventory Staff", "inventory@spareparts.com", "INVENTORY_STAFF");
            userRepository.save(inventory);
        }
        if (!userRepository.existsByUsername("sales")) {
            User sales = new User(null, "sales", "sales123", "Sales Staff", "sales@spareparts.com", "SALES_STAFF");
            userRepository.save(sales);
        }
    }

    public User registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username '" + user.getUsername() + "' is already registered. Please choose another username or log in.");
        }
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("CUSTOMER");
        }
        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Invalid username or password.");
        }
        User user = optionalUser.get();
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password.");
        }
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}