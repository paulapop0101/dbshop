package dd.projects.ddshop.validations;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.exceptions.IncorrectInput;
import org.springframework.context.MessageSource;

import java.util.Locale;


public class UserValidation {
    private final MessageSource messageSource = new AppConfiguration().messageSource();
    public void userValidation(UserCreationDTO userCreationDTO){
        checkEmpty(userCreationDTO);
        if(!userCreationDTO.getPassword().equals(userCreationDTO.getCheckPassword()))
            throw new IncorrectInput(messageSource.getMessage("api.error.password.not.match", null, Locale.ENGLISH));
        checkPassword(userCreationDTO.getPassword());
        checkPhone(userCreationDTO.getPhone());
    }

    private void checkPhone(String phone) {
        if(phone.length()!=10)
            throw new IncorrectInput(messageSource.getMessage("api.error.phone", null, Locale.ENGLISH));
        for(Character c : phone.toCharArray())
            if(!Character.isDigit(c))
                throw new IncorrectInput(messageSource.getMessage("api.error.phone", null, Locale.ENGLISH));

    }

    private void checkPassword(String password) {
        boolean uppercase=false;
        if(password.length()<8)
            throw new IncorrectInput(messageSource.getMessage("api.error.password", null, Locale.ENGLISH));
        for(int i=0;i<password.length();i++) {
            if(Character.isUpperCase(password.charAt(i)))
                uppercase=true;
            if(Character.isSpaceChar(password.charAt(i)))
                throw new IncorrectInput(messageSource.getMessage("api.error.password", null, Locale.ENGLISH));
        }
        if(!uppercase)
            throw new IncorrectInput(messageSource.getMessage("api.error.password", null, Locale.ENGLISH));
    }

    private void checkEmpty(UserCreationDTO userCreationDTO) {
        if(userCreationDTO.getFirstname().isEmpty() || userCreationDTO.getLastname().isEmpty() ||
                userCreationDTO.getEmail().isEmpty() || userCreationDTO.getPhone().isEmpty() ||
                userCreationDTO.getPassword().isEmpty() || userCreationDTO.getCheckPassword().isEmpty() ||
                checkEmptyAddress(userCreationDTO.getBilling_address()) || checkEmptyAddress(userCreationDTO.getDelivery_address()))
            throw new IncorrectInput(messageSource.getMessage("api.error.empty.fields", null, Locale.ENGLISH));
    }

    private boolean checkEmptyAddress(AddressDTO addressDTO) {
        return addressDTO.getCountry().isEmpty() || addressDTO.getCounty().isEmpty() || addressDTO.getCity().isEmpty() ||
                addressDTO.getPostalCode().isEmpty() || addressDTO.getStreetLine().isEmpty();
    }
}
