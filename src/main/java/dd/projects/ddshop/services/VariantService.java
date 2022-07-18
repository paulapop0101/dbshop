package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AssignedValueDTO;
import dd.projects.ddshop.dtos.VariantCreateDTO;
import dd.projects.ddshop.exceptions.EntityDoesNotExist;
import dd.projects.ddshop.mappers.VariantMapper;
import dd.projects.ddshop.models.AssignedValue;
import dd.projects.ddshop.models.Variant;
import dd.projects.ddshop.repositories.AssignedValueRepository;
import dd.projects.ddshop.repositories.ProductRepository;
import dd.projects.ddshop.repositories.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class VariantService {


    private final VariantRepository variantRepository;
    private final VariantMapper variantMapper;

    private final AssignedValueRepository assignedValueRepository;
    public VariantService(VariantRepository variantRepository,ProductRepository productRepository,AssignedValueRepository assignedValueRepository){
        this.variantRepository=variantRepository;
        this.assignedValueRepository=assignedValueRepository;
        this.variantMapper = new VariantMapper(assignedValueRepository,productRepository);
    }

    public void addVariant(VariantCreateDTO variantCreateDTO){

        Variant variant= variantMapper.toVariant(variantCreateDTO);
        for(int assignedValueDTO:variantCreateDTO.getAssignedValues())
            variant.getAssignedValues().add(assignedValueRepository.getReferenceById(assignedValueDTO));

//        List<AssignedValue> values = variantCreateDTO.getAssignedValues()
//                .stream()
//                .map(variantMapper::toAssignedValue)
//                .collect(toList());
//        variant.setAssignedValues(values);
        variantRepository.save(variant);

    }

    public List<Variant> getAllVariants() {
        return  variantRepository.findAll();
    }
    public void updateVariant(Variant variant){
        variantRepository.save(variant);
    }
    public void deleteVariant(int id) {
        variantExists(id);
        variantRepository.deleteById(id);
    }

    public void variantExists(int id)  {
        if(!variantRepository.existsById(id)){
            throw new EntityDoesNotExist("Exception: Variant was not found!");
        }
    }
}
