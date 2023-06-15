package backend.studentregistrationsystem.repositories;

import java.util.List;
import backend.studentregistrationsystem.models.Role;
import backend.studentregistrationsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);

    User findByFirstName(String firstName);

    User findByLastName(String lastName);

    List<User> findByRole(Role role);
}