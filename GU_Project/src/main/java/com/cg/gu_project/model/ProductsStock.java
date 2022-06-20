package com.cg.gu_project.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public Long getDefective() {
        return defective;
    }

    public void setDefective(Long defective) {
        this.defective = defective;
    }

    public Long getMaximumRetailPrice() {
        return maximumRetailPrice;
    }

    public void setMaximumRetailPrice(Long maximumRetailPrice) {
        this.maximumRetailPrice = maximumRetailPrice;
    }

    public Long getSold() {
        return sold;
    }

    public void setSold(Long sold) {
        this.sold = sold;
    }

    public Long getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(Long totalStock) {
        this.totalStock = totalStock;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public ProductsCombination getProductsCombination() {
        return productsCombination;
    }

    public void setProductsCombination(ProductsCombination productsCombination) {
        this.productsCombination = productsCombination;
    }



}