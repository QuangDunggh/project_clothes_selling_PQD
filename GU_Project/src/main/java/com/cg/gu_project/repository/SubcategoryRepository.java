package com.cg.gu_project.repository;

import com.cg.gu_project.dto.SubcategoryDTO;
import com.cg.gu_project.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
            "FROM Subcategory sc " +
            "WHERE sc.deleted=FALSE")
    List<SubcategoryDTO> findAllSubcategoryDTO();

    @Query("SELECT new com.cg.gu_project.dto.SubcategoryDTO" +
            "(sc.id," +
            "sc.subCategoryName," +
            "sc.category.id," +
            "sc.category.categoryName) " +
            "FROM Subcategory sc " +
            "WHERE sc.deleted=TRUE")
    List<SubcategoryDTO> findAllSubcategoryDTOLock();

    @Query("SELECT new com.cg.gu_project.dto.SubcategoryDTO" +
            "(sc.id," +
            "sc.subCategoryName," +
            "sc.category.id," +
            "sc.category.categoryName) " +
            "FROM Subcategory sc " +
            "WHERE sc.category.id=:category_id " +
            "AND sc.deleted = FALSE")
    List<SubcategoryDTO> findListSubcategoryByCategory_id(@Param("category_id") Long id);

    @Query("SELECT new com.cg.gu_project.dto.SubcategoryDTO" +
            "(sc.id," +
            "sc.subCategoryName," +
            "sc.category.id," +
            "sc.category.categoryName) " +
            "FROM Subcategory sc " +
            "WHERE sc.id=:id")
    SubcategoryDTO findSubcategoryDTOById(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE subcategories sb " +
            "SET sb.deleted = TRUE " +
            "WHERE sb.category_id=:id",
             nativeQuery = true)
    void setSubcategoryDeletedIsTrueByCategoryId(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE subcategories sb " +
            "SET sb.deleted = FALSE " +
            "WHERE sb.category_id=:id",
            nativeQuery = true)
    void setSubcategoryDeletedIsFalseByCategoryId(@Param("id") Long id);

}
