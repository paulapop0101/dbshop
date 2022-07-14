package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.AttributeCreateDTO;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AttributeMapper {
    public ProductAttribute toAttribute(AttributeCreateDTO attributeCreateDTO){
        return new ProductAttribute(attributeCreateDTO.getName());
    }
    public AttributeCreateDTO toAttributeDTO(ProductAttribute productAttribute){
        List<String> values = new ArrayList<>();
        for(AttributeValue v : productAttribute.getAttributeValues())
            values.add(v.getValue());
        return new AttributeCreateDTO(productAttribute.getName(),values,new ArrayList<>());
    }

}
