package com.cg.gu_project.service.productsStock;

import com.cg.gu_project.dto.ProductsCombinationClientDTO;
import com.cg.gu_project.model.ProductsStock;
import com.cg.gu_project.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProductsStockService extends IGeneralService <ProductsStock>{

    Optional<ProductsCombinationClientDTO> findProductsCombinationClientDTOById(Long id);

    List<ProductsCombinationClientDTO> findAllProductsCombinationClientDTO();

    List<ProductsCombinationClientDTO> findAllProductsCombinationClientDTOLock();

    List<ProductsCombinationClientDTO> findAllProductsCombinationClientDTOByProductId(Long id);

    void setDeletedTrueFollowProductId(Long id);

    void setDeletedFalseFollowProductId(Long id);

}
