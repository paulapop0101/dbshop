package dd.projects.ddshop.controllers;


import dd.projects.ddshop.dtos.AttributeCreateDTO;
import dd.projects.ddshop.dtos.AttributeDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.services.AttributeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductAttributeController {

    private final AttributeService attributeService;


    public ProductAttributeController(final AttributeService attributeService){
        this.attributeService = attributeService;
    }

    @PostMapping("/addAttribute")
    public ResponseEntity<AttributeCreateDTO> addAttribute(@RequestBody final AttributeCreateDTO attributeCreateDTO) {
        attributeService.addAttribute(attributeCreateDTO);
        return new ResponseEntity<>(attributeCreateDTO, HttpStatus.OK);
    }
    @PostMapping("/addSubcategoryToAttribute/{id}")
    public ResponseEntity<SubcategoryDTO> addSubcategoryToAttribute(@RequestBody final SubcategoryDTO subcategoryDTO, @PathVariable final int id) {
        attributeService.addSubcategoryToAttribute(subcategoryDTO,id);
        return new ResponseEntity<>(subcategoryDTO, HttpStatus.OK);
    }
    @PostMapping("/addAttributeValue/{id}")
    public ResponseEntity<String> addAttributeValue(@RequestParam(name= "value") final String value, @PathVariable final int id) {
        attributeService.addAttributeValue(id,value);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
    @DeleteMapping("/deleteAttributeValue/{id}")
    public boolean deleteAttributeValue(@PathVariable final int id){
       return  attributeService.deleteAttributeValue(id);
    }
    @DeleteMapping("/deleteAttribute/{id}")
    public boolean deleteAttribute(@PathVariable final int id){
        return attributeService.deleteAttribute(id);
    }
    @GetMapping("/getAttributes")
    public ResponseEntity<List<AttributeDTO>> getAttributes(){
        return new ResponseEntity<>(attributeService.getAttributes(), HttpStatus.OK);
    }
}
