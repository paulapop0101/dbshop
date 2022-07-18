package dd.projects.ddshop.validations;

import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.exceptions.IncorrectInput;

public class UserValidation {
    public void userValidation(UserCreationDTO userCreationDTO){
        checkEmpty(userCreationDTO);
        if(!userCreationDTO.getPassword().equals(userCreationDTO.getCheckPassword()))
            throw new IncorrectInput("The passwords to not match");
        checkPassword(userCreationDTO.getPassword());
        checkPhone(userCreationDTO.getPhone());
    }

    private void checkPhone(String phone) {
        if(phone.length()!=10)
            throw new IncorrectInput("Invalid phone number");
        for(Character c : phone.toCharArray())
            if(!Character.isDigit(c))
                throw new IncorrectInput("Invalid phone number");

    }

    private void checkPassword(String password) {
        boolean uppercase=false;
        if(password.length()<8)
            throw new IncorrectInput("The password should have minimum 8 characters, one uppercase, no spaces");
        for(int i=0;i<password.length();i++) {
            if(Character.isUpperCase(password.charAt(i)))
                uppercase=true;
            if(Character.isSpaceChar(password.charAt(i)))
                throw new IncorrectInput("The passwords should have minimum 8 characters, one uppercase, no spaces");
        }
        if(!uppercase)
            throw new IncorrectInput("The password should have minimum 8 characters, one uppercase, no spaces");
    }

    private void checkEmpty(UserCreationDTO userCreationDTO) {
        if(userCreationDTO.getFirstname().isEmpty() || userCreationDTO.getLastname().isEmpty() ||
                userCreationDTO.getEmail().isEmpty() || userCreationDTO.getPhone().isEmpty() ||
                userCreationDTO.getPassword().isEmpty() || userCreationDTO.getCheckPassword().isEmpty() ||
                checkEmptyAddress(userCreationDTO.getBilling_address()) || checkEmptyAddress(userCreationDTO.getDelivery_address()))
            throw new IncorrectInput("The fields should not be empty!");
    }

    private boolean checkEmptyAddress(AddressDTO addressDTO) {
        return addressDTO.getCountry().isEmpty() || addressDTO.getCounty().isEmpty() || addressDTO.getCity().isEmpty() ||
                addressDTO.getPostalCode().isEmpty() || addressDTO.getStreetLine().isEmpty();
    }
}
