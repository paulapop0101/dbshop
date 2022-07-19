package dd.projects.ddshop.controllers;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.services.UserService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class UserController {

   private final UserService userService;

    private final MessageSource messageSource = new AppConfiguration().messageSource();
    public UserController(final UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody final UserDTO userDTO, @PathVariable final int id) {
        userService.updateUser(userDTO,id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.update.successful", null, Locale.ENGLISH), HttpStatus.OK);
    }
    @PostMapping("/addUser")
    public ResponseEntity<Object> addUser(@RequestBody final UserCreationDTO user) {
        userService.addUser(user);
        return new ResponseEntity<>(messageSource.getMessage("api.response.creation.successful", null, Locale.ENGLISH), HttpStatus.OK);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable final int id)  {
        userService.deleteUser(id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.deleted.successfully", null, Locale.ENGLISH), HttpStatus.OK);
    }



}
