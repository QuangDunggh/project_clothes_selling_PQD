package com.cg.gu_project.repository;

import com.cg.gu_project.model.ImageGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageGallery,Long> {
}
