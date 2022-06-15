package com.cg.gu_project.controller.productController.RestfulAPI;

import com.cg.gu_project.dto.ProductClientDTO;
import com.cg.gu_project.dto.ProductDTO;
import com.cg.gu_project.service.productService.IProductService;
import com.cg.gu_project.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping("/showAll")
    public ResponseEntity<?> showAllProduct() {

        List<ProductClientDTO> productClientDTOS = productService.findAllProductClientDTO();

        if (productClientDTOS.isEmpty()) {
            return new ResponseEntity<>("List product is empty", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productClientDTOS, HttpStatus.OK);

    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<ProductClientDTO> productClientDTO = productService.findProductClientDTOById(id);
        if (productClientDTO.isPresent()) {
            return new ResponseEntity<>(productClientDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Can not found product", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(ProductDTO productDTO) {
        productDTO.setId(0L);
        ProductClientDTO productClientDTO = productService.saveProductDTO(productDTO);

        return new ResponseEntity<>(productClientDTO, HttpStatus.CREATED);
    }
}
