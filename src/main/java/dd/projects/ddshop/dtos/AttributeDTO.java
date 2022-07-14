package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AttributeDTO {
    int id;
    List<AttributeValueDTO> values;
    List<SubcategoryDTO> subcategories;
}
