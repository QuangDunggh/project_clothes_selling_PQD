package com.cg.gu_project.mappers;

import com.cg.gu_project.dto.ProductClientDTO;
import com.cg.gu_project.dto.ProductDTO;
import com.cg.gu_project.model.Product;
import com.cg.gu_project.service.categoryService.ICategoryService;
import com.cg.gu_project.service.subcategory.ISubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISubcategoryService subcategoryService;

    public Product toProduct(ProductDTO productDTO) {
        Product product = new Product();

        product.setTitle(productDTO.getTitle());
        product.setCategory(categoryService.findById(productDTO.getCategory_id()).get());
        product.setSubcategory(subcategoryService.findById(productDTO.getSubcategory_id()).get());
        product.setDescription(productDTO.getDescription());

        return product;
    }

    public ProductClientDTO toProductClientDTO(ProductDTO productDTO) {
        ProductClientDTO productClientDTO = new ProductClientDTO();

        productClientDTO.setTitle(productDTO.getTitle());
        productClientDTO.setCategory_id(productDTO.getCategory_id());
        productClientDTO.setCategory_name(productDTO.getCategory_name());
        productClientDTO.setSubcategory_id(productDTO.getSubcategory_id());
        productClientDTO.setSubcategory_name(productDTO.getSubcategory_name());
        productClientDTO.setDescription(productDTO.getDescription());
        productClientDTO.setFileName(productDTO.getFileName());
        productClientDTO.setFileFolder(productDTO.getFileFolder());
        productClientDTO.setFileUrl(productDTO.getFileUrl());
        productClientDTO.setCloudId(productDTO.getCloudId());

        return productClientDTO;
    }


}
