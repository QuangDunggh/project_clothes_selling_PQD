package com.cg.gu_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductClientDTO {

    private Long id;

    private String title;

    private Long category_id;

    private String category_name;

    private Long subcategory_id;

    private String subcategory_name;

    private String description;

    private String fileName;

    private String fileFolder;

    private String fileUrl;

    private String cloudId;


}
