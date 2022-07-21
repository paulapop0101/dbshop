package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.seeProductDTO;
import dd.projects.ddshop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;


    public ProductController(final ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody final ProductDTO productDTO){
        return new ResponseEntity<>(productService.addProduct(productDTO), HttpStatus.OK);
    }
    @GetMapping("/getProducts")
    public ResponseEntity<List<seeProductDTO>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public boolean deleteProduct(@PathVariable final int id){
       return productService.deleteProduct(id);
    }
}
