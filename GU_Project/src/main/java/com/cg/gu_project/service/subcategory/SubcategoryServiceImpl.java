package com.cg.gu_project.service.subcategory;

import com.cg.gu_project.dto.SubcategoryDTO;
import com.cg.gu_project.model.Subcategory;
import com.cg.gu_project.repository.SubcategoryRepository;
import com.cg.gu_project.service.categoryService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubcategoryServiceImpl implements ISubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private ICategoryService categoryService;

    @Override
    public List<Subcategory> findAll() {
        return subcategoryRepository.findAll();
    }

    @Override
    public Optional<Subcategory> findById(Long id) {
        return subcategoryRepository.findById(id);
    }

    @Override
    public Subcategory save(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    @Override
    public void remove(Long id) {
        subcategoryRepository.deleteById(id);
    }

    @Override
    public SubcategoryDTO createSubcategory(SubcategoryDTO subcategoryDTO) {
        Subcategory subcategory = new Subcategory();
        subcategory.setSubCategoryName(subcategoryDTO.getSubcategoryName());
        subcategory.setCategory(categoryService.findById(subcategoryDTO.getCategory_id()).get());
        subcategoryRepository.save(subcategory);
        subcategoryDTO.setId(subcategory.getId());
        subcategoryDTO.setCategoryName(subcategory.getCategory().getCategoryName());
        return subcategoryDTO;
    }

    @Override
    public List<SubcategoryDTO> findAllSubcategoryDTO() {
        return subcategoryRepository.findAllSubcategoryDTO();
    }

    @Override
    public List<SubcategoryDTO> findListSubcategoryByCategory_id(Long id) {
        return subcategoryRepository.findListSubcategoryByCategory_id(id);
    }
}
