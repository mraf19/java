package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

public class TransactionTest {

    @Test
    void testTransaction() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try{
            entityTransaction.begin();
            //DO SOMETHING
            entityTransaction.commit();
        } catch (Throwable throwable){
            entityTransaction.rollback();
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
