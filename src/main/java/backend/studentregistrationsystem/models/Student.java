package backend.studentregistrationsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
    @Id
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public Student() {

    }

    public Student(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getUserId() {
        return userId;
    }
}
