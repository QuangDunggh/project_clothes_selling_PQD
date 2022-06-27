package com.cg.gu_project.service.productCombiantion;

import com.cg.gu_project.Exception.DataInputException;
import com.cg.gu_project.dto.ProductDTO;
import com.cg.gu_project.dto.ProductsCombinationClientDTO;
import com.cg.gu_project.dto.ProductsCombinationDTO;
import com.cg.gu_project.mappers.ProductsCombinationMapper;
import com.cg.gu_project.model.ImageGallery;
import com.cg.gu_project.model.Product;
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

        productsCombinationRepository.save(productsCombination);

        productsStockService.save(productsStock);

        ProductsCombinationClientDTO productsCombinationClientDTO = new ProductsCombinationClientDTO();

        productsCombinationClientDTO.setId(productsStock.getId());
        productsCombinationClientDTO.setProduct_combination_id(productsCombination.getId());
        productsCombinationClientDTO.setCombination_string(productsCombination.getCombinationString());
        productsCombinationClientDTO.setProduct_id(productsCombination.getProduct().getId());
        productsCombinationClientDTO.setFileFolder(productsCombination.getImageGallery().getFileFolder());
        productsCombinationClientDTO.setCloudId(productsCombination.getImageGallery().getCloudId());
        productsCombinationClientDTO.setFileUrl(productsCombination.getImageGallery().getFileUrl());
        productsCombinationClientDTO.setFileName(productsCombination.getImageGallery().getFileName());
        productsCombinationClientDTO.setProduct_title(productsCombinationDTO.getProduct_title());
        productsCombinationClientDTO.setTotal_stock(productsCombinationDTO.getTotal_stock());
        productsCombinationClientDTO.setUnit_price(productsCombinationDTO.getUnit_price());
        productsCombinationClientDTO.setMaximum_retail_price(productsCombinationDTO.getMaximum_retail_price());
        productsCombinationClientDTO.setSku(productsCombinationDTO.getSku());
        productsCombinationClientDTO.setAvailable(0L);
        productsCombinationClientDTO.setDefective(0L);
        productsCombinationClientDTO.setSold(0L);

        return Optional.of(productsCombinationClientDTO);
    }

    @Override
    public ProductsCombinationClientDTO createProductsCombinationNoImage(ProductsCombinationDTO productsCombinationDTO) {
        ProductsCombination productsCombination = combinationMapper.toProductCombination(productsCombinationDTO);

        ProductsStock productsStock = combinationMapper.toProductsStock(productsCombinationDTO);

        ImageGallery imageGallery = imageService.findById(41L).get();

        productsCombination.setImageGallery(imageGallery);

        productsStock.setProductsCombination(productsCombination);

        productsCombinationRepository.save(productsCombination);

        productsStockService.save(productsStock);

        ProductsCombinationClientDTO productsCombinationClientDTO = new ProductsCombinationClientDTO();

        productsCombinationClientDTO.setId(productsStock.getId());
        productsCombinationClientDTO.setProduct_combination_id(productsCombination.getId());
        productsCombinationClientDTO.setCombination_string(productsCombination.getCombinationString());
        productsCombinationClientDTO.setProduct_id(productsCombination.getProduct().getId());
        productsCombinationClientDTO.setFileFolder(productsCombination.getImageGallery().getFileFolder());
        productsCombinationClientDTO.setCloudId(productsCombination.getImageGallery().getCloudId());
        productsCombinationClientDTO.setFileUrl(productsCombination.getImageGallery().getFileUrl());
        productsCombinationClientDTO.setFileName(productsCombination.getImageGallery().getFileName());
        productsCombinationClientDTO.setProduct_title(productsCombinationDTO.getProduct_title());
        productsCombinationClientDTO.setTotal_stock(productsCombinationDTO.getTotal_stock());
        productsCombinationClientDTO.setUnit_price(productsCombinationDTO.getUnit_price());
        productsCombinationClientDTO.setMaximum_retail_price(productsCombinationDTO.getMaximum_retail_price());
        productsCombinationClientDTO.setSku(productsCombinationDTO.getSku());
        productsCombinationClientDTO.setAvailable(0L);
        productsCombinationClientDTO.setDefective(0L);
        productsCombinationClientDTO.setSold(0L);
        return null;
    }

    @Override
    public Optional<ProductsCombinationClientDTO> updateProductCombinationNoImage(ProductsCombinationDTO productsCombinationDTO) {
        Optional<ProductsStock> productsStock = productsStockService.findById(productsCombinationDTO.getId());
        Optional<ProductsCombination> productsCombination = productsCombinationRepository.findById(productsStock.get().getProductsCombination().getId());

        ProductsCombination productsCombinationUpdate = combinationMapper.toProductCombination(productsCombinationDTO);

        productsCombination.get().setCombinationString(productsCombinationUpdate.getCombinationString());
        productsCombination.get().setSku(productsCombinationUpdate.getSku());
        productsCombinationRepository.save(productsCombination.get());

        ProductsStock productsStockUpdate = combinationMapper.toProductsStock(productsCombinationDTO);
        productsStock.get().setProductsCombination(productsCombination.get());
        productsStock.get().setTotalStock(productsStockUpdate.getTotalStock());
        productsStock.get().setUnitPrice(productsStockUpdate.getUnitPrice());
        productsStock.get().setMaximumRetailPrice(productsStockUpdate.getMaximumRetailPrice());
        productsStockService.save(productsStock.get());

        ProductsCombinationClientDTO productsCombinationClientDTO = new ProductsCombinationClientDTO();
        productsCombinationClientDTO.setId(productsStock.get().getId());
        productsCombinationClientDTO.setProduct_combination_id(productsCombination.get().getId());
        productsCombinationClientDTO.setCombination_string(productsCombination.get().getCombinationString());
        productsCombinationClientDTO.setProduct_id(productsCombination.get().getProduct().getId());
        productsCombinationClientDTO.setFileFolder(productsCombination.get().getImageGallery().getFileFolder());
        productsCombinationClientDTO.setCloudId(productsCombination.get().getImageGallery().getCloudId());
        productsCombinationClientDTO.setFileUrl(productsCombination.get().getImageGallery().getFileUrl());
        productsCombinationClientDTO.setFileName(productsCombination.get().getImageGallery().getFileName());
        productsCombinationClientDTO.setProduct_title(productsCombinationDTO.getProduct_title());
        productsCombinationClientDTO.setTotal_stock(productsCombinationDTO.getTotal_stock());
        productsCombinationClientDTO.setUnit_price(productsCombinationDTO.getUnit_price());
        productsCombinationClientDTO.setMaximum_retail_price(productsCombinationDTO.getMaximum_retail_price());
        productsCombinationClientDTO.setSku(productsCombinationDTO.getSku());
        productsCombinationClientDTO.setAvailable(0L);
        productsCombinationClientDTO.setDefective(0L);
        productsCombinationClientDTO.setSold(0L);
        return Optional.of(productsCombinationClientDTO);
    }

    @Override
    public Optional<ProductsCombinationClientDTO> updateProductCombination(ProductsCombinationDTO productsCombinationDTO) {
        return Optional.empty();
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




    public void deleteImage(ProductsCombination productsCombination, Long image_id) {
        try {
            Optional<ImageGallery> productImage = imageService.findById(productsCombination.getImageGallery().getId());

            String publicId = productImage.get().getCloudId();

            productsCombinationRepository.resetImageId(productsCombination.getId());

            uploadService.destroyImage(publicId, uploadUtils.buildImageDestroyParamsProductCombination(productsCombination, publicId));

            imageService.remove(image_id);
        } catch (IOException e) {
            throw new RuntimeException("Can not delete this image");
        }
    }
}
