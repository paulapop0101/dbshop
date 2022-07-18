package dd.projects.ddshop.validations;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.VariantCreateDTO;
import dd.projects.ddshop.exceptions.IncorrectInput;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class VariantValidation {
    private final MessageSource messageSource = new AppConfiguration().messageSource();
    public void variantValidation(VariantCreateDTO variantCreateDTO){
        checkEmpty(variantCreateDTO);
        if(!variantCreateDTO.getQuantity().matches("\\d+")||Integer.parseInt(variantCreateDTO.getQuantity())==0) {
            throw new IncorrectInput(messageSource.getMessage("api.error.quantity.format", null, Locale.ENGLISH));
        }
        if(!variantCreateDTO.getPrice().matches("\\d+(\\.\\d\\d?)?")|| Float.parseFloat(variantCreateDTO.getPrice())==0)
            throw new IncorrectInput(messageSource.getMessage("api.error.price.format", null, Locale.ENGLISH));
    }

    private void checkEmpty(VariantCreateDTO variantCreateDTO) {
        if(variantCreateDTO.getQuantity().isEmpty()||variantCreateDTO.getPrice().isEmpty() || variantCreateDTO.getAssignedValues().isEmpty())
            throw new IncorrectInput(messageSource.getMessage("api.error.empty.fields", null, Locale.ENGLISH));
    }
}