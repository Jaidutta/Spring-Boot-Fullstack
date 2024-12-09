package com.ecommerce.project.repository;

import com.ecommerce.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // JpaRepository takes in 2 params: 1) Type of the Entity and the 2) Type of the primary key of the Entity


    Category findByCategoryName(String categoryName);
}
