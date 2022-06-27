package com.cg.gu_project.service.productsStock;

import com.cg.gu_project.dto.ProductsCombinationClientDTO;
import com.cg.gu_project.model.ProductsStock;
import com.cg.gu_project.repository.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductsStockServiceImpl implements IProductsStockService {

    @Autowired
    private ProductStockRepository productStockRepository;

    @Override
    public List<ProductsStock> findAll() {
        return productStockRepository.findAll();
    }

    @Override
    public Optional<ProductsStock> findById(Long id) {
        return productStockRepository.findById(id);
    }

    @Override
    public ProductsStock save(ProductsStock productsStock) {
        return productStockRepository.save(productsStock);
    }

    @Override
    public void remove(Long id) {
        productStockRepository.deleteById(id);
    }

    @Override
    public Optional<ProductsCombinationClientDTO> findProductsCombinationClientDTOById(Long id) {
        return productStockRepository.findProductsCombinationClientDTOById(id);
    }

    @Override
    public List<ProductsCombinationClientDTO> findAllProductsCombinationClientDTO() {
        return productStockRepository.findAllProductsCombinationClientDTO();
    }

    @Override
    public List<ProductsCombinationClientDTO> findAllProductsCombinationClientDTOLock() {
        return productStockRepository.findAllProductsCombinationClientDTOLock();
    }

    @Override
    public List<ProductsCombinationClientDTO> findAllProductsCombinationClientDTOByProductId(Long id) {
        return productStockRepository.findAllProductsCombinationClientDTOByProductId(id);
    }

    @Override
    public void setDeletedTrueFollowProductId(Long id) {
        productStockRepository.setDeletedTrueFollowProductId(id);
    }

    @Override
    public void setDeletedFalseFollowProductId(Long id) {
        setDeletedFalseFollowProductId(id);
    }


}
