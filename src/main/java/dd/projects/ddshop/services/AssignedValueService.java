package dd.projects.ddshop.services;

import dd.projects.ddshop.exceptions.EntityDoesNotExist;
import dd.projects.ddshop.models.AssignedValue;
import dd.projects.ddshop.repositories.AssignedValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignedValueService {

    @Autowired
    AssignedValueRepository assignedValueRepo;

    public void addVariant(AssignedValue assignedValue){
        assignedValueRepo.save(assignedValue);
    }

    public List<AssignedValue> getAssignedValues() {
        return  assignedValueRepo.findAll();
    }
    public void updateAssignedValue(AssignedValue assignedValue){
        assignedValueRepo.save(assignedValue);
    }
    public void deleteAssignedValue(int id) {
        assignedValueExists(id);
        assignedValueRepo.deleteById(id);
    }

    public void assignedValueExists(int id)  {
        if(!assignedValueRepo.existsById(id)){
            throw new EntityDoesNotExist("Exception: AssignedValue was not found!");
        }
    }
}
