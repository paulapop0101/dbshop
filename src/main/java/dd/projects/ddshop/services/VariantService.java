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
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VariantService {


    private final VariantRepository variantRepository;

    private final VariantMapper variantMapper = Mappers.getMapper(VariantMapper.class);

    private final ProductRepository productRepository;

    private final VariantValidation variantValidation;


    @Autowired
    public VariantService(final VariantRepository variantRepository, final ProductRepository productRepository, final AssignedValueRepository assignedValueRepository){
        this.variantRepository=variantRepository;
        this.productRepository = productRepository;
        this.variantValidation  = new VariantValidation(assignedValueRepository);
    }

    public VariantCreateDTO addVariant(final VariantCreateDTO variantCreateDTO){
        variantValidation.variantValidation(variantCreateDTO);

        final Variant variant= variantMapper.toModel(variantCreateDTO);
        variant.setProduct(productRepository.getReferenceById(variantCreateDTO.getProduct_id()));
        variantRepository.save(variant);
        return variantCreateDTO;

    }

    public List<VariantDTO> getAllVariants() {
        return  variantRepository.findAll().stream().map(variantMapper::toDTO).collect(Collectors.toList());
    }
    public void updateVariant(final Variant variant){
        variantRepository.save(variant);
    }
    public boolean deleteVariant(final int id) {
        variantExists(id);
        variantRepository.deleteById(id);
        return true;
    }

    public void variantExists(final int id)  {
        if(!variantRepository.existsById(id)){
            throw new EntityDoesNotExist("Exception: Variant was not found!");
        }
    }
}
