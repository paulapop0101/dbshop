package dd.projects.ddshop.controllers;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.services.CategoryService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    private final MessageSource messageSource = new AppConfiguration().messageSource();

    public CategoryController(final CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<String> addCategory(@RequestParam(name= "name") final String name){
        categoryService.addCategory(name);
        return new ResponseEntity<>(name,HttpStatus.OK);
    }
    @PostMapping("/addSubcategory/{id}")
    public ResponseEntity<String> addSubcategory(@RequestParam(name= "name") final String name, @PathVariable final int id){
        categoryService.addSubcategory(name,id);
        return new ResponseEntity<>(name,HttpStatus.OK);
    }
    @DeleteMapping("/deleteCategory/{id}")
    public boolean deleteCategory(@PathVariable final int id){
        return categoryService.deleteCategory(id);
    }
    @DeleteMapping("/deleteSubcategory/{id}")
    public boolean deleteSubcategory(@PathVariable final int id){
        return categoryService.deleteSubcategory(id);
    }

}
