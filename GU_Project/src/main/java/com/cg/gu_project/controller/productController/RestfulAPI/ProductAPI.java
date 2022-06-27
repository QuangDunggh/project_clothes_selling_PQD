package com.cg.gu_project.controller.productController.RestfulAPI;

import com.cg.gu_project.dto.ProductClientDTO;
import com.cg.gu_project.dto.ProductDTO;
import com.cg.gu_project.dto.ProductsCombinationClientDTO;
import com.cg.gu_project.model.ImageGallery;
import com.cg.gu_project.model.Product;
import com.cg.gu_project.service.productService.IProductService;
import com.cg.gu_project.service.productsStock.IProductsStockService;
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

    @Autowired
    private IProductsStockService productsStockService;

    @GetMapping("/showAll")
    public ResponseEntity<?> showAllProduct() {

        List<ProductClientDTO> productClientDTOS = productService.findAllProductClientDTO();

        if (productClientDTOS.isEmpty()) {
            return new ResponseEntity<>("List product is empty", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productClientDTOS, HttpStatus.OK);

    }

    @GetMapping("/showAllLock")
    public ResponseEntity<?> showAllProductLock() {
        List<ProductClientDTO> productClientDTOS = productService.findAllProductClientDTOLock();

        if(productClientDTOS.isEmpty()) {
            return new ResponseEntity<>("Can not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productClientDTOS, HttpStatus.OK);
    }

    @GetMapping("/showProductByCategoryId/{id}")
    public ResponseEntity<?> showAllProductByCategoryId(@PathVariable("id") Long id) {
        List<ProductClientDTO> productClientDTOS = productService.findProductClientDTOByCategoryId(id);

        if(productClientDTOS.isEmpty()) {
            return new ResponseEntity<>("Can not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productClientDTOS,HttpStatus.OK);
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

    @PostMapping("/createProductNoImage")
    public ResponseEntity<?> createProductNoImage(@RequestBody ProductDTO productDTO) {

        System.out.println("Product: " + productDTO.getTitle());
        productDTO.setId(0L);

        ProductClientDTO productClientDTO = productService.saveProductDTONoImage(productDTO);
        return new ResponseEntity<>(productClientDTO,HttpStatus.CREATED);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                           ProductDTO productDTO) {
        System.out.println("Vao chua: " + id);
        Optional<Product> product = productService.findById(id);

        if (product.isPresent()) {
            productDTO.setId(id);
            ProductClientDTO productClientDTO = productService.updateProductDTO(productDTO);
            return new ResponseEntity<>(productClientDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>("Can not update product", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateProductNoImage/{id}")
    public ResponseEntity<?> updateProductNoImage(@PathVariable("id") Long id,
                                                  ProductDTO productDTO) {
        Optional<Product> product = productService.findById(id);

        if (product.isPresent()) {
            productDTO.setId(id);
            ProductClientDTO productClientDTO = productService.updateProductWithoutImage(productDTO);
            return new ResponseEntity<>(productClientDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>("Product can not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/suspensionProduct/{id}")
    public ResponseEntity<?> suspensionProduct(@PathVariable("id") Long id) {
        Optional<Product> product = productService.findById(id);

        if(product.isPresent()) {

            List<ProductsCombinationClientDTO> productsCombinationClientDTOS = productsStockService.findAllProductsCombinationClientDTOByProductId(id);

            if(productsCombinationClientDTOS.isEmpty()) {
                product.get().setDeleted(true);
                productService.save(product.get());
            } else {
                product.get().setDeleted(true);
                productService.save(product.get());

                productsStockService.setDeletedTrueFollowProductId(id);
            }

            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        return new ResponseEntity<>("Can not found this product", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/doRestoreProduct/{id}")
    public ResponseEntity<?> doRestoreProduct(@PathVariable("id") Long id) {
        Optional<Product> product = productService.findById(id);

        if(product.isPresent()) {

            List<ProductsCombinationClientDTO> productsCombinationClientDTOS = productsStockService.findAllProductsCombinationClientDTOByProductId(id);
            if (productsCombinationClientDTOS.isEmpty()) {
                product.get().setDeleted(false);

                productService.save(product.get());
            } else {
                product.get().setDeleted(false);

                productService.save(product.get());
                productsStockService.setDeletedFalseFollowProductId(id);
            }

            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        return new ResponseEntity<>("Can not found this product", HttpStatus.NOT_FOUND);
    }

}
