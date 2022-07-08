package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user){
        return new UserDTO(user.getFirstname(),user.getLastname(),user.getEmail(),user.getPhone());
    }
//    public User toUser(UserCreationDTO userCreationDTO){
//        return new User(userCreationDTO.getFirstname(), userCreationDTO.getLastname(),userCreationDTO.getEmail(), userCreationDTO.getPassword(), userCreationDTO.getPhone(),userCreationDTO.getBillingAddress());
//    }
}
