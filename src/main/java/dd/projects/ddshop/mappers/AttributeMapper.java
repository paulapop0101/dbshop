package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.AttributeCreateDTO;
import dd.projects.ddshop.dtos.AttributeDTO;
import dd.projects.ddshop.dtos.AttributeValueDTO;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;


import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface AttributeMapper {

    ProductAttribute toModel(AttributeCreateDTO attributeCreateDTO);
    @Mappings({
            @Mapping(target = "values", expression = "java(toDTOStr(attribute.getAttributeValues()))")
    })
     AttributeDTO toDTO(ProductAttribute attribute);

     List<AttributeValueDTO> toDTOStr(List<AttributeValue> attributeValue);

    AttributeValueDTO toDTO(AttributeValue attributeValue);

}
