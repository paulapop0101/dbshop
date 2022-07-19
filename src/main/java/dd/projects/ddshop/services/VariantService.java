package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.VariantCreateDTO;
import dd.projects.ddshop.dtos.VariantDTO;
import dd.projects.ddshop.exceptions.EntityDoesNotExist;
import dd.projects.ddshop.mappers.VariantMapper;
import dd.projects.ddshop.models.Variant;
import dd.projects.ddshop.repositories.AssignedValueRepository;
import dd.projects.ddshop.repositories.ProductRepository;
import dd.projects.ddshop.repositories.VariantRepository;
import dd.projects.ddshop.validations.VariantValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VariantService {


    private final VariantRepository variantRepository;
    private final VariantMapper variantMapper;

    private final VariantValidation variantValidation = new VariantValidation();
    private final AssignedValueRepository assignedValueRepository;
    public VariantService(VariantRepository variantRepository,ProductRepository productRepository,AssignedValueRepository assignedValueRepository){
        this.variantRepository=variantRepository;
        this.assignedValueRepository=assignedValueRepository;
        this.variantMapper = new VariantMapper(productRepository);
    }

    public void addVariant(VariantCreateDTO variantCreateDTO){
        variantValidation.variantValidation(variantCreateDTO);
        Variant variant= variantMapper.toVariant(variantCreateDTO);
        for(int assignedValueDTO:variantCreateDTO.getAssignedValues())
            variant.getAssignedValues().add(assignedValueRepository.getReferenceById(assignedValueDTO));
        variantRepository.save(variant);

    }

    public List<VariantDTO> getAllVariants() {
        return  variantRepository.findAll().stream().map(variantMapper::toVariantDTO).collect(Collectors.toList());
    }
    public void updateVariant(Variant variant){
        variantRepository.save(variant);
    }
    public void deleteVariant(final int id) {
        variantExists(id);
        variantRepository.deleteById(id);
    }

    public void variantExists(int id)  {
        if(!variantRepository.existsById(id)){
            throw new EntityDoesNotExist("Exception: Variant was not found!");
        }
    }
}
