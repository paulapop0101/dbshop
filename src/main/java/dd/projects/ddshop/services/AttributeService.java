package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AttributeDTO;
import dd.projects.ddshop.mappers.AttributeMapper;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
import dd.projects.ddshop.repositories.AttributeValueRepository;
import dd.projects.ddshop.repositories.productAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Attribute;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AttributeService {

    @Autowired
    private productAttributeRepository productAttributeRepository;
    @Autowired
    private AttributeValueRepository attributeValueRepository;

    private AttributeMapper attributeMapper = new AttributeMapper();
    public void addAttribute(AttributeDTO attributeDTO) {

        ProductAttribute attribute = attributeMapper.toAttribute(attributeDTO);

        List<AttributeValue> attributeValues = new ArrayList<>();
        for(String value : attributeDTO.getValues()) {
            attributeValues.add(new AttributeValue(value, attribute));
        }
        attribute.setAttributeValues(attributeValues);
//        for(AttributeValue v : attribute.getAttributeValues())
//            System.out.println(v.getValue() + v.getProduct_attributes().getName());
        productAttributeRepository.save(attribute);
        attributeValueRepository.saveAll(attribute.getAttributeValues());

    }

    public void deleteAttributeValue(int id){
        attributeValueRepository.deleteById(id);

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
