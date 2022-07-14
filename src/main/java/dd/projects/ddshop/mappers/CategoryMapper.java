package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.models.Category;
import dd.projects.ddshop.models.Subcategory;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class CategoryMapper {
    public Category toCategory(CategoryDTO categoryDTO){
        return  new Category(categoryDTO.getName());
    }
    public CategoryDTO toDTO(Category category){
        List<String> str =  category.getSubcategories()
                .stream()
                .map(CategoryMapper::toSubC)
                .collect(toList());
        return new CategoryDTO(category.getName(),str);
    }

    public static String toSubC(Subcategory category){
        return category.getName();
    }
}
