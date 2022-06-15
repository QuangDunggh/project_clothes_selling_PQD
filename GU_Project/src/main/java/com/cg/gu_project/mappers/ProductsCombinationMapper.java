package com.cg.gu_project.mappers;

import com.cg.gu_project.dto.ProductsCombinationDTO;
import com.cg.gu_project.model.ProductsCombination;
import com.cg.gu_project.model.ProductsStock;
import com.cg.gu_project.service.productCombiantion.IProductsCombinationService;
import com.cg.gu_project.service.productService.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductsCombinationMapper {

    @Autowired
    private IProductService productService;

    public ProductsCombination toProductCombination(ProductsCombinationDTO productsCombinationDTO) {
        ProductsCombination productsCombination = new ProductsCombination();

        String color = productsCombinationDTO.getColor();
        String size = productsCombinationDTO.getSize();

        productsCombination.setProduct(productService.findById(productsCombinationDTO.getProduct_id()).get());
        productsCombination.setCombinationString(color + "_" + size + "_" + productsCombinationDTO.getProduct_id());
        productsCombination.setSku((color + size + productsCombinationDTO.getProduct_id()).toLowerCase());

        return productsCombination;
    }

    public ProductsStock toProductsStock(ProductsCombinationDTO productsCombinationDTO) {
        ProductsStock productsStock = new ProductsStock();

        productsStock.setTotalStock(productsCombinationDTO.getTotal_stock());
        productsStock.setSold(0L);
        productsStock.setAvailable(0L);
        productsStock.setDefective(0L);
        productsStock.setMaximumRetailPrice(productsCombinationDTO.getMaximum_retail_price());
        productsStock.setUnitPrice(productsCombinationDTO.getUnit_price());

        return productsStock;
    }
}
