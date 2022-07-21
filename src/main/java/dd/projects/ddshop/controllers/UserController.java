package dd.projects.ddshop.controllers;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class UserController {

   private final UserService userService;

   @Autowired
    public UserController(final UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody final UserDTO userDTO, @PathVariable final int id) {
        return new ResponseEntity<>(userService.updateUser(userDTO,id), HttpStatus.OK);
    }
    @PostMapping("/addUser")
    public ResponseEntity<UserCreationDTO> addUser(@RequestBody final UserCreationDTO user) {
        return new ResponseEntity<>( userService.addUser(user), HttpStatus.OK);
    }
    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUser(@PathVariable final int id)  {
       return  userService.deleteUser(id);
    }



}
