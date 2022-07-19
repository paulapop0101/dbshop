package dd.projects.ddshop.controllers;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.dtos.Util;
import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.services.AddressService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class AddressController {

    private final AddressService addressService;

    private final MessageSource messageSource = new AppConfiguration().messageSource();
    public AddressController(final AddressService addressService){
        this.addressService = addressService;
    }
    @GetMapping("/getAllAddresses")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return new ResponseEntity<>(addressService.getAllAddresses(), HttpStatus.OK);
    }

    @PostMapping("/addAddress")
    public ResponseEntity<Object> addAddress(@RequestBody final Address address) {
        addressService.addAddress(address);
        return new ResponseEntity<>(Util.getMessage("api.response.creation.successful", null), HttpStatus.OK);
    }

    @PutMapping("/updateAddress/{id}")
    public ResponseEntity<Object> updateAddress(@RequestBody final AddressDTO address, @PathVariable final int id){
        addressService.updateAddress(address,id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.update.successful", null, Locale.ENGLISH), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAddress/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable final int id)  {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.deleted.successfully", null, Locale.ENGLISH), HttpStatus.OK);
    }





}
