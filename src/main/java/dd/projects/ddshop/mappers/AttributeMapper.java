package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.AttributeCreateDTO;
import dd.projects.ddshop.dtos.AttributeDTO;
import dd.projects.ddshop.dtos.AttributeValueDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
import dd.projects.ddshop.models.Subcategory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class AttributeMapper {
    public ProductAttribute toAttribute(AttributeCreateDTO attributeCreateDTO){
        return new ProductAttribute(attributeCreateDTO.getName());
    }
    public AttributeDTO toAttributeDTO(ProductAttribute productAttribute){
        List<AttributeValueDTO> values = productAttribute.getAttributeValues()
                .stream()
                .map(AttributeMapper::toAttributeValueDTO)
                .collect(toList());
        List<SubcategoryDTO> subcategories = productAttribute.getSubcategories()
                .stream()
                .map(AttributeMapper::toSubcategoryDTO)
                .collect(toList());
        return new AttributeDTO(productAttribute.getId(),productAttribute.getName(),values,subcategories);
    }

    public static AttributeValueDTO toAttributeValueDTO(AttributeValue attributeValue){
        return new AttributeValueDTO(attributeValue.getId(),attributeValue.getValue());
    }

    public static SubcategoryDTO toSubcategoryDTO(Subcategory subcategory){
        return new SubcategoryDTO(subcategory.getId(), subcategory.getName());
    }
}
