package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AttributeCreateDTO;
import dd.projects.ddshop.dtos.AttributeDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.mappers.AttributeMapper;
import dd.projects.ddshop.models.AssignedValue;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
import dd.projects.ddshop.repositories.AssignedValueRepository;
import dd.projects.ddshop.repositories.AttributeValueRepository;
import dd.projects.ddshop.repositories.ProductAttributeRepository;
import dd.projects.ddshop.repositories.SubcategoryRepository;
import dd.projects.ddshop.validations.AttributeValidation;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AttributeService {


    private final ProductAttributeRepository productAttributeRepository;
    private final AttributeValueRepository attributeValueRepository;
    private final AssignedValueRepository assignedValueRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final AttributeValidation attributeValidation;

    private final AttributeMapper attributeMapper = Mappers.getMapper(AttributeMapper.class);

    public AttributeService(final ProductAttributeRepository productAttributeRepository, final AttributeValueRepository attributeValueRepository, final AssignedValueRepository assignedValueRepository, final SubcategoryRepository subcategoryRepository){
        this.productAttributeRepository=productAttributeRepository;
        this.attributeValueRepository=attributeValueRepository;
        this.assignedValueRepository = assignedValueRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.attributeValidation = new AttributeValidation(productAttributeRepository);
    }


    public void addAttribute(final AttributeCreateDTO attributeCreateDTO) {
        attributeValidation.attributeValidation(attributeCreateDTO);

        final ProductAttribute attribute = attributeMapper.toModel(attributeCreateDTO);

        addValues(attribute,attributeCreateDTO.getValues());

        productAttributeRepository.save(attribute);
        saveAssignedValues(attribute); // save (attribute - value) combination
    }

    private void addValues(final ProductAttribute attribute, final List<String> values) {
        final List<AttributeValue> attributeValues = new ArrayList<>();
        for(final String value : values) {
            attributeValues.add(new AttributeValue(value, attribute));
        }
        attribute.setAttributeValues(attributeValues);
    }


    private void saveAssignedValues(final ProductAttribute attribute) {
        for(final AttributeValue attributeValue : attribute.getAttributeValues())
            assignedValueRepository.save(new AssignedValue(attribute,attributeValue));
    }

    public void deleteAttributeValue(final int id){
        attributeValueRepository.deleteById(id);

    }
    public void addAttributeValue(final int id, final String value){
        attributeValidation.checkAttributeValue(value,id);

        final ProductAttribute productAttribute = productAttributeRepository.getReferenceById(id);
        final AttributeValue attributeValue=new AttributeValue(value,productAttribute);
        productAttribute.getAttributeValues().add(attributeValue);
        productAttributeRepository.save(productAttribute);

        assignedValueRepository.save(new AssignedValue(productAttribute,attributeValidation.getAttributeValue(productAttribute,value)));

    }

    public void deleteAttribute(final int id){
        productAttributeRepository.deleteById(id);
    }


    public List<AttributeDTO> getAttributes(){

        return productAttributeRepository.findAll()
                .stream()
                .map(attributeMapper::toDTO)
                .collect(toList());
    }

    public void addSubcategoryToAttribute(final SubcategoryDTO subcategoryDTO, final int id) {
        final ProductAttribute attribute = productAttributeRepository.getReferenceById(id);
        attribute.getSubcategories().add(subcategoryRepository.getReferenceById(subcategoryDTO.getId()));
        productAttributeRepository.save(attribute);
    }
}
