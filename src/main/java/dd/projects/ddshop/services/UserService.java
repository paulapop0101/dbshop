package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.exceptions.EntityDoesNotExist;
import dd.projects.ddshop.mappers.AddressMapper;
import dd.projects.ddshop.mappers.UserMapper;
import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.models.User;
import dd.projects.ddshop.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    @Autowired
    userRepository userRepository;
    @Autowired
    AddressService addressService;

    UserMapper userMapper = new UserMapper();

    AddressMapper addressMapper = new AddressMapper();
    public void addUser(UserCreationDTO user){
//        User u = new User("f","f","f","f","f",new Address("g",0,"s","s","s"),new Address("g",0,"s","s","s"));
//        UserMapper userMapper=new UserMapper();
//        UserDTO userDTO = userMapper.toDTO(u);
//        System.out.println(userDTO);
        Address billing_a = addressMapper.toAddress(user.getBilling_address());
        Address delivery_a = addressMapper.toAddress(user.getDelivery_address());
        User u= userMapper.toUser(user,billing_a,delivery_a);
        userRepository.save(u);
    }

    public List<UserDTO> getAllUsers() {
//        List<UserDTO> userDTOList = new ArrayList<>();
//        for (User u : userRepository.findAll()) {
//            userDTOList.add(userMapper.toDTO(u));
//        }
//        return  userDTOList;
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(toList());
    }
    public void updateUser(User user){
        userRepository.save(user);
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
