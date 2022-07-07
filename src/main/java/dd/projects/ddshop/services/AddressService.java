package dd.projects.ddshop.services;

import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    public void addAddress(Address address){ addressRepository.save(address);}

    public List<Address> getAllAddresses() {
        return  addressRepository.findAll();
    }
    public void updateAddress(Address address){ addressRepository.save(address);}

    public void deleteAddress(int id) {
        addressRepository.deleteById(id);
    }

    public void addressExists(int id) throws Exception {
        if(!addressRepository.existsById(id)){
            throw new Exception("Exception: Student was not found!");
        }
    }
}
