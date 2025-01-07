package programmerzamannow.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Member;
import programmerzamannow.jpa.entity.Name;
import programmerzamannow.jpa.util.JpaUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollectionTest {

    @Test
    void insertMember() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Name name = new Name();
        name.setTitle("Mr");
        name.setFirstName("Nobita");
        name.setMiddleName("Dora");
        name.setLastName("Emon");

        Member member = new Member();
        member.setName(name);
        member.setEmail("nobita@email.com");
        member.setHobbies(new ArrayList<>());

        member.getHobbies().add("Coding");
        member.getHobbies().add("Gaming");

        entityManager.persist(member);


        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void update() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Member member = entityManager.find(Member.class, 2);
        member.getHobbies().add("Makan");
        member.getHobbies().add("Travelling");

        entityManager.merge(member);


        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void insertMemberMap() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Name name = new Name();
        name.setTitle("Mrs");
        name.setFirstName("Shizuka");
        name.setMiddleName("Dora");
        name.setLastName("Emon");

        Member member = new Member();
        member.setName(name);
        member.setEmail("shizuka@email.com");
        member.setHobbies(new ArrayList<>());

        member.getHobbies().add("Cooking");
        member.getHobbies().add("Shopping");

        member.setSkills(new HashMap<>());
        member.getSkills().put("Cook", 8);
        member.getSkills().put("Math", 10);

        entityManager.persist(member);


        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
