package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.models.Address;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper2 {

    Address dtoToModel(AddressDTO addressDTO);
}
