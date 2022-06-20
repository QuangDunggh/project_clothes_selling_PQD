package com.cg.gu_project.repository;


import com.cg.gu_project.dto.CategoryDTO;
import com.cg.gu_project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findAllByDeletedIsTrue();

    @Query(value = "SELECT new com.cg.gu_project.dto.CategoryDTO(" +
                    "c.id, " +
                    "c.categoryName," +
                    "COUNT(p.category.id)) " +
                    "FROM Category c " +
                    "LEFT JOIN Product p " +
                    "ON c.id = p.category.id " +
                    "WHERE c.deleted = FALSE " +
                    "GROUP BY c.id")
    List<CategoryDTO> findAllCategoryDTODeleteFalse();

    @Query(value = "SELECT new com.cg.gu_project.dto.CategoryDTO(" +
                    "c.id, " +
                    "c.categoryName," +
                    "COUNT(p.category.id)) " +
                    "FROM Category c " +
                    "LEFT JOIN Product p " +
                    "ON c.id = p.category.id " +
                    "WHERE c.deleted = TRUE " +
                    "GROUP BY c.id")
    List<CategoryDTO> findAllCategoryDTODeleteTrue();


}
