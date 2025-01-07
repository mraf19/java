package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Customer;
import programmerzamannow.jpa.entity.CustomerType;
import programmerzamannow.jpa.util.JpaUtil;

public class CrudTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    @AfterEach
    void tearDown() {
        entityManagerFactory.close();
    }

    @Test
    void create() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("3");
        customer.setName("Momo");

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }


    @Test
    void read() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Customer customer = entityManager.find(Customer.class, "1");

        entityTransaction.commit();

        Assertions.assertEquals("1", customer.getId());
        Assertions.assertEquals("Shiro", customer.getName());


        entityManager.close();
    }

    @Test
    void update() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Customer customer = entityManager.find(Customer.class, "3");
        customer.setType(CustomerType.REGULAR);
        customer.setFullName("Shiroi Hana");

        entityManager.merge(customer);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void delete() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Customer customer = entityManager.find(Customer.class, "2");
        entityManager.remove(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
