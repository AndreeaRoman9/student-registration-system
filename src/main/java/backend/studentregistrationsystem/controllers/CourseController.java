package backend.studentregistrationsystem.controllers;

import backend.studentregistrationsystem.exceptions.ConflictException;
import backend.studentregistrationsystem.exceptions.NotFoundException;
import backend.studentregistrationsystem.models.Course;
import backend.studentregistrationsystem.models.Role;
import backend.studentregistrationsystem.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        if(courseService.findByName(course.getName()) != null) {
            throw new ConflictException("Course with name " + course.getName() + " already exists.");
        }
        return ResponseEntity.ok(courseService.save(course));
    }

    @GetMapping
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<Course> findByName(@PathVariable String name) {
        if(courseService.findByName(name) == null) {
            throw new ConflictException("Course with name " + name + " does not exist.");
        }
        return ResponseEntity.ok(courseService.findByName(name));
    }

    @DeleteMapping
    public ResponseEntity<String> delete() {
        courseService.deleteAll();
        return ResponseEntity.ok("All courses were deleted.");
    }

    @DeleteMapping("/delete-by-name/{name}")
    public void deleteByName(@PathVariable String name) {
        if(courseService.findByName(name) == null) {
            throw new NotFoundException("Course with name " + name + " does not exist.");
        }
        courseService.deleteByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable long id, @RequestBody Role role) {
        if(courseService.findById(id) != null) {
            throw new NotFoundException("Course with id " + id + " does not exist.");
        }
        this.courseService.update(id, role.getName());
        return ResponseEntity.ok(courseService.findById(id));
    }
}
