package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.*;
import programmerzamannow.jpa.util.JpaUtil;

import java.time.LocalDateTime;
import java.util.Calendar;

public class EmbeddedTest {

    @Test
    void testInsert() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Name name = new Name();
        name.setFirstName("Shiro");
        name.setMiddleName("Kirei");
        name.setLastName("Hana");
        name.setTitle("Sacred Flower");

        Member member = new Member();
        member.setEmail("member@email.com");
        member.setName(name);


        entityManager.persist(member);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
    @Test
    void testInsertId() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        DepartmentId departmentId = new DepartmentId();
        departmentId.setCompanyId("shiro");
        departmentId.setDepartmentId("tech");

        Department department = new Department();
        department.setName("shiro tech");
        department.setDepartmentId(departmentId);


        entityManager.persist(department);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testFindId() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        DepartmentId departmentId = new DepartmentId();
        departmentId.setCompanyId("shiro");
        departmentId.setDepartmentId("tech");


        Department department = entityManager.find(Department.class, departmentId);

        Assertions.assertEquals("shiro tech", department.getName() );

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

}
