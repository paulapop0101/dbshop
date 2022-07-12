package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    private final AddressService addressService;
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }
    @GetMapping("/getAllAddresses")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return new ResponseEntity<>(addressService.getAllAddresses(), HttpStatus.OK);
    }

    @PostMapping("/addAddress")
    public ResponseEntity<Object> addAddress(@RequestBody Address address) {
        addressService.addAddress(address);
        return new ResponseEntity<>("Address with id: was created.", HttpStatus.OK);
    }

    @PutMapping("/updateAddress/{id}")
    public ResponseEntity<Object> updateAddress(@RequestBody AddressDTO address, @PathVariable int id){
        addressService.updateAddress(address,id);
        return new ResponseEntity<>("Address with id:"+id+" was updated.", HttpStatus.OK);
    }

    @DeleteMapping("/deleteAddress/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable int id)  {
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Address with id: " + id + " was deleted.", HttpStatus.OK);
    }





}
