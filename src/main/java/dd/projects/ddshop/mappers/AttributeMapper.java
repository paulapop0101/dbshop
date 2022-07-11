package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.AttributeDTO;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
import org.springframework.stereotype.Component;

import javax.management.Attribute;
import java.util.ArrayList;
import java.util.List;

@Component
public class AttributeMapper {
    public ProductAttribute toAttribute(AttributeDTO attributeDTO){
        return new ProductAttribute(attributeDTO.getName());
    }
    public AttributeDTO toAttributeDTO(ProductAttribute productAttribute){
        List<String> values = new ArrayList<>();
        for(AttributeValue v : productAttribute.getAttributeValues())
            values.add(v.getValue());
        return new AttributeDTO(productAttribute.getName(),values);
    }
}
