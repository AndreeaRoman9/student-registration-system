package backend.studentregistrationsystem.services;

import backend.studentregistrationsystem.exceptions.NotFoundException;
import backend.studentregistrationsystem.models.Course;
import backend.studentregistrationsystem.models.Role;
import backend.studentregistrationsystem.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void deleteAll() {
        courseRepository.deleteAll();
    }

    public void deleteById(long id) {
        courseRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        courseRepository.deleteByName(name);
    }

    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }

    public Course findById(long id) {
        return courseRepository.findById(id);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Transactional
    public void update(Long id, String newName) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Course not found with id " + id)
        );
        course.setName(newName);
    }
}
