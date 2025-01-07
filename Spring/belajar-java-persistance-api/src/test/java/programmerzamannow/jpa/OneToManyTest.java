package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Brand;
import programmerzamannow.jpa.entity.Product;
import programmerzamannow.jpa.entity.User;
import programmerzamannow.jpa.util.JpaUtil;

public class OneToManyTest {

    @Test
    void testCreate() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Brand brand = new Brand();
        brand.setId("Brand-Samsung");
        brand.setName("Samsung");
        brand.setDescription("Brand Samsung");
        entityManager.persist(brand);

        Product product1 = new Product();
        product1.setId("p1");
        product1.setName("Samsung Galaxy");
        product1.setBrand(brand);
        product1.setPrice(10_000_000L);
        entityManager.persist(product1);

        Product product2 = new Product();
        product2.setId("p2");
        product2.setName("Samsung S23");
        product2.setBrand(brand);
        product2.setPrice(25_000_000L);
        entityManager.persist(product2);

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

        Product product1 = entityManager.find(Product.class, "p1");
        Product product2 = entityManager.find(Product.class, "p2");

        Assertions.assertEquals(product1.getBrand().getName(), product2.getBrand().getName());

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
