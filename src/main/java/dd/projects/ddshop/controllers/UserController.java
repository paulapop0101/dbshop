package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.models.User;
import dd.projects.ddshop.services.AddressService;
import dd.projects.ddshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    @PutMapping("/updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        userService.userExists(user.getId());
        userService.updateUser(user);
        return new ResponseEntity<>("User with id: "+user.getId()+"was updated.", HttpStatus.OK);
    }
    @PostMapping("/addUser")
    public ResponseEntity<Object> addAddress(@RequestBody UserCreationDTO user) {
        userService.addUser(user);
        return new ResponseEntity<>("New user was created.", HttpStatus.OK);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id)  {
        userService.deleteUser(id);
        return new ResponseEntity<>("User with id: " + id + " was deleted.", HttpStatus.OK);
    }


}
