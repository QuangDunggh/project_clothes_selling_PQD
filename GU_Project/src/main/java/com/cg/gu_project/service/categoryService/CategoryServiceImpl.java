package com.cg.gu_project.service.categoryService;

import com.cg.gu_project.dto.CategoryDTO;
import com.cg.gu_project.model.Category;
import com.cg.gu_project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findAllByDeletedIsTrue() {
        return categoryRepository.findAllByDeletedIsTrue();
    }

    @Override
    public List<CategoryDTO> findAllCategoryDTODeleteFalse() {
        return categoryRepository.findAllCategoryDTODeleteFalse();
    }

    @Override
    public List<CategoryDTO> findAllCategoryDTODeleteTrue() {
        return categoryRepository.findAllCategoryDTODeleteTrue();
    }
}
