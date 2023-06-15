package backend.studentregistrationsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name="grades")
public class Grade {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private float value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
}
