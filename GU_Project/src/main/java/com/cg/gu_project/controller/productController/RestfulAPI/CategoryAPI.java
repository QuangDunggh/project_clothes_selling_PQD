package com.cg.gu_project.controller.productController.RestfulAPI;

import com.cg.gu_project.model.Category;
import com.cg.gu_project.service.categoryService.ICategoryService;
import com.cg.gu_project.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryAPI {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping("/showAllCategory")
    public ResponseEntity<?> showAll() {
        List<Category> categoryDTOs = categoryService.findAll();

        if (categoryDTOs.isEmpty()) {
            return new ResponseEntity<>("Can not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);
    }

    @GetMapping("/countProductByCategory")
    public ResponseEntity<?> countProductByCategory() {
        return null;
    }

    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {

        Category newCategory = categoryService.save(category);

        return new ResponseEntity<>(newCategory,HttpStatus.CREATED);
    }

}
