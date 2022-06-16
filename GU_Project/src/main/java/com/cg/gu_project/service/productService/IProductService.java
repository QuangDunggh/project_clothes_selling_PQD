package com.cg.gu_project.service.productService;

import com.cg.gu_project.dto.ProductClientDTO;
import com.cg.gu_project.dto.ProductDTO;
import com.cg.gu_project.model.Product;
import com.cg.gu_project.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IProductService extends IGeneralService<Product> {

    Optional<ProductClientDTO> findProductClientDTOById(Long id);

    ProductClientDTO saveProductDTO(ProductDTO productDTO);

    ProductClientDTO updateProductDTO(ProductDTO productDTO);

    List<ProductClientDTO> findAllProductClientDTO();

    Long countProductByCategoryId(Long category_id);

}
