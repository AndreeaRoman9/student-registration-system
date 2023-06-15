package backend.studentregistrationsystem.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="professors")
public class Professor {
    @Id
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "professors")
    private final Set<Course> courses = new HashSet<>();

    public Professor() {

    }

    public Professor(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getUserId() {
        return userId;
    }

    public Set<String> getCourses() {
        Set<String> courses = new HashSet<>();
        for (Course course : this.courses) {
            courses.add(course.getName());
        }
        return courses;
    }
}