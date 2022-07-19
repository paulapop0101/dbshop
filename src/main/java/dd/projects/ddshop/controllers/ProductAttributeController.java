package dd.projects.ddshop.controllers;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.AttributeCreateDTO;
import dd.projects.ddshop.dtos.AttributeDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.services.AttributeService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Controller
public class ProductAttributeController {

    private final AttributeService attributeService;

    private final MessageSource messageSource = new AppConfiguration().messageSource();

    public ProductAttributeController(AttributeService attributeService){
        this.attributeService = attributeService;
    }

    @PostMapping("/addAttribute")
    public ResponseEntity<Object> addAttribute(@RequestBody AttributeCreateDTO attributeCreateDTO) {
        attributeService.addAttribute(attributeCreateDTO);
        return new ResponseEntity<>(messageSource.getMessage("api.response.creation.successful", null, Locale.ENGLISH), HttpStatus.OK);
    }
    @PostMapping("/addSubcategoryToAttribute/{id}")
    public ResponseEntity<Object> addSubcategoryToAttribute(@RequestBody SubcategoryDTO subcategoryDTO, @PathVariable final int id) {
        attributeService.addSubcategoryToAttribute(subcategoryDTO,id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.creation.successful", null, Locale.ENGLISH), HttpStatus.OK);
    }
    @PostMapping("/addAttributeValue/{id}")
    public ResponseEntity<String> addAttributeValue(@RequestParam(name= "value") final String value, @PathVariable final int id) {
        attributeService.addAttributeValue(id,value);
        return new ResponseEntity<>(messageSource.getMessage("api.response.creation.successful", null, Locale.ENGLISH), HttpStatus.OK);
    }
    @DeleteMapping("/deleteAttributeValue/{id}")
    public ResponseEntity<Object> deleteAttributeValue(@PathVariable final int id){
        attributeService.deleteAttributeValue(id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.deleted.successfully", null, Locale.ENGLISH), HttpStatus.OK);
    }
    @DeleteMapping("/deleteAttribute/{id}")
    public ResponseEntity<Object> deleteAttribute(@PathVariable final int id){
        attributeService.deleteAttribute(id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.deleted.successfully", null, Locale.ENGLISH), HttpStatus.OK);
    }
    @GetMapping("/getAttributes")
    public ResponseEntity<List<AttributeDTO>> getAttributes(){
        return new ResponseEntity<>(attributeService.getAttributes(), HttpStatus.OK);
    }
}
