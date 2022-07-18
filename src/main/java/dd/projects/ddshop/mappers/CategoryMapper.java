package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.models.Category;
import dd.projects.ddshop.models.Subcategory;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class CategoryMapper {
    public CategoryDTO toDTO(Category category){
        List<SubcategoryDTO> str =  category.getSubcategories()
                .stream()
                .map(CategoryMapper::toSubcategoryDTO)
                .collect(toList());
        return new CategoryDTO(category.getId(), category.getName(),str);
    }

    public static SubcategoryDTO toSubcategoryDTO(Subcategory subcategory){
        return new SubcategoryDTO(subcategory.getId(), subcategory.getName());
    }

}
