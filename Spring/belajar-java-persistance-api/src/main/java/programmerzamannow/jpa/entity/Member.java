package programmerzamannow.jpa.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Table(name = "members")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private Name name;

    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @PostLoad
    public void postLoad(){
        fullName = name.getTitle() + ". " + name.getFirstName() + " " + name.getMiddleName() + " " + name.getLastName();
    }

    @ElementCollection
    @CollectionTable(name = "hobbies", joinColumns = @JoinColumn(
            name = "member_id", referencedColumnName = "id"
    ))
    @Column(name = "name")
    private List<String> hobbies;

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    @ElementCollection
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(
            name = "member_id", referencedColumnName = "id"
    ))
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<String, Integer> skills;

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Integer> skills) {
        this.skills = skills;
    }
}
