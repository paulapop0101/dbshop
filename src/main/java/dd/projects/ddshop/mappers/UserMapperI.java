package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.models.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapperI {
    UserDTO toDTO(User user);
    User dtoToModel(UserDTO userDTO);
}
