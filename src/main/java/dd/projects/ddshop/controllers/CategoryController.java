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
    public ResponseEntity<Object> addCategory(@RequestParam(name= "name") final String name){
        categoryService.addCategory(name);
        return new ResponseEntity<>(messageSource.getMessage("api.response.creation.successful", null, Locale.ENGLISH),HttpStatus.OK);
    }
    @PostMapping("/addSubcategory/{id}")
    public ResponseEntity<Object> addSubcategory(@RequestParam(name= "name") final String name, @PathVariable final int id){
        categoryService.addSubcategory(name,id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.creation.successful", null, Locale.ENGLISH),HttpStatus.OK);
    }
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable final int id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.deleted.successfully", null, Locale.ENGLISH),HttpStatus.OK);
    }
    @DeleteMapping("/deleteSubcategory/{id}")
    public ResponseEntity<Object> deleteSubcategory(@PathVariable final int id){
        categoryService.deleteSubcategory(id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.deleted.successfully", null, Locale.ENGLISH),HttpStatus.OK);
    }

}
