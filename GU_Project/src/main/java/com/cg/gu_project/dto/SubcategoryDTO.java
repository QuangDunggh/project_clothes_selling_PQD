package com.cg.gu_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubcategoryDTO {

    private Long id;

    private String subcategoryName;

    private Long category_id;

    private String categoryName;
}
