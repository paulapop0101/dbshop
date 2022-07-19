package dd.projects.ddshop.validations;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.Util;
import dd.projects.ddshop.exceptions.EntityAlreadyExists;
import dd.projects.ddshop.exceptions.IncorrectInput;
import dd.projects.ddshop.models.User;
import dd.projects.ddshop.repositories.UserRepository;
import org.springframework.context.MessageSource;

import java.util.Locale;


public class UserValidation {


    private final UserRepository userRepository;

    public UserValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void userValidation(UserCreationDTO userCreationDTO){
        checkEmpty(userCreationDTO);
        checkEmail(userCreationDTO);
        if(!userCreationDTO.getPassword().equals(userCreationDTO.getCheckPassword()))
            throw new IncorrectInput(Util.getMessage("api.error.password.not.match", null));
        checkPassword(userCreationDTO.getPassword());
        checkPhone(userCreationDTO.getPhone());
    }

    private void checkEmail(UserCreationDTO userCreationDTO) {
        for(User user : userRepository.findAll())
            if(user.getEmail().equals(userCreationDTO.getEmail()))
                throw new EntityAlreadyExists(Util.getMessage("api.error.email", null));
    }

    private void checkPhone(String phone) {
        if(phone.length()!=10)
            throw new IncorrectInput(Util.getMessage("api.error.phone", null));
        for(Character c : phone.toCharArray())
            if(!Character.isDigit(c))
                throw new IncorrectInput(Util.getMessage("api.error.phone", null));

    }

    private void checkPassword(String password) {
        boolean uppercase=false;
        if(password.length()<8)
            throw new IncorrectInput(Util.getMessage("api.error.password", null));
        for(int i=0;i<password.length();i++) {
            if(Character.isUpperCase(password.charAt(i)))
                uppercase=true;
            if(Character.isSpaceChar(password.charAt(i)))
                throw new IncorrectInput(Util.getMessage("api.error.password", null));
        }
        if(!uppercase)
            throw new IncorrectInput(Util.getMessage("api.error.password", null));
    }

    private void checkEmpty(UserCreationDTO userCreationDTO) {
        if(userCreationDTO.getFirstname().isEmpty() || userCreationDTO.getLastname().isEmpty() ||
                userCreationDTO.getEmail().isEmpty() || userCreationDTO.getPhone().isEmpty() ||
                userCreationDTO.getPassword().isEmpty() || userCreationDTO.getCheckPassword().isEmpty() ||
                checkEmptyAddress(userCreationDTO.getBilling_address()) || checkEmptyAddress(userCreationDTO.getDelivery_address()))
            throw new IncorrectInput(Util.getMessage("api.error.empty.fields", null));
    }

    private boolean checkEmptyAddress(AddressDTO addressDTO) {
        return addressDTO.getCountry().isEmpty() || addressDTO.getCounty().isEmpty() || addressDTO.getCity().isEmpty() ||
                addressDTO.getPostalCode().isEmpty() || addressDTO.getStreetLine().isEmpty();
    }
}
