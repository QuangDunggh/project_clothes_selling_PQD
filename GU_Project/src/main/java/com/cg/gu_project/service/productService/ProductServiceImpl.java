package com.cg.gu_project.service.productService;

import com.cg.gu_project.Exception.DataInputException;
import com.cg.gu_project.dto.ProductClientDTO;
import com.cg.gu_project.dto.ProductDTO;
import com.cg.gu_project.mappers.ProductMapper;
import com.cg.gu_project.model.ImageGallery;
import com.cg.gu_project.model.Product;
import com.cg.gu_project.repository.ProductRepository;
import com.cg.gu_project.service.categoryService.ICategoryService;
import com.cg.gu_project.service.imageService.IImageService;
import com.cg.gu_project.service.subcategory.ISubcategoryService;
import com.cg.gu_project.service.uploadService.IUploadService;
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
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IImageService imageService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISubcategoryService subcategoryService;

    @Autowired
    private UploadUtils uploadUtils;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<ProductClientDTO> findProductClientDTOById(Long id) {
        return productRepository.findProductClientDTOById(id);
    }

    @Override
    public ProductClientDTO saveProductDTO(ProductDTO productDTO) {

        Product product = productMapper.toProduct(productDTO);
        ProductClientDTO productClientDTO = productMapper.toProductClientDTO(productDTO);
        ImageGallery imageGallery = new ImageGallery();
//        imageGallery.setId(0L);
        imageGallery.setFileName(productDTO.getMultipartFile().getOriginalFilename());

        imageService.save(imageGallery);

        uploadAndSaveImage(productDTO, imageGallery);
        product.setImage(imageGallery);
        productRepository.save(product);
        productClientDTO.setId(product.getId());
        productClientDTO.setCategory_name(categoryService.findById(productClientDTO.getCategory_id()).get().getCategoryName());
        productClientDTO.setSubcategory_name(subcategoryService.findById(productDTO.getSubcategory_id()).get().getSubCategoryName());
        productClientDTO.setFileUrl(imageGallery.getFileUrl());
        productClientDTO.setCloudId(imageGallery.getCloudId());
        productClientDTO.setFileName(imageGallery.getFileName());
        productClientDTO.setFileFolder(imageGallery.getFileFolder());
        productClientDTO.setDescription(productDTO.getDescription());

        return productClientDTO;
    }

    @Override
    public ProductClientDTO updateProductDTO(ProductDTO productDTO) {

        System.out.println(productDTO.getId());

        Optional<Product> product = productRepository.findById(productDTO.getId());

        Optional<ImageGallery> imageGallery = imageService.findById(product.get().getImage().getId());

        System.out.println("image id : " + imageGallery.get().getId());

        ProductClientDTO productClientDTO = productMapper.toProductClientDTO(productDTO);

        if (product.isPresent() && imageGallery.isPresent()) {

                Product productUpdate = productMapper.toProduct(productDTO);

                productUpdate.setId(product.get().getId());

                deleteImage(product.get(),imageGallery.get().getId());

                ImageGallery imageGalleryUpdate = new ImageGallery();

                imageGalleryUpdate.setFileName(productDTO.getMultipartFile().getOriginalFilename());

                imageService.save(imageGalleryUpdate);

                uploadAndSaveImage(productDTO, imageGalleryUpdate);

                productUpdate.setImage(imageGalleryUpdate);

                productRepository.save(productUpdate);

                productClientDTO.setId(product.get().getId());
                productClientDTO.setCategory_name(categoryService.findById(productClientDTO.getCategory_id()).get().getCategoryName());
                productClientDTO.setSubcategory_name(subcategoryService.findById(productDTO.getSubcategory_id()).get().getSubCategoryName());
                productClientDTO.setFileUrl(imageGalleryUpdate.getFileUrl());
                productClientDTO.setCloudId(imageGalleryUpdate.getCloudId());
                productClientDTO.setFileName(imageGalleryUpdate.getFileName());
                productClientDTO.setFileFolder(imageGalleryUpdate.getFileFolder());
                productClientDTO.setDescription(productDTO.getDescription());


                return productClientDTO;
            }
        return null;
    }

    @Override
    public ProductClientDTO updateProductWithoutImage(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId()).get();

        ImageGallery imageGallery = product.getImage();

        product = productMapper.toProduct(productDTO);

        product.setId(productDTO.getId());

        product.setImage(imageGallery);

        productRepository.save(product);

        ProductClientDTO productClientDTO = productMapper.toProductClientDTO(productDTO);

        productClientDTO.setId(product.getId());
        productClientDTO.setCategory_name(categoryService.findById(productClientDTO.getCategory_id()).get().getCategoryName());
        productClientDTO.setSubcategory_name(subcategoryService.findById(productDTO.getSubcategory_id()).get().getSubCategoryName());
        productClientDTO.setFileUrl(imageGallery.getFileUrl());
        productClientDTO.setCloudId(imageGallery.getCloudId());
        productClientDTO.setFileName(imageGallery.getFileName());
        productClientDTO.setFileFolder(imageGallery.getFileFolder());
        productClientDTO.setDescription(productDTO.getDescription());

        return productClientDTO;
    }

    public void deleteImage(Product product, Long image_id) {
        try {
            Optional<ImageGallery> productImage = imageService.findById(product.getImage().getId());

            String publicId = productImage.get().getCloudId();

            productRepository.resetImageId(product.getId());

            uploadService.destroyImage(publicId, uploadUtils.buildImageDestroyParams(product, publicId));

            imageService.remove(image_id);
        } catch (IOException e) {
            throw new RuntimeException("Can not delete this image");
        }


    }

    @Override
    public List<ProductClientDTO> findAllProductClientDTO() {
        return productRepository.findAllProductClientDTO();
    }

    @Override
    public Long countProductByCategoryId(Long category_id) {
        return productRepository.countProductByCategoryId(category_id);
    }

    @Override
    public void setProductDeletedIsTrueByCategoryId(Long id) {
        productRepository.setProductDeletedIsTrueByCategoryId(id);
    }

    @Override
    public void setProductDeletedIsFalseByCategoryId(Long id) {
       productRepository.setProductDeletedIsFalseByCategoryId(id);
    }

    @Override
    public void setProductDeletedIsFalseBySubcategoryId(Long id) {
        productRepository.setProductDeletedIsFalseBySubcategoryId(id);
    }

    @Override
    public void setProductDeletedIsTrueBySubcategoryId(Long id) {
        productRepository.setProductDeletedIsTrueBySubcategoryId(id);
    }

    public void uploadAndSaveImage(ProductDTO productDTO, ImageGallery imageGallery) {
        try {
            Map uploadResult = uploadService.uploadImage(productDTO.getMultipartFile(), uploadUtils.buildImageUploadParams(imageGallery));
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
