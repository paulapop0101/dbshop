package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AttributeDTO;
import dd.projects.ddshop.exceptions.EntityAlreadyExists;
import dd.projects.ddshop.exceptions.EntityDoesNotExist;
import dd.projects.ddshop.mappers.AttributeMapper;
import dd.projects.ddshop.models.AssignedValue;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
import dd.projects.ddshop.repositories.AssignedValueRepository;
import dd.projects.ddshop.repositories.AttributeValueRepository;
import dd.projects.ddshop.repositories.ProductAttributeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AttributeService {


    private final ProductAttributeRepository productAttributeRepository;

    private final AttributeValueRepository attributeValueRepository;

    private final AssignedValueRepository assignedValueRepository;

    public AttributeService(ProductAttributeRepository productAttributeRepository, AttributeValueRepository attributeValueRepository, AssignedValueRepository assignedValueRepository){
        this.productAttributeRepository=productAttributeRepository;
        this.attributeValueRepository=attributeValueRepository;
        this.assignedValueRepository = assignedValueRepository;
    }

    private final AttributeMapper attributeMapper = new AttributeMapper();
    public void addAttribute(AttributeDTO attributeDTO) {

        attributeExists(attributeDTO);

        ProductAttribute attribute = attributeMapper.toAttribute(attributeDTO);

        List<AttributeValue> attributeValues = new ArrayList<>();
        for(String value : attributeDTO.getValues()) {
            attributeValues.add(new AttributeValue(value, attribute));
        }

        attribute.setAttributeValues(attributeValues);
        productAttributeRepository.save(attribute);
        saveAssignedValues(attribute);

    }

    private void attributeExists(AttributeDTO attributeDTO) {
        for(ProductAttribute attribute : productAttributeRepository.findAll())
            if(attribute.getName().equals(attributeDTO.getName()))
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

}
