package com.cg.gu_project.repository;

import com.cg.gu_project.dto.ProductsCombinationClientDTO;
import com.cg.gu_project.model.ProductsStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
            " FROM ProductsStock ps")
    List<ProductsCombinationClientDTO> findAllProductsCombinationClientDTO();
}
