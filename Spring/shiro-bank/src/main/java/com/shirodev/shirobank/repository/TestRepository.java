package com.shirodev.shirobank.repository;

import com.shirodev.shirobank.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, String> {
}
