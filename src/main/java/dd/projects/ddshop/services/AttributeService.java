package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AttributeDTO;
import dd.projects.ddshop.mappers.AttributeMapper;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
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

    public AttributeService(ProductAttributeRepository productAttributeRepository, AttributeValueRepository attributeValueRepository){
        this.productAttributeRepository=productAttributeRepository;
        this.attributeValueRepository=attributeValueRepository;
    }

    private final AttributeMapper attributeMapper = new AttributeMapper();
    public void addAttribute(AttributeDTO attributeDTO) {

        ProductAttribute attribute = attributeMapper.toAttribute(attributeDTO);

        List<AttributeValue> attributeValues = new ArrayList<>();
        for(String value : attributeDTO.getValues()) {
            attributeValues.add(new AttributeValue(value, attribute));
        }

        attribute.setAttributeValues(attributeValues);
        productAttributeRepository.save(attribute);

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
