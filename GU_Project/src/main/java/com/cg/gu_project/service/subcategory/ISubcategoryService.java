package com.cg.gu_project.service.subcategory;

import com.cg.gu_project.dto.SubcategoryDTO;
import com.cg.gu_project.model.Subcategory;
import com.cg.gu_project.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISubcategoryService extends IGeneralService<Subcategory> {

   List<SubcategoryDTO> findAllSubcategoryDTOLock();

    SubcategoryDTO createSubcategory(SubcategoryDTO subcategoryDTO);

    List<SubcategoryDTO> findAllSubcategoryDTO();

    List<SubcategoryDTO> findListSubcategoryByCategory_id(Long id);

    SubcategoryDTO findSubcategoryDTOById(Long id);

    void setSubcategoryDeletedIsTrueByCategoryId(Long id);

    void setSubcategoryDeletedIsFalseByCategoryId(Long id);


}
