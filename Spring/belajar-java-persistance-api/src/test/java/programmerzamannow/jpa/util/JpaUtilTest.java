package programmerzamannow.jpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JpaUtilTest {

    @Test
    void testCreate() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();

        Assertions.assertNotNull(entityManagerFactory);
    }

    @Test
    void testEntityManager() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Assertions.assertNotNull(entityManager);

        entityManager.close();
        entityManagerFactory.close();
    }
}
