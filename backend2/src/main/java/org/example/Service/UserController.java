// UserController.java
package org.example.Service;

import org.example.Domain.User;
import org.example.Repository.UserHibernateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserHibernateRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return UserHibernateRepository.find(id);
    }

    // Additional CRUD methods can be added here
}
