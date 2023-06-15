package backend.studentregistrationsystem.controllers;

import backend.studentregistrationsystem.exceptions.ConflictException;
import backend.studentregistrationsystem.exceptions.NotFoundException;
import backend.studentregistrationsystem.models.Role;
import backend.studentregistrationsystem.models.Student;
import backend.studentregistrationsystem.models.User;
import backend.studentregistrationsystem.services.RoleService;
import backend.studentregistrationsystem.services.StudentService;
import backend.studentregistrationsystem.services.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final RoleService roleService;
    private final UserService userService;
    private final StudentService studentService;

    public StudentController(RoleService roleService, UserService userService, StudentService studentService) {
        this.roleService = roleService;
        this.userService = userService;
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{userId}")
    public Student findByUserId(@PathVariable long userId) {
        return studentService.findByUserId(userId);
    }

    @DeleteMapping
    public ResponseEntity<String> delete() {
        studentService.deleteAll();
        return ResponseEntity.ok("All students were deleted.");
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody User user) {
        Role role = roleService.findByName("Student");
        user.setRole(role);
        this.userService.save(user);
        Student student = new Student(user);
        return new ResponseEntity<>(studentService.save(student), HttpStatusCode.valueOf(201));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Student> update(@PathVariable long userId, @RequestBody User user) {
        if(studentService.findByUserId(userId) == null) {
            throw new NotFoundException("Student with user id " + userId + " does not exist.");
        }
        this.studentService.update(userId, user.getLastName(), user.getFirstName());
        return ResponseEntity.ok(this.studentService.findByUserId(userId));
    }
}
