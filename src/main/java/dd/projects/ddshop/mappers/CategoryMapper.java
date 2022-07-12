package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toCategory(CategoryDTO categoryDTO){
        return  new Category(categoryDTO.getName(),categoryDTO.getDescription());
    }
    public CategoryDTO toDTO(Category category){
        return new CategoryDTO(category.getName(),category.getDescription());
    }
}
