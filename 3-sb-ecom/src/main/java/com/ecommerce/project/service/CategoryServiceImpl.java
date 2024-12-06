package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    // private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        // return categories;
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        // category.setCategoryId(nextId++);    // without JPA
        // categories.add(category);            // without JPA
        categoryRepository.save(category);
    }


    /*
    @Override
    public String deleteCategory(Long categoryId) {
        // Added when JPA was incorporated
        List<Category> categories = categoryRepository.findAll();

        // There is another way to achieve this exact same thing: check updateCategory
        Category category = categories.stream()
                .filter(c ->
                        c.getCategoryId().equals(categoryId)
                ).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not found"));

        // categories.remove(category);  // without JPA
        categoryRepository.delete(category);
        return "Category with categoryId:" + categoryId + " has been deleted successfully!!";
    }
     */

    // Optimized Version
    @Override
    public String deleteCategory(Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not found"));

        categoryRepository.delete(savedCategory);
        return "Category with categoryId:" + categoryId + " has been deleted successfully!!";
    }


    /*
    JPA before code optimization
    @Override
    public Category updateCategory(Category category, Long categoryId) {
        // Added when JPA was incorporated
        List<Category> categories = categoryRepository.findAll();

        Optional<Category> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId)).findFirst();

        if(optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            // set the name of the existingCategory to the name that was sent from the client
            existingCategory.setCategoryName(category.getCategoryName());

            // added after incorporating JPA
            Category savedCategory = categoryRepository.save(existingCategory);

            // return existingCategory;
            return savedCategory;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found");
        }
    }
    */


    // Optimized Version
    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;

    }


}
