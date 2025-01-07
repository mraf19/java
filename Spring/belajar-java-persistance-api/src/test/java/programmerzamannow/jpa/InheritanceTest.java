package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.*;
import programmerzamannow.jpa.util.JpaUtil;

import java.time.LocalDateTime;
import java.util.HashSet;

public class InheritanceTest {

    @Test
    void testCreateSingleTable() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Employee employee = new Employee();
        employee.setId("nona");
        employee.setName("Nona Narnia");
        entityManager.persist(employee);

        Manager manager = new Manager();
        manager.setId("dede");
        manager.setName("Dede Andreas");
        manager.setTotalEmployee(100);
        entityManager.persist(manager);

        VicePresident vp = new VicePresident();
        vp.setId("shiro");
        vp.setName("Shiro Kirei Hana");
        vp.setTotalManager(30);
        entityManager.persist(vp);


        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testFindSingleTable() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Manager manager = entityManager.find(Manager.class, "dede");
        Assertions.assertEquals("Dede Andreas", manager.getName());

        Employee employee = entityManager.find(Employee.class, "shiro");
        VicePresident vp = (VicePresident) employee;

        Assertions.assertEquals(30, vp.getTotalManager());


        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testInsertJoinedTable() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        PaymentGopay gopay1 = new PaymentGopay();
        gopay1.setId("gopay1");
        gopay1.setAmount(1_000_000L);
        gopay1.setGopayId("08123456789");
        entityManager.persist(gopay1);

        PaymentCreditCard cc1 = new PaymentCreditCard();
        cc1.setId("cc1");
        cc1.setAmount(10_000_000L);
        cc1.setMaskedCard("455-5555");
        cc1.setBank("BCA");
        entityManager.persist(cc1);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testFindJoinedTable() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        PaymentGopay gopay = entityManager.find(PaymentGopay.class, "gopay1");
        PaymentCreditCard creditCard = entityManager.find(PaymentCreditCard.class, "cc1");


        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testFindJoinedTableParent() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Payment gopay = entityManager.find(Payment.class, "gopay1");

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testInsertTablePerClass() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Transaction transaction = new Transaction();
        transaction.setId("trx01");
        transaction.setBalance(1_000_000L);
        transaction.setCreatedAt(LocalDateTime.now());
        entityManager.persist(transaction);

        TransactionCredit credit = new TransactionCredit();
        credit.setId("trx02");
        credit.setBalance(2_000_000L);
        credit.setCredit_amount(1_000_000L);
        credit.setCreatedAt(LocalDateTime.now());
        entityManager.persist(credit);

        TransactionDebit debit = new TransactionDebit();
        debit.setId("trx03");
        debit.setBalance(1_000_000L);
        debit.setDebitAmount(1_000_000L);
        debit.setCreatedAt(LocalDateTime.now());
        entityManager.persist(debit);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testFindTablePerClass() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Transaction transaction = entityManager.find(Transaction.class, "trx01");
        TransactionDebit debit = entityManager.find(TransactionDebit.class, "trx03");
        TransactionCredit credit = entityManager.find(TransactionCredit.class, "trx02");

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void testInsertMappedSuperClass() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Brand brand = new Brand();
        brand.setId("KANKY");
        brand.setName("SEPATU KANKY");
        brand.setDescription("Sepatu lokal untuk olahraga dan harian");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        entityManager.persist(brand);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

}
