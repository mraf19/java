package programmerzamannow.jpa;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Brand;
import programmerzamannow.jpa.entity.Product;
import programmerzamannow.jpa.entity.SimpleBrand;
import programmerzamannow.jpa.util.JpaUtil;

import java.util.List;

public class CriteriaTest {

    @Test
    void criteriaQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);

        Root<Brand> root = criteria.from(Brand.class);

        criteria.select(root);

        TypedQuery<Brand> query = entityManager.createQuery(criteria);

        List<Brand> brands = query.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getId() + ": " + brand.getName());
        }

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }


    @Test
    void criteriaQueryNonEntity() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Brand> root = criteria.from(Brand.class);

        criteria.select(builder.array(root.get("id"), root.get("name")));

        TypedQuery<Object[]> query = entityManager.createQuery(criteria);
        List<Object[]> objects = query.getResultList();

        for (Object[] object : objects) {
            System.out.println("id: " + object[0]);
            System.out.println("name: " + object[1]);
        }

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void criteriaQueryNonEntityConstructor() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<SimpleBrand> criteria = builder.createQuery(SimpleBrand.class);
        Root<Brand> root = criteria.from(Brand.class);
        criteria.select(builder.construct(SimpleBrand.class, root.get("id"), root.get("name")));

        TypedQuery<SimpleBrand> query = entityManager.createQuery(criteria);
        List<SimpleBrand> simpleBrands = query.getResultList();

        for (SimpleBrand simpleBrand : simpleBrands) {
            System.out.println(simpleBrand.getId() + ": " + simpleBrand.getName());
        }

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void criteriaWhereClause() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);
        Root<Brand> root = criteria.from(Brand.class);

        criteria.select(root);
        criteria.where(
                builder.equal(root.get("name"), "SEPATU KANKY"),
                builder.isNotNull(root.get("createdAt"))
        );

        TypedQuery<Brand> query = entityManager.createQuery(criteria);
        List<Brand> brands = query.getResultList();
        for (Brand brand : brands) {
            System.out.println(brand.getId() + ": " + brand.getName());
        }

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void criteriaWhereClauseOr() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);
        Root<Brand> root = criteria.from(Brand.class);

        criteria.select(root);
        criteria.where(
                builder.or(
                        builder.equal(root.get("name"), "Samsung"),
                        builder.isNotNull(root.get("createdAt"))
                )
        );

        TypedQuery<Brand> query = entityManager.createQuery(criteria);
        List<Brand> brands = query.getResultList();
        for (Brand brand : brands) {
            System.out.println(brand.getId() + ": " + brand.getName());
        }

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void criteriaJoinClause(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        Join<Product, Brand> brand = root.join("brand");

        criteria.select(root);
        criteria.where(
                builder.equal(brand.get("name"), "Samsung")
        );

        TypedQuery<Product> query = entityManager.createQuery(criteria);
        List<Product> products = query.getResultList();
        for (Product product : products) {
            System.out.println(product.getId() + ": " + product.getName());
        }

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void criteriaParameter() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        Join<Product, Brand> brand = root.join("brand");
        ParameterExpression<String> name = builder.parameter(String.class, "name");
        criteria.select(root);
        criteria.where(builder.equal(brand.get("name"), name));

        TypedQuery<Product> query = entityManager.createQuery(criteria);
        query.setParameter("name", "Samsung");
        List<Product> products = query.getResultList();
        for (Product product : products) {
            System.out.println(product.getId() + ": " + product.getName());
        }

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void criteriaAggregate() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);

        Root<Product> product = criteria.from(Product.class);
        Join<Product, Brand> brand = product.join("brand");

        criteria.select(builder.array(
                brand.get("id"),
                builder.min(product.get("price")),
                builder.max(product.get("price")),
                builder.avg(product.get("price"))
        ));

        criteria.groupBy(brand.get("id"));
        criteria.having(builder.greaterThan(builder.min(product.get("price")), 500_000L));

        TypedQuery<Object[]> query = entityManager.createQuery(criteria);
        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects) {
            System.out.println("Brand: " + object[0]);
            System.out.println("Min: " + object[1]);
            System.out.println("Max: " + object[2]);
            System.out.println("Average: " + object[3]);
        }

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void criteriaNonQuery() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Brand> criteria = builder.createCriteriaUpdate(Brand.class);
        Root<Brand> root = criteria.from(Brand.class);

        criteria.set(root.get("name"), "Samsung Updated");
        criteria.where(
                builder.equal(root.get("id"), "Brand-Samsung")
        );

        Query query = entityManager.createQuery(criteria);
        int impactedRecords = query.executeUpdate();

        System.out.println("Success! " + impactedRecords + " rows updated");

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
