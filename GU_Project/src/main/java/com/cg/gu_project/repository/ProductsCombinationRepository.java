package com.cg.gu_project.repository;

import com.cg.gu_project.model.ProductsCombination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsCombinationRepository extends JpaRepository<ProductsCombination, Long> {
}
