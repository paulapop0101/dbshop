package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.models.Category;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryMapper {
    public String toSubC(Category category){
        return category.getName();
    }
}
