package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.AttributeCreateDTO;
import dd.projects.ddshop.services.AttributeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductAttributeController {

    private final AttributeService attributeService;

    public ProductAttributeController(AttributeService attributeService){
        this.attributeService = attributeService;
    }

    @PostMapping("/addAttribute")
    public ResponseEntity<Object> addAttribute(@RequestBody AttributeCreateDTO attributeCreateDTO) {
        attributeService.addAttribute(attributeCreateDTO);
        return new ResponseEntity<>("New attribute was created.", HttpStatus.OK);
    }
    @PostMapping("/addAttributeValue/{id}")
    public ResponseEntity<String> addAttributeValue(@RequestBody String value, @PathVariable int id) {
        attributeService.addAttributeValue(id,value);
        return new ResponseEntity<>("New attribute was created.", HttpStatus.OK);
    }
    @DeleteMapping("/deleteAttributeValue/{id}")
    public ResponseEntity<Object> deleteAttributeValue(@PathVariable int id){
        attributeService.deleteAttributeValue(id);
        return new ResponseEntity<>("Attribute Value was deleted.", HttpStatus.OK);
    }
    @DeleteMapping("/deleteAttribute/{id}")
    public ResponseEntity<Object> deleteAttribute(@PathVariable int id){
        attributeService.deleteAttribute(id);
        return new ResponseEntity<>("Attribute was deleted.", HttpStatus.OK);
    }
    @GetMapping("/getAttributes")
    public ResponseEntity<List<AttributeCreateDTO>> getAttributes(){
        return new ResponseEntity<>(attributeService.getAttributes(), HttpStatus.OK);
    }
}
