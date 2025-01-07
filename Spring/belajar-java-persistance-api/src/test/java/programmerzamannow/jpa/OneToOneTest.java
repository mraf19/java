package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.*;
import programmerzamannow.jpa.util.JpaUtil;

public class OneToOneTest {

    @Test
    void create() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Credential credential = new Credential();
        credential.setId("shiro");
        credential.setEmail("shiro@gmail.com");
        credential.setPassword("rahasia");

        entityManager.persist(credential);

        User user = new User();
        user.setId("shiro");
        user.setName("Shiro");
        entityManager.persist(user);



        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        User shiro = entityManager.find(User.class, "shiro");

        Assertions.assertNotNull(shiro.getCredential());

        Assertions.assertEquals("shiro@gmail.com", shiro.getCredential().getEmail());
        Assertions.assertEquals("rahasia", shiro.getCredential().getPassword());
        Assertions.assertEquals("shiro", shiro.getCredential().getId());
        Assertions.assertEquals("shiro", shiro.getId());
        Assertions.assertEquals("Shiro", shiro.getName());

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testInsertWallet() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        User shiro = entityManager.find(User.class, "shiro");

        Wallet wallet = new Wallet();
        wallet.setUser(shiro);
        wallet.setBalance(1_000_000L);
        entityManager.persist(wallet);


        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testFindWallet() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        User shiro = entityManager.find(User.class, "shiro");

        Assertions.assertNotNull(shiro.getWallet());

        Assertions.assertEquals(1_000_000L, shiro.getWallet().getBalance());;

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
