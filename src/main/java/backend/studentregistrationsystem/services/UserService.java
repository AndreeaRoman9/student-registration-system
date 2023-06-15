package backend.studentregistrationsystem.services;

import java.util.List;
import backend.studentregistrationsystem.models.Role;
import backend.studentregistrationsystem.models.User;
import backend.studentregistrationsystem.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }
    
    public User findById(long id) {
        return userRepository.findById(id);
    }
    
    public void delete(User user) {
        userRepository.delete(user);
    }
    
    public java.util.List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public User findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }
    
    public List<User> findByRole(Role role) {
        return userRepository.findByRole(role);
    }
}
