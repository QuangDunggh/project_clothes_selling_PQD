package com.cg.gu_project.controller.productController.RestfulAPI;

import com.cg.gu_project.dto.SubcategoryDTO;
import com.cg.gu_project.model.Subcategory;
import com.cg.gu_project.service.subcategory.ISubcategoryService;
import com.cg.gu_project.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/subcategory")
public class SubcategoryAPI {

    @Autowired
    private ISubcategoryService subcategoryService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping("/showAllSubcategory")
    public ResponseEntity<?> showAllSubcategory() {
        List<SubcategoryDTO> subcategoryDTOs = subcategoryService.findAllSubcategoryDTO();
        System.out.println(subcategoryDTOs);
        if(subcategoryDTOs.isEmpty()) {
            return new ResponseEntity<>("Not found list subcategory",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subcategoryDTOs, HttpStatus.OK);
    }

    @GetMapping("/showAllSubcategoryByCategory_id/{id}")
    public ResponseEntity<?> showAllSubcategoryByCategory_id(@PathVariable("id") Long id) {
        List<SubcategoryDTO> subcategoryDTOS = subcategoryService.findListSubcategoryByCategory_id(id);

        if(subcategoryDTOS.isEmpty()) {
            return new ResponseEntity<>("Subcategory can not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(subcategoryDTOS,HttpStatus.OK);
    }

    @PostMapping("/createSubcategory")
    public ResponseEntity<?> createSubcategory(@RequestBody SubcategoryDTO subcategoryDTO) {
//        if(result.hasErrors()) {
//            return appUtils.mapErrorToResponse(result);
//        }
        SubcategoryDTO newSubcategory = subcategoryService.createSubcategory(subcategoryDTO);
        return new ResponseEntity<>(newSubcategory, HttpStatus.OK);
    }

    @PutMapping("/editSubcategory")
    public ResponseEntity<?> editSubcategory(@RequestBody SubcategoryDTO subcategoryDTO) {
//        if(result.hasErrors()) {
//            return appUtils.mapErrorToResponse(result);
//        }
        SubcategoryDTO updateSubcategory = subcategoryService.createSubcategory(subcategoryDTO);

        return new ResponseEntity<>(updateSubcategory,HttpStatus.OK);
    }
}
