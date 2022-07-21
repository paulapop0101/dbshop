package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.VariantCreateDTO;
import dd.projects.ddshop.dtos.VariantDTO;
import dd.projects.ddshop.services.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VariantController {
    private final VariantService variantService;

    @Autowired
    public VariantController(final VariantService variantService){
        this.variantService = variantService;
    }

    @GetMapping("/getAllVariants")
    public ResponseEntity<List<VariantDTO>> getAllVariants() {
        return new ResponseEntity<>(variantService.getAllVariants(), HttpStatus.OK);
    }

    @PostMapping("/addVariant")
    public ResponseEntity<VariantCreateDTO> addVariant(@RequestBody final VariantCreateDTO variantCreateDTO){
        return new ResponseEntity<>(variantService.addVariant(variantCreateDTO),HttpStatus.OK);
    }
/*
    @PutMapping("/updateVariant")
    public ResponseEntity<Object> updateVariant(@RequestBody final Variant variant)  {
        variantService.variantExists(variant.getId());
        variantService.updateVariant(variant);
        return new ResponseEntity<>(messageSource.getMessage("api.response.update.successful", null, Locale.ENGLISH), HttpStatus.OK);
    }
*/

    @DeleteMapping("/deleteVariant/{id}")
    public boolean deleteVariant(@PathVariable final int id)  {
        return variantService.deleteVariant(id);
    }
}
