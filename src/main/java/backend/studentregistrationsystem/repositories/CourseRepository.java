package backend.studentregistrationsystem.repositories;

import backend.studentregistrationsystem.models.Course;
import backend.studentregistrationsystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByName(String name);
    void deleteByName(String name);
    Course findById(long id);
}