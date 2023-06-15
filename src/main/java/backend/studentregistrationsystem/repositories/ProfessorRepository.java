package backend.studentregistrationsystem.repositories;

import backend.studentregistrationsystem.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findByUserId(long id);
}
