package com.cg.gu_project.controller.productController.RestfulAPI;

import com.cg.gu_project.dto.ProductsCombinationClientDTO;
import com.cg.gu_project.dto.ProductsCombinationDTO;
import com.cg.gu_project.model.Color;
import com.cg.gu_project.model.Size;
import com.cg.gu_project.service.productCombiantion.IProductsCombinationService;
import com.cg.gu_project.service.productsStock.IProductsStockService;
import com.cg.gu_project.service.sizeAndColor.IColorService;
import com.cg.gu_project.service.sizeAndColor.ISizeService;
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
    private IColorService colorService;

    @Autowired
    private ISizeService sizeService;

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

    @GetMapping("/showAllProductCombinationLock")
    public ResponseEntity<?> showAllProductCombinationLock() {
        return  null;
    }

    @GetMapping("/showAllColor")
    public ResponseEntity<?> showAllColor() {
        List<Color> colors = colorService.findAll();
        if(colors.isEmpty()) {
            return new ResponseEntity<>("Can not found any color", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(colors,HttpStatus.OK);
    }

    @GetMapping("/showAllSize")
    public ResponseEntity<?> showAllSize() {
        List<Size> sizes = sizeService.findAll();
        if(sizes.isEmpty()) {
            return new ResponseEntity<>("Can not found any size", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(sizes,HttpStatus.OK);
    }

    @PostMapping("/createProductsCombination")
    public ResponseEntity<?> createProductsCombination(ProductsCombinationDTO productsCombinationDTO) {
        Optional<ProductsCombinationClientDTO> productsCombinationClientDTO = productsCombinationService.createProductsCombination(productsCombinationDTO);
        if(productsCombinationClientDTO.isPresent()) {
            return new ResponseEntity<>(productsCombinationClientDTO.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Can not create productsCombination", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateProductsCombination/{id}")
    public ResponseEntity<?> updateProductsCombination(@PathVariable("id") Long id, ProductsCombinationDTO productsCombinationDTO) {
        return null;
    }
}
