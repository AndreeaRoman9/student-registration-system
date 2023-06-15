package backend.studentregistrationsystem.repositories;

import java.util.List;

import backend.studentregistrationsystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    Role findById(long id);
    void deleteById(long id);
    void deleteByName(String name);
    void deleteAllByName(String name);
}