package dd.projects.ddshop.controllers;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.dtos.seeProductDTO;
import dd.projects.ddshop.services.ProductService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class ProductController {

    private final ProductService productService;

    private final MessageSource messageSource = new AppConfiguration().messageSource();

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Object> addProduct(@RequestBody ProductDTO productDTO){
        productService.addProduct(productDTO);
        return new ResponseEntity<>(messageSource.getMessage("api.response.creation.successful", null, Locale.ENGLISH), HttpStatus.OK);
    }
    @GetMapping("/getProducts")
    public ResponseEntity<List<seeProductDTO>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable final int id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.deleted.successfully", null, Locale.ENGLISH), HttpStatus.OK);
    }
}
