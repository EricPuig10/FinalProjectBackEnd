package com.app.finalproject.repositories;

import com.app.finalproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository <Category, Long> {
    boolean existsByName(String name);
    Optional<Category> findByName(String name);
}
