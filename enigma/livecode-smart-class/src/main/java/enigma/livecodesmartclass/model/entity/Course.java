package enigma.livecodesmartclass.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    private String id;

    private String code;

    private String title;

    private String description;

    private String level;

    private Double price;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "course")
    private List<Module> modules;

    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private User user;
}
