package com.cg.gu_project.service.productCombiantion;

import com.cg.gu_project.dto.ProductsCombinationClientDTO;
import com.cg.gu_project.dto.ProductsCombinationDTO;
import com.cg.gu_project.model.ProductsCombination;
import com.cg.gu_project.service.IGeneralService;

import java.util.Optional;

public interface IProductsCombinationService extends IGeneralService<ProductsCombination> {

    Optional<ProductsCombinationClientDTO> createProductsCombination(ProductsCombinationDTO productsCombinationDTO);

    ProductsCombinationClientDTO createProductsCombinationNoImage(ProductsCombinationDTO productsCombinationDTO);

    Optional<ProductsCombinationClientDTO> updateProductCombinationNoImage(ProductsCombinationDTO productsCombinationDTO);

    Optional<ProductsCombinationClientDTO> updateProductCombination(ProductsCombinationDTO productsCombinationDTO);
}
