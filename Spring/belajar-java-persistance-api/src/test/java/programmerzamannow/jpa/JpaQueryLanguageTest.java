package programmerzamannow.jpa;

import jakarta.persistence.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.*;
import programmerzamannow.jpa.util.JpaUtil;

import java.util.HashSet;
import java.util.List;

public class JpaQueryLanguageTest {

    @Test
    void select() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("Select b from Brand b", Brand.class);

        List<Brand> resultList = query.getResultList();

        Assertions.assertNotNull(resultList);

        for (Brand result: resultList){
            System.out.println(result.getId() + ": " + result.getName());
        }

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void where() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("Select m from Member m where m.name.firstName = :firstName" +
                " and m.name.lastName = :lastName", Member.class);

        query.setParameter("firstName", "Shiro");
        query.setParameter("lastName", "Hana");

        List<Member> members = query.getResultList();

        Assertions.assertNotNull(members);

        for (Member member: members){
            System.out.println(member.getFullName());
        }

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void join() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p join p.brand b where b.name = :brand", Product.class);
        query.setParameter("brand", "SEPATU KANKY");

        List<Product> products = query.getResultList();

        Assertions.assertNotNull(products);

        for (Product product: products){
            System.out.println(product.getBrand() + ": " + product.getName());
        }
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void joinFetch() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        TypedQuery<User> query = entityManager.createQuery("Select u from User u join fetch u.likes p where p.name = :product", User.class);
        query.setParameter("product", "Samsung");

        List<User> users = query.getResultList();

        for (User user: users ) {
            for (Product product: user.getLikes()){
                System.out.println(product.getBrand().getName());
            }
        }
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void orderBy() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.name desc", Brand.class);

        List<Brand> brands = query.getResultList();

        for (var brand: brands){
            System.out.println(brand.getId() + ": " + brand.getName() + " - " + brand.getDescription());
        }
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void limitOffset() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.name desc", Brand.class);
        query.setMaxResults(1);
        query.setFirstResult(1);
        List<Brand> brands = query.getResultList();

        for (var brand: brands){
            System.out.println(brand.getId() + ": " + brand.getName() + " - " + brand.getDescription());
        }
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void namedQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createNamedQuery("Brand.findAllByName", Brand.class);
        query.setParameter("name", "Samsung");

        List<Brand> brands = query.getResultList();

        for (Brand brand: brands){
            System.out.println(brand.getName());
        }
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void selectSomeField() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("Select b.id, b.name from Brand b where b.name = :name", Object[].class);
        query.setParameter("name", "Samsung");
        List<Object[]> objects = query.getResultList();

        for (Object[] object : objects) {
            System.out.println(object[0] + ": " + object[1]);
        }
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void selectSomeFieldConstructorExpression() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        TypedQuery<SimpleBrand> query = entityManager.createQuery("select new programmerzamannow.jpa.entity.SimpleBrand(b.id, b.name)"
                + " from Brand b where b.name = :name", SimpleBrand.class);
        query.setParameter("name", "Samsung");
        List<SimpleBrand> objects = query.getResultList();

        for (SimpleBrand brand : objects) {
            System.out.println(brand.getId() + ": " + brand.getName());
        }
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void aggregate() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select max(p.price), min(p.price), avg(p.price) from Product p", Object[].class);
        Object[] objects = query.getSingleResult();

        System.out.println("Max :" + objects[0]);
        System.out.println("Min :" + objects[1]);
        System.out.println("Average :" + objects[2]);
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void groupbyHaving() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select b.id, max(p.price), min(p.price), avg(p.price) from Product p join p.brand" +
                " b group by b.id having min(p.price) > :min", Object[].class);
        query.setParameter("min", 500_000L);
        Object[] objects = query.getSingleResult();

        System.out.println("Brand :" + objects[0]);
        System.out.println("Max :" + objects[1]);
        System.out.println("Min :" + objects[2]);
        System.out.println("Average :" + objects[3]);
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void nativeQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Query query = entityManager.createNativeQuery("select * from brands where brands.id = :name", Brand.class);
        query.setParameter("name", "Samsung");
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getName());
        }

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void namedNativeQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createNamedQuery("Brand.Native.findAll", Brand.class);

        List<Brand> brands = query.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getName());
        }

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void nonQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Query query = entityManager.createQuery("update Brand b set b.name = :name where b.id = :id");
        query.setParameter("name", "Samsung Updated");
        query.setParameter("id", "Samsung");

        int impactedRecords = query.executeUpdate();

        System.out.println("Success update " + impactedRecords + " records");

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
