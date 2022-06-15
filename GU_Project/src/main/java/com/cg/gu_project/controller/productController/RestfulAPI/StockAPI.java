package com.cg.gu_project.controller.productController.RestfulAPI;

import com.cg.gu_project.dto.ProductsCombinationClientDTO;
import com.cg.gu_project.dto.ProductsCombinationDTO;
import com.cg.gu_project.service.productCombiantion.IProductsCombinationService;
import com.cg.gu_project.service.productsStock.IProductsStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock")
public class StockAPI {

    @Autowired
    private IProductsStockService productsStockService;

    @Autowired
    private IProductsCombinationService productsCombinationService;

    @GetMapping("/showAllProductsCombination")
    public ResponseEntity<?> showAll() {
        List<ProductsCombinationClientDTO> productsCombinationClientDTOS = productsStockService.findAllProductsCombinationClientDTO();
        if(productsCombinationClientDTOS.isEmpty()) {
            return new ResponseEntity<>("List can not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productsCombinationClientDTOS, HttpStatus.OK);
    }



    @PostMapping("/createProductsCombination")
    public ResponseEntity<?> createProductsCombination(ProductsCombinationDTO productsCombinationDTO) {
        Optional<ProductsCombinationClientDTO> productsCombinationClientDTO = productsCombinationService.createProductsCombination(productsCombinationDTO);
        if(productsCombinationClientDTO.isPresent()) {
            return new ResponseEntity<>(productsCombinationClientDTO.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Can not create productsCombination", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updataProductsCombination/{id}")
    public ResponseEntity<?> updateProductsCombination(@PathVariable("id") Long id, ProductsCombinationDTO productsCombinationDTO) {
        return null;
    }
}
