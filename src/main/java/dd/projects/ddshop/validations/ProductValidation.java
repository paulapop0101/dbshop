package dd.projects.ddshop.validations;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.exceptions.IncorrectInput;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class ProductValidation {
    private final MessageSource messageSource = new AppConfiguration().messageSource();
    public void productValidation(ProductDTO productDTO){
        checkEmpty(productDTO);
    }

    private void checkEmpty(ProductDTO productDTO) {
        if(productDTO.getName().isEmpty() || productDTO.getDescription().isEmpty())
            throw new IncorrectInput(messageSource.getMessage("api.error.empty.fields", null, Locale.ENGLISH));
    }
}
