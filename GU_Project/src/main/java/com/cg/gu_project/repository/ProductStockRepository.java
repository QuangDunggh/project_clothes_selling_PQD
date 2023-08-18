package com.cg.gu_project.repository;

import com.cg.gu_project.dto.ProductsCombinationClientDTO;
import com.cg.gu_project.model.ProductsStock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductsStock, Long> {

    @Query("SELECT new com.cg.gu_project.dto.ProductsCombinationClientDTO" +
            "(ps.id," +
            "ps.productsCombination.product.id," +
            "ps.productsCombination.product.title," +
            "ps.productsCombination.id," +
            "ps.productsCombination.combinationString," +
            "ps.productsCombination.sku," +
            "ps.productsCombination.imageGallery.fileName," +
            "ps.productsCombination.imageGallery.fileFolder," +
            "ps.productsCombination.imageGallery.fileUrl," +
            "ps.productsCombination.imageGallery.cloudId," +
            "ps.totalStock," +
            "ps.available," +
            "ps.sold," +
            "ps.defective," +
            "ps.unitPrice," +
            "ps.maximumRetailPrice)" +
            " FROM ProductsStock ps " +
            "WHERE ps.deleted = FALSE")
    List<ProductsCombinationClientDTO> findAllProductsCombinationClientDTO();


    @Query("SELECT new com.cg.gu_project.dto.ProductsCombinationClientDTO" +
            "(ps.id," +
            "ps.productsCombination.product.id," +
            "ps.productsCombination.product.title," +
            "ps.productsCombination.id," +
            "ps.productsCombination.combinationString," +
            "ps.productsCombination.sku," +
            "ps.productsCombination.imageGallery.fileName," +
            "ps.productsCombination.imageGallery.fileFolder," +
            "ps.productsCombination.imageGallery.fileUrl," +
            "ps.productsCombination.imageGallery.cloudId," +
            "ps.totalStock," +
            "ps.available," +
            "ps.sold," +
            "ps.defective," +
            "ps.unitPrice," +
            "ps.maximumRetailPrice)" +
            " FROM ProductsStock ps " +
            "WHERE ps.deleted = FALSE " +
            "AND ps.productsCombination.product.id=:id")
    List<ProductsCombinationClientDTO> findAllProductsCombinationClientDTOByProductId(@Param("id") Long id);

    @Query("SELECT new com.cg.gu_project.dto.ProductsCombinationClientDTO" +
            "(ps.id," +
            "ps.productsCombination.product.id," +
            "ps.productsCombination.product.title," +
            "ps.productsCombination.id," +
            "ps.productsCombination.combinationString," +
            "ps.productsCombination.sku," +
            "ps.productsCombination.imageGallery.fileName," +
            "ps.productsCombination.imageGallery.fileFolder," +
            "ps.productsCombination.imageGallery.fileUrl," +
            "ps.productsCombination.imageGallery.cloudId," +
            "ps.totalStock," +
            "ps.available," +
            "ps.sold," +
            "ps.defective," +
            "ps.unitPrice," +
            "ps.maximumRetailPrice)" +
            " FROM ProductsStock ps " +
            "WHERE ps.deleted = FALSE " +
            "AND ps.id=:id")
    Optional<ProductsCombinationClientDTO> findProductsCombinationClientDTOById(@Param("id") Long id);

    @Query("SELECT new com.cg.gu_project.dto.ProductsCombinationClientDTO" +
            "(ps.id," +
            "ps.productsCombination.product.id," +
            "ps.productsCombination.product.title," +
            "ps.productsCombination.id," +
            "ps.productsCombination.combinationString," +
            "ps.productsCombination.sku," +
            "ps.productsCombination.imageGallery.fileName," +
            "ps.productsCombination.imageGallery.fileFolder," +
            "ps.productsCombination.imageGallery.fileUrl," +
            "ps.productsCombination.imageGallery.cloudId," +
            "ps.totalStock," +
            "ps.available," +
            "ps.sold," +
            "ps.defective," +
            "ps.unitPrice," +
            "ps.maximumRetailPrice)" +
            " FROM ProductsStock ps " +
            "WHERE ps.deleted = TRUE ")
    List<ProductsCombinationClientDTO> findAllProductsCombinationClientDTOLock();


    @Modifying
    @Query(value = "UPDATE products_combinations pc SET pc.deleted = TRUE WHERE pc.product_id =:id", nativeQuery = true)
    void setDeletedTrueFollowProductId(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE products_combinations pc SET pc.deleted = FALSE WHERE pc.product_id =:id", nativeQuery = true)
    void setDeletedFalseFollowProductId(@Param("id") Long id);
}
