package programmerzamannow.jpa.entity;

import jakarta.persistence.*;
import org.springframework.context.event.EventListener;
import programmerzamannow.jpa.listener.UpdatedAtListener;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

@Entity
@Table(name = "categories")
@EntityListeners({
        UpdatedAtListener.class
})
public class Category implements UpdatedAtAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
