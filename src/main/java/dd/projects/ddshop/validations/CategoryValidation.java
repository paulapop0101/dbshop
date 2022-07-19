package dd.projects.ddshop.validations;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.Util;
import dd.projects.ddshop.exceptions.EntityAlreadyExists;
import dd.projects.ddshop.exceptions.IncorrectInput;
import dd.projects.ddshop.models.Category;
import dd.projects.ddshop.models.Subcategory;
import dd.projects.ddshop.repositories.CategoryRepository;
import dd.projects.ddshop.repositories.SubcategoryRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

public class CategoryValidation {
    private final CategoryRepository categoryRepository;

    private final SubcategoryRepository subcategoryRepository;

    public CategoryValidation(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }


    public void categoryValidation(String name){
        checkEmpty(name);
        checkExists(name);
    }
    public void subcategoryValidation(String name){
        checkEmpty(name);
        checkSubcategoryExists(name);
    }
    private void checkEmpty(String name) {
        if(name.isEmpty())
            throw new IncorrectInput(Util.getMessage("api.error.empty.fields", null));
    }

    private void checkExists(String name) {
        for(Category c : categoryRepository.findAll())
            if(c.getName().equals(name))
                throw new EntityAlreadyExists(Util.getMessage("api.error.category", null));
    }
    private void checkSubcategoryExists(String name) {
            for(Subcategory subcategory:subcategoryRepository.findAll())
                if(subcategory.getName().equals(name))
                    throw new EntityAlreadyExists(Util.getMessage("api.error.subcategory", null));
    }

}
