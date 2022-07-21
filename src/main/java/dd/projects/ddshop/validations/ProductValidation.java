package dd.projects.ddshop.validations;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.repositories.ProductRepository;
import dd.projects.ddshop.utils.Util;
import dd.projects.ddshop.exceptions.IncorrectInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductValidation {

    private final ProductRepository productRepository;

    @Autowired
    public ProductValidation(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void productValidation(final ProductDTO productDTO){
        checkEmpty(productDTO);
        checkDuplicate(productDTO.getName());
    }

    private void checkDuplicate(final String name) {
        if(productRepository.findByName(name)!=null)
            throw new IncorrectInput(Util.getMessage("api.error.duplicate",new Object[]{"Product","name"}));
    }

    private void checkEmpty(final ProductDTO productDTO) {
        if(productDTO.getName().isEmpty() || productDTO.getDescription().isEmpty())
            throw new IncorrectInput(Util.getMessage("api.error.empty.fields", null));
    }
}
