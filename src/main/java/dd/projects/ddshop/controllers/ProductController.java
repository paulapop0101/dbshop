package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.dtos.seeProductDTO;
import dd.projects.ddshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Object> addProduct(@RequestBody ProductDTO productDTO){
        productService.addProduct(productDTO);
        return new ResponseEntity<>("Product added successfully", HttpStatus.OK);
    }
    @GetMapping("/getProducts")
    public ResponseEntity<List<seeProductDTO>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
    }
}
