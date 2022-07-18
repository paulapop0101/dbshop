package dd.projects.ddshop.validations;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.AttributeCreateDTO;
import dd.projects.ddshop.exceptions.EntityAlreadyExists;
import dd.projects.ddshop.exceptions.IncorrectInput;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
import dd.projects.ddshop.repositories.ProductAttributeRepository;
import org.springframework.context.MessageSource;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class AttributeValidation {
    private final MessageSource messageSource = new AppConfiguration().messageSource();
    private final ProductAttributeRepository productAttributeRepository;

    public AttributeValidation(ProductAttributeRepository productAttributeRepository) {
        this.productAttributeRepository = productAttributeRepository;
    }

    public void attributeValidation(AttributeCreateDTO attributeCreateDTO){
        checkEmpty(attributeCreateDTO);
        attributeExists(attributeCreateDTO);
        checkDuplicates(attributeCreateDTO.getValues());
    }

    private void checkEmpty(AttributeCreateDTO attributeCreateDTO) {
        if(attributeCreateDTO.getName().isEmpty()||attributeCreateDTO.getValues().isEmpty())
            throw new IncorrectInput(messageSource.getMessage("api.error.empty.fields", null, Locale.ENGLISH));
    }

    private void checkDuplicates(List<String> values) {
        Set<String> set = new HashSet<>(values);
        if(set.size()!=values.size())
            throw new EntityAlreadyExists(messageSource.getMessage("api.error.duplicates", null, Locale.ENGLISH));
    }

    private void attributeExists(AttributeCreateDTO attributeCreateDTO) {
        for(ProductAttribute attribute : productAttributeRepository.findAll())
            if(attribute.getName().equals(attributeCreateDTO.getName()))
                throw new EntityAlreadyExists(messageSource.getMessage("api.error.attribute.duplicate", null, Locale.ENGLISH));
    }
    public void checkAttributeValue(String value, int id){
        if(value.isEmpty())
            throw new IncorrectInput(messageSource.getMessage("api.error.empty.fields", null, Locale.ENGLISH));
        if(getAttributeValue(productAttributeRepository.getReferenceById(id),value)!=null)
            throw  new EntityAlreadyExists(messageSource.getMessage("api.error.duplicate", null, Locale.ENGLISH));
    }
    public AttributeValue getAttributeValue(ProductAttribute productAttribute, String value){
        for(AttributeValue attributeValue1 : productAttribute.getAttributeValues())
            if(attributeValue1.getValue().equals(value))
                return attributeValue1;

        return null;
    }
}
