package dd.projects.ddshop.services;

import dd.projects.ddshop.exceptions.EntityDoesNotExist;
import dd.projects.ddshop.models.User;
import dd.projects.ddshop.models.Variant;
import dd.projects.ddshop.repositories.variantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantService {

    @Autowired
    variantRepository variantRepo;

    public void addVariant(Variant variant){
        variantRepo.save(variant);
    }

    public List<Variant> getAllVariants() {
        return  variantRepo.findAll();
    }
    public void updateVariant(Variant variant){
        variantRepo.save(variant);
    }
    public void deleteVariant(int id) {
        variantExists(id);
        variantRepo.deleteById(id);
    }

    public void variantExists(int id)  {
        if(!variantRepo.existsById(id)){
            throw new EntityDoesNotExist("Exception: Variant was not found!");
        }
    }
}
