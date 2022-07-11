package dd.projects.ddshop.dtos;

import dd.projects.ddshop.models.Address;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
public class UserCreationDTO {

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private String password;

    private AddressDTO billing_address;

    private AddressDTO delivery_address;
}
