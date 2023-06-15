package backend.studentregistrationsystem.repositories;

import backend.studentregistrationsystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUserId(long id);
}
