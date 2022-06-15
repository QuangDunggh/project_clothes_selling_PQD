package com.cg.gu_project.service.imageService;

import com.cg.gu_project.model.ImageGallery;
import com.cg.gu_project.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ImageServiceImpl implements IImageService{

    @Autowired
    private ImageRepository imageRepository;
    @Override
    public List<ImageGallery> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<ImageGallery> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public ImageGallery save(ImageGallery imageGallery) {
        return imageRepository.save(imageGallery);
    }

    @Override
    public void remove(Long id) {
        imageRepository.deleteById(id);
    }
}
