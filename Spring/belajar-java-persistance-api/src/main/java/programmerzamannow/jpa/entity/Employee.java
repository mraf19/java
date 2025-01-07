package programmerzamannow.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
@DiscriminatorColumn(name = "type")
@DiscriminatorValue("EMPLOYEE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    private String id;

    private String name;
}
