package com.cg.gu_project.service.uploadService;

import com.cg.gu_project.repository.ImageRepository;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
@Service
@Transactional
public class UploadServiceImpl implements IUploadService{
    @Autowired
    private Cloudinary cloudinary;
    @Override
    public Map uploadImage(MultipartFile multipartFile, Map params) throws IOException {
        return cloudinary.uploader().upload(multipartFile.getBytes(),params);
    }

    @Override
    public Map destroyImage(String publicId, Map params) throws IOException {
        return cloudinary.uploader().destroy(publicId,params);
    }

    @Override
    public Map uploadVideo(MultipartFile multipartFile, Map params) throws IOException {
        return null;
    }

    @Override
    public Map destroyVideo(String publicId, Map params) throws IOException {
        return null;
    }
}
