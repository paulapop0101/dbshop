package dd.projects.ddshop.controllers;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.CartDTO;
import dd.projects.ddshop.dtos.CartEntryDTO;
import dd.projects.ddshop.models.Cart_entry;
import dd.projects.ddshop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(final CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addCartEntry/{id}")
    public ResponseEntity<CartEntryDTO> addCartEntry(@RequestBody final CartEntryDTO cartEntryDTO, @PathVariable final int id){
        return new ResponseEntity<>(cartService.addEntry(cartEntryDTO,id), HttpStatus.OK);
    }
    @DeleteMapping("/deleteCartEntry/{id}")
    public boolean deleteCartEntry(@PathVariable final int id){
        return cartService.deleteCartEntry(id);
    }
    @DeleteMapping("/deleteCart/{id}")
    public boolean deleteCart(@PathVariable final int id){
        return cartService.deleteCart(id);
    }
//    @DeleteMapping("/deleteOneCartEntry/{id}")
//    public ResponseEntity<Object> deleteOneCartEntry(@PathVariable final int id){
//        cartService.deleteOneCartEntry(id);
//        return new ResponseEntity<>(messageSource.getMessage("api.response.deleted.successful", null, Locale.ENGLISH), HttpStatus.OK);
//    }

    @GetMapping("/getUserCart/{id}")
    public ResponseEntity<CartDTO> getUserCart( @PathVariable final int id) {
        return new ResponseEntity<>(cartService.getUserCart(id),HttpStatus.OK);
    }

}
