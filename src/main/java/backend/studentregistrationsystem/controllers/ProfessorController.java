package backend.studentregistrationsystem.controllers;

import backend.studentregistrationsystem.exceptions.ConflictException;
import backend.studentregistrationsystem.exceptions.NotFoundException;
import backend.studentregistrationsystem.models.Professor;
import backend.studentregistrationsystem.models.Role;
import backend.studentregistrationsystem.models.User;
import backend.studentregistrationsystem.services.ProfessorService;
import backend.studentregistrationsystem.services.RoleService;
import backend.studentregistrationsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {
    private final RoleService roleService;
    private final UserService userService;
    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(RoleService roleService, UserService userService, ProfessorService professorService) {
        this.roleService = roleService;
        this.userService = userService;
        this.professorService = professorService;
    }

    @GetMapping
    public List<Professor> findAll() {
        return professorService.findAll();
    }

    @GetMapping("/{userId}")
    public Professor findByUserId(@PathVariable long userId) {
        return professorService.findByUserId(userId);
    }

    @DeleteMapping
    public ResponseEntity<String> delete() {
        professorService.deleteAll();
        return ResponseEntity.ok("All professors were deleted.");
    }

    @PostMapping
    public ResponseEntity<Professor> create(@RequestBody User user) {
        Role role = roleService.findByName("Professor");
        user.setRole(role);
        this.userService.save(user);
        Professor professor = new Professor(user);
        return new ResponseEntity<>(professorService.save(professor), HttpStatusCode.valueOf(201));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Professor> update(@PathVariable long userId, @RequestBody User user) {
        if(professorService.findByUserId(userId) == null) {
            throw new NotFoundException("Professor with user id " + userId + " does not exist.");
        }
        this.professorService.update(userId, user.getLastName(), user.getFirstName());
        return ResponseEntity.ok(this.professorService.findByUserId(userId));
    }
}
