package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

   private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDTO, @PathVariable int id) {
        userService.updateUser(userDTO,id);
        return new ResponseEntity<>("User with id: "+id+"was updated.", HttpStatus.OK);
    }
    @PostMapping("/addUser")
    public ResponseEntity<Object> addUser(@RequestBody UserCreationDTO user) {
        userService.addUser(user);
        return new ResponseEntity<>("New user was created.", HttpStatus.OK);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id)  {
        userService.deleteUser(id);
        return new ResponseEntity<>("User with id: " + id + " was deleted.", HttpStatus.OK);
    }



}
