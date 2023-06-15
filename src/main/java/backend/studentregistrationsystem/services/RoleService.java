package backend.studentregistrationsystem.services;

import backend.studentregistrationsystem.exceptions.NotFoundException;
import backend.studentregistrationsystem.models.Role;
import backend.studentregistrationsystem.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void deleteAll() {
        roleRepository.deleteAll();
    }

    public void deleteById(long id) {
        roleRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        roleRepository.deleteByName(name);
    }

    public void deleteAllByName(String name) {
        roleRepository.deleteAllByName(name);
    }

    public void deleteAllById(Iterable<Long> ids) {
        roleRepository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<Long> ids) {
        roleRepository.deleteAllById(ids);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role findById(long id) {
        return roleRepository.findById(id);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public void update(Long id, String newName) {
        Role role = roleRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Role not found with id " + id)
        );
        role.setName(newName);
    }
}
