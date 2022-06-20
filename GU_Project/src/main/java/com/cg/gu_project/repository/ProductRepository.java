package com.cg.gu_project.repository;


import com.cg.gu_project.dto.ProductClientDTO;
import com.cg.gu_project.dto.ProductDTO;
import com.cg.gu_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query(value = "UPDATE products p SET p.image_id = NULL WHERE p.id =:id", nativeQuery = true)
    void resetImageId(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE products p SET p.deleted = TRUE WHERE p.category_id=:id", nativeQuery = true)
    void setProductDeletedIsTrueByCategoryId(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE products p SET p.deleted = FALSE WHERE p.category_id=:id", nativeQuery = true)
    void setProductDeletedIsFalseByCategoryId(@Param("id") Long id);


    @Modifying
    @Query(value = "UPDATE products p SET p.deleted = TRUE WHERE p.subcategory_id=:id", nativeQuery = true)
    void setProductDeletedIsTrueBySubcategoryId(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE products p SET p.deleted = FALSE WHERE p.subcategory_id=:id", nativeQuery = true)
    void setProductDeletedIsFalseBySubcategoryId(@Param("id") Long id);


}
