package com.cg.gu_project.service.productCombiantion;

import com.cg.gu_project.Exception.DataInputException;
import com.cg.gu_project.dto.ProductDTO;
import com.cg.gu_project.dto.ProductsCombinationClientDTO;
import com.cg.gu_project.dto.ProductsCombinationDTO;
import com.cg.gu_project.mappers.ProductsCombinationMapper;
import com.cg.gu_project.model.ImageGallery;
import com.cg.gu_project.model.ProductsCombination;
import com.cg.gu_project.model.ProductsStock;
import com.cg.gu_project.repository.ProductsCombinationRepository;
import com.cg.gu_project.service.imageService.IImageService;
import com.cg.gu_project.service.productsStock.IProductsStockService;
import com.cg.gu_project.service.uploadService.IUploadService;
import com.cg.gu_project.utils.AppUtils;
import com.cg.gu_project.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ProductsCombinationServiceImpl implements IProductsCombinationService {
    @Autowired
    private ProductsCombinationRepository productsCombinationRepository;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private ProductsCombinationMapper combinationMapper;

    @Autowired
    private UploadUtils uploadUtils;

    @Autowired
    private IProductsStockService productsStockService;

    @Override
    public List<ProductsCombination> findAll() {
        return productsCombinationRepository.findAll();
    }

    @Override
    public Optional<ProductsCombination> findById(Long id) {
        return productsCombinationRepository.findById(id);
    }

    @Override
    public ProductsCombination save(ProductsCombination productsCombination) {
        return productsCombinationRepository.save(productsCombination);
    }

    @Override
    public void remove(Long id) {
        productsCombinationRepository.deleteById(id);
    }

    @Override
    public Optional<ProductsCombinationClientDTO> createProductsCombination(ProductsCombinationDTO productsCombinationDTO) {
        ProductsCombination productsCombination = combinationMapper.toProductCombination(productsCombinationDTO);

        ProductsStock productsStock = combinationMapper.toProductsStock(productsCombinationDTO);

        ImageGallery imageGallery = new ImageGallery();
        imageGallery.setFileName(productsCombinationDTO.getMultipartFile().getOriginalFilename());
        imageService.save(imageGallery);

        uploadAndSaveImage(productsCombinationDTO, imageGallery);

        productsCombination.setImageGallery(imageGallery);

        productsStock.setProductsCombination(productsCombination);

        ProductsCombinationClientDTO productsCombinationClientDTO = new ProductsCombinationClientDTO();

        return Optional.of(productsCombinationClientDTO);
    }

    public void uploadAndSaveImage(ProductsCombinationDTO productsCombinationDTO, ImageGallery imageGallery) {
        try {
            Map uploadResult = uploadService.uploadImage(productsCombinationDTO.getMultipartFile(), uploadUtils.buildImageUploadParams(imageGallery));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            imageGallery.setFileName(imageGallery.getId() + "." + fileFormat);
            imageGallery.setFileUrl(fileUrl);
            imageGallery.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
            imageGallery.setCloudId(imageGallery.getFileFolder() + "/" + imageGallery.getId());
            imageService.save(imageGallery);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại");
        }

    }
}
