package programmerzamannow.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends Employee{

    public Integer getTotalEmployee() {
        return totalEmployee;
    }

    public void setTotalEmployee(Integer totalEmployee) {
        this.totalEmployee = totalEmployee;
    }

    @Column(name = "total_employee")
    private Integer totalEmployee;
}
