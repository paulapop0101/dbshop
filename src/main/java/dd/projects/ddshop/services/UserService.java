package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.exceptions.EntityDoesNotExist;
import dd.projects.ddshop.mappers.AddressMapper;
import dd.projects.ddshop.mappers.UserMapper;
import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.models.User;
import dd.projects.ddshop.repositories.UserRepository;
import dd.projects.ddshop.validations.UserValidation;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserValidation userValidation;

    public UserService(final UserRepository userRepository){
        this.userRepository = userRepository;
        this.userValidation = new UserValidation(userRepository);
    }

    UserMapper userMapper = new UserMapper();

    AddressMapper addressMapper = new AddressMapper();
    public void addUser(final UserCreationDTO user){
        userValidation.userValidation(user);
        final Address billing_a = addressMapper.toAddress(user.getBilling_address());
        final Address delivery_a = addressMapper.toAddress(user.getDelivery_address());
        final User u= userMapper.toUser(user,billing_a,delivery_a);
        userRepository.save(u);
    }


    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(toList());
    }
    public void updateUser(final UserDTO user, final int id){
        userExists(id);
        final User u = userRepository.getReferenceById(id);
        u.setFirstname(user.getFirstname());
        u.setLastname(user.getLastname());
        u.setPhone(user.getPhone());
        userRepository.save(u);
    }
    public void deleteUser(final int id) {
        userExists(id);
        userRepository.deleteById(id);
    }

    public void userExists(final int id)  {
        if(!userRepository.existsById(id)){
            throw new EntityDoesNotExist("Exception: User was not found!");
        }
    }
}
