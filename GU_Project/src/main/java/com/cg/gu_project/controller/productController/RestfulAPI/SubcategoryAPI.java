package com.cg.gu_project.controller.productController.RestfulAPI;

import com.cg.gu_project.dto.SubcategoryDTO;
import com.cg.gu_project.model.Subcategory;
import com.cg.gu_project.service.productService.IProductService;
import com.cg.gu_project.service.subcategory.ISubcategoryService;
import com.cg.gu_project.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subcategory")
public class SubcategoryAPI {

    @Autowired
    private ISubcategoryService subcategoryService;

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IProductService productService;

    @GetMapping("/showAllSubcategory")
    public ResponseEntity<?> showAllSubcategory() {
        List<SubcategoryDTO> subcategoryDTOs = subcategoryService.findAllSubcategoryDTO();
        System.out.println(subcategoryDTOs);
        if(subcategoryDTOs.isEmpty()) {
            return new ResponseEntity<>("Not found list subcategory",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subcategoryDTOs, HttpStatus.OK);
    }

    @GetMapping("/showAllSubcategoryLock")
    public ResponseEntity<?> showAllSubcategoryLock() {
        List<SubcategoryDTO> subcategoryDTOS = subcategoryService.findAllSubcategoryDTOLock();

        if(subcategoryDTOS.isEmpty()) {
            return new ResponseEntity<>("Can not found list", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(subcategoryDTOS,HttpStatus.OK);
    }

    @GetMapping("/showAllSubcategoryByCategory_id/{id}")
    public ResponseEntity<?> showAllSubcategoryByCategory_id(@PathVariable("id") Long id) {
        List<SubcategoryDTO> subcategoryDTOS = subcategoryService.findListSubcategoryByCategory_id(id);

        if(subcategoryDTOS.isEmpty()) {
            return new ResponseEntity<>("Subcategory can not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(subcategoryDTOS,HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Subcategory> subcategory = subcategoryService.findById(id);

        if(subcategory.isPresent()) {
            SubcategoryDTO subcategoryDTO = subcategoryService.findSubcategoryDTOById(id);

            return new ResponseEntity<>(subcategoryDTO,HttpStatus.OK);
        }

        return new ResponseEntity<>("Subcategory can not found", HttpStatus.NOT_FOUND);


    }

    @PostMapping("/createSubcategory")
    public ResponseEntity<?> createSubcategory(@RequestBody SubcategoryDTO subcategoryDTO) {
//        if(result.hasErrors()) {
//            return appUtils.mapErrorToResponse(result);
//        }
        subcategoryDTO.setId(null);
        SubcategoryDTO newSubcategory = subcategoryService.createSubcategory(subcategoryDTO);
        return new ResponseEntity<>(newSubcategory, HttpStatus.OK);
    }

    @PutMapping("/editSubcategory/{id}")
    public ResponseEntity<?> editSubcategory(@PathVariable("id") Long id,
                                             @RequestBody SubcategoryDTO subcategoryDTO) {
//        if(result.hasErrors()) {
//            return appUtils.mapErrorToResponse(result);
//        }
        Optional<Subcategory> subcategory = subcategoryService.findById(id);
        if(subcategory.isPresent()) {
            subcategoryDTO.setId(id);
            SubcategoryDTO updateSubcategory = subcategoryService.createSubcategory(subcategoryDTO);

            return new ResponseEntity<>(updateSubcategory,HttpStatus.OK);
        }

        return new ResponseEntity<>("Can not found subcategory", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/suspensionSubcategory/{id}")
    public ResponseEntity<?> suspensionSubcategory(@PathVariable("id") Long id) {
        Optional<Subcategory> subcategory = subcategoryService.findById(id);

        if(subcategory.isPresent()) {
            subcategory.get().setDeleted(true);
            subcategoryService.save(subcategory.get());

            productService.setProductDeletedIsTrueBySubcategoryId(id);

            return new ResponseEntity<>(id,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/doRestoreSubcategory/{id}")
    public ResponseEntity<?> doRestoreSubcateogry(@PathVariable("id") Long id) {
        Optional<Subcategory> subcategory = subcategoryService.findById(id);

        if(subcategory.isPresent()) {
            subcategory.get().setDeleted(false);

            subcategoryService.save(subcategory.get());

            productService.setProductDeletedIsFalseBySubcategoryId(id);

            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>("Can not found subcategory", HttpStatus.NOT_FOUND);
    }
}
