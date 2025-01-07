package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Brand;
import programmerzamannow.jpa.util.JpaUtil;

import java.time.LocalDateTime;

public class LockingTest {

    @Test
    void testInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

//        Brand brand = new Brand();
//        brand.setId("NOKIA");
//        brand.setName("Nokia");
//        brand.setDescription("HP KUAT DAN TAHAN LAMA");
//        brand.setCreatedAt(LocalDateTime.now());
//        brand.setUpdatedAt(LocalDateTime.now());
//        entityManager.persist(brand);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testDemo1() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "NOKIA");
        brand.setName("Nokia Demo 1");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());

        Thread.sleep(10* 1_000L);
        entityManager.persist(brand);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testDemo2() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "NOKIA");
        brand.setName("Nokia Demo 1");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        entityManager.persist(brand);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testOptimisticDemo1() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "NOKIA", LockModeType.PESSIMISTIC_WRITE);
        brand.setName("Nokia UPDATED 1");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());

        Thread.sleep(10* 1_000L);
        entityManager.persist(brand);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void tesOptimistictDemo2() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "NOKIA", LockModeType.PESSIMISTIC_WRITE);
        brand.setName("Nokia UPDATED 2");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        entityManager.persist(brand);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
