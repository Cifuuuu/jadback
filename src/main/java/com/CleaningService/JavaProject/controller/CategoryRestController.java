package com.CleaningService.JavaProject.controller;

import com.CleaningService.JavaProject.utils.Category;
import com.CleaningService.JavaProject.utils.CategoryDAOImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {
    
    private final CategoryDAOImpl categoryDAO;

    public CategoryRestController(CategoryDAOImpl categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Category>> getAllCategories() {
        try {
            ArrayList<Category> categories = categoryDAO.getAllCategory();
            return ResponseEntity.ok(categories);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getTotalCategories() {
        try {
            Integer total = categoryDAO.getTotalCategory();
            return ResponseEntity.ok(total);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int categoryId) {
        try {
            Category category = categoryDAO.getCategoryById(categoryId);
            if (category != null) {
                return ResponseEntity.ok(category);
            }
            return ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable("name") String categoryName) {
        try {
            Category category = categoryDAO.getCategoryByName(categoryName);
            if (category != null) {
                return ResponseEntity.ok(category);
            }
            return ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        try {
            boolean created = categoryDAO.createCategory(category);
            if (created) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Category created successfully");
            }
            return ResponseEntity.badRequest().body("Failed to create category");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating category: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable("id") int categoryId, 
                                               @RequestBody Category category) {
        try {
            category.setCategory_id(categoryId);
            boolean updated = categoryDAO.updateCategory(category);
            if (updated) {
                return ResponseEntity.ok("Category updated successfully");
            }
            return ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating category: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int categoryId) {
        try {
            boolean deleted = categoryDAO.deleteCategory(categoryId);
            if (deleted) {
                return ResponseEntity.ok("Category deleted successfully");
            }
            return ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting category: " + e.getMessage());
        }
    }
}