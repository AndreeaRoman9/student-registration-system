package backend.studentregistrationsystem.services;

import backend.studentregistrationsystem.exceptions.NotFoundException;
import backend.studentregistrationsystem.models.Professor;
import backend.studentregistrationsystem.models.Student;
import backend.studentregistrationsystem.models.User;
import backend.studentregistrationsystem.repositories.ProfessorRepository;
import backend.studentregistrationsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    private final UserRepository userRepository;

    @Autowired
    public ProfessorService(UserRepository userRepository, ProfessorRepository professorRepository) {
        this.userRepository = userRepository;
        this.professorRepository = professorRepository;
    }

    public Professor findByUserId(long id) {
        return professorRepository.findByUserId(id);
    }

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public void deleteAll() {
        professorRepository.deleteAll();
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Transactional
    public void update(Long userId, String newLastName, String newFirstName)
    {
        Professor professor = professorRepository.findByUserId(userId);
        if (professor == null) {
            throw new NotFoundException("Professor with userId " + userId + " was not found.");
        }
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id " + userId + " was not found.")
        );
        user.setLastName(newLastName);
        user.setFirstName(newFirstName);
        userRepository.save(user);
    }
}
