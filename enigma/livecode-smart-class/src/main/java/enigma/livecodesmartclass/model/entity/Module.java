package enigma.livecodesmartclass.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Entity
@Table(name = "modules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private String description;

    @Column(name = "sequence_order")
    private Long sequenceOrder;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne()
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
}
