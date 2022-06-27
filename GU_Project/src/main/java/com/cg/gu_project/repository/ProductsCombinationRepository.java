package com.cg.gu_project.repository;

import com.cg.gu_project.model.ProductsCombination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsCombinationRepository extends JpaRepository<ProductsCombination, Long> {

    @Modifying
    @Query(value = "UPDATE products_combinations ps " +
            "SET ps.image_gallery_id=null " +
            "WHERE ps.id=:id", nativeQuery = true)
   void resetImageId(@Param("id") Long id);
}
