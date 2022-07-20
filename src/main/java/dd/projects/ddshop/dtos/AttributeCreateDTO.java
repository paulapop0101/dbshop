package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AttributeCreateDTO {
    String name;
    List<String> values;
    List<SubcategoryDTO> subcategories;

}
