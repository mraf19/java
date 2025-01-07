package programmerzamannow.jpa.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

    @EmbeddedId
    private DepartmentId departmentId;

    private String name;

    public DepartmentId getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(DepartmentId departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
