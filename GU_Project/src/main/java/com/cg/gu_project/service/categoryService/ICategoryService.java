package com.cg.gu_project.service.categoryService;

import com.cg.gu_project.dto.CategoryDTO;
import com.cg.gu_project.model.Category;
import com.cg.gu_project.service.IGeneralService;

import java.util.List;

public interface ICategoryService extends IGeneralService<Category> {

    List<Category> findAllByDeletedIsTrue();

    List<CategoryDTO> findAllCategoryDTODeleteFalse();

    List<CategoryDTO> findAllCategoryDTODeleteTrue();
}
