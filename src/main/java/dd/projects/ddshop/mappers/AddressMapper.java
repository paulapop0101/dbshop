package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.models.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toAddress(AddressDTO a){
        return new Address(a.getStreetLine(),a.getPostalCode(),a.getCity(), a.getCounty(),a.getCountry());
    }
}
