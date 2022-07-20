package dd.projects.ddshop.controllers;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.VariantCreateDTO;
import dd.projects.ddshop.dtos.VariantDTO;
import dd.projects.ddshop.models.Variant;
import dd.projects.ddshop.services.VariantService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class VariantController {
    private final VariantService variantService;

    private final MessageSource messageSource = new AppConfiguration().messageSource();
    public VariantController(final VariantService variantService){
        this.variantService = variantService;
    }

    @GetMapping("/getAllVariants")
    public ResponseEntity<List<VariantDTO>> getAllVariants() {
        return new ResponseEntity<>(variantService.getAllVariants(), HttpStatus.OK);
    }

    @PostMapping("/addVariant")
    public ResponseEntity<VariantCreateDTO> addVariant(@RequestBody final VariantCreateDTO variantCreateDTO){
        variantService.addVariant(variantCreateDTO);
        return new ResponseEntity<>(variantCreateDTO,HttpStatus.OK);
    }
//    @PutMapping("/updateVariant")
//    public ResponseEntity<Object> updateVariant(@RequestBody final Variant variant)  {
//        variantService.variantExists(variant.getId());
//        variantService.updateVariant(variant);
//        return new ResponseEntity<>(messageSource.getMessage("api.response.update.successful", null, Locale.ENGLISH), HttpStatus.OK);
//    }

    @DeleteMapping("/deleteVariant/{id}")
    public boolean deleteVariant(@PathVariable final int id)  {
        return variantService.deleteVariant(id);
    }
}
