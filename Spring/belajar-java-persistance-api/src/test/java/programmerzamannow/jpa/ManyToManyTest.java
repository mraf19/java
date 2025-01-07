package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Product;
import programmerzamannow.jpa.entity.User;
import programmerzamannow.jpa.util.JpaUtil;

import java.util.HashSet;

public class ManyToManyTest {

    @Test
    void testInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Product product1 = entityManager.find(Product.class, "p1");
        Product product2 = entityManager.find(Product.class, "p2");

        User user = entityManager.find(User.class, "shiro");
        user.setLikes(new HashSet<>());
        user.getLikes().add(product1);
        user.getLikes().add(product2);

        entityManager.merge(user);

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

        User user = entityManager.find(User.class, "shiro");

        user.getLikes().forEach(like -> {
            System.out.println(like.getName());
        });

        Assertions.assertEquals(1, user.getLikes().size());

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testUpdate() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        User user = entityManager.find(User.class, "shiro");
        Product product = null;

        for(Product item: user.getLikes()){
            product = item;
            break;
        }

        user.getLikes().remove(product);

        entityManager.merge(user);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
