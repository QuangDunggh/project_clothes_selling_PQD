package com.cg.gu_project.repository;

import com.cg.gu_project.dto.SubcategoryDTO;
import com.cg.gu_project.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    @Query("SELECT new com.cg.gu_project.dto.SubcategoryDTO" +
            "(sc.id," +
            "sc.subCategoryName," +
            "sc.category.id," +
            "sc.category.categoryName) " +
            "FROM Subcategory sc")
    List<SubcategoryDTO> findAllSubcategoryDTO();

    @Query("SELECT new com.cg.gu_project.dto.SubcategoryDTO" +
            "(sc.id," +
            "sc.subCategoryName," +
            "sc.category.id," +
            "sc.category.categoryName) " +
            "FROM Subcategory sc " +
            "WHERE sc.category.id=:category_id")
    List<SubcategoryDTO> findListSubcategoryByCategory_id(@Param("category_id") Long id);
}
