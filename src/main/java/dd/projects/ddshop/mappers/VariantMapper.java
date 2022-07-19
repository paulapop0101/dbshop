package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.*;
import dd.projects.ddshop.models.AssignedValue;
import dd.projects.ddshop.models.Variant;
import dd.projects.ddshop.repositories.AssignedValueRepository;
import dd.projects.ddshop.repositories.ProductRepository;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VariantMapper {

    private final ProductRepository productRepository;
    public VariantMapper(final ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    public static AssignedValueDTO toAssignedValueDTO(final AssignedValue assignedValue){
        final VarAttributeDTO attributeDTO = new VarAttributeDTO(assignedValue.getProductAttribute().getId(),assignedValue.getProductAttribute().getName());
        final AttributeValueDTO attributeValueDTO = new AttributeValueDTO(assignedValue.getAttributeValue().getId(),assignedValue.getAttributeValue().getValue());
        return new AssignedValueDTO(attributeDTO,attributeValueDTO);
    }

    public VariantDTO toVariantDTO(final Variant variant){
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final List<AssignedValueDTO> assignedValueDTOList = variant.getAssignedValues().stream().map(VariantMapper::toAssignedValueDTO).collect(Collectors.toList());
        return new VariantDTO(variant.getProduct().getName(),variant.getPrice(),variant.getQuantity(),dateFormat.format(variant.getAdded_date()),assignedValueDTOList);
    }

    public Variant toVariant(final VariantCreateDTO variantCreateDTO){
        return new Variant(Integer.parseInt(variantCreateDTO.getQuantity()),
                Float.parseFloat(variantCreateDTO.getPrice()),productRepository.getReferenceById(variantCreateDTO.getProduct_id()));
    }
}
