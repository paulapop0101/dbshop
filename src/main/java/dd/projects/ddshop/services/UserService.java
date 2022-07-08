package dd.projects.ddshop.services;

import dd.projects.ddshop.exceptions.EntityDoesNotExist;
import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.models.User;
import dd.projects.ddshop.repositories.AddressRepository;
import dd.projects.ddshop.repositories.userRepository;
import dd.projects.ddshop.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    userRepository userRepository;
    @Autowired
    AddressService addressService;
    public void addUser(User user){
        if(!addressService.addressExists(user.getDefault_delivery_address().getId()))
            addressService.addAddress(user.getDefault_delivery_address());
        if(!addressService.addressExists(user.getDefault_billing_address().getId()))
            addressService.addAddress(user.getDefault_billing_address());
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return  userRepository.findAll();
    }
    public void updateUser(User user){
        userRepository.save(user);
    }
public User getUserByID(int id){
        return userRepository.getReferenceById(id);
}
    public void deleteUser(int id) {
        userExists(id);
        userRepository.deleteById(id);
    }

    public void userExists(int id)  {
        if(!userRepository.existsById(id)){
            throw new EntityDoesNotExist("Exception: User was not found!");
        }
    }
}