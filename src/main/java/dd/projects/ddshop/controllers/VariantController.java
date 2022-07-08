package dd.projects.ddshop.controllers;

import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.models.Variant;
import dd.projects.ddshop.services.AddressService;
import dd.projects.ddshop.services.VariantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class VariantController {
    VariantService variantService;

    @GetMapping("/getAllVariants")
    public ResponseEntity<List<Variant>> getAllVariants() {
        return new ResponseEntity<>(variantService.getAllVariants(), HttpStatus.OK);
    }


    @PutMapping("/updateVariant")
    public ResponseEntity<Object> updateVariant(@RequestBody Variant variant)  {
        variantService.variantExists(variant.getId());
        variantService.updateVariant(variant);
        return new ResponseEntity<>("Variant with id:"+variant.getId()+ "was updated.", HttpStatus.OK);
    }

    @DeleteMapping("/deleteVariant/{id}")
    public ResponseEntity<Object> deleteVariant(@PathVariable int id)  {
        variantService.deleteVariant(id);
        return new ResponseEntity<>("Variant with id: " + id + " was deleted.", HttpStatus.OK);
    }
}