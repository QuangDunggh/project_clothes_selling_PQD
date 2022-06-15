package com.cg.gu_project.repository;


import com.cg.gu_project.dto.ProductClientDTO;
import com.cg.gu_project.dto.ProductDTO;
import com.cg.gu_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new com.cg.gu_project.dto.ProductClientDTO" +
            "(p.id, " +
            "p.title, " +
            "p.category.id, " +
            "p.category.categoryName, " +
            "p.subcategory.id, " +
            "p.subcategory.subCategoryName, " +
            "p.description," +
            "p.image.fileName," +
            "p.image.fileFolder," +
            "p.image.fileUrl," +
            "p.image.cloudId) " +
            "FROM Product p " +
            "WHERE p.deleted = FALSE ")
    List<ProductClientDTO> findAllProductClientDTO();

    @Query("SELECT new com.cg.gu_project.dto.ProductClientDTO" +
            "(p.id, " +
            "p.title, " +
            "p.category.id, " +
            "p.category.categoryName, " +
            "p.subcategory.id, " +
            "p.subcategory.subCategoryName, " +
            "p.description," +
            "p.image.fileName," +
            "p.image.fileFolder," +
            "p.image.fileUrl," +
            "p.image.cloudId) " +
            "FROM Product p " +
            "WHERE p.deleted = FALSE " +
            "AND p.id=:id ")
    Optional<ProductClientDTO> findProductClientDTOById(@Param("id") Long id);

    Long countProductByCategoryId(Long category_id);


}
