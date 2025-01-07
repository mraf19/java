package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Category;
import programmerzamannow.jpa.entity.Member;
import programmerzamannow.jpa.util.JpaUtil;

import java.time.LocalDateTime;
import java.util.Calendar;

public class EntityListenerTest {

    @Test
    void listener() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Category category = new Category();
        category.setName("Toy");

        entityManager.persist(category);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void listenerEntity() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Member member = entityManager.find(Member.class, "1");

        entityTransaction.commit();

        Assertions.assertEquals("Sacred Flower. Shiro Kirei Hana", member.getFullName());

        entityManager.close();
        entityManagerFactory.close();
    }
}
