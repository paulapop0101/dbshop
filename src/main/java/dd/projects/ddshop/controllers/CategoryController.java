package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Object> addCategory(@RequestParam(name= "name") final String name){
        categoryService.addCategory(name);
        return new ResponseEntity<>("Category has been added",HttpStatus.OK);
    }
    @PostMapping("/addSubcategory/{id}")
    public ResponseEntity<Object> addSubcategory(@RequestParam(name= "name") final String name, @PathVariable int id){
        categoryService.addSubcategory(name,id);
        return new ResponseEntity<>("Subcategory has been added",HttpStatus.OK);
    }
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable final int id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Category has been deleted",HttpStatus.OK);
    }

}
