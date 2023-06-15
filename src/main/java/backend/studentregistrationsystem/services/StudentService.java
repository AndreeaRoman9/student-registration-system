package backend.studentregistrationsystem.services;

import backend.studentregistrationsystem.exceptions.NotFoundException;
import backend.studentregistrationsystem.models.Role;
import backend.studentregistrationsystem.models.Student;
import backend.studentregistrationsystem.models.User;
import backend.studentregistrationsystem.repositories.RoleRepository;
import backend.studentregistrationsystem.repositories.StudentRepository;
import backend.studentregistrationsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    public Student findByUserId(long id) {
        return studentRepository.findByUserId(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void deleteAll() {
        studentRepository.deleteAll();
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional
    public void update(Long userId, String newLastName, String newFirstName)
    {
        Student student = studentRepository.findByUserId(userId);
        if (student == null) {
            throw new NotFoundException("Student with userId " + userId + " was not found.");
        }
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id " + userId + " was not found.")
        );
        user.setLastName(newLastName);
        user.setFirstName(newFirstName);
        userRepository.save(user);
    }
}
