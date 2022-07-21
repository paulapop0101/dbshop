package dd.projects.ddshop.validations;

import dd.projects.ddshop.dtos.AssignedValueDTO;
import dd.projects.ddshop.dtos.VarAssignedValueDTO;
import dd.projects.ddshop.models.AssignedValue;
import dd.projects.ddshop.models.Variant;
import dd.projects.ddshop.repositories.AssignedValueRepository;
import dd.projects.ddshop.repositories.ProductRepository;
import dd.projects.ddshop.utils.Util;
import dd.projects.ddshop.dtos.VariantCreateDTO;
import dd.projects.ddshop.exceptions.IncorrectInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VariantValidation {
    @Autowired
    public VariantValidation(final AssignedValueRepository assignedValueRepository) {
    }

    public void variantValidation(final VariantCreateDTO variantCreateDTO){
        checkEmpty(variantCreateDTO);
        if(!variantCreateDTO.getQuantity().matches("\\d+")||Integer.parseInt(variantCreateDTO.getQuantity())==0) {
            throw new IncorrectInput(Util.getMessage("api.error.quantity.format", null));
        }
        if(!variantCreateDTO.getPrice().matches("\\d+(\\.\\d\\d?)?")|| Float.parseFloat(variantCreateDTO.getPrice())==0)
            throw new IncorrectInput(Util.getMessage("api.error.price.format", null));

    }




    private void checkEmpty(final VariantCreateDTO variantCreateDTO) {
        if(variantCreateDTO.getQuantity().isEmpty()||variantCreateDTO.getPrice().isEmpty() || variantCreateDTO.getAssignedValues().isEmpty())
            throw new IncorrectInput(Util.getMessage("api.error.empty.fields", null));
    }
}
