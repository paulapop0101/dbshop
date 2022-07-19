package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CartEntryDTO;
import dd.projects.ddshop.models.Cart;
import dd.projects.ddshop.models.Cart_entry;
import dd.projects.ddshop.models.Variant;
import dd.projects.ddshop.repositories.CartEntryRepository;
import dd.projects.ddshop.repositories.CartRepository;
import dd.projects.ddshop.repositories.UserRepository;
import dd.projects.ddshop.repositories.VariantRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    private final CartEntryRepository cartEntryRepository;

    private final VariantRepository variantRepository;

    public CartService(UserRepository userRepository, CartRepository cartRepository, CartEntryRepository cartEntryRepository, VariantRepository variantRepository){
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartEntryRepository = cartEntryRepository;
        this.variantRepository = variantRepository;
    }

    public void addEntry(CartEntryDTO cartEntryDTO, final int user_id){
        Cart cart = cartExists(user_id);
        if (cart==null) {
            cart = new Cart(userRepository.getReferenceById(user_id));
            cartRepository.save(cart);
        }

        Cart_entry entry = entryExists(cart,cartEntryDTO.getVariant_id());
        if(entry!=null){ //if variant already in cart
            entry.setQuantity(entry.getQuantity()+cartEntryDTO.getQuantity());
            entry.setTotal_price_per_entity(entry.getPrice_per_piece()*entry.getQuantity());
            cart.setTotal_price(cart.getTotal_price()+entry.getPrice_per_piece()*cartEntryDTO.getQuantity());
            cartEntryRepository.save(entry);
        }
        else {
            Variant variant = variantRepository.getReferenceById(cartEntryDTO.getVariant_id());
            Cart_entry cart_entry = new Cart_entry(cartEntryDTO.getQuantity(), variant.getPrice(), variant.getPrice() * cartEntryDTO.getQuantity(), cart, variant);
            cartEntryRepository.save(cart_entry);
            cart.getCart_entries().add(cart_entry);
            cart.setTotal_price(cart.getTotal_price() + variant.getPrice() * cartEntryDTO.getQuantity());

        }
        cartRepository.save(cart);
    }

    private Cart_entry entryExists(Cart cart,int variant_id) {
        for(Cart_entry entry : cart.getCart_entries())
            if(entry.getVariant_id().getId()==variant_id)
                return entry;
        return null;
    }

    private Cart cartExists(int user_id) {
        for(Cart cart: cartRepository.findAll())
            if(cart.getUser().getId()==user_id)
                return cart;
        return null;
    }

    public void deleteCartEntry(final int id){
        Cart_entry entry = cartEntryRepository.getReferenceById(id);
        Cart cart = entry.getCart_id();
        cart.setTotal_price(cart.getTotal_price()-entry.getTotal_price_per_entity());
        cartEntryRepository.deleteById(id);
    }
    public void deleteCart(final int id){
        cartRepository.deleteById(id);
    }
}
