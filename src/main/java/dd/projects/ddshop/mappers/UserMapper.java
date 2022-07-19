package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(final User user){
        return new UserDTO(user.getFirstname(),user.getLastname(),user.getPhone());
    }
    public User toUser(final UserCreationDTO userCreationDTO, final Address billing_a, final Address delivery_a){

        return new User(userCreationDTO.getFirstname(), userCreationDTO.getLastname(),userCreationDTO.getEmail(), userCreationDTO.getPassword(), userCreationDTO.getPhone(),billing_a,delivery_a);
    }
}
