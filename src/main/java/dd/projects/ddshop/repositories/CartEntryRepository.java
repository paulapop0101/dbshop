package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Cart_entry;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

public interface CartEntryRepository extends JpaRepository<Cart_entry,Integer> {

    Cart_entry findByVariant_idEquals(int id);


//    @Query("SELECT Cart_entry .id FROM Cart_entry WHERE Cart_entry .variant_id = :id")
//    Cart_entry findbyNushce(int id);
}
