package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/api")  // all the endpoints in this class starts with /api
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("api/public/categories")
    // @RequestMapping USE CASE
    // 1. At class level to shorten the common preceding URI
    // 2. to define the url path and the method
    //@RequestMapping(value = "api/public/categories", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("api/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        // @Valid annotation is used to validate the request body and for a more user-friendly error message
        // @Valid annotation in the controller is used in the controller method along with validation annotation in the model

        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully!!", HttpStatus.CREATED);
    }

    // DELETE CATEGORY BEFORE CLEAN UP
    /*
    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
       try{
           String status = categoryService.deleteCategory(categoryId);

           return new ResponseEntity<>(status, HttpStatus.OK);
           /* DIFFERENT WAYS TO WRITE THE RESPONSEENTITY CODE
           // return ResponseEntity.ok(status);
           // return ResponseEntity.status(HttpStatus.OK).body(status);
            */
      // }
      // catch(ResponseStatusException e) {
      //      return new ResponseEntity<>(e.getReason(), e.getStatusCode());
     //  }
   // }

    // DELETE CATEGORY AFTER CLEANUP
    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {

        String status = categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    /*
    // THIS IS BEFORE IMPLEMENTING THE CUSTOM EXCEPTION
    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryId) {
        try {
            Category savedCategory = categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>("Category with categoryId:" + categoryId + " updated!!", HttpStatus.OK);
        }
        catch (ResponseStatusException e) {
           return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

     */
    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category, @PathVariable Long categoryId) {
        Category savedCategory = categoryService.updateCategory(category,categoryId);
        return new ResponseEntity<>("Category with categoryId:" + categoryId + " updated!!", HttpStatus.OK);
    }
}
