package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.AssignedValueDTO;
import dd.projects.ddshop.dtos.VariantCreateDTO;
import dd.projects.ddshop.models.AssignedValue;
import dd.projects.ddshop.models.Variant;
import dd.projects.ddshop.repositories.AssignedValueRepository;
import dd.projects.ddshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VariantMapper {

    private final AssignedValueRepository assignedValueRepository;
    private final ProductRepository productRepository;
    public VariantMapper(AssignedValueRepository assignedValueRepository, ProductRepository productRepository){
        this.assignedValueRepository = assignedValueRepository;
        this.productRepository = productRepository;
    }


    public AssignedValue toAssignedValue(AssignedValueDTO assignedValueDTO){
        for(AssignedValue assignedValue : assignedValueRepository.findAll())
            if(assignedValue.getProductAttribute().getId()==assignedValueDTO.getAttribute().getId() &&
                assignedValue.getAttributeValue().getId()==assignedValueDTO.getValue().getId())
                return assignedValue;
        return null;
    }

    public Variant toVariant(VariantCreateDTO variantCreateDTO){
        return new Variant(Integer.parseInt(variantCreateDTO.getQuantity()),
                Float.parseFloat(variantCreateDTO.getPrice()),productRepository.getReferenceById(variantCreateDTO.getProduct_id()));
    }
}
