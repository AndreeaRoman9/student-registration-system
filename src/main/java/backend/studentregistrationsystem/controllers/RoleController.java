package backend.studentregistrationsystem.controllers;

import backend.studentregistrationsystem.exceptions.ConflictException;
import backend.studentregistrationsystem.exceptions.NotFoundException;
import backend.studentregistrationsystem.models.Role;
import backend.studentregistrationsystem.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role role) {
        if(roleService.findByName(role.getName()) != null) {
            throw new ConflictException("Role with name " + role.getName() + " already exists.");
        }
        return ResponseEntity.ok(roleService.save(role));
    }

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<Role> findByName(@PathVariable String name) {
        if(roleService.findByName(name) == null) {
            throw new ConflictException("Role with name " + name + " does not exist.");
        }
        return ResponseEntity.ok(roleService.findByName(name));
    }

    @DeleteMapping
    public ResponseEntity<String> delete() {
        roleService.deleteAll();
        return ResponseEntity.ok("All roles were deleted.");
    }

    @DeleteMapping("/delete-by-name/{name}")
    public void deleteByName(@PathVariable String name) {
        if(roleService.findByName(name) == null) {
            throw new NotFoundException("Role with name " + name + " does not exist.");
        }
        roleService.deleteByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable long id, @RequestBody Role role) {
        if(roleService.findById(id) == null) {
            throw new NotFoundException("Role with id " + id + " does not exist.");
        }
        this.roleService.update(id, role.getName());
        return ResponseEntity.ok(roleService.findById(id));
    }
}
