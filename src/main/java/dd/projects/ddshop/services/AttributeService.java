package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AttributeCreateDTO;
import dd.projects.ddshop.dtos.AttributeDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.exceptions.EntityAlreadyExists;
import dd.projects.ddshop.mappers.AttributeMapper;
import dd.projects.ddshop.mappers.CategoryMapper;
import dd.projects.ddshop.models.AssignedValue;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
import dd.projects.ddshop.models.Subcategory;
import dd.projects.ddshop.repositories.AssignedValueRepository;
import dd.projects.ddshop.repositories.AttributeValueRepository;
import dd.projects.ddshop.repositories.ProductAttributeRepository;
import dd.projects.ddshop.repositories.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class AttributeService {


    private final ProductAttributeRepository productAttributeRepository;

    private final AttributeValueRepository attributeValueRepository;

    private final AssignedValueRepository assignedValueRepository;

    private final SubcategoryRepository subcategoryRepository;

    public AttributeService(ProductAttributeRepository productAttributeRepository, AttributeValueRepository attributeValueRepository, AssignedValueRepository assignedValueRepository, SubcategoryRepository subcategoryRepository){
        this.productAttributeRepository=productAttributeRepository;
        this.attributeValueRepository=attributeValueRepository;
        this.assignedValueRepository = assignedValueRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    private final AttributeMapper attributeMapper = new AttributeMapper();

    public void addAttribute(AttributeCreateDTO attributeCreateDTO) {

        attributeExists(attributeCreateDTO);
        checkDuplicates(attributeCreateDTO.getValues());
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
    private void checkDuplicates(List<String> values) {
        Set<String> set = new HashSet<>(values);
        if(set.size()!=values.size())
            throw new EntityAlreadyExists("Exception, value duplicates found");
    }

    private void attributeExists(AttributeCreateDTO attributeCreateDTO) {
        for(ProductAttribute attribute : productAttributeRepository.findAll())
            if(attribute.getName().equals(attributeCreateDTO.getName()))
                throw new EntityAlreadyExists("Exception, attribute already exists");
    }

    private void saveAssignedValues(ProductAttribute attribute) {
        for(AttributeValue attributeValue : attribute.getAttributeValues())
            assignedValueRepository.save(new AssignedValue(attribute,attributeValue));
    }

    public void deleteAttributeValue(int id){
        attributeValueRepository.deleteById(id);

    }
    public void addAttributeValue(int id, String value){

        ProductAttribute productAttribute = productAttributeRepository.getReferenceById(id);

        if(getAttributeValue(productAttribute,value)!=null)
            throw  new EntityAlreadyExists("Exception, attribute value duplicate!");

        AttributeValue attributeValue=new AttributeValue(value,productAttribute);
        productAttribute.getAttributeValues().add(attributeValue);
        productAttributeRepository.save(productAttribute);

        assignedValueRepository.save(new AssignedValue(productAttribute,getAttributeValue(productAttribute,value)));

    }
    public AttributeValue getAttributeValue(ProductAttribute productAttribute, String value){
        for(AttributeValue attributeValue1 : productAttribute.getAttributeValues())
            if(attributeValue1.getValue().equals(value))
                return attributeValue1;

        return null;
    }


    public void deleteAttribute(int id){
        productAttributeRepository.deleteById(id);
    }


    public List<AttributeDTO> getAttributes(){

        return productAttributeRepository.findAll()
                .stream()
                .map(attributeMapper::toAttributeDTO)
                .collect(toList());
    }

    public void addSubcategoryToAttribute(SubcategoryDTO subcategoryDTO, int id) {
        ProductAttribute attribute = productAttributeRepository.getReferenceById(id);
        attribute.getSubcategories().add(subcategoryRepository.getReferenceById(subcategoryDTO.getId()));
        productAttributeRepository.save(attribute);
    }
}
