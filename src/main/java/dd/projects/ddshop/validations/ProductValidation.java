package dd.projects.ddshop.validations;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.utils.Util;
import dd.projects.ddshop.exceptions.IncorrectInput;

public class ProductValidation {

    public void productValidation(final ProductDTO productDTO){
        checkEmpty(productDTO);
    }

    private void checkEmpty(final ProductDTO productDTO) {
        if(productDTO.getName().isEmpty() || productDTO.getDescription().isEmpty())
            throw new IncorrectInput(Util.getMessage("api.error.empty.fields", null));
    }
}
