package com.cg.gu_project.service.productsStock;

import com.cg.gu_project.dto.ProductsCombinationClientDTO;
import com.cg.gu_project.model.ProductsStock;
import com.cg.gu_project.service.IGeneralService;

import java.util.List;

public interface IProductsStockService extends IGeneralService <ProductsStock>{

    List<ProductsCombinationClientDTO> findAllProductsCombinationClientDTO();

}
