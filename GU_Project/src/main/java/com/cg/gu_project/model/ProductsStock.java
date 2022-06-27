package com.cg.gu_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products_stocks")
public class ProductsStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "available")
    private Long available;

    @Column(name = "defective")
    private Long defective;

    @Column(name = "maximum_retail_price")
    private Long maximumRetailPrice;

    @Column(name = "sold")
    private Long sold;

    @Column(name = "total_stock")
    private Long totalStock;

    @Column(name = "unit_price")
    private Long unitPrice;

    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "products_combination_id", nullable = false)
    private ProductsCombination productsCombination;

}