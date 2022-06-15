package com.cg.gu_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductsCombinationClientDTO {

    private Long id;

    private Long product_id;

    private String product_title;

    private Long product_combination_id;

    private String combination_string;

    private String sku;

    private String fileName;

    private String fileFolder;

    private String fileUrl;

    private String cloudId;

    private Long total_stock;

    private Long available;

    private Long sold;

    private Long defective;

    private Long unit_price;

    private Long maximum_retail_price;


}
