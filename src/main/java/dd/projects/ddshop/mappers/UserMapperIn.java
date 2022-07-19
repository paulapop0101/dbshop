package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapperIn {
    public abstract UserDTO toDTO(User user);

    public abstract User dtoToModelAux(final UserCreationDTO userDTO);

    public User dtoToModel(final UserCreationDTO userCreationDTO){
        User user = dtoToModelAux(userCreationDTO);
        user.setDefault_billing_address(dtoToModel(userCreationDTO.getBilling_address()));
        user.setDefault_billing_address(dtoToModel(userCreationDTO.getBilling_address()));
        return user;
    }

    public abstract Address dtoToModel(AddressDTO addressDTO);
}
