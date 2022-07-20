package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.*;
import dd.projects.ddshop.models.Variant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface VariantMapper {

    @Mappings({
            @Mapping(target = "name", expression = "java(variant.getProduct().getName())"),
            @Mapping(target = "added_date",dateFormat = "dd-MM-yyyy HH:mm:ss")
    })
    VariantDTO toDTO(Variant variant);

    Variant toModel(VariantCreateDTO variantCreateDTO);
}
