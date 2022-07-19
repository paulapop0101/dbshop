package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.OrderCreateDTO;
import dd.projects.ddshop.models.*;
import dd.projects.ddshop.repositories.AddressRepository;
import dd.projects.ddshop.repositories.CartRepository;
import dd.projects.ddshop.repositories.OrdersRepository;
import dd.projects.ddshop.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    private final AddressRepository addressRepository;


    public OrderService(OrdersRepository ordersRepository, UserRepository userRepository, CartRepository cartRepository, AddressRepository addressRepository) {
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
    }

    public void createOrder(OrderCreateDTO orderCreateDTO){
        User user = userRepository.getReferenceById(orderCreateDTO.getUser_id());
        Cart cart = cartRepository.getReferenceById(orderCreateDTO.getCart_id());
        Address address1 = addressRepository.getReferenceById(orderCreateDTO.getDelivery_address());
        Address address2 = addressRepository.getReferenceById(orderCreateDTO.getInvoice_address());
        Orders orders = new Orders(user,cart,orderCreateDTO.getPayment(),address1,address2);
        ordersRepository.save(orders);
        cart.setStatus(1);
    }

    public void deleteOrder(final int id) {
        ordersRepository.deleteById(id);
    }
}
