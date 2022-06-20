package com.cg.gu_project.controller.productController.RestfulAPI;

import com.cg.gu_project.dto.CategoryDTO;
import com.cg.gu_project.model.Category;
import com.cg.gu_project.service.categoryService.ICategoryService;
import com.cg.gu_project.service.productService.IProductService;
import com.cg.gu_project.service.subcategory.ISubcategoryService;
import com.cg.gu_project.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryAPI {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ISubcategoryService subcategoryService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping("/showAllCategory")
    public ResponseEntity<?> showAll() {
        List<CategoryDTO> categoryDTOs = categoryService.findAllCategoryDTODeleteFalse();

        if (categoryDTOs.isEmpty()) {
            return new ResponseEntity<>("Can not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);
    }

    @GetMapping("/getAllCategoryLock")
    public ResponseEntity<?> showAllCategoryLock() {
        List<CategoryDTO> categories = categoryService.findAllCategoryDTODeleteTrue();

        if(categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("/countProductByCategory")
    public ResponseEntity<?> countProductByCategory() {
        return null;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);

        if(category.isPresent()) {
            return new ResponseEntity<>(category,HttpStatus.OK);
        }

        return new ResponseEntity<>("Can not found category", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {

        Category newCategory = categoryService.save(category);

        return new ResponseEntity<>(newCategory,HttpStatus.CREATED);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id,
                                            @RequestBody Category category) {
        Optional<Category> categoryCurrent = categoryService.findById(id);

        if(categoryCurrent.isPresent()) {
            categoryCurrent.get().setCategoryName(category.getCategoryName());
            Category categoryUpdate = categoryService.save(categoryCurrent.get());
            return new ResponseEntity<>(categoryUpdate, HttpStatus.OK);
        }

        return new ResponseEntity<>("Category can not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/suspensionCategory/{id}")
    public ResponseEntity<?> suspensionCategory(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);

        if(category.isPresent()) {

            category.get().setDeleted(true);
            categoryService.save(category.get());

            subcategoryService.setSubcategoryDeletedIsTrueByCategoryId(id);

            productService.setProductDeletedIsTrueByCategoryId(id);

            return new ResponseEntity<>(id,HttpStatus.OK);

        }

        return new ResponseEntity<>("Can not found this category", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/doRestoreCategory/{id}")
    public ResponseEntity<?> doRestoreCategory(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);

        if(category.isPresent()) {
            category.get().setDeleted(false);
            categoryService.save(category.get());

            productService.setProductDeletedIsFalseByCategoryId(id);

            subcategoryService.setSubcategoryDeletedIsFalseByCategoryId(id);

            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        return new ResponseEntity<>("Can not found category", HttpStatus.NOT_FOUND);
    }

}
