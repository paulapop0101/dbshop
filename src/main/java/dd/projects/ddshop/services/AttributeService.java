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
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AttributeService {


    private final ProductAttributeRepository productAttributeRepository;
    private final AttributeValueRepository attributeValueRepository;
    private final AssignedValueRepository assignedValueRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final AttributeValidation attributeValidation;

    public AttributeService(ProductAttributeRepository productAttributeRepository, AttributeValueRepository attributeValueRepository, AssignedValueRepository assignedValueRepository, SubcategoryRepository subcategoryRepository){
        this.productAttributeRepository=productAttributeRepository;
        this.attributeValueRepository=attributeValueRepository;
        this.assignedValueRepository = assignedValueRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.attributeValidation = new AttributeValidation(productAttributeRepository);
    }

    private final AttributeMapper attributeMapper = new AttributeMapper();

    public void addAttribute(AttributeCreateDTO attributeCreateDTO) {
        attributeValidation.attributeValidation(attributeCreateDTO);

        ProductAttribute attribute = attributeMapper.toAttribute(attributeCreateDTO);
        for(String value : attributeCreateDTO.getValues()) {
            attribute.getAttributeValues().add(new AttributeValue(value, attribute));
        }

        for(int id: attributeCreateDTO.getSubcategories()){
            attribute.getSubcategories().add(subcategoryRepository.getReferenceById(id));
        }

        productAttributeRepository.save(attribute);
        saveAssignedValues(attribute); // save (attribute - value) combination
    }


    private void saveAssignedValues(ProductAttribute attribute) {
        for(AttributeValue attributeValue : attribute.getAttributeValues())
            assignedValueRepository.save(new AssignedValue(attribute,attributeValue));
    }

    public void deleteAttributeValue(final int id){
        attributeValueRepository.deleteById(id);

    }
    public void addAttributeValue(final int id, String value){
        attributeValidation.checkAttributeValue(value,id);

        ProductAttribute productAttribute = productAttributeRepository.getReferenceById(id);
        AttributeValue attributeValue=new AttributeValue(value,productAttribute);
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
                .map(attributeMapper::toAttributeDTO)
                .collect(toList());
    }

    public void addSubcategoryToAttribute(SubcategoryDTO subcategoryDTO, final int id) {
        ProductAttribute attribute = productAttributeRepository.getReferenceById(id);
        attribute.getSubcategories().add(subcategoryRepository.getReferenceById(subcategoryDTO.getId()));
        productAttributeRepository.save(attribute);
    }
}
