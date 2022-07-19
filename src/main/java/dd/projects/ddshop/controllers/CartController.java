package dd.projects.ddshop.controllers;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.CartEntryDTO;
import dd.projects.ddshop.services.CartService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class CartController {
    private final CartService cartService;

    private final MessageSource messageSource = new AppConfiguration().messageSource();

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addCartEntry/{id}")
    public ResponseEntity<Object> addCartEntry(@RequestBody CartEntryDTO cartEntryDTO, @PathVariable final int id){
        cartService.addEntry(cartEntryDTO,id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.creation.successful", null, Locale.ENGLISH), HttpStatus.OK);
    }
    @DeleteMapping("/deleteCartEntry/{id}")
    public ResponseEntity<Object> deleteCartEntry(@PathVariable final int id){
        cartService.deleteCartEntry(id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.deleted.successful", null, Locale.ENGLISH), HttpStatus.OK);
    }
    @DeleteMapping("/deleteCart/{id}")
    public ResponseEntity<Object> deleteCart(@PathVariable final int id){
        cartService.deleteCart(id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.deleted.successful", null, Locale.ENGLISH), HttpStatus.OK);
    }
//    @DeleteMapping("/deleteOneCartEntry/{id}")
//    public ResponseEntity<Object> deleteOneCartEntry(@PathVariable final int id){
//        cartService.deleteOneCartEntry(id);
//        return new ResponseEntity<>(messageSource.getMessage("api.response.deleted.successful", null, Locale.ENGLISH), HttpStatus.OK);
//    }

}
