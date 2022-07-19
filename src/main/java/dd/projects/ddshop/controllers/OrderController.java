package dd.projects.ddshop.controllers;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.OrderCreateDTO;
import dd.projects.ddshop.services.OrderService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class OrderController {
    private final MessageSource messageSource = new AppConfiguration().messageSource();
    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Object> createOrder(@RequestBody final OrderCreateDTO orderCreateDTO){
        orderService.createOrder(orderCreateDTO);
        return new ResponseEntity<>(messageSource.getMessage("api.response.creation.successful", null, Locale.ENGLISH), HttpStatus.OK);
    }
    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable final int id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>(messageSource.getMessage("api.response.deleted.successfully", null, Locale.ENGLISH), HttpStatus.OK);
    }
}
